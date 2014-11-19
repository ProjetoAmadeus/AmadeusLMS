/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
 **/

package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.PersonDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Status;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.syncronize.AccessInfo;

public class PersonHibernateDAO extends GenericHibernateDAO<Person, Integer>
		implements PersonDAO {

	public PersonHibernateDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Person> getTeachersByName(String name) {
		Criteria crit = getSession().createCriteria(Person.class);
		crit.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
		List<Person> results = crit.list();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Person> getAssistanceInCourse(Course course, Role role) {
		/*
		 * old: List<Person> results =
		 * getSession().createSQLQuery("SELECT * from person where " +
		 * "id in (SELECT person_id from person_role_course where course_id = "
		 * + course.getId() + " AND role_id = " + role.getId() +
		 * ")").addEntity(Person.class).list();
		 */
		String hqlQuary = "select p from Person as p, PersonRoleCourse as prc"
				+ " where p.id = prc.person.id" + " and prc.course.id = "
				+ course.getId() + " and prc.role.id = " + role.getId();

		List<Person> results = getSession().createQuery(hqlQuary).list();

		return results;
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllEmails() {
		Criteria crit = getSession().createCriteria(Person.class);
		crit.setProjection(Projections.property("email"));
		List<String> emails = crit.list();
		return emails;
	}

	@Override
	public Person getPersonByLogin(String login) {

		StringBuilder hql = new StringBuilder();
		hql.append("select p from Person p " + "where p.accessInfo.login = '"
				+ login + "'");

		Person person = (Person) getSession().createQuery(hql.toString())
				.uniqueResult();
		return person;
	}

	@Override
	public Person getPersonByUserName(String userName) {
		StringBuilder hql = new StringBuilder();
		hql.append("select p from Person p where p.name = '" + userName + "'");
		Person person = (Person) getSession().createQuery(hql.toString())
				.uniqueResult();
		return person;
	}

	/**
	 * By Nailson Cunha
	 */
	@Override
	public int getPersonByTwitterLogin(String screenName) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/amadeus_web", "postgres",
					"postgres");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM person WHERE twitterlogin='" + screenName + "'");
			while(rs.next()){
				int id = rs.getInt("id");
				return id;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
