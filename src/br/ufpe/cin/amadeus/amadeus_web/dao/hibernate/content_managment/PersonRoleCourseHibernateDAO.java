/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.PersonRoleCourseDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.PersonRoleCourse;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

public class PersonRoleCourseHibernateDAO extends GenericHibernateDAO<PersonRoleCourse, Integer> implements PersonRoleCourseDAO {

	@SuppressWarnings("unchecked")
	public  List<Person> searchUsersByCoursesAndRole(Course course, Role role){
/*old:		
		List<Person> results = getSession().createSQLQuery("SELECT * from person where " +
				"id in (SELECT person_id from person_role_course where course_id = " + course.getId() + " AND role_id= " + role.getId() + ")").addEntity(Person.class).list();
*/		
		String hqlQuery = "select p from Person as p, PersonRoleCourse prc" +
						  " where p.id = prc.person.id" +
						  " and prc.course.id = " + course.getId()+
						  " and prc.role.id = " + role.getId()+" order by p.name asc";
		List<Person> results = getSession().createQuery(hqlQuery).list();
		
		return results;
	}
	
	public List<PersonRoleCourse> getStudentByUser(AccessInfo userInfo){
		

		StringBuilder hql = new StringBuilder();
		hql.append("select prc from PersonRoleCourse prc, " +
				    "Person p, " +
				    "AccessInfo aci " +
				    "where prc.person.id = p.id " +
				    "and p.accessInfo.id = aci.id " +
				    "and aci.login = '"+userInfo.getLogin()+"' " +
				    "and aci.password = '"+userInfo.getPassword()+"'");
		
		@SuppressWarnings("unchecked")
		List<PersonRoleCourse> results = getSession().createQuery(hql.toString()).list();
		
		return results;
	}
	
	public int getNumberOfStudentsInCourse(Course course, Role role){
		int result = this.findUsersRegisteredInCourseByRole(course, role).size();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public  boolean isRegisteredUser(Person user, Course course){
	/*old
		List<PersonRoleCourse> results = getSession().createSQLQuery("SELECT * from person_role_course where course_id = " + course.getId() + " AND person_id= " + user.getId()).addEntity(PersonRoleCourse.class).list();
	*/	
		Criteria criteria = getSession().createCriteria(PersonRoleCourse.class);
		criteria.add(Restrictions.eq("course.id", course.getId()));
		criteria.add(Restrictions.eq("person.id", user.getId()));
		List<PersonRoleCourse> results = criteria.list(); 
		if(!results.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public  List<PersonRoleCourse> findUsersRegisteredInCourseByRole(Course course, Role role){
		Criteria crit = getSession().createCriteria(PersonRoleCourse.class);
		crit.add(Restrictions.eq("course.id", course.getId()));
		crit.add(Restrictions.eq("role.id", role.getId()));
		
		List<PersonRoleCourse> results = crit.list();
		
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<PersonRoleCourse> canAssistanceRequest(Person person, Course course, Role role) {
/*old:		
		List<PersonRoleCourse> results = getSession().createSQLQuery("SELECT * from person_role_course where course_id = " + course.getId() + " AND person_id= " + person.getId() + " AND role_id= " + role.getId()).addEntity(PersonRoleCourse.class).list();
*/		
		Criteria criteria = getSession().createCriteria(PersonRoleCourse.class);
		criteria.add(Restrictions.eq("course.id", course.getId()));
		criteria.add(Restrictions.eq("person.id", person.getId()));
		criteria.add(Restrictions.eq("role.id", role.getId()));
		
		List<PersonRoleCourse> results = criteria.list();
		
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isStudent(Person person, Course course, Role role) {
/* * old:
		List<PersonRoleCourse> results = getSession().createSQLQuery("SELECT * from person_role_course where course_id = " 
				+ course.getId() + " AND person_id= " + person.getId() + " AND role_id= " + role.getId()).addEntity(PersonRoleCourse.class).list();
*/		
		Criteria criteria = getSession().createCriteria(PersonRoleCourse.class);
		criteria.add(Restrictions.eq("course.id",course.getId()));
		criteria.add(Restrictions.eq("person.id", person.getId()));
		criteria.add(Restrictions.eq("role.id", role.getId()));
		List<PersonRoleCourse> results = criteria.list();
		
		if(!results.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	// lista de PersonRoleCourse onde o usuario e monitor ou professor em um curso em particular
	@SuppressWarnings("unchecked")
	public List<PersonRoleCourse> getPossibleTeacherOrAssistantsInCourse(
			Person person, Role roleA, Role roleT, Course course) {
/*old:		List<PersonRoleCourse> results = getSession().createSQLQuery(
				"SELECT * from person_role_course where course_id = "
						+ course.getId() + " AND person_id= "
						+ person.getId() + " AND ( role_id= "
						+ roleA.getId() + " OR role_id= " + roleT.getId()
						+ " )").addEntity(PersonRoleCourse.class).list();
*/
		Criteria criteria = getSession().createCriteria(PersonRoleCourse.class);
		criteria.add(Restrictions.eq("course.id", course.getId()));
		criteria.add(Restrictions.eq("person.id",person.getId()));
		criteria.add(Restrictions.or(Restrictions.eq("role.id", roleA.getId()), Restrictions.eq("role.id",roleT.getId())));
		List<PersonRoleCourse> results = criteria.list();
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonRoleCourse> getByRoleInCourse(Person person, Course course, Role role) {
/*old:		List<PersonRoleCourse> results = getSession().createSQLQuery("SELECT * from person_role_course where course_id = " + course.getId() 
				+ " AND person_id= " + person.getId() + " AND role_id= " + role.getId()).addEntity(PersonRoleCourse.class).list();
	*/	
		Criteria criteria = getSession().createCriteria(PersonRoleCourse.class);
		criteria.add(Restrictions.eq("course.id", course.getId()));
		criteria.add(Restrictions.eq("person.id",person.getId()));
		criteria.add(Restrictions.eq("role.id",role.getId()));
		List<PersonRoleCourse> results = criteria.list();
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonRoleCourse> getStudentRolesCourses(int studentId) {
		Criteria critRoles = getSession().createCriteria(PersonRoleCourse.class);
		critRoles.add(Restrictions.eq("person.id", studentId));
		List<PersonRoleCourse> roles = critRoles.list();
		
		return roles;
	}
}
