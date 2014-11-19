/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.RoleType;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.settings.MobileSettings;
import br.ufpe.cin.amadeus.amadeus_web.domain.settings.WebSettings;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.permissions.content_management.CoursePermissions;

public class SystemActions extends LookupDispatchAction {
	
	public Facade facade = Facade.getInstance(); 
	public static final WebSettings webSettings = WebSettings.getInstance();
	public static final MobileSettings mobileSettings = MobileSettings.getInstance();
	
	private final String FORWARD_SHOW_VIEW_WELCOME = "fShowViewWelcome";
	private final String FORWARD_SHOW_VIEW_MENU = "fShowViewMenu";
	private final String FORWARD_SHOW_VIEW_PAGE_NOT_FOUND = "fShowViewPageNotFound";
	private final String FORWARD_SHOW_VIEW_EXCEPTION = "fShowViewException";
	private static final String FORWARD_SHOW_VIEW_ACCESS_DENIED = "fShowViewAccessDenied";
	
	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("system.showViewWelcome", "showViewWelcome");
		map.put("system.showViewMenu", "showViewMenu");
		map.put("system.showViewPageNotFound", "showViewPageNotFound");
		map.put("system.showViewException", "showViewException");
		map.put("system.showViewAccessDenied", "showViewAccessDenied");
		
		return map;
	}
	
	public ActionForward showViewPageNotFound(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return mapping.findForward(FORWARD_SHOW_VIEW_PAGE_NOT_FOUND);
	}
	
	public ActionForward showViewException(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return mapping.findForward(FORWARD_SHOW_VIEW_EXCEPTION);
	}
	
	public ActionForward showViewWelcome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(!SystemActions.isLoggedUser(request)) {
			String content = facade.getMostPopularKeywwordsPreparedAsHTML();
			
			request.setAttribute("content", content);
			request.setAttribute("webSettings", SystemActions.webSettings);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_WELCOME);
		} else {
			forward = this.showViewMenu(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		AccessInfo userInfo = (AccessInfo) request.getSession().getAttribute("user");
			
		if(userInfo != null) {
			userInfo = facade.searchUserById(userInfo.getId());
			facade.flush();
			
			ProfileType	profile = userInfo.getTypeProfile();	
				
			List<Course> userCourses = facade.searchCoursesByAccessInfo(userInfo);
			
			int pendingTasks;
			
			boolean youCan = false;
			
			if(profile == ProfileType.ADMIN){
				pendingTasks = 	facade.getNumberOfPendingTasks(userInfo);
				youCan = true;
			} else if(profile == ProfileType.PROFESSOR) {
				pendingTasks = 	facade.getNumberOfPendingTasks(userInfo);
				pendingTasks = pendingTasks + facade.getStudentPendingTasks(userInfo.getPerson()).size();
				youCan = true;
			} else {
				pendingTasks = facade.getStudentPendingTasks(userInfo.getPerson()).size();
			}
			
			String content = facade.getMostPopularKeywwordsPreparedAsHTML();
			
			
			request.setAttribute("userCourses", userCourses);
			request.setAttribute("youCan", youCan);
			request.setAttribute("pendingTasks", pendingTasks);
			request.setAttribute("content", content);
			forward = mapping.findForward(FORWARD_SHOW_VIEW_MENU);
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public static boolean isLoggedUser(HttpServletRequest request) {
		boolean isLoggedUser = false;
		
		AccessInfo accessInfo = (AccessInfo) request.getSession().getAttribute("user");
		
		if(accessInfo != null) {
			isLoggedUser = true;
		}
		
		return isLoggedUser; 
	}

	public static ActionForward showViewAccessDenied(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward(FORWARD_SHOW_VIEW_ACCESS_DENIED);
	}
	
	public static void setMenuPermissionsForUserInRequest(HttpServletRequest request, Course course) throws Exception {	
		
		boolean canInsertCourse = false;
		boolean canEditCourse = false;
		boolean canDeleteCourse = false;
		boolean canViewCourseEvaluations = CoursePermissions.userCanShowViewCourseEvaluations(request, course);
		boolean canAssistanceRequest = false;
		boolean canViewCourseContent = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		ProfileType userProfileType = user.getTypeProfile();
		Role userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
		
		if(userProfileType == ProfileType.ADMIN) {
			canInsertCourse = true;
			canEditCourse = true;
			canDeleteCourse = true;
			canViewCourseContent = true;
		} else if (userRoleInCourse != null) {
			if(	userProfileType == ProfileType.PROFESSOR) {
				canInsertCourse = true;
			}
			
			if(	userRoleInCourse.getRoleType() == RoleType.TEACHER || 
				userRoleInCourse.getRoleType() == RoleType.ASSISTANT) {
				canEditCourse = true;
			}
			
			if( userRoleInCourse.getRoleType() == RoleType.TEACHER) {
				canDeleteCourse = true;
			}
			
			if( userRoleInCourse.getRoleType() == RoleType.TEACHER ||
				userRoleInCourse.getRoleType() == RoleType.ASSISTANT ||
				userRoleInCourse.getRoleType() == RoleType.STUDENT) {
				canViewCourseContent = true;
			}
		}		
		
		if (Facade.getInstance().canAssistanceRequest(user.getPerson(), course)) {
			canAssistanceRequest = true;
		}
	
		request.setAttribute("canInsertCourse", canInsertCourse);
		request.setAttribute("canEditCourse", canEditCourse);
		request.setAttribute("canDeleteCourse", canDeleteCourse);
		request.setAttribute("canViewCourseEvaluations", canViewCourseEvaluations);
		request.setAttribute("canViewCourseContent", canViewCourseContent);
		request.setAttribute("canAssistanceRequest", canAssistanceRequest);
	}
}