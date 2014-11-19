/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.evaluation;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.RoleType;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.EvaluationRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.SystemActions;
import br.ufpe.cin.amadeus.amadeus_web.struts.action.content_management.UtilActivities;

public class EvaluationActions extends org.apache.struts.actions.DispatchAction{

	private Facade facade = Facade.getInstance();
	
	private final String FORWARD_SHOW_VIEW_EDIT_EVALUATION = "fShowViewEditEvaluation";
	private final String FORWARD_SHOW_VIEW_NEW_EVALUATION = "fShowViewNewEvaluation";
	private final String FORWARD_SHOW_VIEW_LIST_EVALUATION = "fShowViewListEvaluation";
	private final String FORWARD_SHOW_VIEW_LIST_STUTENDS_FROM_EVALUATION = "fShowViewListStutendsFromEvaluation";
	private final String FORWARD_SHOW_VIEW_EVALUATION = "fShowViewEvaluationActivity";
	private final String FORWARD_SHOW_VIEW_REALIZE_EVALUATION_ACTIVITY = "fShowViewRealizeEvaluationActivity";
	
	public ActionForward showViewNewEvaluation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		Module module = facade.getModuleById(idModule);
		
		request.setAttribute("module", module);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_NEW_EVALUATION);
	}
	
	public ActionForward newEvaluation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idModule = Integer.parseInt(request.getParameter("idModule"));
		Module module = facade.getModuleById(idModule);
		
		DynaActionForm myForm = (DynaActionForm) form;
		
		Evaluation evaluation = new Evaluation();
		String description = myForm.getString("descriptionEvaluation");
		boolean evaluationsRealized = (Boolean) myForm.get("afterdeadlineachievedEvaluation");
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date start = null;
		Date finish = null;
		try {
			start = (Date) formatter.parse(myForm.getString("startEvaluation"));
			finish = (Date) formatter.parse(myForm.getString("finishEvaluation"));
			finish.setHours(23);
			finish.setMinutes(59);
			finish.setSeconds(59);
		} catch (ParseException e) {
			log.error(e.getMessage());
		};
		
		evaluation.setDescription(description);
		evaluation.setStart(start);
		evaluation.setFinish(finish);
		evaluation.setAfterdeadlineachieved(evaluationsRealized);
				
		module.getEvaluations().add(evaluation); 
		
		facade.flush();
		
		// AJAX REVERSO
		UtilActivities.eraseAndWriteNameActivity(idModule);
		return null;
	}
	
	public ActionForward showViewEditEvaluation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		Module module = evaluation.getModule();
		

		
		boolean canAddEditDeleteQuestions = false;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		
		
		if(evaluation.getStart().getTime() > new Date().getTime() || 
				evaluation.getEvaluationsRealized().size() == 0) {
			canAddEditDeleteQuestions = true;
		}
		
		List<EvaluationRealized> evaluationsRealized = evaluation.getEvaluationsRealized();
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		
		data.put("idEvaluation",evaluation.getId());
		data.put("descriptionEvaluation",evaluation.getDescription());
		data.put("startEvaluation", formatter.format(evaluation.getStart()));
		data.put("finishEvaluation", formatter.format(evaluation.getFinish()));
		data.put("afterdeadlineachievedEvaluation", evaluation.isAfterdeadlineachieved());

		request.setAttribute("module", module);
		request.setAttribute("evaluationActivity", data);
		request.setAttribute("idEvaluation", evaluation.getId());
		
		request.setAttribute("canAddEditDeleteQuestions", canAddEditDeleteQuestions);
		request.setAttribute("evaluationsRealized", evaluationsRealized);
		request.setAttribute("evaluation", evaluation);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EDIT_EVALUATION);
	}
	
	public ActionForward editEvaluation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		DynaActionForm myForm = (DynaActionForm) form;
		
		int idEvaluation = Integer.parseInt(request.getParameter("idEvaluation"));
		Evaluation evaluation = facade.getEvaluationById(idEvaluation);
		Module module = evaluation.getModule();
		
		String description = myForm.getString("descriptionEvaluation");
		boolean afterdeadlineachievedEvaluation = (Boolean) myForm.get("afterdeadlineachievedEvaluation");
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");  
		Date start = null;
		Date finish = null;
		
		try {
			start = (Date) formatter.parse(myForm.getString("startEvaluation"));
			finish = (Date) formatter.parse(myForm.getString("finishEvaluation"));
		} catch (ParseException e) {
			log.error(e.getMessage());
		};
		
		evaluation.setDescription(description);
		evaluation.setStart(start);
		evaluation.setFinish(finish);
		evaluation.setAfterdeadlineachieved(afterdeadlineachievedEvaluation);
		finish.setHours(23);
		finish.setMinutes(59);
		finish.setSeconds(59);
		
		facade.flush();
		
		// AJAX REVERSO
		UtilActivities.eraseAndWriteNameActivity(module.getId());
		return null;
	}
	
	public ActionForward deleteEvaluation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int idEvaluation = Integer.valueOf(request.getParameter("idEvaluation"));
		
		Evaluation evaluation = facade.getEvaluationById(idEvaluation);
		Module module = evaluation.getModule();
		
		module.getEvaluations().remove(evaluation);
		
		facade.flush();
		
		// AJAX REVERSO
		UtilActivities.eraseAndWriteNameActivity(module.getId());
		return null;

	}
	
	public ActionForward showViewListEvaluation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		user = facade.searchUserById(user.getId());
		
		if(user != null) {
			this.getAllPossiblePermissionFromCourse(request);
			
			int courseId = Integer.parseInt(request.getParameter("courseId"));
			
			Course course = new Course();
			course.setId(courseId);
			
			List<Evaluation> evaluations = new ArrayList<Evaluation>();
			
			evaluations = facade.getAllEvaluationFromCourse(course);
			
			request.setAttribute("evaluations", evaluations);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_LIST_EVALUATION);
		} else {
			forward = null;
		}
		
		return forward;
	}
	
	public ActionForward showViewEvaluationActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		String callFromPaddingTask = request.getParameter("callFromPaddingTask");
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		
		AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
		loggedUser = facade.searchUserById(loggedUser.getId());
		
		Person loggedUserPerson = loggedUser.getPerson();
		Role loggedUserRole = facade.getRoleByPersonInCourse(loggedUserPerson, evaluation.getModule().getCourse());
		
		boolean isAdmin = false;
		boolean isTeacher = false;
		boolean isStudent = false;
		boolean isAssistant = false;
		
		RoleType loggedUserRoleType;
		
		if (loggedUserRole != null) {
			loggedUserRoleType = loggedUserRole.getRoleType();
		}else {
			loggedUserRoleType = RoleType.ADMIN;
		} 

		if(loggedUserRoleType == RoleType.ADMIN) {
			isAdmin = true;
		} else if (loggedUserRoleType == RoleType.TEACHER) {
			isTeacher = true;
		} else if (loggedUserRoleType == RoleType.ASSISTANT) {
			isAssistant = true;
		} else if (loggedUserRoleType == RoleType.STUDENT) {
			isStudent = true;
		}
		
		List<EvaluationRealized> evaluationsRealized = evaluation.getEvaluationsRealized();
		
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("isTeacher", isTeacher);
		request.setAttribute("isStudent", isStudent);
		request.setAttribute("isAssistant", isAssistant);
		request.setAttribute("evaluationsRealized", evaluationsRealized);
		request.setAttribute("evaluation", evaluation);
		request.setAttribute("callFromPaddingTask", callFromPaddingTask);
		request.setAttribute("dateNow", new Date());
		
		return mapping.findForward(FORWARD_SHOW_VIEW_EVALUATION);
	}
	
	public ActionForward showViewListStudentsFromEvaluation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		user = facade.searchUserById(user.getId());
		
		if(user != null) {
			this.getAllPossiblePermissionFromCourse(request);
			
			int idCourse = Integer.parseInt(request.getParameter("courseId"));
			int idEvaluation = Integer.parseInt(request.getParameter("idEvaluation"));
			
			Course course = facade.getCoursesById(idCourse);
			Evaluation evaluation = facade.getEvaluationById(idEvaluation);
			
			List<Person> participants = facade.listStudentsByCourse(course);
			
			List<EvaluationRealized> evaluationsRealized = evaluation.getEvaluationsRealized();
			
			List<QuestionRealized> questionsRealized = null;
			
			for (EvaluationRealized evaluationRealized : evaluationsRealized) {
				questionsRealized = evaluationRealized.getQuestionsRealized();
			}
			
			for (QuestionRealized questionRealized : questionsRealized) {
				System.out.println("##############"+questionRealized.getId());
			}
			
			request.setAttribute("evaluationsRealized", questionsRealized);
			request.setAttribute("evaluation", evaluation);
			request.setAttribute("participants", participants);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_LIST_STUTENDS_FROM_EVALUATION);
		} else {
			forward = null;
		}
		
		return forward;
	}

	public ActionForward getStudentSituationFormEvaluation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int personId = Integer.parseInt(request.getParameter("personId"));
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		int situation = 0;
		
		//0 Pendente
		//1 Corrigida
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		
		List<EvaluationRealized> evaluationRealized = evaluation.getEvaluationsRealized();
		
		for (EvaluationRealized eRealized : evaluationRealized) {
			if(eRealized.getStudent().getId() == personId) {
				if(eRealized.getCorrectedDate() == null) {
					situation = 0;
				} else {
					situation = 1;
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		out.print(situation);
		
		response.setContentType("text/HTML");
		
		return null;
	}
	
	private void getAllPossiblePermissionFromCourse(HttpServletRequest request) throws Exception {
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		user = facade.searchUserById(user.getId());
		
		if(user != null) {
		    int courseId = Integer.parseInt(request.getParameter("courseId"));
			
			Course course = facade.getCoursesById(courseId);
				
			ProfileType loggedProfile = user.getTypeProfile();
			
			boolean isOwner = false;
			boolean canDeleteCourse = false;
			
			Role userRole = facade.getRoleByPersonInCourse(user.getPerson(), course);
			if(userRole != null){
				if(userRole.getRoleType() == RoleType.TEACHER || userRole.getRoleType() == RoleType.ASSISTANT){
					isOwner = true;
				}
			}
			
			if(userRole != null){
				if(userRole.getRoleType() == RoleType.TEACHER){
					canDeleteCourse = true;
				}
			}
			
			if (loggedProfile == ProfileType.ADMIN) {
				isOwner = true;
				canDeleteCourse = true;
			}
			
			boolean isTeacher = false;
			if (loggedProfile == ProfileType.PROFESSOR || loggedProfile == ProfileType.ADMIN) {
				isTeacher = true;
			}
			
			boolean canRegisterUser = false;
			if (facade.canRegisterUser(user, course)) {
				canRegisterUser = true;
			}
			
			boolean canAssistanceRequest = false;
			if (facade.canAssistanceRequest(user.getPerson(), course)) {
				canAssistanceRequest = true;
			}
			
			boolean canViewCourseContent = false;
			if(userRole != null || isOwner) if(isOwner || userRole.getRoleType() == RoleType.TEACHER || 
					userRole.getRoleType() == RoleType.ASSISTANT || userRole.getRoleType() == RoleType.STUDENT) {
				canViewCourseContent = true;
			}
				
			request.setAttribute("canViewCourseContent", canViewCourseContent);
			request.setAttribute("canAssistanceRequest", canAssistanceRequest);
			request.setAttribute("canRegisterUser", canRegisterUser);
			request.setAttribute("isOwner", isOwner);
			request.setAttribute("canDeleteCourse", canDeleteCourse);
			request.setAttribute("isTeacher", isTeacher);
			request.setAttribute("course", course);
		}
	}
	
	public ActionForward showViewRealizeEvaluationActivity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
		
		Evaluation evaluation = facade.getEvaluationById(evaluationId);
		
		AccessInfo info = (AccessInfo) request.getSession().getAttribute("user");
		info = facade.searchUserById(info.getId());
		
		Person person = info.getPerson();
		
		EvaluationRealized myEvaluationRealized = null;
		
		for (EvaluationRealized er : evaluation.getEvaluationsRealized()) {
			if(er.getStudent().equals(person)) {
				myEvaluationRealized = er;
			}
		}
		
		
		request.setAttribute("evaluation", evaluation);
		request.setAttribute("myEvaluationRealized", myEvaluationRealized);
		
		return mapping.findForward(FORWARD_SHOW_VIEW_REALIZE_EVALUATION_ACTIVITY);
	}
	
	public ActionForward correctEvaluationRealized(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int idEvaluation = Integer.parseInt(request.getParameter("evaluationId"));
		int idEvaluationRealized = Integer.parseInt(request.getParameter("idEvaluationRealized"));
		
		Evaluation evaluation = facade.getEvaluationById(idEvaluation);
		EvaluationRealized evaluationRealized = new EvaluationRealized();
		
		for (EvaluationRealized er : evaluation.getEvaluationsRealized()) {
			if(er.getId() == idEvaluationRealized) {
				evaluationRealized = er;
				break;
			}	
		}
		
		int countQuestions = evaluationRealized.getQuestionsRealized().size();
		List<QuestionRealized> questionsRealized = evaluationRealized.getQuestionsRealized();
		float finalGrade = 0;
		
		for( int x = 0; x < countQuestions; x++ ) {
			questionsRealized.get(x).setGrade(Float.parseFloat(request.getParameter("grade"+x)));
			finalGrade += questionsRealized.get(x).getGrade();
			
			if(request.getParameter("comment"+x) != null) {
				questionsRealized.get(x).setComment(request.getParameter("comment"+x));
			}
		}
		
		evaluationRealized.setCorrectedDate(new Date());
		evaluationRealized.setGrade(finalGrade);
		
		return this.showViewEvaluationActivity(mapping, form, request, response);
	}
	
	public ActionForward getUserEvaluationGrade(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(SystemActions.isLoggedUser(request)) {
			int personId = Integer.parseInt(request.getParameter("personId")); 
			int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
			Float grade = null;
			
			Evaluation evaluation = facade.getEvaluationById(evaluationId);
			List<EvaluationRealized> evaluationsRealized = evaluation.getEvaluationsRealized();
			
			for (EvaluationRealized e : evaluationsRealized) {
				if(e.getStudent().getId() == personId) {
					grade = e.getGrade();
					break;
				}
			}
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			if(grade != null){
				out.print(grade);
			} else {
				out.print("&nbsp;");
			}
		}
		
		return null;
	}	
}