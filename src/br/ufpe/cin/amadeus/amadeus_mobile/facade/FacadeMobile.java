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
import java.util.List;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.LearningObjectMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.UserMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.bussiness.CourseBussinessMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.bussiness.HomeworkBussinessMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.bussiness.LoginBussinessMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.bussiness.NoticeBussinessMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.bussiness.PoolBussinessMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.bussiness.ICourseBussinessMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.bussiness.IHomeworkBussinessMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.bussiness.ILoginBussinessMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.bussiness.INoticeBussinessMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.bussiness.IPoolBussinessMobile;


public class FacadeMobile {

	private ILoginBussinessMobile cadLogin;

	private ICourseBussinessMobile cadCurso;

	private INoticeBussinessMobile cadNotice;
	
	private IHomeworkBussinessMobile cadHomework;
	
	private IPoolBussinessMobile cadPool;

	private static FacadeMobile facade;
	
	private static AmadeusFacade amadeusFacade;
	
	public FacadeMobile() {
		this.cadLogin = new LoginBussinessMobile();
		this.cadCurso = new CourseBussinessMobile();
		this.cadNotice = new NoticeBussinessMobile();
		this.cadHomework = new HomeworkBussinessMobile();
		this.cadPool = new PoolBussinessMobile();
	}
	
	public static FacadeMobile getInstance() {
		if(facade == null) {
			facade = new FacadeMobile();
		}
		if (amadeusFacade == null){
			amadeusFacade = AmadeusFacade.getInstance();
		}
		return facade;
	}
	

	public UserMobile verifyLogin(String login, String senha) {
		UserMobile user = amadeusFacade.verifylogin(login, senha);
	    return user;
	}

	public List<CourseMobile> listCourses(String login) {
//		return cadCurso.listarCursos(login);		
		return amadeusFacade.listCourses(login);
	}

	public br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile getCourse(int idCourse, String login) {
		return cadCurso.getCourse(idCourse, login);
	}

	public List<NoticeMobile> getNoticesCourse(int idCourse, String login) {
		return cadNotice.getNoticesCourse(idCourse, login);
	}
	
	public List<NoticeMobile> getNoticesModule(int idModule, String login) {
		return cadNotice.getNoticesModule(idModule, login);
	}
	
	public List<NoticeMobile> getNoticesAmadeus(String login) {
		return cadNotice.getNoticesAmadeus(login);
	}
	
	public NoticeMobile getNotice(int idNotice, String login) {
		return cadNotice.getNotice(idNotice, login);
	}
	
	public void addNotice(NoticeMobile n){
		cadNotice.addNotice(n);
	}
	
	public NoticeMobile getNoticeSMS(int idNotice) {
		return cadNotice.getNoticeSMS(idNotice);
	}
	
	public int existNotice(NoticeMobile not) {
		return cadNotice.existNotice(not);
	}
	
	public ModuleMobile getModule(int idModule) {
	    return amadeusFacade.getModule(idModule);
	}
	
	public void updateCourse(CourseMobile c, String login){
		cadCurso.updateCourse(c, login);
	}
	
	public HomeworkMobile getHomework(int id){
		return amadeusFacade.getHomework(id);
	}
	
	public PollMobile getPoll(int id){
		return amadeusFacade.getPoll(id);
	}
	
	public LearningObjectMobile getLearningObject (int id){
		return amadeusFacade.getLearningObject(id);
	}
	
	public boolean sent(int idNotice) {
		return cadNotice.sent(idNotice);
	}
		
	public int pagesQuantity(List elements) {
		int size = elements.size();
		int result = -1;
		if(size <= 5) {
			result = 1;
		}else {
			result = size / 5;
			if(elements.size() % 5 != 0) {
				result++;
			}
		}
		return result;
	}
	
	//Método que retorna todas os homeworks e Enquetes do usuário
	public List<Object> getAllCoursesActivities(String login) {
		return this.cadCurso.getAllCoursesActivities(login);
	}
	
	//método que retorna todos os materiais de todos os cursos no qual o login está matriculado.
	public List<MaterialMobile> getAllCoursesMaterials(String login){
		return this.cadCurso.getAllCoursesMaterials(login);
	}
	
	//	Método que retorna todas os homeworks e Enquetes do módulo do curso
	public List<Object> getModuleActivities(int idModule) {
		ModuleMobile m = this.getModule(idModule);
		List<Object> l = new ArrayList<Object>();
		l.addAll(m.getHomeworks());
		return l;
	}
	
//	Método que retorna todas os homeworks e Enquetes do curso
	public List<Object> getCourseActivities(int idCourse) {
		
		List<Object> retorno = new ArrayList<Object>();
		List<HomeworkMobile> homes = null;
		br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile c = this.getCourse(idCourse, "bigode");
		for (ModuleMobile mod : c.getModules()){
			homes = mod.getHomeworks();
			retorno.addAll(homes);
		}
		
		retorno.addAll(amadeusFacade.getForunsLikeHomeworks(idCourse));
		retorno.addAll(amadeusFacade.getGamesLikeHomeworks(idCourse));		
		retorno.addAll(amadeusFacade.getPollsLikeHomeworks(idCourse));
		retorno.addAll(amadeusFacade.getLearningObjectsLikeHomeworks(idCourse));
		
		for(int i =0; i< retorno.size(); i++) {
			HomeworkMobile h =(HomeworkMobile)retorno.get(i);
			h.setIdCourse(idCourse);
			retorno.set(i, h);
		}
		
		return retorno;

	}
		
	public void updatePoll(String login, int id, List<br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile> choices){
		amadeusFacade.updatePoll(login, id, choices);
	}
	
	public PersonMobile getPerson(String login){
//		return amadeusFacade.getPerson(login);
		return cadLogin.getLogin(login);
	}
	
	public HomeworkMobile getHomework(int id, String tipo) {
		return amadeusFacade.getHomework(id, tipo);
	}
	
	public void updateLogin(PersonMobile p){
		cadLogin.updateLogin(p);
	}
	
	public PersonMobile getLogin(String login) {
		return cadLogin.getLogin(login);
	}
	
	public void insertLogin(String login){
		cadLogin.insertLogin(login);
	}
	
	public void updateNotice(NoticeMobile n, String login) {
		cadNotice.updateNotice(n, login);
	}
	
	public int getLastId(int idCourse) {
		return this.cadNotice.getLastId(idCourse);
	}
}	