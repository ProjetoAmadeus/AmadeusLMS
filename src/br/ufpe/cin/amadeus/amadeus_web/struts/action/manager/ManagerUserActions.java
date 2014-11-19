package br.ufpe.cin.amadeus.amadeus_web.struts.action.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidUserException;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;

public class ManagerUserActions extends SystemActions {

	private final String FORWARD_SHOW_VIEW_ALL_USERS_IN_MANAGER_USERS = "fShowViewAllUsersInManagerUsers";
	private final String FORWARD_SHOW_VIEW_ALL_COURSES_IN_MANAGER_USERS = "fShowViewAllCoursesInManagerUsers";

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("manager.user.searchUsers", "searchUsers");
		map.put("manager.user.removeUser", "removeUser");
		map.put("manager.user.allowUserLogon", "allowUserLogon");
		map.put("manager.course.advancedSearchCourse", "advancedSearchCourse");
		
		return map;
	}
	
	private boolean permissionSearchUser(HttpServletRequest request) {
		boolean userCanToDo = false;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		
		if(user.getTypeProfile() == ProfileType.ADMIN) {
			userCanToDo = true;
		}
		
		return userCanToDo;
	}
	
	public ActionForward removeUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ){
		ActionMessages messages = new ActionMessages();
		String idString = request.getParameter("to");
		Integer id = Integer.parseInt(idString);
		try {
			facade.removeUser(id);
			List<AccessInfo> userList = facade.getAllUsers();
			request.setAttribute("users", userList);
		} catch (InvalidUserException e) {
			messages.add("confirmation", new ActionMessage(e.getMessage()));
		}
		return mapping.findForward(FORWARD_SHOW_VIEW_ALL_USERS_IN_MANAGER_USERS);
	}
	
	public ActionForward allowUserLogon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		ActionMessages messages = new ActionMessages();
		String idString = request.getParameter("to");
		Integer id = Integer.parseInt(idString);
		try {
			facade.allowUserLogon(id);
			List<AccessInfo> userList = facade.getAllUsers();
			request.setAttribute("users", userList);
		} catch (InvalidUserException e) {
			messages.add("confirmation", new ActionMessage(e.getMessage()));
		}
		return mapping.findForward(FORWARD_SHOW_VIEW_ALL_USERS_IN_MANAGER_USERS);
	}
	
	public ActionForward searchUsers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = this.showViewWelcome(mapping, form, request, response);
		
		if(isLoggedUser(request)) {
			if(this.permissionSearchUser(request)) {
				String userName = request.getParameter("userName");
				Integer userType = Integer.parseInt(request.getParameter("userType"));
				
				List<AccessInfo> users = facade.searchUsers(userName, userType, 0);
				
				request.setAttribute("users", users);
				forward = mapping.findForward(FORWARD_SHOW_VIEW_ALL_USERS_IN_MANAGER_USERS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		}
		
		return forward;
	}
	
	public ActionForward advancedSearchCourse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DynaActionForm dyna = (DynaActionForm) form;
		String courseName = dyna.getString("name");
		String professorName = dyna.getString("professorName").trim();
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date initialDate = null;
		Date finalDate = null;
		try {
			initialDate = (Date) formatter.parse(dyna.getString("initialDate"));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		try {
			finalDate = (Date) formatter.parse(dyna.getString("finalDate"));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		
		List<Course> results = facade.getCoursesByAdvancedRule(courseName, professorName, initialDate, finalDate);
		
		request.setAttribute("courses", results);
		
		return mapping
				.findForward(FORWARD_SHOW_VIEW_ALL_COURSES_IN_MANAGER_USERS);

	}
}
