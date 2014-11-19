/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.struts.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.formula.functions.Evaluate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Archive;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Choice;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ExternalLink;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.PersonRoleCourse;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.RoleType;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Alternative;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Question;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionAlternativable;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionAssociation;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionDiscursive;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionGap;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionMultiple;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionTrueFalse;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.exception.CourseInvalidException;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;
import br.ufpe.cin.amadeus.amadeus_web.permissions.content_management.CoursePermissions;
import br.ufpe.cin.amadeus.amadeus_web.util.DateValidator;

public class CourseActions extends SystemActions {

	private final String FORWARD_INSERT_COURSE_STEP_TWO = "fInsertCourseStepTwo";
	private final String FORWARD_SEARCH_COURSE = "fSearchCourse";
	private final String FORWARD_VIEW_DELETE_CONFIRMATION = "fViewDeleteConfirmation";
	private final String FORWARD_SHOW_VIEW_COURSE = "fShowViewCourse";
	private final String FORWARD_SHOW_VIEW_CHANGE_TEACHER = "fShowViewChangeTeacher";
	private final String FORWARD_SHOW_VIEW_COURSE_NOT_LOGGED = "fShowViewCourseNotLogged";
	private final String FORWARD_SHOW_VIEW_COURSE_PARTICIPANTS = "fShowViewCourseParticipants";
	private final String FORWARD_SHOW_VIEW_SHOW_MODULES = "fShowViewShowModules";
	private final String FORWARD_SHOW_VIEW_EDIT_COURSE = "fShowViewEditCourse";
	private final String FORWARD_SHOW_VIEW_COURSE_EVALUATIONS = "fShowViewCourseEvaluations";
	private final String FORWARD_SHOW_VIEW_SEND_MAIL = "fShowViewSendMail";
	private final String FORWARD_SHOW_VIEW_NEW_EXTERNAL_LINK = "fshowViewNewExternalLink";
	private final String FORWARD_SHOW_VIEW_REPLICATE_COURSE = "fReplicateCourseStepOne";
	
	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("courses.searchButton", "searchCourse");
		map.put("PROPERTY BUTTON NAME", "METODO");
		map.put("courseForm.nextStep", "insertCourseStepOne");
		map.put("courseForm.nextStepReplication", "replicateCourseStepOne"); //DELETE
		map.put("course.viewReplicateCourse", "viewReplicateCourse");
		map.put("course.viewChangeTeacher", "viewChangeTeacher");
		map.put("general.register", "insertCourseStepTwo");
		map.put("general.update", "editCourse");
		map.put("general.cancel", "cancelEdition");
		map.put("viewCourse.registration", "viewCourse");
		map.put("course.delete", "deleteCourse");
		map.put("course.viewDeleteConfirmation", "viewDeleteCourseConfirmation");
		
		
		map.put("course.unregisterStudentCourse", "unregisterStudentCourse");
		
		map.put("course.showViewCourseParticipants", "showViewCourseParticipants");
		map.put("course.showViewCourse", "showViewCourse");
		map.put("course.showViewCourseNotLogged", "showViewCourseNotLogged");
		map.put("course.showViewEditCourse", "showViewEditCourse");
		map.put("course.showViewShowModules", "showViewShowModules");
		map.put("course.showViewCourseEvaluations", "showViewCourseEvaluations");
		map.put("course.showViewSendMail", "showViewSendMail");
		map.put("course.sendMailForCourseParticipants", "sendMailForCourseParticipants");
		map.put("course.changeTeacher", "changeTeacher");
		map.put("course.sendMailForCourseParticipants", "sendMailForCourseParticipants");
		
		return map;
	}
	
	public ActionForward changeTeacher(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("########: CHEGOU!!!!");
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int teacher = Integer.parseInt(request.getParameter("teacher")); 
		Course course = facade.getCoursesById(courseId);
		Person oldProfessor = course.getProfessor();
		Person professor = facade.getPersonByID(teacher);
		course.setProfessor(professor);
		List<PersonRoleCourse> prcList = course.getPersonsRolesCourse();
		for (int i = 0; i < prcList.size(); i++) {
			if(oldProfessor.getId() == prcList.get(i).getPerson().getId()){
				prcList.get(i).setPerson(professor);
			}
		}
		facade.flush();
		response.sendRedirect("course.do?method=showViewCourse&courseId="+course.getId());
		return null;
	}

	
	public ActionForward replicateCourseStepOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CourseInvalidException, IOException {
		
		DynaActionForm myForm = (DynaActionForm) form;
		
		String lala = myForm.getString("keywords");
		
		ActionMessages messages = new ActionMessages();
		int id = (Integer) myForm.get("courseId");
		Course newCourse = new Course();
		Course c = facade.getCoursesById(id);
		newCourse.setName(myForm.getString("name"));
		newCourse.setObjectives(myForm.getString("objectives"));
		newCourse.setContent(myForm.getString("content"));
		newCourse.setMaxAmountStudents(Integer.valueOf((String) myForm.get("maxAmountStudents")));

		String initialRegistrationDay = (String) myForm.get("initialRegistrationDay");
		String initialRegistrationMonth = (String) myForm.get("initialRegistrationMonth");
		String initialRegistrationYear = (String) myForm.get("initialRegistrationYear");
		String finalRegistrationDay = (String) myForm.get("finalRegistrationDay");
		String finalRegistrationMonth = (String) myForm.get("finalRegistrationMonth");
		String finalRegistrationYear = (String) myForm.get("finalRegistrationYear");
		String initialCourseDay = (String) myForm.get("initialCourseDay");
		String initialCourseMonth = (String) myForm.get("initialCourseMonth");
		String initialCourseYear = (String) myForm.get("initialCourseYear");
		String finalCourseDay = (String) myForm.get("finalCourseDay");
		String finalCourseMonth = (String) myForm.get("finalCourseMonth");
		String finalCourseYear = (String) myForm.get("finalCourseYear");

		DateValidator ir = new DateValidator(messages, initialRegistrationDay, initialRegistrationMonth, initialRegistrationYear, true);
		DateValidator fr = new DateValidator(messages, finalRegistrationDay, finalRegistrationMonth, finalRegistrationYear, true);
		DateValidator ic = new DateValidator(messages, initialCourseDay, initialCourseMonth, initialCourseYear, true);
		DateValidator fc = new DateValidator(messages, finalCourseDay, finalCourseMonth, finalCourseYear, true);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		Date today = new Date();
		today.setDate(today.getDate() - 1);
		Date initReg = ir.getDate();
		Date finalReg = fr.getDate();
		Date initCourse = ic.getDate();
		Date finalCourse = fc.getDate();
		
		if (initReg.after(finalReg)) {
			messages.add("invalidDate", new ActionMessage("errors.initialRegistrationDate"));
		} else if (initCourse.after(finalCourse)) {
			messages.add("invalidDate", new ActionMessage("errors.initialCourseDate"));
		} else if (finalReg.after(initCourse)) {
			messages.add("invalidDate", new ActionMessage("errors.finalRegistrationDate"));
		} if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		newCourse.setInitialRegistrationDate(initReg);
		newCourse.setFinalRegistrationDate(finalReg);
		newCourse.setInitialCourseDate(initCourse);
		newCourse.setFinalCourseDate(finalCourse);

		Set<Keyword> keywords = new HashSet<Keyword>();
		String rawKeywords = myForm.getString("keywords");
		
		if(rawKeywords != null) {
			String[] keywordsOfCourse = rawKeywords.split(",");
			for (String kw : keywordsOfCourse) {
				Keyword keyword = facade.getKeywordWithIdByName(kw);
				if(!keyword.getName().equals("")){
					boolean alreadyHasKeyword = false;
					for(Keyword ahkw : keywords) {
						if(keyword.equals(ahkw)){
							alreadyHasKeyword = true;
						}
					}
					if(!alreadyHasKeyword) {
						keywords.add(keyword);
					}
				}
				
			}
		}
		
		synchronized (this) {
			facade.incrementPopularityKeyword(c.getId(), keywords);
			facade.decrementPopularityKeyword(c.getId(), keywords);	
		}
		
		newCourse.setKeywords(keywords);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		try {
			/*newCourse.setMaxAmountStudents(c.getMaxAmountStudents());
			newCourse.setName(c.getName());
			newCourse.setObjectives(c.getObjectives());
			newCourse.setContent(c.getContent());
			newCourse.setCreationDate(c.getCreationDate());
			newCourse.setInitialCourseDate(c.getInitialCourseDate());
			newCourse.setInitialRegistrationDate(c.getInitialRegistrationDate());
			newCourse.setFinalRegistrationDate(c.getFinalRegistrationDate());
			newCourse.setFinalCourseDate(c.getFinalCourseDate());*/
			newCourse.setProfessor(c.getProfessor());
			//newCourse.setStudentsScores(c.getStudentsScores());
			//newCourse.setId(0);
			List<Module> moduleList = new ArrayList<Module>();
			for (int i = 0; i < c.getModules().size(); i++) {
				//System.out.println("i: "+i);
				Module newModule = new Module();
				newModule.setName(c.getModules().get(i).getName());
				newModule.setDescription(c.getModules().get(i).getDescription());
				newModule.setVisible(c.getModules().get(i).isVisible());
				newModule.setCourse(newCourse);
				newModule.setPosition(c.getModules().get(i).getPosition());
				for (int j = 0; j < c.getModules().get(i).getMaterialRequests().size(); j++) {
					//System.out.println("j1: "+j);
					MaterialRequest materialRequest = new MaterialRequest();
					materialRequest.setName(c.getModules().get(i).getMaterialRequests().get(j).getName());
					materialRequest.setDescription(c.getModules().get(i).getMaterialRequests().get(j).getDescription());
					materialRequest.setAllowLateDeliveries(c.getModules().get(i).getMaterialRequests().get(j).isAllowLateDeliveries());
					materialRequest.setCreationDate(new Date());
					materialRequest.setDeliveryDate(new Date());
					materialRequest.setModule(newModule);
					newModule.getMaterialRequests().add(materialRequest);
				}
				
				for (int j = 0; j < c.getModules().get(i).getMaterials().size(); j++) {
					//System.out.println("j2: "+j);
					Material material = new Material();
					material.setArchiveName(c.getModules().get(i).getMaterials().get(j).getArchiveName());
					material.setExtension(c.getModules().get(i).getMaterials().get(j).getExtension());
					material.setAllowLateDeliveries(c.getModules().get(i).getMaterials().get(j).isAllowLateDeliveries());
					material.setCreationDate(new Date());
					material.setAuthor(c.getProfessor());
					material.setModule(newModule);
					Archive newArchive = new Archive();
					newArchive.setArchive(c.getModules().get(i).getMaterials().get(j).getArchive().getArchive());
					material.setArchive(newArchive);
					newModule.getMaterials().add(material);
				}
				
				for (int j = 0; j < c.getModules().get(i).getPolls().size(); j++) {
					//System.out.println("j3: "+j);
					Poll newPoll = new Poll();
					newPoll.setName(c.getModules().get(i).getPolls().get(j).getName());
					newPoll.setQuestion(c.getModules().get(i).getPolls().get(j).getQuestion());
					newPoll.setCreationDate(new Date());
					newPoll.setFinishDate(new Date());
					newPoll.setModule(newModule);
					newModule.getPolls().add(newPoll);
					//System.out.println("SIZE1: "+size);
					for (int k = 0; k < c.getModules().get(i).getPolls().get(j).getChoices().size(); k++) {
						//System.out.println("SIZE2: "+c.getModules().get(i).getPolls().get(j).getChoices().size());
						//System.out.println("k: "+k);
						Choice newChoice = new Choice();
						newChoice.setAlternative(c.getModules().get(i).getPolls().get(j).getChoices().get(k).getAlternative());
						newChoice.setPoll(newPoll);
						newModule.getPolls().get(j).getChoices().add(newChoice);
					}
					
				}
				
				for (int j = 0; j < c.getModules().get(i).getForums().size(); j++) {
					Forum newForum = new Forum();
					
					newForum.setName(c.getModules().get(i).getForums().get(j).getName());
					newForum.setDescription(c.getModules().get(i).getForums().get(j).getDescription());
					newForum.setCreationDate(new Date());
					newForum.setModule(newModule);
					newModule.getForums().add(newForum);
				}
				
				for (int j = 0; j < c.getModules().get(i).getExternalLinks().size(); j++) {
					ExternalLink newExternalLink = new ExternalLink();
					
					newExternalLink.setName(c.getModules().get(i).getExternalLinks().get(j).getName());
					newExternalLink.setUrl(c.getModules().get(i).getExternalLinks().get(j).getUrl());
					newExternalLink.setDescription(c.getModules().get(i).getExternalLinks().get(j).getDescription());
					newExternalLink.setCreationDate(new Date());
					newExternalLink.setModule(newModule);
					newModule.getExternalLinks().add(newExternalLink);
				}
				
				for (int j = 0; j < c.getModules().get(i).getVideos().size(); j++) {
					VideoIriz newVideo = new VideoIriz();
					
					newVideo.setName(c.getModules().get(i).getVideos().get(j).getName());
					newVideo.setDescription(c.getModules().get(i).getVideos().get(j).getDescription());
					newVideo.setYoutubeId(c.getModules().get(i).getVideos().get(j).getYoutubeId());
					newVideo.setCreationDate(new Date());
					newVideo.setModule(newModule);
					
					newModule.getVideos().add(newVideo);
					
				}
				
				for (int j = 0; j < c.getModules().get(i).getLearningObjects().size(); j++) {
					LearningObject newLearningObject = new LearningObject();
					
					newLearningObject.setName(c.getModules().get(i).getLearningObjects().get(j).getName());
					newLearningObject.setDescription(c.getModules().get(i).getLearningObjects().get(j).getDescription());
					newLearningObject.setUrl(c.getModules().get(i).getLearningObjects().get(j).getUrl());
					newLearningObject.setCreationDate(new Date());
					newLearningObject.setModule(newModule);
					
					newModule.getLearningObjects().add(newLearningObject);
				}
				
				for (int j = 0; j < c.getModules().get(i).getEvaluations().size(); j++) {
					Evaluation newEvaluation = new Evaluation();
					
					newEvaluation.setDescription(c.getModules().get(i).getEvaluations().get(j).getDescription());
					newEvaluation.setStart(new Date());
					newEvaluation.setFinish(new Date());
					newEvaluation.setAfterdeadlineachieved(c.getModules().get(i).getEvaluations().get(j).isAfterdeadlineachieved());
					
					
					
					
					for (int k = 0; k < c.getModules().get(i).getEvaluations().get(j).getQuestions().size(); k++) {
						Question question = c.getModules().get(i).getEvaluations().get(j).getQuestions().get(k);
						
						if (question instanceof QuestionAssociation){
							QuestionAssociation oldQuestion = (QuestionAssociation)question;
							QuestionAssociation newQuestion = new QuestionAssociation();
							
							newQuestion.setDescription(oldQuestion.getDescription());
							//newQuestion.setAssociations(oldQuestion.getAssociations());
							newQuestion.setCourse(newCourse);
							newCourse.getQuestions().add(newQuestion);
							newEvaluation.getQuestions().add((Question)newQuestion);
							newQuestion.getEvaluations().add(newEvaluation);
						}
						
						if (question instanceof QuestionGap){
							QuestionGap oldQuestionGap = (QuestionGap)question;
							QuestionGap newQuestionGap = new QuestionGap();
							
							newQuestionGap.setDescription(oldQuestionGap.getDescription());
							newQuestionGap.setCourse(newCourse);
							newCourse.getQuestions().add(newQuestionGap);
							newEvaluation.getQuestions().add((Question)newQuestionGap);
							newQuestionGap.getEvaluations().add(newEvaluation);
						}
						
						if(question instanceof QuestionDiscursive){
							QuestionDiscursive oldQuestionDiscursive = (QuestionDiscursive)question;
							QuestionDiscursive newQuestionDiscursive = new QuestionDiscursive();
							
							newQuestionDiscursive.setDescription(oldQuestionDiscursive.getDescription());
							newQuestionDiscursive.setCourse(newCourse);
							newCourse.getQuestions().add(newQuestionDiscursive);
							newEvaluation.getQuestions().add((Question)newQuestionDiscursive);
							newQuestionDiscursive.getEvaluations().add(newEvaluation);
						}
						
						if(question instanceof QuestionMultiple){
							QuestionMultiple oldQuestionMultiple = (QuestionMultiple)question;
							QuestionMultiple newQuestionMultiple = new QuestionMultiple();
							newQuestionMultiple.setDescription(oldQuestionMultiple.getDescription());
							newQuestionMultiple.setCourse(newCourse);
							newCourse.getQuestions().add(newQuestionMultiple);
							newEvaluation.getQuestions().add((Question)newQuestionMultiple);
							for (int l = 0; l < oldQuestionMultiple.getAlternatives().size(); l++) {
								Alternative newAlternative = new Alternative();
								newAlternative.setCorrect(oldQuestionMultiple.getAlternatives().get(l).isCorrect());
								newAlternative.setDescription(oldQuestionMultiple.getAlternatives().get(l).getDescription());
								newAlternative.setQuestion(newQuestionMultiple);
								newQuestionMultiple.getAlternatives().add(newAlternative);
								newQuestionMultiple.getEvaluations().add(newEvaluation);
							}
						}
						
						if(question instanceof QuestionTrueFalse){
							QuestionTrueFalse oldQuestionTrueFalse = (QuestionTrueFalse)question;
							QuestionTrueFalse newQuestionTrueFalse = new QuestionTrueFalse();
							newQuestionTrueFalse.setDescription(oldQuestionTrueFalse.getDescription());
							newQuestionTrueFalse.setCourse(newCourse);
							newQuestionTrueFalse.getEvaluations().add(newEvaluation);
							newEvaluation.getQuestions().add((Question)newQuestionTrueFalse);
							newCourse.getQuestions().add(newQuestionTrueFalse);
							
							for (int l = 0; l < oldQuestionTrueFalse.getAlternatives().size(); l++) {
								Alternative newAlternative = new Alternative();
								newAlternative.setCorrect(oldQuestionTrueFalse.getAlternatives().get(l).isCorrect());
								newAlternative.setDescription(oldQuestionTrueFalse.getAlternatives().get(l).getDescription());
								newAlternative.setQuestion(newQuestionTrueFalse);
								newQuestionTrueFalse.getAlternatives().add(newAlternative);
								
							}
							
						}
						
					}
					
					newEvaluation.setModule(newModule);
					newModule.getEvaluations().add(newEvaluation);
					newEvaluation.setEvaluationsRealized(null);
					
				}
				
				moduleList.add(newModule);
				
			}

			newCourse.setModules(moduleList);
			
			Role teacherRole = facade.searchRoleByConstant(RoleType.TEACHER);
			
			PersonRoleCourse prc = new PersonRoleCourse();
			prc.setCourse(newCourse);
			prc.setPerson(newCourse.getProfessor());
			prc.setRole(teacherRole);
			newCourse.getPersonsRolesCourse().clear();
			newCourse.getPersonsRolesCourse().add(prc);
			facade.validateCourseStepOne(newCourse);
			facade.insertCourse(newCourse);
			
			facade.flush();
		} catch (CourseInvalidException e) {
			messages.add("confirmationCourse",	new ActionMessage(e.getMessage()));
			saveErrors(request, messages);
			return mapping.getInputForward();
		}
		
		response.sendRedirect("course.do?method=showViewCourse&courseId="+newCourse.getId());
		return null;
		
	}

	public ActionForward insertCourseStepOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();

		AccessInfo user = (AccessInfo) request.getSession()
				.getAttribute("user");
		Person p = new Person();
		if (user != null) {
			p = user.getPerson();
		}

		Course c = new Course();
		c.setProfessor(p);
		c.setName(myForm.getString("name"));
		c.setObjectives(myForm.getString("objectives"));
		c.setContent(myForm.getString("content"));
		c.setMaxAmountStudents(Integer.valueOf((String) myForm.get("maxAmountStudents")));

		String initialRegistrationDay = (String) myForm.get("initialRegistrationDay");
		String initialRegistrationMonth = (String) myForm.get("initialRegistrationMonth");
		String initialRegistrationYear = (String) myForm.get("initialRegistrationYear");
		
		String finalRegistrationDay = (String) myForm.get("finalRegistrationDay");
		String finalRegistrationMonth = (String) myForm.get("finalRegistrationMonth");
		String finalRegistrationYear = (String) myForm.get("finalRegistrationYear");
		
		String initialCourseDay = (String) myForm.get("initialCourseDay");
		String initialCourseMonth = (String) myForm.get("initialCourseMonth");
		String initialCourseYear = (String) myForm.get("initialCourseYear");
		
		String finalCourseDay = (String) myForm.get("finalCourseDay");
		String finalCourseMonth = (String) myForm.get("finalCourseMonth");
		String finalCourseYear = (String) myForm.get("finalCourseYear");

		DateValidator ir = new DateValidator(messages, initialRegistrationDay,
				initialRegistrationMonth, initialRegistrationYear, true);
		DateValidator fr = new DateValidator(messages, finalRegistrationDay,
				finalRegistrationMonth, finalRegistrationYear, true);
		DateValidator ic = new DateValidator(messages, initialCourseDay,
				initialCourseMonth, initialCourseYear, true);
		DateValidator fc = new DateValidator(messages, finalCourseDay,
				finalCourseMonth, finalCourseYear, true);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		Date today = new Date();
		today.setDate(today.getDate() - 1);
		Date initReg = ir.getDate();
		Date finalReg = fr.getDate();
		Date initCourse = ic.getDate();
		Date finalCourse = fc.getDate();
		
		if (initReg.before(today))
			messages.add("invalidDate", new ActionMessage("errors.todayRegistrationDate"));
		else if (initReg.after(finalReg))
			messages.add("invalidDate", new ActionMessage("errors.initialRegistrationDate"));
		else if (initCourse.after(finalCourse))
			messages.add("invalidDate", new ActionMessage("errors.initialCourseDate"));
		else if (finalReg.after(initCourse))
			messages.add("invalidDate", new ActionMessage("errors.finalRegistrationDate"));

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		c.setInitialRegistrationDate(initReg);
		c.setFinalRegistrationDate(finalReg);
		c.setInitialCourseDate(initCourse);
		c.setFinalCourseDate(finalCourse);

		try {
			facade.validateCourseStepOne(c);
			System.out.println("HUHUHUHU: Step1");
		} catch (CourseInvalidException e) {
			messages.add("confirmationCourse",
					new ActionMessage(e.getMessage()));
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		request.getSession().setAttribute("incompleteCourse", c);
		return mapping.findForward(FORWARD_INSERT_COURSE_STEP_TWO);

	}
	
	public ActionForward viewChangeTeacher(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CourseInvalidException, IOException, Exception {
		
		ActionForward forward = null;
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		
		Course course = facade.getCoursesById(courseId);

		boolean isOwner = false;
		
		AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
		loggedUser = facade.searchUserById(loggedUser.getId());
		
		ProfileType loggedProfile = loggedUser.getTypeProfile();


		Role userRole = facade.getRoleByPersonInCourse(loggedUser.getPerson(), course);
		if(userRole != null){
			if(userRole.getRoleType() == RoleType.TEACHER || userRole.getRoleType() == RoleType.ASSISTANT){
				isOwner = true;
			}
		}
		
		if (loggedProfile == ProfileType.ADMIN) {
			isOwner = true;
		}
		if(isOwner){
			ArrayList<AccessInfo> teacherList = (ArrayList<AccessInfo>) facade.searchUsers("", 2, 0);
			System.out.println("########: " + teacherList.get(0).getLogin());
			request.setAttribute("teacherList", teacherList);
			request.setAttribute("courseId", courseId);
			forward = mapping.findForward(FORWARD_SHOW_VIEW_CHANGE_TEACHER);
		}else{
			forward = this.showViewMenu(mapping, form, request, response);
		}
		
		return forward;
	}

	public ActionForward insertCourseStepTwo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();

		Course c = (Course) request.getSession().getAttribute("incompleteCourse");
		request.getSession().removeAttribute("incompleteCourse");
		
		Set<Keyword> keywords = new HashSet<Keyword>();
		String rawKeywords = myForm.getString("keywords");
		
		if(rawKeywords != null) {
			String[] keywordsOfCourse = rawKeywords.split(",");
			for (String kw : keywordsOfCourse) {
				Keyword keyword = facade.getKeywordWithIdByName(kw);
				if(!keyword.getName().equals("")){
					boolean alreadyHasKeyword = false;
					for(Keyword ahkw : keywords) {
						if(keyword.equals(ahkw)){
							alreadyHasKeyword = true;
						}
					}
					if(!alreadyHasKeyword) {
						keywords.add(keyword);
					}
				}
				
			}
		}
		
		synchronized (this) {
			facade.incrementPopularityKeyword(c.getId(), keywords);
			facade.decrementPopularityKeyword(c.getId(), keywords);	
		}
		
		c.setKeywords(keywords);

		c.setModules(new ArrayList<Module>());

		try {	
			Role teacherRole = facade.searchRoleByConstant(RoleType.TEACHER);
			PersonRoleCourse prc = new PersonRoleCourse();
			prc.setCourse(c);
			prc.setPerson(c.getProfessor());
			prc.setRole(teacherRole);
			
			c.getPersonsRolesCourse().add(prc);
			
			facade.insertCourse(c);
			
			facade.flush();
		} catch (CourseInvalidException e) {
			messages.add("confirmationCourse",new ActionMessage(e.getMessage()));
			saveErrors(request, messages);
			return mapping.getInputForward();
		}
		
		response.sendRedirect("course.do?method=showViewShowModules&idCourse="+c.getId());
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public ActionForward searchCourse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dyna = (DynaActionForm) form;
		String courseName = dyna.getString("courseName").trim();

		List<Course>[] results = null;
		if (!courseName.equals("") && courseName != null) {
			results = facade.getCoursesByRule(courseName);
			if(results[0].size() == 0 && results[1].size() == 0 
				&& results[2].size() == 0 && results[3].size() == 0 ){
				results = facade.getCoursesByRule(facade.searchString(courseName));
			}
		
		}
		
		

		int numberOfResults = results[0].size() + results[1].size()
				+ results[2].size() + results[3].size();
		int numberOfPages = 1;
		if (numberOfResults % 10 == 0) {
			numberOfPages = numberOfResults / 10;
		} else {
			numberOfPages = (numberOfResults / 10) + 1;
		}

		List<Course>[][] resultsClassified = new ArrayList[4][numberOfResults];
		resultsClassified = facade
				.classifyCoursesByPage(results, numberOfPages);

		request.setAttribute("criteria", courseName);
		request.setAttribute("foundCourses", resultsClassified);
		request.setAttribute("numberPages", numberOfPages);
		request.setAttribute("numberOfResults", numberOfResults);

		return mapping.findForward(FORWARD_SEARCH_COURSE);

	}

	public ActionForward viewCourse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DynaActionForm myForm = (DynaActionForm) form;
		
		Course c = facade.getCoursesById((Integer) myForm.get("id"));
		
		AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
		user = facade.searchUserById(user.getId());

		Person person = user.getPerson();
		
		facade.registerStudentCourse(c, person);

		facade.updateCourse(c);
		
		return this.showViewMenu(mapping, myForm, request, response);
	}

	public ActionForward unregisterStudentCourse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Integer idStudent = Integer.valueOf(request.getParameter("studentId"));
		Person student = facade.getPersonByID(idStudent);
		
		Integer idCourse = Integer.valueOf(request.getParameter("courseId"));
		Course course = facade.getCoursesById(idCourse);
		
		facade.unregisterStudentCourse(course, student);
		facade.updateCourse(course);
		request.setAttribute("courseId", idCourse);
		
		return this.showViewCourseParticipants(mapping, form, request, response);
	}
	
	public ActionForward editCourse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		DynaActionForm myForm = (DynaActionForm) form;
		ActionMessages messages = new ActionMessages();
		int id = (Integer) myForm.get("id");
		
		Course c = facade.getCoursesById(id);
		c.setName(myForm.getString("name"));
		c.setObjectives(myForm.getString("objectives"));
		c.setContent(myForm.getString("content"));
		c.setMaxAmountStudents(Integer.valueOf((String) myForm.get("maxAmountStudents")));

		String initialRegistrationDay = (String) myForm.get("initialRegistrationDay");
		String initialRegistrationMonth = (String) myForm.get("initialRegistrationMonth");
		String initialRegistrationYear = (String) myForm.get("initialRegistrationYear");
		String finalRegistrationDay = (String) myForm.get("finalRegistrationDay");
		String finalRegistrationMonth = (String) myForm.get("finalRegistrationMonth");
		String finalRegistrationYear = (String) myForm.get("finalRegistrationYear");
		String initialCourseDay = (String) myForm.get("initialCourseDay");
		String initialCourseMonth = (String) myForm.get("initialCourseMonth");
		String initialCourseYear = (String) myForm.get("initialCourseYear");
		String finalCourseDay = (String) myForm.get("finalCourseDay");
		String finalCourseMonth = (String) myForm.get("finalCourseMonth");
		String finalCourseYear = (String) myForm.get("finalCourseYear");

		DateValidator ir = new DateValidator(messages, initialRegistrationDay, initialRegistrationMonth, initialRegistrationYear, true);
		DateValidator fr = new DateValidator(messages, finalRegistrationDay, finalRegistrationMonth, finalRegistrationYear, true);
		DateValidator ic = new DateValidator(messages, initialCourseDay, initialCourseMonth, initialCourseYear, true);
		DateValidator fc = new DateValidator(messages, finalCourseDay, finalCourseMonth, finalCourseYear, true);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		Date today = new Date();
		today.setDate(today.getDate() - 1);
		Date initReg = ir.getDate();
		Date finalReg = fr.getDate();
		Date initCourse = ic.getDate();
		Date finalCourse = fc.getDate();
		
		if (initReg.after(finalReg)) {
			messages.add("invalidDate", new ActionMessage("errors.initialRegistrationDate"));
		} else if (initCourse.after(finalCourse)) {
			messages.add("invalidDate", new ActionMessage("errors.initialCourseDate"));
		} else if (finalReg.after(initCourse)) {
			messages.add("invalidDate", new ActionMessage("errors.finalRegistrationDate"));
		} if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		c.setInitialRegistrationDate(initReg);
		c.setFinalRegistrationDate(finalReg);
		c.setInitialCourseDate(initCourse);
		c.setFinalCourseDate(finalCourse);

		Set<Keyword> keywords = new HashSet<Keyword>();
		String rawKeywords = myForm.getString("keywords");
		
		if(rawKeywords != null) {
			String[] keywordsOfCourse = rawKeywords.split(",");
			for (String kw : keywordsOfCourse) {
				Keyword keyword = facade.getKeywordWithIdByName(kw);
				if(!keyword.getName().equals("")){
					boolean alreadyHasKeyword = false;
					for(Keyword ahkw : keywords) {
						if(keyword.equals(ahkw)){
							alreadyHasKeyword = true;
						}
					}
					if(!alreadyHasKeyword) {
						keywords.add(keyword);
					}
				}
				
			}
		}
		
		synchronized (this) {
			facade.incrementPopularityKeyword(c.getId(), keywords);
			facade.decrementPopularityKeyword(c.getId(), keywords);	
		}
		
		c.setKeywords(keywords);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return mapping.getInputForward();
		}

		try {
			facade.validateCourseStepOne(c);
			facade.insertCourse(c);
			facade.deleteKeywordsOrphan();
		} catch (CourseInvalidException e) {
			messages.add("confirmationCourse",	new ActionMessage(e.getMessage()));
			saveErrors(request, messages);
			return mapping.getInputForward();
		}
		
		response.sendRedirect("course.do?method=showViewCourse&courseId="+c.getId());
		
		return null;
	}

	public ActionForward cancelEdition(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("cancel");
	}
	
	public ActionForward deleteCourse(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int courseId = Integer.valueOf(request.getParameter("courseId"));
		
		Course course = facade.getCoursesById(courseId);
		
		AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
		loggedUser = facade.searchUserById(loggedUser.getId());
		
		ProfileType loggedProfile = loggedUser.getTypeProfile();
		
		boolean isOwner = false;
		
		Role userRole = facade.getRoleByPersonInCourse(loggedUser.getPerson(), course);
		if(userRole != null){
			if(userRole.getRoleType() == RoleType.TEACHER || userRole.getRoleType() == RoleType.ASSISTANT){
				isOwner = true;
			}
		}
		
		if (loggedProfile == ProfileType.ADMIN) {
			isOwner = true;
		}
		
		if(isOwner) {
			for (Keyword k : course.getKeywords()) {
				k.setPopularity(k.getPopularity()-1);
			}
			facade.deleteCourse(course);	
			facade.deleteKeywordsOrphan();
		} 
		
		return this.showViewMenu(mapping, form, request, response);
	}
	
	public ActionForward viewReplicateCourse(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		int courseId = Integer.valueOf(request.getParameter("courseId"));
		Course course = facade.getCoursesById(courseId);
		
		boolean isOwner = false;
		
		AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
		loggedUser = facade.searchUserById(loggedUser.getId());
		
		ProfileType loggedProfile = loggedUser.getTypeProfile();


		Role userRole = facade.getRoleByPersonInCourse(loggedUser.getPerson(), course);
		if(userRole != null){
			if(userRole.getRoleType() == RoleType.TEACHER || userRole.getRoleType() == RoleType.ASSISTANT){
				isOwner = true;
			}
		}
		
		if (loggedProfile == ProfileType.ADMIN) {
			isOwner = true;
		}
		
		if(isOwner) {
			ArrayList<String> wordList = new ArrayList<String>();
			
			
			
			request.setAttribute("course", course);
			Set<Keyword> setKeywork = course.getKeywords();
			Iterator<Keyword> it = setKeywork.iterator();
			
			while(it.hasNext()){
				wordList.add(it.next().getName());
			}
				
			String keywordStr = new String();
			
			for (int i = 0; i < wordList.size(); i++) {
				keywordStr = keywordStr.concat(wordList.get(i)+", ");
			}
			request.setAttribute("keywordsStr", keywordStr);
			forward = mapping.findForward(FORWARD_SHOW_VIEW_REPLICATE_COURSE);
		} else {
			forward = this.showViewMenu(mapping, form, request, response);
		}
			
		return forward;
		
	}

	public ActionForward viewDeleteCourseConfirmation(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		int courseId = Integer.valueOf(request.getParameter("courseId"));
		
		Course course = facade.getCoursesById(courseId);
		
		AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
		loggedUser = facade.searchUserById(loggedUser.getId());
		
		ProfileType loggedProfile = loggedUser.getTypeProfile();
		
		boolean isOwner = false;
		
		Role userRole = facade.getRoleByPersonInCourse(loggedUser.getPerson(), course);
		if(userRole != null){
			if(userRole.getRoleType() == RoleType.TEACHER || userRole.getRoleType() == RoleType.ASSISTANT){
				isOwner = true;
			}
		}
		
		if (loggedProfile == ProfileType.ADMIN) {
			isOwner = true;
		}
		
		if(isOwner) {
			request.setAttribute("course", course);
			forward = mapping.findForward(FORWARD_VIEW_DELETE_CONFIRMATION);
		} else {
			forward = this.showViewMenu(mapping, form, request, response);
		}
			
		return forward;
	}
	
	public ActionForward showViewCourse(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
			user = facade.searchUserById(user.getId());
			
			int courseId;
		    
		    if(request.getParameter("courseId") == null) {
		    	courseId = (Integer) request.getAttribute("courseId");
		    } else {
		    	courseId = Integer.parseInt(request.getParameter("courseId"));
		    }
			
			Course course = facade.getCoursesById(courseId);
			List<Person> teachers = facade.getTeachersByCourse(course);
			List<Person> assistants = facade.listAssistantsByCourse(course);
			
			int studentsNumber = facade.getNumberOfStudentsInCourse(course);
			course.setNumberOfStudentsInCourse(studentsNumber);
			
			Set<Keyword> keywords = course.getKeywords();
			
			boolean canRegisterUser = false;
			
			if (facade.canRegisterUser(user, course)) {
				canRegisterUser = true;
			}
			
			Role userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
			
			SystemActions.setMenuPermissionsForUserInRequest(request, course);
			request.setAttribute("userRoleInCourse", ( userRoleInCourse != null) ? userRoleInCourse.getRoleType() : null );
			request.setAttribute("canRegisterUser", canRegisterUser);
			request.setAttribute("course", course);
			request.setAttribute("teachers", teachers);
			request.setAttribute("assistants", assistants);
			request.setAttribute("keywords", keywords);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_COURSE);
		} else {
			forward = this.showViewCourseNotLogged(mapping, form, request, response);
		}
		
		return forward;
	}

	public ActionForward showViewCourseNotLogged(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

	    int courseId = Integer.parseInt(request.getParameter("courseId"));
		
		Course course = facade.getCoursesById(courseId);
		List<Person> teachers = facade.getTeachersByCourse(course);

		int studentsNumber = facade.getNumberOfStudentsInCourse(course);
		course.setNumberOfStudentsInCourse(studentsNumber);
		
		Set<Keyword> keywords = course.getKeywords();
		List<Person> assistants = facade.listAssistantsByCourse(course);
		
		request.setAttribute("course", course);
		request.setAttribute("teachers", teachers);
		request.setAttribute("assistants", assistants);
		request.setAttribute("keywords", keywords);
		request.setAttribute("size", keywords.size());

		return mapping.findForward(FORWARD_SHOW_VIEW_COURSE_NOT_LOGGED);
	}
	
	public ActionForward showViewCourseParticipants(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)){
			AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
			user = facade.searchUserById(user.getId());

		    int courseId = Integer.parseInt(request.getParameter("courseId"));
		    
		    Course course = facade.getCoursesById(courseId);
			
		    Role userRoleInCourse = facade.getRoleByPersonInCourse(user.getPerson(), course);
		    
			List<Person> participants = facade.listStudentsByCourse(course);
			List<Person> teachers = facade.listTeachersByCourse(course);
			List<Person> assistants = facade.listAssistantsByCourse(course);
			
			
			SystemActions.setMenuPermissionsForUserInRequest(request, course);
			request.setAttribute("course", course);
			request.setAttribute("participants", participants);
			request.setAttribute("teachers", teachers);
			request.setAttribute("assistants", assistants);
			request.setAttribute("userRoleInCourse", userRoleInCourse);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_COURSE_PARTICIPANTS);
		} else {
			forward = showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewEditCourse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			String courseIdStr = request.getParameter("courseId");
			
			if(courseIdStr == null){
				courseIdStr = request.getParameter("id");
			}
			
		    int courseId = Integer.parseInt(courseIdStr);
			
			Course course = facade.getCoursesById(courseId);
			
			ArrayList<AccessInfo> teacherList = (ArrayList<AccessInfo>) facade.searchUsers("", 2, 0);
			//System.out.println("########: " + teacherList.get(0).getLogin());
			
			Set<Keyword> keywords = course.getKeywords();
			Iterator ikeywords = keywords.iterator();
			String keywordsStr = ""; 
			int counter = 1;
			while(ikeywords.hasNext()){
				if(counter < keywords.size()){
					keywordsStr = keywordsStr + ((Keyword)ikeywords.next()).getName() + ", ";
					counter++;
				} else {
					keywordsStr = keywordsStr + ((Keyword)ikeywords.next()).getName();
				}
			}
			
			GregorianCalendar gc = new GregorianCalendar();
			HashMap<String, Object> data = new HashMap<String, Object>();
			
			gc.setTime(course.getInitialRegistrationDate());
			data.put("initialRegistrationDay", gc.get(Calendar.DAY_OF_MONTH));
			data.put("initialRegistrationMonth", gc.get(Calendar.MONTH) + 1);
			data.put("initialRegistrationYear", gc.get(Calendar.YEAR));
			gc.setTime(course.getFinalRegistrationDate());
			data.put("finalRegistrationDay", gc.get(Calendar.DAY_OF_MONTH));
			data.put("finalRegistrationMonth", gc.get(Calendar.MONTH) + 1);
			data.put("finalRegistrationYear", gc.get(Calendar.YEAR));
			gc.setTime(course.getInitialCourseDate());
			data.put("initialCourseDay", gc.get(Calendar.DAY_OF_MONTH));
			data.put("initialCourseMonth", gc.get(Calendar.MONTH) + 1);
			data.put("initialCourseYear", gc.get(Calendar.YEAR));
			gc.setTime(course.getFinalCourseDate());
			data.put("finalCourseDay", gc.get(Calendar.DAY_OF_MONTH));
			data.put("finalCourseMonth", gc.get(Calendar.MONTH) + 1);
			data.put("finalCourseYear", gc.get(Calendar.YEAR));
			
			boolean canAssistanceRequest = false;
			
			AccessInfo loggedUser = (AccessInfo) request.getSession().getAttribute("user");
			loggedUser = facade.searchUserById(loggedUser.getId());
			
			if(facade.canAssistanceRequest(loggedUser.getPerson(), course)) {
				canAssistanceRequest = true;
			}
			
			request.setAttribute("canAssistanceRequest", canAssistanceRequest);
			request.setAttribute("data", data);
			request.setAttribute("keywordsStr", keywordsStr);
			request.setAttribute("course", course);
			request.setAttribute("teacherList", teacherList);
			
			forward = mapping.findForward(FORWARD_SHOW_VIEW_EDIT_COURSE);
		} else {
			forward = new SystemActions().showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewShowModules(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) { 
			AccessInfo user = (AccessInfo) request.getSession().getAttribute("user");
			user = facade.searchUserById(user.getId());
			
			int idCourse = Integer.parseInt(request.getParameter("idCourse"));
			
			Course course = facade.getCoursesById(idCourse);
			
			if(CoursePermissions.userCanShowViewShowModules(request, course)) {
				Role userRoleInCourse = Facade.getInstance().getRoleByPersonInCourse(user.getPerson(), course);
				
				List<Module> modules = course.getModules();
				
				List<Person> assistances = facade.getAssistanceInCourse(course);
		
				SystemActions.setMenuPermissionsForUserInRequest(request, course);
				request.setAttribute("userRoleInCourse", ( userRoleInCourse != null) ? userRoleInCourse.getRoleType() : null );
				request.setAttribute("course", course);
				request.setAttribute("modules", modules);
				request.setAttribute("assistants", assistances);
				
				forward = mapping.findForward(FORWARD_SHOW_VIEW_SHOW_MODULES);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewCourseEvaluations(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			int courseId = Integer.parseInt(request.getParameter("courseId"));
			Course course = facade.getCoursesById(courseId);
			
			if(CoursePermissions.userCanShowViewCourseEvaluations(request, course)) {
				List<Person> participants = facade.listStudentsByCourse(course);
				
				SystemActions.setMenuPermissionsForUserInRequest(request, course);
				request.setAttribute("participants", participants);
				request.setAttribute("course", course);
				
				forward = mapping.findForward(FORWARD_SHOW_VIEW_COURSE_EVALUATIONS);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward showViewSendMail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			int courseId = Integer.parseInt(request.getParameter("courseId"));
			Course course = facade.getCoursesById(courseId);
		
			if(CoursePermissions.userCanShowViewSendMail(request, course)) {
				request.setAttribute("course", course);
				forward = mapping.findForward(FORWARD_SHOW_VIEW_SEND_MAIL);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
	
	public ActionForward sendMailForCourseParticipants(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		if(SystemActions.isLoggedUser(request)) {
			int courseId = Integer.parseInt(request.getParameter("courseId"));
			Course course = facade.getCoursesById(courseId);
			
			if(CoursePermissions.userCanSendMailForCourseParticipants(request, course)) {
				List<String> emails = null;
				String subject = request.getParameter("subject");
				String message = request.getParameter("message");
				
				emails = facade.getEmailUsersOfCourse(courseId);
				
				facade.sendMail(emails, subject, message);
				
				request.setAttribute("course", course);
				request.setAttribute("subject", subject);
				request.setAttribute("message", message);
				request.setAttribute("success", Boolean.TRUE);
				
				forward = mapping.findForward(FORWARD_SHOW_VIEW_SEND_MAIL);
			} else {
				forward = SystemActions.showViewAccessDenied(mapping, form, request, response);
			}
		} else {
			forward = this.showViewWelcome(mapping, form, request, response);
		}
		
		return forward;
	}
}