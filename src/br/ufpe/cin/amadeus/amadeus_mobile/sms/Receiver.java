/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.sms;

import java.util.ArrayList;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.facade.AmadeusFacade;
import br.ufpe.cin.amadeus.amadeus_mobile.facade.FacadeMobile;
import br.ufpe.cin.amadeus.amadeus_web.struts.messages.Messages;

public class Receiver {
	
	private AmadeusFacade amadeusFacade;
	private FacadeMobile facade;
	
	public static ArrayList<Thread> threads = new ArrayList<Thread>(); 
	
	public Receiver() {
		this.amadeusFacade = AmadeusFacade.getInstance();
		this.facade = FacadeMobile.getInstance();
	}
	
	private String truncate(String target, int maxSize) {
		return (target.length() > maxSize ? target.substring(0, maxSize-3)+"..." : target);
	}
	
	/**
	 * Creates generic SMS messages about the course informations
	 * determina
	 * @param messageType
	 * @param courseName
	 * @return
	 */
	private String createMessage(String messageType, String courseName) {
		String sms = Messages.getString(messageType);
		return "[" + this.truncate(courseName, 130-sms.length()) + "] " + sms;
	}
	
	/**
	 * Creates specifics SMS messages about the module
	 * @param messageType
	 * @param courseName
	 * @param moduleNumber
	 * @return
	 */
	private String createMessage(String messageType, String courseName, int moduleNumber) {
		String sms = Messages.getString(messageType);
		return "[" + this.truncate(courseName, 130-sms.length()) + ", Módulo "+moduleNumber+"] " + sms;
	}
	
	/**
	 * Method that alters the course information
	 * @param courseId - Course id
	 */
	public void updatedCourse(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course course) {
		String courseName = course.getName();
		String sms = this.createMessage("mobile.course.courseUpdate", courseName);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Atualização de Curso", sms, -1, course.getId(), 1, false, -1, date);
		CourseUpdateThread thread = new CourseUpdateThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that updates the Teachers inside a Course
	 * @param courseId - Course Id
	 */
	public void teachersUpdate(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course course) {
		String courseName = course.getName();
		String sms = this.createMessage("mobile.course.teachersUpdate", courseName);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Alteração de Professor", sms, -1, course.getId(), 1, false, -1, date);
		TeacherUpdateThread thread = new TeacherUpdateThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that changes the Course Assistants in a Course
	 * @param courseId - Course Id
	 */
	public void assistentsUpdate(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course course) {
		String courseName = course.getName();
		String sms = this.createMessage("mobile.course.assistentsUpdate", courseName);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Alteração de Assistente", sms, -1, course.getId(), 1, false, -1, date);
		AssistentsUpdateThread thread = new AssistentsUpdateThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that Adds a Module in a Course
	 * @param courseId - Course Id
	 * @param moduleId - Module Id
	 */
	public void addModule(int courseId, int moduleId) {
		String courseName = this.amadeusFacade.getCourse(courseId).getName();
		String moduleName = this.amadeusFacade.getModule(moduleId).getNome();
		String sms = this.createMessage("mobile.course.newModule", courseName);
		sms = sms + this.truncate(moduleName, 130-sms.length());
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Inserção de Módulo", sms, moduleId, courseId, 1, false, -1, date);
		AddModuleThread thread = new AddModuleThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that Removes a Module in a Course
	 * @param courseId - Course Id
	 * @param moduleId - Module Id
	 */
	public void removeModule(int courseId, int moduleId) {
		String courseName = this.amadeusFacade.getCourse(courseId).getName();
		String moduleName = this.amadeusFacade.getModule(moduleId).getNome();
		String sms = this.createMessage("mobile.course.deletedModule", courseName);
		sms = sms + this.truncate(moduleName, 130-sms.length());
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Remoção de Módulo", sms, moduleId, courseId, 1, false, -1, date);
		RemoveModuleThread thread = new RemoveModuleThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that Adds a Material in a Module
	 * @param courseId - Course Id
	 * @param moduleId - Module Id
	 * @param materialId - Material Id
	 */
	public void addMaterial(int courseId, int moduleId, int materialId) {
		CourseMobile course = this.amadeusFacade.getCourse(courseId);
		String courseName = course.getName();
		int modNumber = getModuleNumber(moduleId, course);
		String sms = this.createMessage("mobile.course.newMaterial", courseName, modNumber);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Material Adicionado", sms, moduleId, courseId, 2, false, materialId, date);
		AddMaterialThread thread = new AddMaterialThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}

	/**
	 * Returns the module number corresponding to the course
	 * @param moduleId
	 * @param course
	 * @return
	 */
	private int getModuleNumber(int moduleId, CourseMobile course) {
		int modNumber = 1;
		for (ModuleMobile m : course.getModules()) {
			if (m.getId() == moduleId) break;
			else modNumber++;
		}
		return modNumber;
	}
	
	/**
	 * Method that Removes a Material in a Module
	 * @param courseId - Course Id
	 * @param moduleId - Module Id
	 * @param materialId - Material Id
	 */
	public void removeMaterial(int courseId, int moduleId, int materialId) {
		CourseMobile course = this.amadeusFacade.getCourse(courseId);
		String courseName = course.getName();
		int modNumber = getModuleNumber(moduleId, course);
		String sms = this.createMessage("mobile.course.deletedMaterial", courseName, modNumber);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Matarial Removido", sms, moduleId, courseId, 2, false, materialId, date);
		RemoveMaterialThread thread = new RemoveMaterialThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that Adds a Homework in a Module
	 * @param courseId - Course Id
	 * @param moduleId - Module Id
	 * @param homeworkId - Homework Id
	 */
	public void addHomework(int courseId, int moduleId, int homeworkId) {
		CourseMobile course = this.amadeusFacade.getCourse(courseId);
		String courseName = course.getName();
		int modNumber = getModuleNumber(moduleId, course);
		String sms = this.createMessage("mobile.course.newActivity", courseName, modNumber);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Atividade Adicionada", sms, moduleId, courseId, 0, false, homeworkId, date);
		AddHomeworkThread thread = new AddHomeworkThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that Removes a Homework in a Module
	 * @param courseId - Course Id
	 * @param moduleId - Module Id
	 * @param homeworkId - Homework Id
	 */
	public void removeHomework(int courseId, int moduleId, int homeworkId) {
		CourseMobile course = this.amadeusFacade.getCourse(courseId);
		String courseName = course.getName();
		int modNumber = getModuleNumber(moduleId, course);
		String sms = this.createMessage("mobile.course.deletedActivity", courseName, modNumber);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Atividade Removida", sms, moduleId, courseId, 0, false, homeworkId, date);
		RemoveHomeworkThread thread = new RemoveHomeworkThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that Updates a Homework in a Module
	 * @param courseId - Course Id
	 * @param moduleId - Module Id
	 * @param homeworkId - Homework Id
	 */
	public void homeworkUpdate(int courseId, int moduleId, int homeworkId) {
		CourseMobile course = this.amadeusFacade.getCourse(courseId);
		String courseName = course.getName();
		int modNumber = getModuleNumber(moduleId, course);
		String sms = this.createMessage("mobile.course.activityUpdate", courseName, modNumber);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Atividade Atualizada", sms, moduleId, courseId, 0, false, homeworkId, date);
		HomeworkUpdateThread thread = new HomeworkUpdateThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that Inserts a Poll in a Module
	 * @param courseId - Course Id
	 * @param moduleId - Module Id
	 * @param pollId - Poll Id
	 */
	public void addPoll(int courseId, int moduleId, int pollId) {
		CourseMobile course = this.amadeusFacade.getCourse(courseId);
		String courseName = course.getName();
		int modNumber = getModuleNumber(moduleId, course);
		String sms = this.createMessage("mobile.course.newPoll", courseName, modNumber);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Enquete Adicionada", sms, moduleId, courseId, 0, false, pollId, date);
		AddPollThread thread = new AddPollThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that Removes a Poll in a Module
	 * @param courseId - Course Id
	 * @param moduleId - Module Id
	 * @param pollId - Poll Id
	 */
	public void removePoll(int courseId, int moduleId, int pollId) {
		CourseMobile course = this.amadeusFacade.getCourse(courseId);
		String courseName = course.getName();
		int modNumber = getModuleNumber(moduleId, course);
		String sms = this.createMessage("mobile.course.deletedPoll", courseName, modNumber);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Enquete Removida", sms, moduleId, courseId, 0, false, pollId, date);
		RemovePollThread thread = new RemovePollThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}

	/**
	 * Method that Alters a Poll in a Module
	 * @param courseId - Course Id
	 * @param moduleId - Module Id
	 * @param pollId - Poll Id
	 */
	public void pollUpdate(int courseId, int moduleId, int pollId) {
		CourseMobile course = this.amadeusFacade.getCourse(courseId);
		String courseName = course.getName();
		int modNumber = getModuleNumber(moduleId, course);
		String sms = this.createMessage("mobile.course.pollUpdate", courseName, modNumber);
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Enquete Atualizada", sms, moduleId, courseId, 0, false, pollId, date);
		PollUpdateThread thread = new PollUpdateThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that Adds a Course in AMADeUs
	 * @param couseId - Course Id
	 */
	public void addCourse(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course course) {
		String courseName = course.getName();
		String sms = Messages.getString("mobile.newCourse");
		sms += this.truncate(courseName, 130-sms.length());
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Novo Curso", sms, -1, course.getId(), 4, false, -1, date);
		AddCourseThread thread = new AddCourseThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	/**
	 * Method that Removes a Course in AMADeUs
	 * @param couseId - Course Id
	 */
	public void removeCourse(br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course course) {
		String courseName = course.getName();
		String sms = Messages.getString("mobile.deletedCourse");
		sms += this.truncate(courseName, 130-sms.length());
		java.util.Date  date = new java.util.Date(System.currentTimeMillis());
		NoticeMobile notice = new NoticeMobile("Curso Removido", sms, -1, course.getId(), 4, false, -1, date);
		RemoveCourseThread thread = new RemoveCourseThread(notice, courseName);
		this.threads.add(thread);
		thread.start();
	}
	
	public AmadeusFacade getAmadeusFacade() {
		return amadeusFacade;
	}

	public void setAmadeusFacade(AmadeusFacade amadeusFacade) {
		this.amadeusFacade = amadeusFacade;
	}

	public FacadeMobile getFacade() {
		return facade;
	}

	public void setFacade(FacadeMobile facade) {
		this.facade = facade;
	}

	public static ArrayList<Thread> getThreads() {
		return threads;
	}

	public static void setThreads(ArrayList<Thread> threads) {
		Receiver.threads = threads;
	}
}