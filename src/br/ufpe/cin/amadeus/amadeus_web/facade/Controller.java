/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.facade;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.mail.MessagingException;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import br.ufpe.cin.amadeus.amadeus_web.dao.DAOFactory;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.AmadeusDroidHistoricDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.ArchiveDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.CourseDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.ForumDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.GameDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.GroupsDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.HistoryLearningObjectDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.HomeworkDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.KeywordDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.LearningObjectDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.LogDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MaterialDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MaterialRequestDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MessageDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.ModuleDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.PersonRoleCourseDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.PollDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.RoleDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.VideoIrizDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.evaluation.EvaluationDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.evaluation.EvaluationRealizedDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.externallink.ExternalLinkDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.HibernateUtil;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.AccessInfoDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.OpenIDDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.PersonDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.ResumeDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.UserRequestDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.AmadeusDroidHistoric;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ExternalLink;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Groups;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.HistoryLearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Homework;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Log;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Message;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.PersonRoleCourse;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.RoleType;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Status;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Alternative;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Question;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionDiscursive;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionGap;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionMultiple;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionTrueFalse;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.EvaluationRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionDiscursiveRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.OpenID;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Resume;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.UserRequest;
import br.ufpe.cin.amadeus.amadeus_web.exception.CourseInvalidException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidCurrentPasswordException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidLogonException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidMaterialException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidUserException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidVideoException;
import br.ufpe.cin.amadeus.amadeus_web.exception.RequestException;
import br.ufpe.cin.amadeus.amadeus_web.struts.messages.Messages;
import br.ufpe.cin.amadeus.amadeus_web.syncronize.Archive;
import br.ufpe.cin.amadeus.amadeus_web.syncronize.LogVisualizacao;
import br.ufpe.cin.amadeus.amadeus_web.syncronize.StudentHaveGroup;
import br.ufpe.cin.amadeus.amadeus_web.syncronize.TimelineItem;
import br.ufpe.cin.amadeus.amadeus_web.util.Cryptography;
import br.ufpe.cin.amadeus.amadeus_web.util.DateConstructor;
import br.ufpe.cin.amadeus.amadeus_web.util.MailSender;
import br.ufpe.cin.amadeus.amadeus_web.util.RequestWrapper;

public class Controller {

	private static DAOFactory factory = null;

	private static Controller controller = null;

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	private Controller() {
		factory = DAOFactory.instance(DAOFactory.HIBERNATE);
		this.initializeSystem();
	}

	public Person insertPerson(Person p) throws InvalidUserException {
		Person person;
		try {
			if (this.existEmail(p.getEmail())) {
				throw new InvalidUserException("errors.email.alreadyExists");
			}
			if (this.existLogin(p.getAccessInfo().getLogin())) {
				throw new InvalidUserException("errors.login.alreadyExists");
			}
			
			try {
				PersonDAO personRep = factory.getPersonDAO();
				person = personRep.makePersistent(p);
			} catch (RuntimeException e) {
				throw e;
			}
			
		} catch (RuntimeException e) {
			throw e;
		}
		return person;
	}
	
	public void removeUser(int id) throws InvalidUserException{
		AccessInfoDAO accessInfoDAO = factory.getAccessInfoDAO();
		AccessInfo accessInfo = accessInfoDAO.findById(id, false);
		if (accessInfo == null) {
			throw new InvalidUserException("errors.email.alreadyExists");
		}
		accessInfo.setTypeProfile(ProfileType.INACTIVE);
		accessInfoDAO.merge(accessInfo);
	}
	
	public void allowUserLogon(int id) throws InvalidUserException{
		AccessInfoDAO accessInfoDAO = factory.getAccessInfoDAO();
		AccessInfo accessInfo = accessInfoDAO.findById(id, false);
		if (accessInfo == null){
			throw new InvalidUserException("errors.email.alreadyExists");
		}
		accessInfo.setTypeProfile(ProfileType.STUDENT);
		accessInfoDAO.merge(accessInfo);
	}

	public void validateCourseStepOne(Course c) throws CourseInvalidException {

		if (c.getName().equals("")) {
			throw new CourseInvalidException("errors.course.name.required");
		}
		if (this.courseNameExist(c)) {
			throw new CourseInvalidException("errors.course.name.existed");
		}
		if (c.getObjectives().equals("")) {
			throw new CourseInvalidException(
					"errors.course.objectives.required");
		}
		if (c.getContent().equals("")) {
			throw new CourseInvalidException("errors.course.content.required");
		}
		if (c.getMaxAmountStudents() <= 0) {
			throw new CourseInvalidException(
					"errors.course.maxStudents.invalid");
		}
	}
	
	public boolean courseNameExist(Course c) throws CourseInvalidException {
		boolean retorno = false;
		try {
			CourseDAO courseDAO = factory.getCourseDAO();		
			retorno = courseDAO.courseNameExist(c);
		} catch (RuntimeException e) {
			throw e;
		}
		return retorno;
	}

	public Course insertCourse(Course c) throws CourseInvalidException {
		Course result;
		try {
			if (c.getKeywords().isEmpty()) {
				throw new CourseInvalidException(
						"errors.course.keywords.required");
			}
			CourseDAO courseRep = factory.getCourseDAO();
			result = courseRep.makePersistent(c);
			LogDAO logRep = factory.getLogDAO();
			Log log = new Log();
			log.setDate(new Date());
			PersonDAO personDAO = factory.getPersonDAO();
			Person person = personDAO.findById(4, false);
			log.setIdUser(person);
			logRep.makePersistent(log);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}

	public void deleteCourse(Course course) throws CourseInvalidException {
		try {
			CourseDAO courseDAO = factory.getCourseDAO();
			courseDAO.makeTransient(course);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public Course updateCourse(Course c) {
		Course result;
		try {
			CourseDAO courseRep = factory.getCourseDAO();			
			result = courseRep.makePersistent(c);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}

	public boolean existLogin(String login) {
		boolean result = false;
		try {
			List<AccessInfo> search = new ArrayList<AccessInfo>();
			AccessInfoDAO aiRep = factory.getAccessInfoDAO();
			search = aiRep.getUsersByLogin(login);
			if (!search.isEmpty()){
				result = true;
			}
		} catch (RuntimeException e) {
			
		}
		return result;
	}

	public boolean existEmail(String email) {
		try {
			Person pCrit = new Person();
			PersonDAO personRep = factory.getPersonDAO();
			String[] excludes = new String[1]; 
			excludes[0] = "gender";
			pCrit.setEmail(email);
			pCrit = personRep.findUniqueByExample(pCrit, excludes);
			return (pCrit != null);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public boolean existCPF(String cpf) {
		try {
			Person pCrit = new Person();
			PersonDAO personRep = factory.getPersonDAO();
			String[] excludes = new String[1];
			excludes[0] = "gender";
			pCrit.setCpf(cpf);
			pCrit = personRep.findUniqueByExample(pCrit, excludes);
			return (pCrit != null);
		} catch (RuntimeException e) {
			throw e;
		}
	}
		
	public List<Keyword> getMostPopularKeywords() {
		List<Keyword> results = new ArrayList<Keyword>();
		try {
			KeywordDAO keywordRep = factory.getKeywordDAO();
			results = keywordRep.getMostPopularKeywords();
		} catch (RuntimeException e) {
			throw e;
		}
		return results;
	}

	public Keyword insertKeyword(Keyword keyword) {
		Keyword result = null;
		try {
			KeywordDAO keywordRep = factory.getKeywordDAO();
			result = keywordRep.makePersistent(keyword);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
	public void removeKeyword(Keyword keyword) {
		try {
			KeywordDAO keywordRep = factory.getKeywordDAO();
			keywordRep.makeTransient(keyword);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public boolean existKeyword(Keyword keyword) {
		boolean result = false;
		try {
			KeywordDAO keywordRep = factory.getKeywordDAO();
			String[] excludes = new String[3]; 
			excludes[0] = "id";
			excludes[1] = "popularity";
			excludes[2] = "keywordGroup";
			Keyword keywordResult = keywordRep.findUniqueByExample(keyword, excludes);
			if(keywordResult != null){
				result = true;
			}
			
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}

	public Person getAtributesFromEmail(String email) {
		Person pCrit = new Person();
		PersonDAO personRep = factory.getPersonDAO();
		String[] excludes = new String[1]; 
		excludes[0] = "gender";
		pCrit.setEmail(email);
		return pCrit = personRep.findUniqueByExample(pCrit, excludes);
	}
	
	public Person getPersonByID(int id) {
		Person person = null;
		try {
			PersonDAO personDAO = factory.getPersonDAO();
			person = personDAO.findById(id, false);
		} catch (RuntimeException e) {
			throw e;
		}
		return person;
	}
	
	public Person getPersonByLogin(String login){
		Person person = null;
		try {
			PersonDAO personDAO = factory.getPersonDAO();
			person = personDAO.getPersonByLogin(login);
		} catch (RuntimeException e) {
			throw e;
		}
		return person;
	}
	
	public Person getPersonByUserName(String userName){
		Person person = null;
		try {
			PersonDAO personDAO = factory.getPersonDAO();
			person = personDAO.getPersonByUserName(userName);
		} catch (RuntimeException e) {
			throw e;
		}
		return person;
	}


	public String remindPassword(String email) throws MessagingException,
			IOException {

		try {
			Person pAtt = this.getAtributesFromEmail(email);

			String subject = Messages.getString("remindPassword.emailSubject")
					+ "\n";// email // subject
			String finalText = Messages
						.getString("remindPassword.emailIntroduction")
						+ " "
						+ pAtt.getName()
						+ "\n\n" // User name
						+ Messages.getString("remindPassword.emailText")
						+ ":\n" // text message
						+ Messages.getString("remindPassword.emailLogin")
						+ ": "
						+ pAtt.getAccessInfo().getLogin()
						+ "\n"// user login
						+ Messages.getString("remindPassword.emailPassword")
						+ ": "
						+ Cryptography.decrypt(pAtt.getAccessInfo().getPassword());
			
			MailSender.sendMail(email, subject, finalText);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return email;
	}

	public void sendMail(List<String> emails, String subject, String message){
		message = RequestWrapper.cleanXSSReverse(message);
		MailSender.sendMail(emails, subject, message);
	}
	
	public void sendMail(String to, String subject, String message) {	
		message = RequestWrapper.cleanXSSReverse(message);
		MailSender.sendMail(to, subject, message);
	}
	
	public List<Course> getCoursesByKeyword(String keyword) {
		List<Course> results = null;
		try {
			CourseDAO courseRep = factory.getCourseDAO();
			KeywordDAO keywordRep = factory.getKeywordDAO();
			
			Keyword key = keywordRep.getKeywordByName(keyword);
			
			results = courseRep.getCoursesByKeyword(key);
		} catch (RuntimeException e) {
			throw e;
		}
		return results;
	}

	public List<Course> getCoursesByRule(String rule) {
		List<Course> courses = getCoursesByName(rule);
		add(courses, getCoursesByContent(rule));
		add(courses, getCoursesByKeyword(rule));
		add(courses, getCoursesByProfessor(rule));
		add(courses, getCoursesByObjectives(rule));
		return courses;
	}

	/* implementando agora.. */
	public List<Course> getCoursesByAdvancedRule(String name, String professorName, Date initialDate, Date finalDate) {
		List<Course> result = factory.getCourseDAO().getCoursesByAdvancedRule(name, professorName, initialDate, finalDate);
		return result;
	}

	public List<Course>[] getClassifiedCoursesByRule(String rule) {
		List<Course>[] courses = classifyCoursesByDate(getCoursesByRule(rule));
		return courses;
	}

	public List<Course>[] getClassifiedCoursesByKeyword(String keyword) {
		List<Course>[] courses = classifyCoursesByDate(getCoursesByKeyword(keyword));
		return courses;
	}

	private List<Course> getCoursesByName(String name) {
		List<Course> results = null;
		try {
			CourseDAO courseRep = factory.getCourseDAO();
			results = courseRep.getCoursesByName(name);
		} catch (RuntimeException e) {
			throw e;
		}
		return results;
	}

	private List<Course> getCoursesByContent(String content) {
		List<Course> results = null;
		try {
			CourseDAO courseRep = factory.getCourseDAO();
			results = courseRep.getCoursesByContent(content);
		} catch (RuntimeException e) {
			throw e;
		}
		return results;
	}

	private List<Course> getCoursesByObjectives(String objectives) {
		List<Course> results = null;
		try {
			CourseDAO courseRep = factory.getCourseDAO();
			results = courseRep.getCoursesByObjectives(objectives);
		} catch (RuntimeException e) {
			throw e;
		}
		return results;
	}

	private List<Course> getCoursesByProfessor(String professorName) {
		List<Course> results = null;
		try {
			CourseDAO courseRep = factory.getCourseDAO();
			PersonDAO personRep = factory.getPersonDAO();
			List<Person> professors = personRep.getTeachersByName(professorName);
			results = courseRep.getCoursesByProfessors(professors);
		} catch (RuntimeException e) {
			throw e;
		}
		return results;
	}

	public List<Person> getTeachersByCourse(Course course) {
		try {
			List<Person> professores = new ArrayList<Person>();

			PersonRoleCourseDAO prcRep = factory.getPersonRoleCourseDAO();

			Role teacherRole = this.getRole(RoleType.TEACHER);
			
			professores = prcRep.searchUsersByCoursesAndRole(course, teacherRole);
			return professores;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public List<Keyword> getKeywordsByCourse(Course course) {
		try {
			List<Keyword> keywords = new ArrayList<Keyword>();
			KeywordDAO keywordsRep = factory.getKeywordDAO();
			keywords = keywordsRep.getKeywordsByCourse(course);
	
			return keywords;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	private void add(List<Course> target, List<Course> toBeAdded) {
		for (Course c : toBeAdded)
			if (!target.contains(c))
				target.add(c);
	}

	
	/** 
	 * @param toBeClassified the courses list to be classified
	 * 
	 * @return result contains 4 lists of courses:
	 * 		the first one is organized by initial registration date;
	 * 		the second one is organized by final registration date; 
	 * 		the third one is organized by initial course date;
	 * 		and the fourth one is organized by final course date.
	 *  
	 */
	@SuppressWarnings("unchecked")
	private List<Course>[] classifyCoursesByDate(List<Course> toBeClassified) {
		List<Course>[] result = new ArrayList[4];
		for (int i = 0; i < result.length; i++)
			result[i] = new ArrayList<Course>();
		Date today = DateConstructor.today();
		for (Course course : toBeClassified) {
			course.setNumberOfStudentsInCourse(this
					.getNumberOfStudentsInCourse(course));
			if (today.compareTo(course.getInitialRegistrationDate())<=0) {  
				if (!result[0].contains(course)) {
					result[0].add(course);
				}
			}
			if (today.compareTo(course.getFinalRegistrationDate()) <= 0 &&
					 today.compareTo(course.getInitialRegistrationDate())>=0) {  
				if (!result[1].contains(course)) {
					result[1].add(course);
				}
			}

			if (today.compareTo(course.getInitialCourseDate()) >= 0
					&& today.compareTo(course.getFinalCourseDate()) <= 0)
				if (!result[2].contains(course)) {
					result[2].add(course);
				}

			if (today.compareTo(course.getFinalCourseDate()) > 0)
				if (!result[3].contains(course)) {
					result[3].add(course);
				}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course>[][] classifyCoursesByPage(List<Course>[] toBeClassified, int numberOfPages) {
		List<Course>[][] result = new ArrayList[4][numberOfPages];
		
		for(int i=0; i<numberOfPages; i++){
			result[0][i] = new ArrayList<Course>();
			result[1][i] = new ArrayList<Course>();
			result[2][i] = new ArrayList<Course>();
			result[3][i] = new ArrayList<Course>();
		}
		
		Iterator iList0 = toBeClassified[0].iterator();
		Iterator iList1 = toBeClassified[1].iterator();
		Iterator iList2 = toBeClassified[2].iterator();
		Iterator iList3 = toBeClassified[3].iterator();
		
		int PageNum = 0;
		int size = 0;
		while(iList0.hasNext()){
			size = result[0][PageNum].size();
			if(size == 10){
				PageNum++;
			} else {
				result[0][PageNum].add((Course)iList0.next());
			}
		}
		while(iList1.hasNext()){
			size = result[1][PageNum].size() + result[0][PageNum].size();
			if(size == 10){
				PageNum++;
			} else {
				result[1][PageNum].add((Course)iList1.next());
			}
		}
		while(iList2.hasNext()){
			size = result[0][PageNum].size() + result[1][PageNum].size() + result[2][PageNum].size();
			if(size == 10){
				PageNum++;
			} else {
				result[2][PageNum].add((Course)iList2.next());
			}
		}
		while(iList3.hasNext()){
			size = result[0][PageNum].size() + result[1][PageNum].size() + result[2][PageNum].size()+ result[3][PageNum].size();
			if(size == 10){
				PageNum++;
			} else {
				result[3][PageNum].add((Course)iList3.next());
			}
		}
		
		return result;
	}
	
	public AccessInfo searchUserByLogin(String login) {

		String[] excludes = new String[] { "id", "password", "typeProfile" };

		AccessInfo infoUser = new AccessInfo();
		infoUser.setLogin(login);

		AccessInfoDAO access = factory.getAccessInfoDAO();
		return (access.findUniqueByExample(infoUser, excludes));
	}

	public Person editUser(Person person) throws Exception {
			
		int yearNowadays = Calendar.getInstance().get(Calendar.YEAR);
		
		int yearTitulation = 0;
		if (person.getResume().getYear() != null) {
			yearTitulation = person.getResume().getYear();
		}
		
		
		try {
						
			if (person.getResume().getYear() == null || yearTitulation <= yearNowadays) {			

				PersonDAO personDAO = factory.getPersonDAO();
				personDAO.merge(person);
			} else {
				throw new Exception("errors.yearTitulation");
			}
			
		} catch (RuntimeException e) {
			throw e;
		}
		return person;
	}
	
	private boolean verifyEmail(Person person){
		boolean result = false;
		
		Person personBD = this.getAtributesFromEmail(person.getEmail());
		
		if(personBD == null || personBD.getId() == person.getId()) {
			result = true;
		}
			
		return result;
	}
	
	public boolean verifyEmail(int personId, String email){
		boolean result = false;
		
		Person personBD = this.getAtributesFromEmail(email);
		
		if(personId != 0) {
			if(personBD == null || personBD.getId() == personId) {
				result = true;
			}
		} else {
			if(personBD == null) {
				result = true;
			}
		}
			
		return result;
	}
	
	public boolean validateUser(AccessInfo accessInfo){
		boolean validate = true;
		
		AccessInfo aInfo = null;
		try {
			if (accessInfo.getLogin() != null && accessInfo.getPassword() != null) {
				aInfo = this.searchUserByLogin(accessInfo.getLogin());
				if(aInfo != null)
					if(aInfo.getTypeProfile() == (ProfileType.INACTIVE)){
						validate = false;
					}
				if ((aInfo == null)
						|| (!accessInfo.getPassword().equals(aInfo.getPassword())))
					validate = false;
					
			}
		} catch (RuntimeException e) {
			throw e;
		}
		
		return validate;
	}

	public AccessInfo logon(AccessInfo accesssInfo)
			throws InvalidLogonException {
		AccessInfo aInfo = null;
		try {
			if (accesssInfo.getLogin() != null && accesssInfo.getPassword() != null) {
				aInfo = this.searchUserByLogin(accesssInfo.getLogin());
				if(aInfo != null)
					if(aInfo.getTypeProfile() == (ProfileType.INACTIVE)){
						throw new InvalidLogonException();
					}
				if ((aInfo == null)
						|| (!accesssInfo.getPassword().equals(aInfo.getPassword())))
					throw new InvalidLogonException();
					
			}
		} catch (RuntimeException e) {
			throw e;
		}
		return aInfo;

	}

	@SuppressWarnings("unchecked")
	public int getNumberOfPendingTasks(AccessInfo userInfo) {
		int ret = 0;
		try {
			ProfileType profile = userInfo.getTypeProfile();
		
			if (profile == ProfileType.ADMIN) {
	
				UserRequestDAO request = factory.getUserRequestDAO();
				
				List<UserRequest> userRequestList = HibernateUtil.getSessionFactory().getCurrentSession().
					createSQLQuery("SELECT * from userRequest where " +
						"STATUS_TYPE = "+Status.WAITING.ordinal()+" and teachingRequest = "+true).addEntity(UserRequest.class).list();
			
				Role teacherRole = searchRoleByConstant(RoleType.TEACHER);
	
				int userRequestListAssistants  = 
					request.getNumberOfRequestsToProfessor(userInfo, teacherRole);
	
				ret = userRequestList.size() + userRequestListAssistants;
	
			} else if (profile == ProfileType.PROFESSOR) {
	
				UserRequestDAO request = factory.getUserRequestDAO();
	
				Role teacherRole = searchRoleByConstant(RoleType.TEACHER);
	
				ret = request.getNumberOfRequestsToProfessor(userInfo, teacherRole);
	
			} 
		} catch (RuntimeException e) {
			throw e;
		}	
		return ret;
	}

	/*
	private int getDoneHomeworks(AccessInfo userInfo) {

		DeliveryDAO deliveryRequest = factory.getDeliveryDAO();

		Role studentRole = this.searchRoleByConstant(RoleType.STUDENT);

		return deliveryRequest.getNumberOfDoneHomeworks(userInfo, studentRole);
	}
	
	private int getTotalHomeworks(AccessInfo userInfo) {

		HomeworkDAO homeworkRequest = factory.getHomeworkDAO();

		Role studentRole = this.searchRoleByConstant(RoleType.STUDENT);

		return homeworkRequest.getTotalUserHomeworks(userInfo, studentRole);
	}
	 */

	public Role searchRoleByConstant(RoleType roleType) {
		Role exampleRole = new Role();
		try {
			RoleDAO roleAccess = factory.getRoleDAO();
			exampleRole.setRoleType(roleType);
			String excludes[] = new String[] { "id" };
			exampleRole = roleAccess.findUniqueByExample(exampleRole, excludes);
		} catch (RuntimeException e) {
			throw e;
		}
		return exampleRole;
	}

	public AccessInfo searchUserById(int id) {
		AccessInfo accessInto = null;
		try {
			AccessInfoDAO access = factory.getAccessInfoDAO();
			accessInto = access.findById(id, false);
		} catch (RuntimeException e) {
			throw e;
		}
		return accessInto;
	}

	public List<Course> searchCoursesByAccessInfo(AccessInfo userInfo) {
		List<Course> courses = null;
		try {
			CourseDAO courseRequest = factory.getCourseDAO();
			courses = courseRequest.searchCoursesByUser(userInfo);
		} catch (RuntimeException e) {
			throw e;
		}
		
		return courses;
	}
	
	public List<br.ufpe.cin.amadeus.amadeus_web.syncronize.PersonRoleCourse> getStudentByUser(AccessInfo userInfo){
		List<PersonRoleCourse> prcList;
		List<br.ufpe.cin.amadeus.amadeus_web.syncronize.PersonRoleCourse> newPrcList = 
			new ArrayList<br.ufpe.cin.amadeus.amadeus_web.syncronize.PersonRoleCourse>();
		try {
			PersonRoleCourseDAO prcDAO = factory.getPersonRoleCourseDAO();
			prcList = prcDAO.getStudentByUser(userInfo);
			for (PersonRoleCourse prc : prcList) {
				br.ufpe.cin.amadeus.amadeus_web.syncronize.PersonRoleCourse obj = new br.ufpe.cin.amadeus.amadeus_web.syncronize.PersonRoleCourse();
				obj.setId(prc.getId());
				obj.setCourse(new br.ufpe.cin.amadeus.amadeus_web.syncronize.Course());
				obj.getCourse().setId(prc.getCourse().getId());
				obj.setPerson(new br.ufpe.cin.amadeus.amadeus_web.syncronize.Person());
				obj.getPerson().setId(prc.getPerson().getId());
				obj.getPerson().setName(prc.getPerson().getName());
				obj.setRole(new br.ufpe.cin.amadeus.amadeus_web.syncronize.Role());
				obj.getRole().setId(prc.getRole().getId());
				
				if (prc.getRole().getRoleType().equals(RoleType.ADMIN)) {
					obj.getRole().setRoleType(0);
				}else if (prc.getRole().getRoleType().equals(RoleType.STUDENT)) {
					obj.getRole().setRoleType(1);
				}else if (prc.getRole().getRoleType().equals(RoleType.ASSISTANT)) {
					obj.getRole().setRoleType(2);
				}else if (prc.getRole().getRoleType().equals(RoleType.TEACHER)) {
					obj.getRole().setRoleType(3);
				}
				newPrcList.add(obj);
			}
			
			return newPrcList;
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public Archive getArchiveByMaterial(int material_id){
		
		ArchiveDAO arcDao = factory.getArchiveDAO();
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Archive oldArchive = arcDao.getArchiveByMaterial(material_id);
		Archive newArchive = new Archive();
		newArchive.setId(oldArchive.getId());
		newArchive.setArchive(oldArchive.getArchive());
		
		return newArchive;
	}
	
	public Material findMaterialById(int material_id) {
		MaterialDAO matDAO = factory.getMaterialDAO();
		return matDAO.getMaterialByID(material_id);
	}
		
	public List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Course> getCoursesByUser(AccessInfo userInfo) {
		List<Course> courses = null;
		List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Course> newListCourse = new ArrayList<br.ufpe.cin.amadeus.amadeus_web.syncronize.Course>();
		List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Material> newListMaterial = null;
		List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum> newListForum = null;
		br.ufpe.cin.amadeus.amadeus_web.syncronize.AccessInfo newAccessInfo = null;
		br.ufpe.cin.amadeus.amadeus_web.syncronize.Person newPerson = null;
		
		try {
			CourseDAO courseRequest = factory.getCourseDAO();
			courses = courseRequest.getCoursesByUser(userInfo);
			for (Course course : courses) {
				newListMaterial = new ArrayList<br.ufpe.cin.amadeus.amadeus_web.syncronize.Material>();
				newListForum = new ArrayList<br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum>();
				
				br.ufpe.cin.amadeus.amadeus_web.syncronize.Course c = new br.ufpe.cin.amadeus.amadeus_web.syncronize.Course();
				c.setContent(course.getContent());
				c.setFinalCourseDate(course.getFinalCourseDate());
				c.setFinalRegistrationDate(course.getFinalRegistrationDate());
				c.setId(course.getId());
				c.setInitialCourseDate(course.getInitialCourseDate());
				c.setInitialRegistrationDate(course.getInitialRegistrationDate());
				c.setMaxAmountStudents(course.getMaxAmountStudents());
				c.setName(course.getName());
				c.setNumberOfStudentsInCourse(course.getNumberOfStudentsInCourse());
				c.setObjectives(course.getObjectives());
				
				newPerson = new br.ufpe.cin.amadeus.amadeus_web.syncronize.Person();
				newPerson.setBirthDate(course.getProfessor().getBirthDate());
				newPerson.setCity(course.getProfessor().getCity());
				newPerson.setCpf(course.getProfessor().getCpf());
				newPerson.setEmail(course.getProfessor().getEmail());
				newPerson.setGender(course.getProfessor().getGender());
				newPerson.setId(course.getProfessor().getId());
				newPerson.setName(course.getProfessor().getName());
				newPerson.setPhoneNumber(course.getProfessor().getPhoneNumber());
				newPerson.setState(course.getProfessor().getState());
				newAccessInfo = new br.ufpe.cin.amadeus.amadeus_web.syncronize.AccessInfo();
				newAccessInfo.setId(course.getProfessor().getAccessInfo().getId());
				newAccessInfo.setPassword(course.getProfessor().getAccessInfo().getPassword());
				newAccessInfo.setLogin(course.getProfessor().getAccessInfo().getLogin());
												
				newPerson.setAccessInfo(newAccessInfo);
				c.setProfessor(newPerson);
				
				List<Module> modules = course.getModules();
				for (Module module : modules) {
					
					List<Material> materials = module.getMaterials();
					for (Material material : materials) {
						br.ufpe.cin.amadeus.amadeus_web.syncronize.Material mat = new br.ufpe.cin.amadeus.amadeus_web.syncronize.Material();
						mat.setId(material.getId());
						mat.setGrade(material.getGrade());
						mat.setExtension(material.getExtension());
						mat.setCreationDate(material.getCreationDate());
						mat.setCorrectedDate(material.getCorrectedDate());
						mat.setArchiveName(material.getArchiveName());
						mat.setCourse_id(c.getId());
						
						newListMaterial.add(mat);
					}
					
					List<Forum> foruns = module.getForums();
					for (Forum obj : foruns) {
						br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum forum = new br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum();
						forum.setId(obj.getId());
						forum.setName(obj.getName());
						forum.setDescription(obj.getDescription());
						forum.setCreationDate(obj.getCreationDate());
						forum.setModule_id(module.getId());
						
						List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Message> newListMessage = 
							new ArrayList<br.ufpe.cin.amadeus.amadeus_web.syncronize.Message>();
						List<Message> messages = obj.getMessages();
						for (Message obj2 : messages) {
							br.ufpe.cin.amadeus.amadeus_web.syncronize.Message message = new br.ufpe.cin.amadeus.amadeus_web.syncronize.Message();
							message.setId(obj2.getId());
							message.setBody(obj2.getBody());
							message.setDate(obj2.getDate());
							message.setForum_id(obj2.getForum().getId());
							message.setPersonName(obj2.getAuthor().getName());
							message.setPersonLogin(obj2.getAuthor().getAccessInfo().getLogin());
							
							newListMessage.add(message);
						}
						
						forum.setMessages(newListMessage);
						newListForum.add(forum);
					}
					
				}
				
				c.setMaterials(newListMaterial);
				c.setForuns(newListForum);
				newListCourse.add(c);
			}
			
		} catch (RuntimeException e) {
			throw e;
		}
		
		return newListCourse;
	}
	
	private void initializeSystem() {
		try {
			RoleDAO roleRep = factory.getRoleDAO();
			List<Role> roles = roleRep.findAll();
			if (roles.isEmpty()) {
				Role student = new Role();
				Role professor = new Role();
				Role assistant = new Role();
	
				student.setRoleType(RoleType.STUDENT);
				professor.setRoleType(RoleType.TEACHER);
				assistant.setRoleType(RoleType.ASSISTANT);
	
				roleRep.makePersistent(student);
				roleRep.makePersistent(professor);
				roleRep.makePersistent(assistant);
			}
		
		} catch (RuntimeException e) {
			throw e;
		}
			
			if (!existLogin("admin")) {
				AccessInfo ai = new AccessInfo();
				Person person = new Person();
				ai.setLogin("admin");
				ai.setPassword("admin");
				ai.setTypeProfile(ProfileType.ADMIN);
				person.setName("Administrador");
				person.setEmail("admin@amadeus.org.br");
				person.setAccessInfo(ai);
				ai.setPerson(person);
				try {
					this.insertPerson(person);
				} catch (InvalidUserException e) {
					e.printStackTrace();
				}
			}
	}

	public AccessInfo updateUser(AccessInfo ai) {
		AccessInfo result;
		try {
			AccessInfoDAO aiRep = factory.getAccessInfoDAO();
			result = aiRep.makePersistent(ai);
			return result;
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public void updateResume(Resume r) {
		try {
			ResumeDAO resumeRep = factory.getResumeDAO();
			resumeRep.makePersistent(r);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public void insertUserRequest(UserRequest userR) {

		try {
			UserRequestDAO userRep = factory.getUserRequestDAO();
			userRep.makePersistent(userR);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	private List<UserRequest> searchTeachingRequest(int personId) {
		UserRequestDAO request = factory.getUserRequestDAO();
		return request.searchTeachingRequest(personId);
	}
	
	public boolean canRequestTeaching(Person person) {

		List<UserRequest> results = this.searchTeachingRequest(person.getId());

			if (results != null) {

				for (UserRequest request : results) {

					if ((request.getStatus() == Status.WAITING)
							|| (request.getStatus() == Status.APPROVED))
						return false;

				}

			}

			return true;
	}

	public void editPassword(AccessInfo accessInfo, String currentPassword,
			String newPassword, String newPasswordConfirmation)
			throws InvalidCurrentPasswordException {
		try {
			AccessInfoDAO accessInfoDAO = factory.getAccessInfoDAO();
			
			try {
				currentPassword = Cryptography.encrypt(currentPassword);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			if (accessInfo.getPassword().equals(currentPassword)) {
				accessInfo.setPassword(newPassword);
				accessInfoDAO.makePersistent(accessInfo);
			} else {
				throw new InvalidCurrentPasswordException();
			}
		} catch (RuntimeException e) {
			throw e;
		}

	}

	public int getNumberOfStudentsInCourse(Course c) {
		int result = 0;
		try {
			PersonRoleCourseDAO prcRep = factory.getPersonRoleCourseDAO();
			Role studentRole = this.searchRoleByConstant(RoleType.STUDENT);
			result = prcRep.getNumberOfStudentsInCourse(c,studentRole);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
	private Role getRole(RoleType role) {
		Role roleCrit = new Role();
		try {
			RoleDAO roleRep = factory.getRoleDAO();
			roleCrit.setRoleType(role);
			roleCrit = roleRep.findUniqueByExample(roleCrit);
		} catch (RuntimeException e) {
			throw e;
		}
		return roleCrit;
	}

	public Course getCoursesById(int courseId) {
		Course result;
		try {
			CourseDAO courseDAO = factory.getCourseDAO();
			result = courseDAO.findById(courseId, false);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		
		CourseDAO courseDAO = factory.getCourseDAO();
		courses = courseDAO.findAll();
		
		return courses;
		
	}
	
	public Module insertModule(Module module) {
		Module result;
		try {
			ModuleDAO moduleRep = factory.getModuleDAO();
			result = moduleRep.makePersistent(module);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
	public AmadeusDroidHistoric insertSocialHitory(AmadeusDroidHistoric historic){
		AmadeusDroidHistoric result;
		try {
			AmadeusDroidHistoricDAO historyDAO = factory.getAmadeusDroidHistoricDAO();
			result = historyDAO.makePersistent(historic);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
	public List<AmadeusDroidHistoric> getSocialHistory(){
		List<AmadeusDroidHistoric> result;
		try {
			AmadeusDroidHistoricDAO historyDAO = factory.getAmadeusDroidHistoricDAO();
			result = historyDAO.getHistoric();
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}

	public void updateModule(Module module) {
		try {
			ModuleDAO moduleRep = factory.getModuleDAO();
			moduleRep.makePersistent(module);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void deleteModule(Module module) {
		try {
			ModuleDAO moduleRep = factory.getModuleDAO();
			moduleRep.makeTransient(module);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public Poll getPollById(int id) {
		Poll result;
		PollDAO pollDAO = factory.getPollDAO();
		result = pollDAO.findById(id, false);
		return result;
	}
	
	public Homework getHomeworkById(int id) {
		Homework result;
		HomeworkDAO homeworkDAO = factory.getHomeworkDAO();
		result = homeworkDAO.findById(id, false);
		return result;
	}
	
	public Material getMaterialById(int id) {
		Material result;
		MaterialDAO materialDAO = factory.getMaterialDAO();
		result = materialDAO.findById(id, false);
		return result; 
	}
	
	public MaterialRequest getMaterialRequestById(int id) {
		MaterialRequest result;
		MaterialRequestDAO materialDAO = factory.getMaterialRequestDAO();
		result = materialDAO.findById(id, false); 
		return result;
	}
	
	public Module getModuleById(int id) {
		Module result;
		try {
			ModuleDAO moduleDAO = factory.getModuleDAO();
			result = moduleDAO.findById(id, true);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
	public boolean canRegisterUser(AccessInfo user, Course course) {
		Date today = DateConstructor.today();
		if (today.compareTo(course.getInitialRegistrationDate()) < 0 ||
				today.compareTo(course.getFinalRegistrationDate()) > 0 || 
				user.getTypeProfile() == ProfileType.ADMIN)
			return false;
		
		try {
			Role role = null;
			role = this.searchRoleByConstant(RoleType.STUDENT);
			
			if(this.isRegistered(user.getPerson(),course))
	    		return false;
			
			if(!this.hasVacancy(course,role))
			return false;
		
		} catch (RuntimeException e) {
			throw e;
		}
    	return true;
	}
	
	public boolean hasVacancy(Course course, Role role){
		List<PersonRoleCourse> results = new ArrayList<PersonRoleCourse>();
		
		try {
			PersonRoleCourseDAO prcDAO = factory.getPersonRoleCourseDAO();
			results = prcDAO.findUsersRegisteredInCourseByRole(course,role);
		} catch (RuntimeException e) {
			throw e;
		}
		
		int registereds = results.size();
		if(results.isEmpty() || registereds < course.getMaxAmountStudents()){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isRegistered(Person user, Course course){
		boolean result = false;
		try {
			PersonRoleCourseDAO prcDAO = factory.getPersonRoleCourseDAO();
			result = prcDAO.isRegisteredUser(user, course);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
	public List<Person> listStudentsByCourse(Course course) {
		try {
			List<Person> students = new ArrayList<Person>();

			PersonRoleCourseDAO prcRep = factory.getPersonRoleCourseDAO();

			Role studentRole = this.getRole(RoleType.STUDENT);
			
			students = prcRep.searchUsersByCoursesAndRole(course, studentRole);
			return students;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public List<Person> listAssistantsByCourse(Course course) {
		try{
			List<Person> assistants = new ArrayList<Person>();
			
			PersonRoleCourseDAO prcRep = factory.getPersonRoleCourseDAO();
	
			Role studentRole = this.getRole(RoleType.ASSISTANT);
			
			assistants = prcRep.searchUsersByCoursesAndRole(course, studentRole);
			return assistants;
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public void confirmRegistry(AccessInfo accinfo) {
 		String subject = "Confirma��o de cadastro na Plataforma de Educa��o a Dist�ncia Amadeus";
 		String message = "Parab�ns "+ accinfo.getPerson().getName() +",\n";
 		message += "Seu cadastro na plataforma de de Educa��o  a dist�ncia Amadeus foi realizado com sucesso. \n";
 		message += "Seguem seus dados cadastrais: \n";
 		message += "\n";
 		message += "Nome: " + accinfo.getPerson().getName() + "\n";
 		message += "Login: " + accinfo.getLogin() + "\n";
 		try {
			message += "Senha: " + Cryptography.decrypt(accinfo.getPassword()) + "\n";
		} catch (Exception e) {
			e.printStackTrace();
		} 
 		message += "\n";
 		message += "Bom aprendizado!";
 		try {
 			MailSender.sendMail(accinfo, subject, message);
 		} catch (MessagingException me) {}
 	}

	public Game updateGame(Game game) {
		try {
			Game result;
			GameDAO gameRep = factory.getGameDAO();

			result = gameRep.makePersistent(game);

			return result;
		} catch (RuntimeException e) {
			throw e;
		}
		
	}

	public Game getGameById(int gameId) {
		GameDAO access = factory.getGameDAO();
		return access.findById(gameId, false);
	}
	
	public void updatePersonRoleCourse(PersonRoleCourse personRoleCourse) {
		try {
			PersonRoleCourseDAO courseRep = factory.getPersonRoleCourseDAO();
			courseRep.makePersistent(personRoleCourse);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void requestAssistance(Course course,
			Resume resume, String interest) throws RequestException {
		
			List<UserRequest> userRequest = searchAssistanceRequest(resume.getPerson(), course);
			
			if (userRequest.size() != 0) {
			
				for (UserRequest useRequestElement : userRequest) {
				
					useRequestElement.setCourse(course);
					useRequestElement.setInterest(interest);
					useRequestElement.setPerson(resume.getPerson());
					useRequestElement.setStatus(Status.WAITING);
					useRequestElement.setTeachingRequest(false);
					
					useRequestElement.setUserRequestDate(new Date());
					
					updateResume(resume);
					updateUserRequest(useRequestElement);
					
					return;
				}
				
			}
			
			UserRequest userR = new UserRequest();
			userR.setCourse(course);
			userR.setInterest(interest);
			userR.setPerson(resume.getPerson());
			userR.setStatus(Status.WAITING);
			userR.setTeachingRequest(false);

			userR.setUserRequestDate(new Date());

			updateResume(resume);
			course.getUserRequest().add(userR);
			updateCourse(course);
	}
	
	public boolean isValidationDataToRegisterCourse(Course course) {
		Date today = new Date();
		Calendar calendarToday = new GregorianCalendar();
		calendarToday.setTime(today);

		Calendar calendarInitialRegistrationDate = new GregorianCalendar();
		calendarInitialRegistrationDate.setTime(course
				.getInitialRegistrationDate());

		Calendar calendarFinalCourseDate = new GregorianCalendar();
		calendarFinalCourseDate.setTime(course.getFinalCourseDate());

		if ((calendarToday.getTime().compareTo(
				calendarInitialRegistrationDate.getTime()) < 0)
				|| (calendarToday.getTime().compareTo(
						calendarFinalCourseDate.getTime()) > 0))
			return false;

		return true;
		
		
		
	}

	public List<UserRequest> searchAssistanceRequest(Person person, Course course) {
		UserRequestDAO request = factory.getUserRequestDAO();
		return request.searchAssistanceRequest(person, course);
	}

	public List<PersonRoleCourse> getPossibleTeacherOrAssistantsInCourse(Person person, Course course) {

		Role roleT = searchRoleByConstant(RoleType.TEACHER);
		Role roleA = searchRoleByConstant(RoleType.ASSISTANT);

		PersonRoleCourseDAO request = factory.getPersonRoleCourseDAO();
		return request.getPossibleTeacherOrAssistantsInCourse(person,
				roleA, roleT, course);

	}
	
	public boolean isStudent(Person person, Course course){
		Role role = searchRoleByConstant(RoleType.STUDENT);
		PersonRoleCourseDAO request = factory.getPersonRoleCourseDAO();
		return request.isStudent(person, course, role);
	}
	
	public boolean canAssistanceRequest(Person person, Course course) {
		
		List<PersonRoleCourse> resultsAssistants = this.getPossibleTeacherOrAssistantsInCourse(person, course);
		
		if (resultsAssistants.size() != 0) {
						
			for (PersonRoleCourse requestA : resultsAssistants) {
				return false;	
			}
		}
		
		List<UserRequest> results = searchAssistanceRequest(person, course);
	
		if (results.size() != 0) {

			for (UserRequest request : results) {
				if (this.isValidationDataToRegisterCourse(request.getCourse()))
					if ( request.getStatus() == Status.WAITING || request.getStatus() == Status.APPROVED )
						return false;			
			}
			return true;		
		}

		if (this.isValidationDataToRegisterCourse(course) && (isStudent(person, course)))
			return true;	
			
		return false;
	}
	
	public List<UserRequest> getPossibleTeachers() {
		UserRequestDAO request = factory.getUserRequestDAO();
		return request.getPossibleTeachers();
	}
	
	public List<UserRequest> getPossibleAssistants(AccessInfo accessInfo){
		Role roleTeacher = searchRoleByConstant(RoleType.TEACHER);
		UserRequestDAO request = factory.getUserRequestDAO();
		List<UserRequest> teacherRequests = request.getPossibleAssistants(accessInfo, 
				roleTeacher);
		return teacherRequests;
	}
	
	public void deleteUserRequest(UserRequest userRequest){
		try {
			UserRequestDAO userRequestRep = factory.getUserRequestDAO();
			userRequestRep.makeTransient(userRequest);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public void updateUserRequest(UserRequest userRequest) {
		try {
			UserRequestDAO userRequestRep = factory.getUserRequestDAO();
			userRequestRep.makePersistent(userRequest);
		} catch (RuntimeException e) {			
			throw e;
		}
	}
	
	public void approveTeachingRequest(UserRequest userRequest) throws Exception {

        userRequest.getPerson().getAccessInfo().setTypeProfile(ProfileType.PROFESSOR);
		userRequest.setTeachingRequest(true);
		userRequest.setStatus(Status.APPROVED);
		
        try {
        	updateUser(userRequest.getPerson().getAccessInfo());
            updateUserRequest(userRequest);
        } catch (Exception e) {
			throw e;
		}
	}
	
	public String sendEmailApproveTeachingRequest(String email)	throws MessagingException, IOException {

		try {
			// Build an email message
			Person pAtt = this.getAtributesFromEmail(email);
		
			// subject
			String subject = Messages.getString("teachingRequest.emailSubject")
					+ "\n";
		
			// email text
			String finalText = Messages
					.getString("teachingRequest.congratulation")
					+ " " // User name
					+ pAtt.getName()
					+ ","
					+ "\n\n"// text message
					+ Messages.getString("teachingRequest.emailTextApproved")
					+ "\n\n"
					+ Messages.getString("teachingRequest.thank")
					+ "\n\n" + Messages.getString("teachingRequest.amadeus");
		
			// send a message
			MailSender.sendMail(email, subject, finalText);
		
		} catch (RuntimeException e) {
			throw e;
		}
		
		return email;
	
	}
	
	public void disapprovedTeachingRequest(UserRequest userRequest) throws Exception {
		
        userRequest.setStatus(Status.DISAPPROVED);
		userRequest.setTeachingRequest(true);

		try {
			updateUserRequest(userRequest);		
	    } catch (Exception e) {
        	throw e;
		}
	}
	
	public String sendEmailDisapproveTeachingRequest(String email,
			String justification) throws MessagingException, IOException {
		try {
			// Build an email message
			Person pAtt = this.getAtributesFromEmail(email);

			// subject
			String subject = Messages.getString("teachingRequest.emailSubject")
					+ "\n";

			// email text
			String finalText = Messages
					.getString("teachingRequest.emailIntroduction")
					+ " "
					+ pAtt.getName()
					+ ","
					+ "\n\n"
					+ Messages
							.getString("teachingRequest.emailTextDisapproved")
					+ "\n\n"
					+ Messages.getString("teachingRequest.reasonDisapproved")
					+ "\n\n"
					+ justification
					+ "\n\n"
					+ Messages.getString("teachingRequest.amadeus");

			// send a message
			MailSender.sendMail(email, subject, finalText);

		} catch (RuntimeException e) {
			throw e;
		}
		return email;
	}
	
	public void approveAssistanceRequest(UserRequest userRequest, Course course) throws RequestException{

		try {
			userRequest.setStatus(Status.APPROVED);
			userRequest.setCourse(course);
			userRequest.setTeachingRequest(false);				
							
			Role roleAssistant = searchRoleByConstant(RoleType.ASSISTANT);
			Role roleStudent = searchRoleByConstant(RoleType.STUDENT);
			
			List<PersonRoleCourse> personRoleCourse = this.getByRoleInCourse(userRequest.getPerson(), course, roleStudent);
			
			if (personRoleCourse.size() != 0) {

				for (PersonRoleCourse requestPRC : personRoleCourse) {
					
					if (this.isValidationDataToRegisterCourse(requestPRC.getCourse())){
						requestPRC.setCourse(course);
						requestPRC.setPerson(userRequest.getPerson());
						requestPRC.setRole(roleAssistant);
						
						this.updateUserRequest(userRequest);
						this.updatePersonRoleCourse(requestPRC);
					}
				
				}

			}	
		} catch (Exception e) {
			throw new RequestException("errors.request");
		}
		
	}
	
	public String sendEmailApproveAssistanceRequest(String email) throws MessagingException, IOException {
		try {
			// Build an email message
			Person pAtt = this.getAtributesFromEmail(email);
		
			// subject
			String subject = Messages.getString("assistanceRequest.emailSubject")
					+ "\n";
		
			// email text
			String finalText = Messages
					.getString("assistanceRequest.congratulation")
					+ " " // User name
					+ pAtt.getName()
					+ ","
					+ "\n\n"// text message
					+ Messages.getString("assistanceRequest.emailTextApproved")
					+ "\n\n"
					+ Messages.getString("assistanceRequest.thank")
					+ "\n\n" + Messages.getString("assistanceRequest.amadeus");
		
			// send a message
			MailSender.sendMail(email, subject, finalText);
		
		} catch (RuntimeException e) {
			throw e;
		}
		
		return email;
	
	}
	
	public String sendEmailDisapproveAssistanceRequest(String email,
			String justification) throws MessagingException, IOException {
		try {
			// Build an email message
			Person pAtt = this.getAtributesFromEmail(email);

			// subject
			String subject = Messages.getString("assistanceRequest.emailSubject")
					+ "\n";

			// email text
			String finalText = Messages
					.getString("assistanceRequest.emailIntroduction")
					+ " "
					+ pAtt.getName()
					+ ","
					+ "\n\n"
					+ Messages
							.getString("assistanceRequest.emailTextDisapproved")
					+ "\n\n"
					+ Messages.getString("assistanceRequest.reasonDisapproved")
					+ "\n\n"
					+ justification
					+ "\n\n"
					+ Messages.getString("assistanceRequest.amadeus");

			// send a message
			MailSender.sendMail(email, subject, finalText);

		} catch (RuntimeException e) {
			throw e;
		}
		return email;
	}
		
	public void disapprovedAssistanceRequest(UserRequest userRequest) throws RequestException {
		try {
			userRequest.setStatus(Status.DISAPPROVED);
			userRequest.setTeachingRequest(false);
			
			this.updateUserRequest(userRequest);
		} catch (Exception e) {
			throw new RequestException("errors.request");
		}
	}
	
	public List<PersonRoleCourse> getByRoleInCourse(Person person, Course course, Role role){
		PersonRoleCourseDAO request = factory.getPersonRoleCourseDAO();
		return request.getByRoleInCourse(person, course, role);
	}
	
	public UserRequest searchUserRequestById(int id) {
		UserRequestDAO userRequestDAO = factory.getUserRequestDAO();
		return userRequestDAO.findById(id, false);
	}
	
	public List<UserRequest> requestTeacherOfUser(Person person) {
		UserRequestDAO request = factory.getUserRequestDAO();
		return request.requestTeacherOfUser(person.getId());
	}
	
	public void requestTeacher(Resume resume, String interest) {
		
		List<UserRequest> userRequest = requestTeacherOfUser(resume.getPerson());
		
		if (userRequest.size() != 0) {
		
			for (UserRequest useRequestElement : userRequest) {
			
				if (useRequestElement.getStatus() != Status.APPROVED) {
					useRequestElement.setInterest(interest);
					useRequestElement.setPerson(resume.getPerson());
					useRequestElement.setStatus(Status.WAITING);
					useRequestElement.setTeachingRequest(true);
					
					useRequestElement.setUserRequestDate(new Date());
					
					updateResume(resume);
					updateUserRequest(useRequestElement);
					
					return;
				} else {
					
					updateResume(resume);
				}
			}
		}
		
		UserRequest userR = new UserRequest();
		userR.setInterest(interest);
		userR.setPerson(resume.getPerson());
		userR.setStatus(Status.WAITING);
		userR.setTeachingRequest(true);
		
		userR.setUserRequestDate(new Date());
		
		updateResume(resume);
		insertUserRequest(userR);
		
	}
	
	public List<Person> getAssistanceInCourse(Course course){
		Role role = searchRoleByConstant(RoleType.ASSISTANT); 
		PersonDAO request = factory.getPersonDAO();
		return request.getAssistanceInCourse(course, role);
	}

	public Role getRoleByPersonInCourse(Person person, Course course) throws Exception {
		Role role = null;
		try {
			RoleDAO request = factory.getRoleDAO();
			List<Role> roleRequest = request.getRoleByPersonInCourse(person, course);
			
			for (Role roleElement : roleRequest) {
				role = roleElement;
			}
			
		} catch (Exception e) {
			throw e;
		}
		return role;
	}
	
	public void insertForum(Forum forum) {
		try {
			ForumDAO forumRep = factory.getForumDAO();
			forumRep.makePersistent(forum);
		} catch (RuntimeException e) {
			throw e;
		}	
	}

	public Forum updateForum(Forum forum) {
		try {
			Forum result;
			ForumDAO forumRep = factory.getForumDAO();
			result = forumRep.makePersistent(forum);
			return result;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public Forum getForumById(int forumId) {
		ForumDAO forum = factory.getForumDAO();
		return forum.findById(forumId, false);
	}
	
	public List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum> getListForum(){
		List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum> listForum = new ArrayList<br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum>();
		ForumDAO forum = factory.getForumDAO();
		
		List<Forum> forumList = forum.getListForum();
		for (Forum obj : forumList) {
			br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum f = new br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum();
			f.setId(obj.getId());
			f.setName(obj.getName());
			f.setDescription(obj.getDescription());
			f.setCreationDate(obj.getCreationDate());
			List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Message> listMessage = 
				new ArrayList<br.ufpe.cin.amadeus.amadeus_web.syncronize.Message>();
			List<Message> messages = obj.getMessages();
			for (Message message : messages) {
				br.ufpe.cin.amadeus.amadeus_web.syncronize.Message m = new br.ufpe.cin.amadeus.amadeus_web.syncronize.Message();
				m.setId(message.getId());
				m.setBody(message.getBody());
				m.setDate(message.getDate());
				m.setPersonName(message.getAuthor().getName());
				m.setForum_id(obj.getId());
				m.setPersonLogin(message.getAuthor().getAccessInfo().getLogin());
				listMessage.add(m);
			}
			f.setMessages(listMessage);
			listForum.add(f);
		}
		
		return listForum;
	}
	
	public br.ufpe.cin.amadeus.amadeus_web.syncronize.Message getLastMessage(){
		MessageDAO mDAO = factory.getMessageDAO();
		
		Message m = mDAO.getLastMessage();
		br.ufpe.cin.amadeus.amadeus_web.syncronize.Message message = new br.ufpe.cin.amadeus.amadeus_web.syncronize.Message();
		message.setId(m.getId());
		message.setBody(m.getBody());
		message.setDate(m.getDate());
		message.setForum_id(m.getForum().getId());
		message.setPersonName(m.getAuthor().getName());
		
		return message;
	}

	public List<Message> searchMessageByPaging(int tamanhoBloco, int qtdBloco, Forum forum) {
		qtdBloco = qtdBloco - 1;
		qtdBloco = tamanhoBloco * qtdBloco;
		
		MessageDAO request = factory.getMessageDAO();
		return request.searchMessageByPaging(tamanhoBloco, qtdBloco, forum);
	}
	
	public void saveMessage(Message message){
		try {
			MessageDAO request = factory.getMessageDAO();
			request.makePersistent(message);
		} catch (RuntimeException e) {
			throw(e);
		}
	}

	public int getSizeSearchMessageByForum(Forum forum) {
		MessageDAO request = factory.getMessageDAO();
		return request.getSizeSearchMessageByForum(forum);
	}
	
	public boolean isValidationDataToInsertPoll(Course course, Date finalDatePoll) {
		
		Calendar calendarFinalDatePoll = new GregorianCalendar();
		calendarFinalDatePoll.setTime(finalDatePoll);
		
		Calendar calendarInitialCourseDate = new GregorianCalendar();
		calendarInitialCourseDate.setTime(course.getInitialCourseDate());
		
		Calendar calendarFinalCourseDate = new GregorianCalendar();
		calendarFinalCourseDate.setTime(course.getFinalCourseDate());
		
		if ((calendarFinalDatePoll.getTime().compareTo(calendarInitialCourseDate.getTime()) > 0) &&
				(calendarFinalDatePoll.getTime().compareTo(calendarFinalCourseDate.getTime()) <= 0))
			return true;
	
		return false;
	}
	
	public boolean isValidationDataToAnswerPoll(Poll poll) {
		
		Date today = new Date();
		Calendar calendarToday = new GregorianCalendar();
		calendarToday.setTime(today);
		
		Calendar calendarFinalDatePoll = new GregorianCalendar();
		calendarFinalDatePoll.setTime(poll.getFinishDate());
		
		if (calendarFinalDatePoll.getTime().compareTo(calendarToday.getTime()) > 0)
			return true;
	
		return false;
	}
	
	public boolean isValidationDataToAnswerForum(Forum forum, Course course) {
		
		boolean canAnswerForum = false;

		Date today = new Date();
		Calendar calendarToday = new GregorianCalendar();
		calendarToday.setTime(today);

		Calendar calendarInitDateCourse = new GregorianCalendar();
		calendarInitDateCourse.setTime(course.getInitialCourseDate());

		Calendar calendarFinalDateCourse = new GregorianCalendar();
		calendarFinalDateCourse.setTime(course.getFinalCourseDate());

		if ((calendarToday.getTime()
				.compareTo(calendarInitDateCourse.getTime()) >= 0)
				&& (calendarToday.getTime().compareTo(
						calendarFinalDateCourse.getTime()) <= 0))
			canAnswerForum = true;

		return canAnswerForum;
	}
	
	public int getNextPositionModule(Course course) {
		int result = 0;
		
		try {
			ModuleDAO moduleDAO = factory.getModuleDAO();	
			result = moduleDAO.getNextPositionModule(course);
		} catch(RuntimeException e) {
			throw e;
		}
		
		return result; 
	}

	
	public Keyword getKeywordWithIdByName(String name) {
		Keyword result = new Keyword();
		try {
			KeywordDAO keywordDAO = factory.getKeywordDAO();
			result = keywordDAO.getKeywordWithIdByName(name);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
	public String searchString(String oldString){
		String newString = oldString.toLowerCase();
		int length = newString.length();
		String c;
		for(int i = 0; i< length; i = i + 1){
			c = newString.charAt(i) + "";
		  if ( c.equals("a") || c.equals("e") || c.equals("i") || c.equals("o") 
				  || c.equals("u")){
			 
			  if(i == length - 1 && length > 1){ // quando � o �ltimo char
				  
				  newString = newString.substring(0, i) + "%";
				  
			  } else if (i == 0 && length > 1) { // quando � o primeiro
				  newString = "%" +  
				  newString.substring(1, length);
			  } else { 							// no meio da string 
				  newString = newString.substring(0, i) + "%" + 
				  newString.substring(i + 1, length);
			  }	  
		 }
		}
		return newString;
	}
	
	public void merge(Object object) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.merge(object);
		} catch (RuntimeException e) {
			throw e;
		}
	}


	public void insertMaterial(Material material) {
		try {
			MaterialDAO materialRep = factory.getMaterialDAO();
			materialRep.makePersistent(material);	
		} catch (RuntimeException e) {
			throw e;
		}
		
	}
	
	public void insertMaterialRequest (MaterialRequest materialRequest) {
		try {	
			MaterialRequestDAO materialRep = factory.getMaterialRequestDAO();
			materialRep.makePersistent(materialRequest);
		} catch (RuntimeException e) {
			throw e;
		}
		
	}

	public MaterialRequest updateMaterialRequest(MaterialRequest materialRequest) {
		try {
			MaterialRequest result;
			MaterialRequestDAO materialRequestRep = factory.getMaterialRequestDAO();
			result = materialRequestRep.makePersistent(materialRequest);
			return result;
		} catch (RuntimeException e) {
			throw e;
		}
		
	}

	public Material getMaterial(Person person, MaterialRequest materialRequest) {
		try {
			Material result;
			MaterialDAO materialRep = factory.getMaterialDAO();
			result = materialRep.getMaterial(person, materialRequest);
			return result;
		} catch (RuntimeException e) {
			throw e;
		}	
	}

	public void validateMaterial(FormFile archive, String name) throws InvalidMaterialException {
		if(name == null || name.equals("")){
			throw new InvalidMaterialException("errors.material.name.required");
		}else if(archive.getFileName().equals("")){
			throw new InvalidMaterialException("errors.material.file.required");
		}else if(archive.getFileSize() > 10000000){
			throw new InvalidMaterialException("errors.material.file.size");
		}
	}
	
	public void validateVideoStep1(String name, String description) throws InvalidVideoException {
		if(name == null || name.equals("")){
			throw new InvalidVideoException("errors.video.name.required");
		}else if(name.length() >= 255){
			throw new InvalidVideoException("errors.video.name.tooLong");
		}else if(description == null || description.equals("")){
			throw new InvalidVideoException("errors.video.description.required");
		}else if(description.length() >= 255){
			throw new InvalidVideoException("errors.video.description.tooLong");
		}
	}
	
	public void validateVideoURL(String url) throws InvalidVideoException {
		if(url == null || url.equals("")){
			throw new InvalidVideoException("errors.video.url.required");
		}else if(url.length() >= 255){
			throw new InvalidVideoException("errors.video.url.tooLong");
		}
	}
	
	public void validateVideoFile(FormFile archive) throws InvalidVideoException {
		if(archive == null || archive.getFileName().equals("")){
			throw new InvalidVideoException("errors.video.videoFile.required");
		}
	}
	
	public void deleteKeywordsOrphan() {
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.deleteKeywordsOrphan();
	}
	
	public void incrementPopularityKeyword(int courseId, Set<Keyword> keywords) {
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.incrementPopularityKeyword(courseId, keywords);
	}
	
	public void decrementPopularityKeyword(int courseId, Set<Keyword> keywords) {
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.decrementPopularityKeyword(courseId, keywords);
	}
	
	public void flush()	{
		HibernateUtil.getSessionFactory().getCurrentSession().flush();
	}

	public VideoIriz getVideoByID(int videoId) {
		VideoIrizDAO  videoDAO = factory.getVideoIrizDAO();
		return videoDAO.findById(videoId, false);
	}

	public LearningObject getLearningObjectById(int learningId){
		LearningObjectDAO learningDAO = factory.getLearningObjectDAO();
		return learningDAO.findById(learningId, false);
	}
	
	public HistoryLearningObject getHistoryLearningObject(int idHistory){
		HistoryLearningObjectDAO historyDAO = factory.getHistoryLearningObjectDAO();
		return historyDAO.findById(idHistory, false);
	}

	public List<Object> getStudentPendingTasks(Person student) {
		List<Object> tasks = new ArrayList<Object>();
		List<MaterialRequest> tasksMaterialRequest = new ArrayList<MaterialRequest>();
		List<Evaluation> tasksEvaluation = new ArrayList<Evaluation>();
		
		PersonRoleCourseDAO personDAO = factory.getPersonRoleCourseDAO();
		List<PersonRoleCourse> roles = personDAO.getStudentRolesCourses(student.getId());
		
		List<Course> courses = new ArrayList<Course>();
		for(PersonRoleCourse role : roles){ //list the courses the student is enrolled
			if(role.getRole().getRoleType() == RoleType.STUDENT){
				courses.add(role.getCourse());
			}
		}
		
		for(Course course : courses){
			
			for(Module module : course.getModules()){ //iterating in each module 
				tasksMaterialRequest.addAll(module.getMaterialRequests()); //list all the fictasks of the module
				tasksEvaluation.addAll(module.getEvaluations());
				
				for(Material material : module.getMaterials()){ //remove already delivered stuff
					if(material.getAuthor() != null 
							&& material.getAuthor().equals(student)){
						tasksMaterialRequest.remove(material.getRequestedMaterial());
					}
				}
				
				for(Evaluation evaluation : module.getEvaluations()) {
					for (EvaluationRealized evaluationRealized : evaluation.getEvaluationsRealized()) {
						if(evaluationRealized.getStudent() != null
								&& evaluationRealized.getStudent().equals(student)){
							tasksEvaluation.remove(evaluation);
						}
					}
				}
				
			}
		}
		
		tasks.addAll(tasksMaterialRequest);
		tasks.addAll(tasksEvaluation);
		
		return tasks;
	}
	
	public int getTotalAccessLearningObject(int idLearning){
		HistoryLearningObjectDAO historyDAO = factory.getHistoryLearningObjectDAO();
		return historyDAO.getTotalAccessLearningObject(idLearning);
	}
	
	public HistoryLearningObject startSessionHistoryLearningObject(HistoryLearningObject history){
		HistoryLearningObjectDAO historyDAO = factory.getHistoryLearningObjectDAO();
		HistoryLearningObject result = historyDAO.makePersistent(history);
		return result;
	}
	
	public void insertEvaluation(Evaluation evaluation) {
		try {
			EvaluationDAO evaluationRep = factory.getEvaluationDAO();
			evaluationRep.makePersistent(evaluation);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	
	public Evaluation getEvaluationById(int evaluationId) {
		EvaluationDAO evaluation = factory.getEvaluationDAO();
		return evaluation.findById(evaluationId, false);
	}
	
	public Question getQuestionById(Evaluation evaluation, int questionId) {
		Question question = null;
		
		for (Question q : evaluation.getQuestions()) {
			if(q.getId() == questionId) {
				question = q;
			}
		}
		
		return question;
	}
	
	public void insertEvaluationRealized(EvaluationRealized evR) {
		EvaluationRealizedDAO evDAO = factory.getEvaluationRealizedDAO();
		evDAO.makePersistent(evR);
	}
	
	public void registerStudentCourse(Course course, Person person) {
		Role studentRole = this.searchRoleByConstant(RoleType.STUDENT);

		PersonRoleCourse prc = new PersonRoleCourse();
		prc.setCourse(course);
		prc.setPerson(person);
		prc.setRole(studentRole);

		course.getPersonsRolesCourse().add(prc);
	}
	
	public void unregisterStudentCourse(Course course, Person person){
		
		Course courseCopy = this.getCoursesById(course.getId());
		List<PersonRoleCourse> roles = courseCopy.getPersonsRolesCourse();
		Iterator<PersonRoleCourse> ite = roles.iterator(); 
		while(ite.hasNext()){
			PersonRoleCourse prc = ite.next();
			System.out.println("########: "+prc.getPerson().getName());
			if(prc.getPerson().getId() == person.getId()){
				System.out.println("$$$: "+prc.getPerson().getName());
				ite.remove();
			}
		}
		
	}
	
	public String getMostPopularKeywwordsPreparedAsHTML() {
		
		List<Keyword> keywords = this.getMostPopularKeywords();
		
		int mostPopular = 1;
			
		if(keywords != null && keywords.size() > 0){
			mostPopular = keywords.get(0).getPopularity();
		}
		
		Collections.shuffle(keywords);
		int popClass1;
		 
		if(mostPopular/5 == 0){
			popClass1 = 1;
		}else{
			popClass1 = (mostPopular/5);
		}
		
		int popClass2 = 2*popClass1;
		int popClass3 = 3*popClass1;
		int popClass4 = 4*popClass1;
		
		String content = "";
		Integer count = 0;
		
		for (Keyword k : keywords) {
			String[] keyworksClass = {"class='keywork0'", "class='keywork1'", 
					"class='keywork2'", "class='keywork3'", "class='keywork4'"};
			int pop = 0;
			
			if(k != null){
				if(k.getPopularity() <= popClass1){
					pop = 0;
				}else if(k.getPopularity() <= popClass2){
					pop = 1;
				}else if(k.getPopularity() <= popClass3){
					pop = 2;
				}else if(k.getPopularity() <= popClass4){
					pop = 3;
				}else{
					pop = 4;
				}
			}	
			
			count++;
			content += "<a "+keyworksClass[pop]+" href=\"fSearchCourse.do?keyword_course="+k.getName()+"\">"+k.getName()+"</a>";
			if (count <= keywords.size() - 1) {
				content +=", ";
			}
		}
		
		return content;
	}
	
	public List<Evaluation> getAllEvaluationFromCourse(Course course) {
		EvaluationDAO evaluationDAO = factory.getEvaluationDAO();
		List<Evaluation> evaluations = evaluationDAO.getAllEvaluationFromCourse(course);
		
		return evaluations;
	}
	
	public ActionMessages validadeQuestionMultiple(QuestionMultiple questionMultipleForValidation) {
		ActionMessages messages = new ActionMessages();
		
		if(questionMultipleForValidation.getDescription() == null || 
				questionMultipleForValidation.getDescription().trim().equals("")){
			messages.add("error", new ActionMessage("errors.evaluation.question.description.required"));
		} else if(questionMultipleForValidation.getDescription().length() > 25000) {
			messages.add("error", new ActionMessage("errors.evaluation.question.description.maxlength"));
		}
		
		for(Alternative alternative : questionMultipleForValidation.getAlternatives()){
			if(alternative.getDescription() == null || 
					alternative.getDescription().trim().equals("")){
				messages.add("error", new ActionMessage("errors.evaluation.question.alternative.required"));
				break;
			}
		}
		
		boolean hasOneCorrectAlternative = false;
		
		for(Alternative alternative : questionMultipleForValidation.getAlternatives()){
			if(alternative.isCorrect()) {
				hasOneCorrectAlternative = alternative.isCorrect();
			}
		}
		
		if(!hasOneCorrectAlternative) {
			messages.add("error", new ActionMessage("errors.evaluation.question.alternative.noHaveOneCorrect"));
		}
		
		return messages;
	}
	
	public ActionMessages validateQuestionRealizedDiscursive(QuestionDiscursiveRealized questionDiscursiveRealized) {
		ActionMessages messages = new ActionMessages();
		
		if(questionDiscursiveRealized.getAnswer() == null || 
				questionDiscursiveRealized.getAnswer().trim().equals("")) {
			messages.add("error", new ActionMessage("errors.evaluation.question.answer.discursive.required"));
		} else if(questionDiscursiveRealized.getAnswer().length() > 25000) {
			messages.add("error", new ActionMessage("errors.evaluation.question.answer.discursive.maxlength"));
		}
		
		return messages;
	}
	
	public ActionMessages validateQuestionDiscursive(QuestionDiscursive questionDiscursiveForValidation) {
		ActionMessages messages = new ActionMessages();
		
		if(questionDiscursiveForValidation.getDescription() == null || 
				questionDiscursiveForValidation.getDescription().trim().equals("")) {
			messages.add("error", new ActionMessage("errors.evaluation.question.description.required"));
		} else if(questionDiscursiveForValidation.getDescription().length() > 25000) {
			messages.add("error", new ActionMessage("errors.evaluation.question.description.maxlength"));
		}
		
		return messages;
	}
	
	public ActionMessages validateQuestionGap(QuestionGap questionGapForValidation) {
		ActionMessages messages = new ActionMessages();
		
		if(questionGapForValidation.getDescription() == null || 
				questionGapForValidation.getDescription().trim().equals("")) {
			messages.add("error", new ActionMessage("errors.evaluation.question.description.required"));
		} else if(questionGapForValidation.getDescription().length() > 25000) {
			messages.add("error", new ActionMessage("errors.evaluation.question.description.maxlength"));
		}
		
		return messages;
	}
	
	public ActionMessages validateQuestionTrueFalse(QuestionTrueFalse questionTrueFalseForValidation) {
		ActionMessages messages = new ActionMessages();
		
		if(questionTrueFalseForValidation.getDescription() == null || 
				questionTrueFalseForValidation.getDescription().trim().equals("")){
			messages.add("error", new ActionMessage("errors.evaluation.question.description.required"));
		} else if(questionTrueFalseForValidation.getDescription().length() > 25000) {
			messages.add("error", new ActionMessage("errors.evaluation.question.description.maxlength"));
		}
		
		for(Alternative alternative : questionTrueFalseForValidation.getAlternatives()){
			if(alternative.getDescription() == null || 
					alternative.getDescription().trim().equals("")){
				messages.add("error", new ActionMessage("errors.evaluation.question.alternative.required"));
				break;
			}
		}
		
		return messages;
	}
	
	public OpenID getOpenIDByIdentity(String identity) {
		OpenIDDAO openIDDAO = factory.getOpenIDDAO();
		OpenID openID = openIDDAO.getByIdentity(identity);
		
		return openID;
	}
	
	public OpenID getOpenIDById(int id) {
		OpenIDDAO openIDDAO = factory.getOpenIDDAO();
		OpenID openID = openIDDAO.findById(id, false);
		
		return openID;
	}
	
	public List<AccessInfo> getAllUsers() {
		AccessInfoDAO accessInfoDAO = factory.getAccessInfoDAO();
		List<AccessInfo> users = accessInfoDAO.getAllUserOrderByName();
	
		return users;
	}
	
	public List<String> getAllEmail() {
		PersonDAO personDAO = factory.getPersonDAO();
		List<String> emails = personDAO.getAllEmails();
		
		return emails;
	}
	
	public List<String> getEmailOfStudens() {
		List<AccessInfo> users = this.getAllUsers();
		List<String> emails = new ArrayList<String>();
		
		for (AccessInfo user : users) {
			if(user.getTypeProfile() == ProfileType.STUDENT) {
				emails.add(user.getPerson().getEmail());
			}
		}
		
		return emails;
	}
	
	public List<String> getEmailOfTeachers() {
		List<AccessInfo> users = this.getAllUsers();
		List<String> emails = new ArrayList<String>();
		
		for (AccessInfo user : users) {
			if(user.getTypeProfile() == ProfileType.STUDENT) {
				emails.add(user.getPerson().getEmail());
			}
		}
		
		return emails;
	}

	public List<String> getEmailOfProfessors() {
		List<AccessInfo> users = this.getAllUsers();
		List<String> emails = new ArrayList<String>();
		
		for (AccessInfo user : users) {
			if(user.getTypeProfile() == ProfileType.PROFESSOR) {
				emails.add(user.getPerson().getEmail());
			}
		}
		
		return emails;
	}
	
	public List<String> getEmailOfAdmins() {
		List<AccessInfo> users = this.getAllUsers();
		List<String> emails = new ArrayList<String>();
		
		for (AccessInfo user : users) {
			if(user.getTypeProfile() == ProfileType.ADMIN) {
				emails.add(user.getPerson().getEmail());
			}
		}
		
		return emails;
	}
	
	public List<String> getEmailUsersOfCourse(int courseId) {
		List<Person> users = new ArrayList<Person>();
		List<String> emails = new ArrayList<String>();
		
		Course course = this.getCoursesById(courseId);
		
		users.addAll(this.getTeachersByCourse(course));
		users.addAll(this.listAssistantsByCourse(course));
		users.addAll(this.listStudentsByCourse(course));
		
		for (Person user : users) {
			emails.add(user.getEmail());
		}
		
		return emails;
	}

	public List<Game> getGamesByCourse(
			int courseID) {
		
		ArrayList<Game> games = new ArrayList<Game>();
		
		CourseDAO courseRep = factory.getCourseDAO();
		Course course = courseRep.findById(courseID, false);
		
		List<Module> modules = course.getModules();
		
		for(Module module : modules){
			games.addAll(module.getGames());
		}
		
		return games;
	}
	
	public List<AccessInfo> searchUsers(String userName, Integer userType, Integer courseId) {
		AccessInfoDAO accessInfoDAO = factory.getAccessInfoDAO();
		return accessInfoDAO.searchUsers(userName, userType, courseId);
	}
	
	public List<AccessInfo> searchUsers(Integer userType) {
		AccessInfoDAO accessInfoDAO = factory.getAccessInfoDAO();
		return accessInfoDAO.searchUsers(userType);
	}
	
	
	//external link
	public ExternalLink getExternalLinkById(int idLink){
		ExternalLinkDAO externalLinkDAO = factory.getExternalLinkDAO();
		return externalLinkDAO.findById(idLink, true);
	}
	public void insertExternalLink(ExternalLink externalLink){
		ExternalLinkDAO externalLinkDAO = factory.getExternalLinkDAO();
		externalLinkDAO.makePersistent(externalLink);
	}
	public void deleteExternalLink(ExternalLink externalLink){
		ExternalLinkDAO externalLinkDAO = factory.getExternalLinkDAO();
		externalLinkDAO.makeTransient(externalLink);
	}
	
	//Log
	public void saveLog(Log log){
		LogDAO logRep = factory.getLogDAO();
		logRep.makePersistent(log);
	}

	public Log getLog() throws Exception {
		LogDAO logRep = factory.getLogDAO();
		Log log = new Log();
		log.setDate(new Date());
		PersonDAO personDAO = factory.getPersonDAO();
		Person pessoa = personDAO.findById(4, false);
		log.setIdUser(pessoa);
		logRep.makePersistent(log);
		
		return log;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayGameScore(int idGame)
	{
		List gameScores = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT p.name, (MAX( l.pontuacao)) as pontuacao FROM log l, person p "+
				"WHERE l.codigo = " + Log.LOG_CODIGO_JOGAR + " "+
				"AND idobjeto = " + idGame + " " +
				"AND l.person_id = p.id " +
				"GROUP BY p.name").list();
		
		String array = "";
		
		for(int i=0; i<gameScores.size(); i++)
		{
			Object[] temp = (Object[]) gameScores.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\"Aluno\":\""+temp[0]+"\",\"Pontuação\":"+temp[1]+"}";			
		}		
		array+="]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayGameScoreVisualizacao(int idGame)
	{
		List gameScores = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT p.name, (MAX( l.pontuacao)) as pontuacao FROM log l, person p "+
				"WHERE l.codigo = " + Log.LOG_CODIGO_JOGAR + " "+
				"AND idobjeto = " + idGame + " " +
				"AND l.person_id = p.id " +
				"GROUP BY p.name").list();
		
		String array = "";
		
		for(int i=0; i<gameScores.size(); i++)
		{
			Object[] temp = (Object[]) gameScores.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{'Aluno':'"+temp[0]+"','Pontuação':"+temp[1]+"}";			
		}		
		array+="]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getXmlGameScore(int idGame)
	{
		List gameScores = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT p.name, (MAX( l.pontuacao)) as pontuacao FROM log l, person p "+
				"WHERE l.codigo = " + Log.LOG_CODIGO_JOGAR + " "+
				"AND idobjeto = " + idGame + " " +
				"AND l.person_id = p.id " +
				"GROUP BY p.name").list();
		
		String array = "<clazzes>";
		
		int maxScore = 0;
		int minScore = Integer.MAX_VALUE;
		
		for(int i=0; i<gameScores.size(); i++)
		{
			Object[] temp = (Object[]) gameScores.get(i);
			
			int tempScore = 0;
			
			if(temp[1].getClass().equals(BigInteger.class))
			{
				tempScore = ((BigInteger)temp[1]).intValue();				
			}
			else if(temp[1].getClass().equals(Integer.class))
			{
				tempScore = ((Integer)temp[1]).intValue();
			}
			
			
			if(tempScore>maxScore)
			{
				maxScore = tempScore;
			}
			if(tempScore<minScore)
			{
				minScore = tempScore;
			}
		}
		
		
		for(int i=0; i<gameScores.size(); i++)
		{
			Object[] temp = (Object[]) gameScores.get(i);
			double color = 0;
			if(temp[1].getClass().equals(BigInteger.class))
			{
				color = interpolacaoCor(((BigInteger)temp[1]).intValue(), minScore, maxScore);				
			}
			else if(temp[1].getClass().equals(Integer.class))
			{
				color = interpolacaoCor(((Integer)temp[1]).intValue(), minScore, maxScore);				
			}
			array+="<clazz name=\""+temp[0]+"\" score=\""+temp[1]+"\" color=\""+color+"\"/>";			
		}		
		array+="</clazzes>";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayGameLevel(int idGame)
	{
		List gameLevel = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT p.name, (MAX( l.fases)) as pontuacao FROM log l, person p "+
				"WHERE l.codigo = " + Log.LOG_CODIGO_JOGAR + " "+
				"AND idobjeto = " + idGame + " " +
				"AND l.person_id = p.id " +
				"GROUP BY p.name").list();
		
		String array = "";
		
		for(int i=0; i<gameLevel.size(); i++)
		{
			Object[] temp = (Object[]) gameLevel.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\"Aluno\":\""+temp[0]+"\",\"Level\":"+temp[1]+"}";			
		}		
		array+="]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getXmlGameLevel(int idGame)
	{
		List gameLevel = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT p.name, (MAX( l.fases)) as pontuacao FROM log l, person p "+
				"WHERE l.codigo = " + Log.LOG_CODIGO_JOGAR + " "+
				"AND idobjeto = " + idGame + " " +
				"AND l.person_id = p.id " +
				"GROUP BY p.name").list();
		
		String array = "<clazzes>";
		
		int maxScore = 0;
		int minScore = Integer.MAX_VALUE;
		
		for(int i=0; i<gameLevel.size(); i++)
		{
			Object[] temp = (Object[]) gameLevel.get(i);
			
			int tempScore = 0;
			
			if(temp[1].getClass().equals(BigInteger.class))
			{
				tempScore = ((BigInteger)temp[1]).intValue();				
			}
			else if(temp[1].getClass().equals(Integer.class))
			{
				tempScore = ((Integer)temp[1]).intValue();
			}
			
			if(tempScore>maxScore)
			{
				maxScore = tempScore;
			}
			if(tempScore<minScore)
			{
				minScore = tempScore;
			}
		}
		
		
		for(int i=0; i<gameLevel.size(); i++)
		{
			Object[] temp = (Object[]) gameLevel.get(i);
			double color = 0;
			if(temp[1].getClass().equals(BigInteger.class))
			{
				color = interpolacaoCor(((BigInteger)temp[1]).intValue(), minScore, maxScore);				
			}
			else if(temp[1].getClass().equals(Integer.class))
			{
				color = interpolacaoCor(((Integer)temp[1]).intValue(), minScore, maxScore);				
			}
			array+="<clazz name=\""+temp[0]+"\" score=\""+temp[1]+"\" color=\""+color+"\"/>";			
		}		
		array+="</clazzes>";
		
		return array;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayModuleGameTotalTime(int idModule)
	{
		List moduleGameTime = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT g.name, (SUM( l.tempo)) as pontuacao FROM log l, game g, module m " +
				"WHERE l.codigo = " + Log.LOG_CODIGO_JOGAR + " " +
				"AND m.id = " +idModule + " " +
				"AND g.module_id = m.id " +
				"AND l.idobjeto = g.id " + 
				"GROUP BY g.name").list();
		
		String array = "";
		
		for(int i=0; i<moduleGameTime.size(); i++)
		{
			Object[] temp = (Object[]) moduleGameTime.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			

			array+="{\"Jogo\":\""+temp[0]+"\",\"Tempo\":"+temp[1]+"}";				
			
		}		
		array+="]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayModuleGameTimePerDay(int idModule)
	{
		List moduleGameTime  = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT g.name, (date(l.date)) as data, (SUM(l.tempo)) as tempo FROM log l, game g, module m " +
				"WHERE l.codigo = " + Log.LOG_CODIGO_JOGAR + " " +
				"AND m.id = " + idModule + " " +
				"AND g.module_id = m.id " +
				"AND l.idobjeto = g.id " +
				"GROUP BY data, g.name " +
				"ORDER BY data").list();
		
		String array = "";
		
		Date timeAtual = null;
		
		for(int i=0; i<moduleGameTime.size(); i++)
		{
			Object[] temp = ((Object[]) moduleGameTime.get(i));
			Date timeTemp = (Date) temp[1];
			
			if(timeAtual != null)
			{
				if(timeAtual.equals(timeTemp))
				{
					array += ",";
				}
				else
				{
					timeAtual = timeTemp;
					array += "},{\"Dia\":\"" + timeAtual.toString() + "\",";
				}
			}
			else
			{
				timeAtual = timeTemp;
				array += "[{\"Dia\":\"" + timeAtual.toString() + "\",";
			}
			
			array += "\"" + temp[0] + "\":" + temp[2];
			
		}		
		array+="}]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayGameGrid(int idGame)
	{
		List logs = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT date(l.date) as data, p.name, l.fases, l.tempo, l.pontuacao " +
				"FROM log l, person p WHERE l.codigo = " + Log.LOG_CODIGO_JOGAR + " AND l.idobjeto = " + idGame + 
				" AND l.person_id = p.id ORDER BY data").list();
		
		String array = "";
		
		for(int i=0; i<logs.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			

			array+="{'data':'"+temp[0].toString()+"','nome':'"+temp[1]+"','level':"+temp[2]+",'tempo':"+temp[3]+",'score':"+temp[4]+"}";				
			
		}		
		array+="]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayGameGridByUser(int idGame,int idUser)
	{
		List logs = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT date(l.date) as data, p.name, l.fases, l.tempo, l.pontuacao " +
				"FROM log l, person p WHERE l.codigo = " + Log.LOG_CODIGO_JOGAR + " AND l.idobjeto = " + idGame + 
				" AND l.person_id = p.id AND p.access_info_id = " + idUser + " ORDER BY data").list();
		
		String array = "";
		
		for(int i=0; i<logs.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			

			array+="{'data':'"+temp[0].toString()+"','nome':'"+temp[1]+"','level':"+temp[2]+",'tempo':"+temp[3]+",'score':"+temp[4]+"}";				
			
		}		
		array+="]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayTagCloudForum(int idModule)
	{
		/*String select = "SELECT (REGEXP_SPLIT_TO_TABLE(m.body,E'\\\\s+')) as word , " +
		"COUNT(REGEXP_SPLIT_TO_TABLE(m.body,E'\\\\s+')) as count " +
		"FROM message m, forum f " +
		"WHERE f.module_id = " + idModule + 
		" AND m.forum_id = f.id " +
		"GROUP BY word " +
		"ORDER BY count DESC " +
		"LIMIT 20";*/

		String select = "Select tag.word as word , Count(tag.word) as count from " +
			"(SELECT (REGEXP_SPLIT_TO_TABLE(m.body,E'\\\\s+')) as word " + 
			"FROM message m, forum f " +
			"WHERE f.module_id = "  + idModule + " " +
			"AND m.forum_id = f.id ) tag " +
			"where char_length(tag.word )>3 " +	 
			"GROUP BY word " +
			"ORDER BY count DESC " + 
			"LIMIT 20 ";
		
		List tags = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery(select).list();
		
		if(tags.size()>0)
		{
			Object[] temp = (Object[]) tags.get(0);

			int tempSize = 0;

			if(temp[1].getClass().equals(BigInteger.class))
			{
				tempSize = ((BigInteger)temp[1]).intValue();				
			}
			else if(temp[1].getClass().equals(Integer.class))
			{
				tempSize = ((Integer)temp[1]).intValue();
			}

			double max = tempSize;

			temp = (Object[]) tags.get(tags.size()-1);

			tempSize = 0;

			if(temp[1].getClass().equals(BigInteger.class))
			{
				tempSize = ((BigInteger)temp[1]).intValue();				
			}
			else if(temp[1].getClass().equals(Integer.class))
			{
				tempSize = ((Integer)temp[1]).intValue();
			}

			double min = tempSize;		

			String array = "{\"tags\":{\"tag\":[";

			for(int i=0; i<tags.size(); i++)
			{
				temp = (Object[]) tags.get(i);

				if(i != 0)
				{			
					array += ",";
				}

				double size = 0;
				if(temp[1].getClass().equals(BigInteger.class))
				{
					size = interpolacaoFontSize(((BigInteger)temp[1]).intValue(), min, max);				
				}
				else if(temp[1].getClass().equals(Integer.class))
				{
					size = interpolacaoFontSize(((Integer)temp[1]).intValue(), min, max);				
				}

				array+="{\"color\":\"0x000000\",\"counter\":"+temp[1]+",\"meuPeso\":70,\"name\":\""+temp[0]+"\",\"repetead\":2,\"size\":" + size + ",\"tid\":2,\"weight\":20}";				

			}		
			array+="]}}";
			return array;
		}
		else
		{
			return "";
		}
	}
	private double interpolacaoFontSize(int val, double min, double max) {
		
		double retorno = 0;
		
		if(min != max)
		{
			retorno = (val-min)/(max-min);
			
			retorno += 1;
			
			retorno = retorno*70-60;			
		}
		else
		{
			retorno = 80;
		}
		
		
		return retorno;
	}
	
	
	private double interpolacaoCor(int val, int min, int max) {
	
		double metade = (max-min)/2 + min;
		double retorno = 0;
		
		if(val>metade)
		{
			retorno = (val-metade)/(max-metade);
		}
		else if(val<metade)
		{
			retorno = -((metade-val)/(metade-min));
		}
		else
		{
			retorno = 0;
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayPostsPerModule(int idModule){
		
		List logs = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT p.name as nome, count(l.*) as postagens " +
				"from person p, log l, message m, forum f where " +
				"p.id = l.person_id and l.codigo = "+Log.LOG_CODIGO_FORUM_POST+" and l.idobjeto = m.id " +
				"and m.forum_id = f.id and f.module_id = "+idModule+" group by p.name;").list();
		
		String array = "";
		
		for(int i=0; i<logs.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\"Aluno\":\""+temp[0]+"\",\"Quantidade de Posts\":"+temp[1]+"}";			
		}		
		array+="]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArraySizeMessagePerModule(int idModule){
		
		List logs = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT p.name as nome, (sum(l.tamanhomensagem)/count(l.tamanhomensagem)) as mensagens " +
				"from person p, log l, message m, forum f where " +
				"p.id = l.person_id and l.codigo = "+Log.LOG_CODIGO_FORUM_POST+" and l.idobjeto = m.id " +
				"and m.forum_id = f.id and f.module_id = "+idModule+" group by p.name;").list();
		
		String array = "";
		
		for(int i=0; i<logs.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\"Aluno\":\""+temp[0]+"\",\"Tamanho de Mensagem\":"+temp[1]+"}";			
		}		
		array+="]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayPersonGameTimePerModule(int idPerson, int idModule){
		
		List logs = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT g.name as nome, sum(l.tempo) as tempo from " +
				"game g, log l where l.person_id = "+idPerson+" and l.codigo = "+Log.LOG_CODIGO_JOGAR+" " +
				"and l.idobjeto = g.id and g.module_id = "+idModule+" group by g.name;").list();
		
		
		String array = "";
		
		for(int i=0; i<logs.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\"Aluno\":\""+temp[0]+"\",\"Tempo\":"+temp[1]+"}";			
		}		
		array+="]";
		
		//System.out.println(array);
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONObjectTempoLevelPontuacao(int idGame)
	{
		String query = "SELECT  p.name,  SUM(tempo) as tempo, MAX(fases) as level, SUM(pontuacao) as pontuacao FROM log l, person p " +
			"WHERE l.person_id = p.id AND l.idobjeto = " + idGame + " " +
			"AND l.codigo = " + Log.LOG_CODIGO_JOGAR + " GROUP BY p.name;";
		
		List gameScores = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery(query).list();
		String eixoX = "Tempo";
		String eixoY = "Level";
		String eixoRadius = "Pontuacao";
		
		String array = "[[{\"eixoX\":\"" + eixoX + "\",\"eixoY\":\"" + eixoY + "\", \"eixoRadius\":\"" + eixoRadius + "\",\"titulo\":\"Tempo x Level X Pontuação\"}]";
		
		for(int i=0; i<gameScores.size(); i++)
		{
			Object[] temp = (Object[]) gameScores.get(i);
			
			array+=",[{\"name\":\""+temp[0]+"\",\"" + eixoX +""+i+ "\":"+temp[1]+",\"" + eixoY +""+i+ "\":"+temp[2]+",\"" + eixoRadius +""+i+ "\":"+temp[3]+"}]";			
		}		
		array+="]";
		
		//System.out.println(array);
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONObjectLevelPontuacao(int idGame)
	{
		List gameScores = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT p.name, fases, MAX(pontuacao) FROM log l, person p " +
				"WHERE l.person_id = p.id AND l.idobjeto = " + idGame + " " +
				"AND l.codigo = " + Log.LOG_CODIGO_JOGAR + " GROUP BY fases, p.name ORDER BY fases;").list();
		String eixoX = "Level";
		String eixoY = "Pontuacao";
		
		String array = "{\"eixoX\":\"" + eixoX + "\",\"eixoY\":\"" + eixoY + "\", \"titulo\":\"Level x Pontuação\", \"source\":[";
		
		Object[] temp = null;
		if(gameScores.size()>0)
		{
			temp = (Object[]) gameScores.get(gameScores.size()-1);
		}
		
		int tempLevel = 0;
		int anteriorLevel = 0;
		
		for(int i=0; i<gameScores.size(); i++)
		{
			temp = (Object[]) gameScores.get(i);
			
			int atualLevel = 0;
			if(temp[1].getClass().equals(BigInteger.class))
			{
				atualLevel = ((BigInteger)temp[1]).intValue();				
			}
			else if(temp[1].getClass().equals(Integer.class))
			{
				atualLevel = ((Integer)temp[1]).intValue();				
			}
			
			if(anteriorLevel<atualLevel && anteriorLevel!=0)
			{
				tempLevel++;
				array+="}";
			}
			
			if(i>0)
			{
				while(tempLevel < atualLevel)
				{
					array+=",{\"" + eixoX +"\":"+tempLevel+"}";
					tempLevel++;
				}				
			}
			else
			{
				if(temp[1].getClass().equals(BigInteger.class))
				{
					tempLevel = ((BigInteger)temp[1]).intValue();				
				}
				else if(temp[1].getClass().equals(Integer.class))
				{
					tempLevel = ((Integer)temp[1]).intValue();				
				} 
			}
			if(anteriorLevel!=0)
			{
				if(anteriorLevel==tempLevel)
				{
					array+=",\""+temp[0]+"\":" + temp[2] + "";
				}
				else
				{
					array+=",{\""+temp[0]+"\":" + temp[2] + ",\"" + eixoX + "\":"+temp[1];
					anteriorLevel = tempLevel;					
				}				
			}
			else
			{
				array+="{\""+temp[0]+"\":" + temp[2] + ",\"" + eixoX + "\":"+temp[1];
				anteriorLevel = tempLevel;
			}
		}		
		array+="}]}";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONObjectQuantidadeTamanhoMSG(int idModule)
	{
		List gameScores = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT  p.name,  (SUM(tamanhomensagem)/COUNT(p.name)) as tamanho_msg, COUNT(p.name) as qdt_msg " +
				"FROM log l, person p, message m, forum f " +
				"WHERE l.person_id = p.id  AND l.codigo = " + Log.LOG_CODIGO_FORUM_POST + " " +
				"AND l.idobjeto = m.id	AND m.forum_id = f.id AND f.module_id = " + idModule + " " +
				"GROUP BY p.name;").list();
		String eixoX = "Tamanho";
		String eixoY = "Quantidade";
		
		String array = "[[{\"eixoX\":\"" + eixoX + "\",\"eixoY\":\"" + eixoY + "\", \"titulo\":\"Tamanho Medio das Mensagens x Quantidade de Mensagens\"}]";
		
		for(int i=0; i<gameScores.size(); i++)
		{
			Object[] temp = (Object[]) gameScores.get(i);
			
			array+=",[{\"name\":\""+temp[0]+"\",\"" + eixoX +""+i+ "\":"+temp[1]+",\"" + eixoY + ""+i+"\":"+temp[2]+"}]";
		}		
		array+="]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONObjectTempoQuantidadePartidas(int idGame)
	{
		List gameScores = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT  p.name,  SUM(tempo), COUNT(p.name) " +
				"FROM log l, person p " +
				"WHERE l.person_id = p.id " +
				"AND l.idobjeto = " + idGame + " " +
				"AND l.codigo = " + Log.LOG_CODIGO_JOGAR + " " +
				"GROUP BY p.name;").list();
		String eixoX = "Tempo";
		String eixoY = "Quantidade";
		
		String array = "[[{\"eixoX\":\"" + eixoX + "\",\"eixoY\":\"" + eixoY + "\", \"titulo\":\"Tempo Total Jogado x Quantidade de Partidas\"}]";
		
		for(int i=0; i<gameScores.size(); i++)
		{
			Object[] temp = (Object[]) gameScores.get(i);
			
			array+=",[{\"name\":\""+temp[0]+"\",\"" + eixoX +""+i+ "\":"+temp[1]+",\"" + eixoY + ""+i+"\":"+temp[2]+"}]";
		}		
		array+="]";
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONArrayGameMeta(int idGame)
	{
		List gameScores = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT p.name, (MAX( l.metaalternativa)) as meta FROM log l, person p "+
				"WHERE l.codigo = " + Log.LOG_CODIGO_JOGAR + " "+
				"AND idobjeto = " + idGame + " " +
				"AND l.person_id = p.id " +
				"GROUP BY p.name").list();
		
		String array = "";
		
		for(int i=0; i<gameScores.size(); i++)
		{
			Object[] temp = (Object[]) gameScores.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\"Aluno\":\""+temp[0]+"\",\"Meta\":"+temp[1]+"}";			
		}		
		array+="]";
		
		return array;
	}
	
	//Tempo logado
	@SuppressWarnings("unchecked")
	public String getJSONArrayPersonTimeOnline(int idPerson){

		List logs = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT p.id, p.name, l.codigo, l.date "+
				"FROM log l, person p "+ 
				"WHERE (l.codigo = 1 OR l.codigo = 2)AND p.id = l.person_id "+
		"ORDER BY l.date").list();


		String array = "";

		ArrayList<Long> listaTempo = new ArrayList();
		ArrayList<String> listaNomes = new ArrayList();
		ArrayList<Integer> listaID = new ArrayList();

		for(int i=0; i<logs.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);

			if((Integer)temp[2] == Log.LOG_CODIGO_LOGIN)
			{
				int contador = i+1;

				if(!(listaID.contains((Integer)temp[0])))
				{
					listaID.add((Integer)temp[0]);
					listaNomes.add((String)temp[1]);
				}

				while(true)
				{
					if(((Integer)((Object[]) logs.get(contador))[0]).intValue() == ((Integer)temp[0]).intValue()
							&& ((Integer)((Object[]) logs.get(contador))[2]).intValue() == Log.LOG_CODIGO_LOGOUT)
					{
						break;
					}else
					{
						contador = contador + 1;
					}
				}

				Object[] temporario = (Object[])logs.get(contador);

				Date primeira = (Date)temp[3];
				Date segunda = (Date)temporario[3];
				if(listaID.lastIndexOf(listaID.lastIndexOf((Integer)temp[0])) > -1 &&
						listaTempo.get(listaID.lastIndexOf(listaID.lastIndexOf((Integer)temp[0]))) != null)
				{
					if(listaTempo.get(listaID.lastIndexOf(listaID.lastIndexOf((Integer)temp[0])))== null)
					{
						listaTempo.set(listaID.lastIndexOf(listaID.lastIndexOf((Integer)temp[0])), segunda.getTime() - primeira.getTime());

					}else
					{
						listaTempo.set(listaID.lastIndexOf(listaID.lastIndexOf((Integer)temp[0])), listaTempo.get(listaID.lastIndexOf((Integer)temp[0]))+ (segunda.getTime() - primeira.getTime()));
					}
				}else
				{
					listaTempo.add(segunda.getTime() - primeira.getTime());
				}
			}


		}
		for(int i=0; i<listaID.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);

			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}

			array+="{\"Aluno\":\""+listaNomes.get(listaID.lastIndexOf((Integer)temp[0]))+"\",\"Tempo\":"+listaTempo.get(listaID.lastIndexOf((Integer)temp[0]))+"}";
		}


		array+="]";

		//System.out.println(array);

		return array;
	}

	//Visualização do Fórum
	@SuppressWarnings("unchecked")
	public String getJSONArrayForumVisualizacao(int idModule, int idAluno)
	{
		List logs = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT nome, sum(count) as postagens " +
				"FROM ((SELECT DISTINCT f.name as nome, COUNT(l.*) " +
				"FROM log l, person_role_course prc, forum f " +
				"WHERE " +
				"l.codigo =  4 " +
				"AND l.idobjeto =   f.id " +
				"AND l.person_id =  "+ idAluno +" " +
				"AND f.module_id =  "+ idModule +" " +
				"AND prc.person_id = l.person_id " +
				"AND prc.role_id = 1 " +
				"GROUP BY nome) UNION " +
				"(SELECT f.name as nome, (0) as count from forum f   " +
				"where f.module_id =  "+ idModule +"" +
				"group by nome)) as pas group by nome;").list();
		
		String array = "";
		
		for(int i=0; i<logs.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\"Topico\":"+temp[0]+"\",\"Quantidade\":"+temp[1]+"}";			
		}		
		array+="]";
		
		return array;
	}
	
	//Posts em Fórum
	@SuppressWarnings("unchecked")
	public String getJSONArrayPostsPerUser(int idModule, int idUser){
		
		List logs = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery(
				"select nome, sum(count) as postagens " +
				"FROM ((select f.name as nome, (0) as count from forum f " +
				"where f.module_id = " + idModule + " " +
				"group by nome)  union "   +
				"(SELECT f.name as nome, count(l.*) " + 
				"from person p, log l, message m, forum f " +  
				"where (p.id = "+idUser+" AND p.id = l.person_id and l.codigo = "+Log.LOG_CODIGO_FORUM_POST+" " +
				"and l.idobjeto = m.id and m.forum_id = f.id and f.module_id = "+idModule+ ") " +
		"group by f.name, p.name)) as pas group by nome;").list();

		String array = "";

		for(int i=0; i<logs.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\"Topico\":\""+temp[0]+"\",\"Quantidade\":"+temp[1]+"}";			
		}		
		array+="]";
		
		return array;
	}
	
	//Visualizações do Material
	@SuppressWarnings("unchecked")
	public String getJSONArrayMaterialView(int idUsuario, int idModule)
	{
		List gameScores = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT m.archivename, COUNT(l.*) " +
				"FROM log l, person p, person_role_course prc, material m"  +
				"WHERE " +
				"m.id = " + idModule + " " +
				"AND l.codigo =  5 " +
				"AND l.idobjeto = m.id " +
				"AND l.person_id = " + idUsuario + " " +
				"AND prc.person_id = p.id " +
				"AND prc.role_id = 1 " +
				"GROUP BY m.archivename").list();
		
		String array = "";
		
		for(int i=0; i<gameScores.size(); i++)
		{
			Object[] temp = (Object[]) gameScores.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\"Material\":"+temp[0]+"\",\"Quantidade\":"+temp[1]+"}";			
		}		
		array+="]";
		
		return array;
	}
	
	//Respostas de Enquete
	@SuppressWarnings("unchecked")
	public String getJSONArrayPollAnswered(int moduleID, int idAluno)
	{
		List logs = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery("SELECT poll.name, COUNT(l.*) " +
				"FROM log l, person p, module m, person_role_course prc, poll " +
				"WHERE " +
				" m.id =  "+ moduleID +" " +
				" AND m.course_id = prc.course_id " +
				" AND prc.role_id = "+ idAluno +" " +
				" AND prc.person_id = p.id " +
				" AND poll.module_id = m.id " +
				" AND l.idobjeto = poll.id " +
				" AND l.codigo = 6 " +  
				"GROUP BY poll.name").list();
		
		String array = "";
		
		for(int i=0; i<logs.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\",\"Poll\":"+temp[0]+"\"Aluno\":\""+temp[1]+"\"}";			
		}		
		array+="]";
		
		return array;
	}
	
	//Abertura do Jogo
	@SuppressWarnings("unchecked")
	public String getJSONArrayGameOpen(int idModule, int idAluno)
	{
		List logs = HibernateUtil.getSessionFactory().getCurrentSession().
		createSQLQuery(
				"SELECT nome, sum(count) as jogadas " +
				"FROM ((SELECT DISTINCT g.name as nome, COUNT(l.*) " +
				"FROM log l, person_role_course prc, game g " +
				"WHERE " +
				"l.codigo =  7 " +
				"AND l.idobjeto =   g.id " +
				"AND l.person_id =  " + idAluno +" " +
				"AND g.module_id =  " + idModule +" " +
				"AND prc.person_id = l.person_id " +
				"AND prc.role_id = 1 " +
				"GROUP BY nome) UNION " +
				"(SELECT g.name as nome, (0) as count from game g " +
				"WHERE g.module_id =   " + idModule +"  " +
				"group by nome)) as pas group by nome; ").list();
		
		String array = "";
		
		for(int i=0; i<logs.size(); i++)
		{
			Object[] temp = (Object[]) logs.get(i);
			if(array.length() == 0)
			{
				array = "[";
			}
			else
			{
				array += ",";
			}			
			array+="{\"Jogo\":\""+temp[0]+"\",\"Quantidade\":"+temp[1]+"}";			
		}		
		array+="]";
		
		return array;
	}
	/*
	 * TODO - M�todos para recuperar grupo
	 */
	public List<Groups> getGroups(int id) {
		List<Groups> grupo = null;
		try {
			
			 GroupsDAO groupsDAO = factory.getGroupsDAO();
			 grupo = groupsDAO.getGroupsByCourseID(id);
			 
		} catch (RuntimeException e) {
			throw e;
		}
		return grupo;
	}
	
	public List<Person> getPersonByGroupsID(int id) {
		List<Person> grupo = null;
		try {
			
			 GroupsDAO groupsDAO = factory.getGroupsDAO();
			 grupo = groupsDAO.getPersonByGroupsID(id);
			 
		} catch (RuntimeException e) {
			throw e;
		}
		return grupo;
	}
	
	public List<StudentHaveGroup> getStudentsHaveGroup(Course course, Person person)
	{				
		PersonRoleCourseDAO personRoleCourseDAO = factory.getPersonRoleCourseDAO();
		List<StudentHaveGroup> result = personRoleCourseDAO.getStudentHaveGroupByCourse(course, person);
		
		return result;
	}
	
	public Module getUltimoModulo(int courseID)
	{				
		GroupsDAO groupsDAO = factory.getGroupsDAO();
		Module result  = groupsDAO.getUltimoModulo(courseID);
		
		return result;
	}
	
	public List<LogVisualizacao> getLogsByDayAndGroup(String data, int groupId)
	{
		GroupsDAO groupsDAO = factory.getGroupsDAO();
		List<Log> list = groupsDAO.getLogsByDayAndGroup(data, groupId);
		
		List<LogVisualizacao> result = new ArrayList<LogVisualizacao>();
		for(Log log : list)
		{
			LogVisualizacao logVisualizacao = null;
			Facade facade = Facade.getInstance();
			
			
			if(log.getCodigo() == Log.LOG_CODIGO_VISUALIZACAO_MATERIAL)
			{
				Material material = facade.getMaterialByID(log.getIdObjeto());
				
				String materialName = "";
				if(material !=null)
				{
					materialName = material.getArchiveName();
				}
				else
				{
					ExternalLink link = facade.getExternalLinkById(log.getIdObjeto());
					materialName = link.getName();
				}
				
				logVisualizacao = new LogVisualizacao(log, materialName);
			}
			else
			{
				logVisualizacao = new LogVisualizacao(log);
			}
			
			result.add(logVisualizacao);
		}
		
		return result;
	}
	
	public List<LogVisualizacao> getLogsByDayAndPerson(String data, int groupID, int personID)
	{
		PersonDAO personDAO = factory.getPersonDAO();
		List<Log> list = personDAO.getLogsByDayAndPerson(data, groupID, personID);
		List<LogVisualizacao> result = new ArrayList<LogVisualizacao>();
		for(Log log : list)
		{
			LogVisualizacao logVisualizacao = null;
			Facade facade = Facade.getInstance();
			
			
			if(log.getCodigo() == Log.LOG_CODIGO_VISUALIZACAO_MATERIAL)
			{
				String materialName = "";
				
				try
				{
					Material material = facade.getMaterialByID(log.getIdObjeto());
					materialName = material.getArchiveName();					
				}
				catch(Exception e)
				{
					ExternalLink link = facade.getExternalLinkById(log.getIdObjeto());
					materialName = link.getName();					
				}
				logVisualizacao = new LogVisualizacao(log, materialName);
			}
			else
			{
				logVisualizacao = new LogVisualizacao(log);
			}
			
			result.add(logVisualizacao);
		}
		
		return result;
	}
	
	public Groups inserGroups(Groups g) throws CourseInvalidException {
		Groups result;
		try {			
			GroupsDAO groupsDAO = factory.getGroupsDAO();
			result = groupsDAO.makePersistent(g);
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
	public List<TimelineItem> listarTimelineGroup(Groups g){
		
		List<TimelineItem> result = null;
		try {			
			GroupsDAO groupsDAO = factory.getGroupsDAO();
			result = groupsDAO.getTimelineByGroupsID(g.getId());
			
			int max = 0;
			int min = 10000000;
			
			for(int i = 0; i<result.size(); i++){
				if(max < result.get(i).getFrequency()){
					max = result.get(i).getFrequency();
				}
				if(min > result.get(i).getFrequency()){
					min = result.get(i).getFrequency();
				}
			}
			
			for(int i = 0; i<result.size(); i++){
				if(max == min){
					result.get(i).setClasse("class3");
				}else
				{
					int classe = (int) (((double)(result.get(i).getFrequency()-min))/(((double)(max-min))/9));
					
					switch (classe)
					{
					case 0:
						result.get(i).setClasse("class1");
						break;
					case 1:
						result.get(i).setClasse("class2");
						break;
					case 2:
						result.get(i).setClasse("class3");
						break;
					case 3:
						result.get(i).setClasse("class4");
						break;
					case 4:
						result.get(i).setClasse("class5");
						break;
					case 5:
						result.get(i).setClasse("class6");
						break;
					case 6:
						result.get(i).setClasse("class7");
						break;
					case 7:
						result.get(i).setClasse("class8");
						break;
					case 8:
						result.get(i).setClasse("class9");
						break;
					case 9:
						result.get(i).setClasse("class10");
						break;
					}
				}
			}
			
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
public List<TimelineItem> listarTimelinePerson(Person p, Groups g){
		
		List<TimelineItem> result = null;
		try {			
			PersonDAO personDAO = factory.getPersonDAO();
			result = personDAO.getTimelineByPersonID(p.getId(), g.getId());
			
			int max = 0;
			int min = 10000000;
			
			for(int i = 0; i<result.size(); i++){
				if(max < result.get(i).getFrequency()){
					max = result.get(i).getFrequency();
				}
				if(min > result.get(i).getFrequency()){
					min = result.get(i).getFrequency();
				}
			}
			
			for(int i = 0; i<result.size(); i++){
				if(max == min){
					result.get(i).setClasse("class3");
				}else
				{
					int classe = (int) (((double)(result.get(i).getFrequency()-min))/(((double)(max-min))/9));
					
					switch (classe)
					{
					case 0:
						result.get(i).setClasse("class1");
						break;
					case 1:
						result.get(i).setClasse("class2");
						break;
					case 2:
						result.get(i).setClasse("class3");
						break;
					case 3:
						result.get(i).setClasse("class4");
						break;
					case 4:
						result.get(i).setClasse("class5");
						break;
					case 5:
						result.get(i).setClasse("class6");
						break;
					case 6:
						result.get(i).setClasse("class7");
						break;
					case 7:
						result.get(i).setClasse("class8");
						break;
					case 8:
						result.get(i).setClasse("class9");
						break;
					case 9:
						result.get(i).setClasse("class10");
						break;
					}
				}
			}
			
		} catch (RuntimeException e) {
			throw e;
		}
		return result;
	}
	
	public Groups getGroupsById(int id)
	{
		Groups result = null;
		GroupsDAO groupsDAO = factory.getGroupsDAO();
		result = groupsDAO.findById(id, false);
		return result;
	}
	
	public boolean pesronHaveGroup(int courseId, int personId)
	{
		GroupsDAO groupsDAO = factory.getGroupsDAO();
		return groupsDAO.personHaveGroup(courseId, personId);
	}
	
	public boolean verificarStatusPorForum(List<Person> alunos, Forum forum)
	{
		ForumDAO forumDAO = factory.getForumDAO();
		return forumDAO.verificarStatusPorForum(alunos, forum);
	}
	
	public boolean verificarStatusPorGame(List<Person> alunos, Game game)
	{
		GameDAO gameDAO = factory.getGameDAO();
		return gameDAO.verificarStatusPorGame(alunos, game);
	}
}