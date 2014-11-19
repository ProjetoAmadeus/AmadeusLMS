/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.UserRequestDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Status;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.UserRequest;

public class UserRequestHibernateDAO extends GenericHibernateDAO<UserRequest,Integer> implements UserRequestDAO{
	
	public UserRequestHibernateDAO() {
		super();
	}
	
	
	@SuppressWarnings("unchecked")
	public int getNumberOfRequestsToProfessor(AccessInfo userInfo, Role teacherRole) {
/* old :
		String sqlQuery = "select * from userrequest where " +
		"status_type = '" + Status.WAITING.ordinal() + "' and teachingrequest = false and course_id in " +
		"(select course_id from person_role_course where " +
		"person_id = " + userInfo.getPerson().getId() +" and " +
		"role_id = " + teacherRole.getId() +")";
		System.out.println("query : " + sqlQuery);
		List<UserRequest> results = getSession().createSQLQuery(sqlQuery).addEntity(UserRequest.class).list();
*/		
		String hqlQuery = "select ur from UserRequest as ur,PersonRoleCourse as prc "+
		                  " where ur.course.id = prc.course.id and prc.person.id = " + userInfo.getPerson().getId() +
		                  " and prc.role.id = " + teacherRole.getId() +
		                  " and ur.statusType = " + Status.WAITING.ordinal() +
		                  " and ur.teachingRequest = " + false;
		Query query = getSession().createQuery(hqlQuery);
		
		
		List<UserRequest> results= query.list();
		return results.size();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRequest> searchTeachingRequest(int personId) {
/*
old:
		String sqlQuery = "SELECT {u.*} from userrequest {u} where u.person_id = '" + personId
		+ "' AND u.course_id IS NULL ";	
		List<UserRequest> results = getSession().createSQLQuery(sqlQuery).addEntity("u",UserRequest.class).list();
*/		

		Criteria criteria = getSession().createCriteria(UserRequest.class);
		criteria.add(Restrictions.eq("person.id", personId));
		criteria.add(Restrictions.isNull("course"));
		List<UserRequest> results = criteria.list();
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRequest> searchAssistanceRequest(Person person,
			Course course) {
/*old:
 		List<UserRequest> results = getSession().createSQLQuery(
				"select * from userrequest where " + "teachingrequest = false and course_id = "
						+ course.getId() + " and person_id = " + person.getId())
				.addEntity("u", UserRequest.class).list();
*/		
		Criteria criteria = getSession().createCriteria(UserRequest.class);
		criteria.add(Restrictions.eq("teachingRequest", false));
		criteria.add(Restrictions.eq("course", course));
		criteria.add(Restrictions.eq("person",person));
		List<UserRequest> results = criteria.list();
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRequest> getPossibleTeachers() {
/*old:
		List<UserRequest> results = getSession().createSQLQuery("SELECT {r.*} from userrequest {r} where " +
				"r.teachingrequest = true and r.status_type = '" + Status.WAITING.ordinal()+"'").addEntity("r",UserRequest.class).list();
*/		
		
		Criteria criteria = getSession().createCriteria(UserRequest.class);
		criteria.add(Restrictions.eq("teachingRequest", true));
		criteria.add(Restrictions.eq("statusType",Status.WAITING));
		List<UserRequest> results = criteria.list();
		return results;	
	}
	
	//	 retorna uma lista de professores
	@SuppressWarnings("unchecked")
	public List<UserRequest> getPossibleAssistants(AccessInfo accessInfo,
			Role role) {
/*old: 
		List<UserRequest> results = getSession()
				.createSQLQuery(
						"SELECT {u.*} from userrequest {u} where u.status_type = '"
								+ Status.WAITING.ordinal()
								+ "' and u.teachingrequest = false and u.course_id in (select course_id from person_role_course where "
								+ "role_id = " + role.getId()
								+ " and person_id = "
								+ accessInfo.getPerson().getId() + ")")
				.addEntity("u", UserRequest.class).list();
*/	
		String hqlQuery = "select ur from UserRequest as ur, PersonRoleCourse as prc "+
		                  " where ur.course.id = prc.course.id"+
		                  " and prc.role.id = " + role.getId() +
		                  " and prc.person.id = " + accessInfo.getPerson().getId() +
		                  " and ur.teachingRequest = " + false +
		                  " and ur.statusType = " + Status.WAITING.ordinal();
		List<UserRequest> results = getSession().createQuery(hqlQuery).list();
		
		return results;
	}
	
	//	 retorna uma lista de professores
	@SuppressWarnings("unchecked")
	public List<UserRequest> getTeacherAssistants(AccessInfo accessInfo, Role role){
/* old:
		String sqlQuery = "SELECT {u.*} from userrequest {u} where u.status_type = '" + Status.APPROVED.ordinal() 
		+ "' and u.teachingrequest = false and u.course_id in (select course_id from person_role_course where role_id = " +role.getId()+" and person_id = " +
		accessInfo.getPerson().getId()+")";
		List<UserRequest> results = getSession().createSQLQuery(sqlQuery).addEntity("u",UserRequest.class).list();
*/		
		String hqlQuery = "select ur from UserRequest as ur,PersonRoleCourse as prc" +
		 				  " where ur.course.id = prc.course.id" +
		 				  " and prc.role.id = " + role.getId() +
		 				  " and prc.person.id = " + accessInfo.getPerson().getId() +
		 				  " and ur.statusType = " + Status.APPROVED.ordinal() +
		 				  " and ur.teachingRequest = " + false;
		List<UserRequest> results = getSession().createQuery(hqlQuery).list();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRequest> requestTeacherOfUser(int personId) {
/*old:
		List<UserRequest> results = getSession().createSQLQuery(
				"SELECT {u.*} from userrequest {u} where u.person_id = '" + personId
						+ "' AND u.teachingrequest = true AND u.course_id IS NULL ").addEntity("u",
				UserRequest.class).list();
*/		
		Criteria criteria = getSession().createCriteria(UserRequest.class);
		criteria.add(Restrictions.eq("person.id", personId));
		criteria.add(Restrictions.eq("teachingRequest", true));
		criteria.add(Restrictions.isNull("course"));
		List<UserRequest> results = criteria.list();
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRequest> getRequestsToAdmin(){
		List<UserRequest> results = null;
		
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("statusType",	Status.WAITING));
		crit.add(Restrictions.eq("teachingRequest",true));
		results = crit.list();
		return results;
	}
}



