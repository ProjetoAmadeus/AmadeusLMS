/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.UserMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.util.Conversor;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidLogonException;
import br.ufpe.cin.amadeus.amadeus_web.facade.Facade;

public class AmadeusFacade{

	private static AmadeusFacade facade;
	
	private AmadeusFacade() {
	}
	
	public static AmadeusFacade getInstance() {
		if (facade == null) {
			facade = new AmadeusFacade();
		}
		return facade;
	}
	
	public UserMobile verifylogin(String login, String password) {
		UserMobile user = null;
		if (login != null && password != null) {
			try {
				AccessInfo ai = new AccessInfo();
				ai.setLogin(login);
				ai.setPassword(password);
				
				ai = Facade.getInstance().logon(ai);
				user = new UserMobile(ai.getId(), ai.getLogin(), ai.getPassword());
			} catch (InvalidLogonException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public ArrayList<String> findLoginsByCourse(int idCourse){
		
		ArrayList<String> returnList = new ArrayList<String>();
		
		Course c = Facade.getInstance().getCoursesById(idCourse);
		List<Person> persons = Facade.getInstance().listStudentsByCourse(c);
		persons.addAll(Facade.getInstance().listTeachersByCourse(c));
		for (Person p : persons){
		   returnList.add(p.getAccessInfo().getLogin());
		}
		return returnList;
	}
	
 	public List<br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile> listCourses(String login) {
		
		List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course> coursesAm = null;
		try {
			AccessInfo ai = Facade.getInstance().searchUserByLogin(login);
			coursesAm = Facade.getInstance().searchCoursesByAccessInfo(ai);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
						
		return Conversor.converterCursos(coursesAm);
	}
	
	public br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile getModule(int idModule) {
		
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module mod = Facade.getInstance().getModuleById(idModule);	    
		ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> lista = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile>();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game g : mod.getGames()){
		   lista.add(Conversor.converterGameToHomework(g));
		}
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum f : mod.getForums()){
		   lista.add(Conversor.converterForumToHomework(f));
		}
		 
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll p : mod.getPolls()){
		   lista.add(Conversor.converterPollToHomework(p));
		}
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject learning : mod.getLearningObjects()){
			lista.add(Conversor.converterLearningObjectToHomework(learning));
		}
		
		br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile m = Conversor.converterModulo(mod);
		m.getHomeworks().addAll(lista);
		 
		return m;
	}
	
	public br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile getHomework(int idHome){
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Homework home = Facade.getInstance().getHomeworkByID(idHome);		
		return Conversor.converterHomework(home);
	}
	
	public br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile getHomework(int id, String tipo){
		br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile retorno = null;
		
		if (tipo.equals(HomeworkMobile.HOMEWORK)){
		  retorno = Conversor.converterHomework(Facade.getInstance().getHomeworkByID(id));
		}
		else if (tipo.equals(HomeworkMobile.FORUM)){
		  retorno = Conversor.converterForumToHomework(Facade.getInstance().getForumById(id));
		} 
		else if (tipo.equals(HomeworkMobile.POLL)){
		  retorno = Conversor.converterPollToHomework(Facade.getInstance().getPollByID(id));
		}
		else if (tipo.equals(HomeworkMobile.GAME)){
		  retorno = Conversor.converterGameToHomework(Facade.getInstance().getGameById(id));
		}

		return retorno;
	}
	
	public br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile getPoll(int idPool){
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll pl = Facade.getInstance().getPollByID(idPool);
		return Conversor.converterPool(pl);
	}
	
	public br.ufpe.cin.amadeus.amadeus_mobile.basics.LearningObjectMobile getLearningObject (int idLearningObject){
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject learning = Facade.getInstance().getLearningObjectById(idLearningObject);
		return Conversor.converterLearningObject(learning);
	}
	
	public br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile getCourse(int idC){
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course c = Facade.getInstance().getCoursesById(idC);
		return Conversor.converterCurso(c);
	}
	
	public br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile getPerson(String login){
		br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile p = null;
		try {
			AccessInfo ai = Facade.getInstance().searchUserByLogin(login);
			p = Conversor.converterPerson(ai.getPerson());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> getGamesLikeHomeworks(int idCourse){
		
		ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile>();
		
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course c = Facade.getInstance().getCoursesById(idCourse);
		List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module> mods = c.getModules();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module m : mods){

		   for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game g : m.getGames()){
			  retorno.add(Conversor.converterGameToHomework(g));
		   }
		}
	
		return retorno;
	}
	
	public ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> getForunsLikeHomeworks(int idCourse){
		
		ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile>();
		
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course c = Facade.getInstance().getCoursesById(idCourse);
		List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module> mods = c.getModules();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module m : mods){

		   for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum f : m.getForums()){
			  retorno.add(Conversor.converterForumToHomework(f));
		   }
		}
	
		return retorno;
	}
	
	public ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> getPollsLikeHomeworks(int idCourse){
		
		ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile>();
		
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course c = Facade.getInstance().getCoursesById(idCourse);
		List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module> mods = c.getModules();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module m : mods){

		   for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll p : m.getPolls()){
			  retorno.add(Conversor.converterPollToHomework(p));
		   }
		}
	
		return retorno;
	}
	
	public ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> getLearningObjectsLikeHomeworks(int idCourse){
		
		ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile> retorno = new ArrayList<br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile>();
		
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course c = Facade.getInstance().getCoursesById(idCourse);
		List<br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module> mods = c.getModules();
		
		for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module m : mods){

		   for (br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject learning : m.getLearningObjects()){
			  retorno.add(Conversor.converterLearningObjectToHomework(learning));
		   }
		}
	
		return retorno;
	}
	
	public void updatePoll(String login, int id, List<br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile> choicesMM){
		
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll pollAW = Facade.getInstance().getPollByID(id);
			
		br.ufpe.cin.amadeus.amadeus_web.domain.register.Person personAW = Facade.getInstance().searchUserByLogin(login).getPerson();
		br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Answer answerAW = new Answer();
		answerAW.setPerson(personAW);
		answerAW.setAnswerDate(new Date());
		pollAW.getAnswers().add(answerAW);
		
		for (int i = 0; i < pollAW.getChoices().size(); i++){
		   pollAW.getChoices().get(i).setVotes(choicesMM.get(i).getVotes());
		   pollAW.getChoices().get(i).setPercentage(choicesMM.get(i).getPercentage());
		}
	}
	
}