/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.CourseDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.NotNullOrBlankPropertySelector;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Archive;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.middleware.services.session.Key;

import static org.hibernate.criterion.Expression.*;

public class CourseHibernateDAO extends GenericHibernateDAO<Course, Integer>
		implements CourseDAO {

	@SuppressWarnings("unchecked")
	public List<Course> searchCoursesByUser(AccessInfo userInfo){
		
		
/*old		
		List<Course> results = getSession().createSQLQuery("SELECT * from course where " +
				"id in (SELECT course_id from person_role_course where person_id = "
				+userInfo.getPerson().getId() + ")").addEntity(Course.class).list();
*/	
		String hqlQuary = "select c from Course c,PersonRoleCourse prc where " +
						  "c.id = prc.course.id and prc.person.id = "+ userInfo.getPerson().getId();
		List<Course> results = getSession().createQuery(hqlQuary).list();
		
		return results;
		
		
	}
	
	@Override
	public List<Course> getCoursesByUser(AccessInfo userInfo) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("select c from Course c, " +
				    "PersonRoleCourse prc, " +
				    "Person p, " +
				    "AccessInfo aci " +
				    "where prc.course.id = c.id " +
				    "and prc.person.id = p.id " +
				    "and p.accessInfo.id = aci.id " +
				    "and aci.login = '"+userInfo.getLogin()+"' " +
				    "and aci.password = '"+userInfo.getPassword()+"'");
		
		List<Course> results = getSession().createQuery(hql.toString()).list();
					
		return results;
	}
		
	@SuppressWarnings("unchecked")
	public List<Course> getCoursesByKeyword(Keyword key){
		
/*old:		List<Course> results = getSession().createSQLQuery("SELECT * from course where " +
				"id in (SELECT course_id from keywordsofcourse where keywords_id = "
				+ key.getId() + ")").addEntity(Course.class).list();
*/		
		String hqlQuery = "select c from Course c join c.keywords k where k.id =  " + key.getId();
		List<Course> results = getSession().createQuery(hqlQuery).list();
		
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Course> getCoursesByContent(String cont){
		Criteria crit = getSession().createCriteria(Course.class);
		crit.add(Restrictions.ilike("content", cont, MatchMode.ANYWHERE));
		List<Course> results = crit.list();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getCoursesByObjectives(String objs){
		Criteria crit = getSession().createCriteria(Course.class);
		crit.add(Restrictions.ilike("objectives", objs, MatchMode.ANYWHERE));
		List<Course> results = crit.list();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getCoursesByName(String name){
		Criteria crit = getSession().createCriteria(Course.class);
		crit.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
		List<Course> results = crit.list();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getCoursesByProfessors(List<Person> professors){
		List<Course> results = new ArrayList<Course>();
		
		java.util.Iterator<Person> iProfessors = professors.iterator();
		while(iProfessors.hasNext()){
			Person professor = iProfessors.next();
/*old:			List<Course> partialResults = getSession().createSQLQuery("SELECT * from course where " +
					"id in (SELECT course_id from person_role_course where person_id = "
					+ professor.getId() + ")").addEntity(Course.class).list();
*/			
			String hql = "select c from Course c,PersonRoleCourse prc where " +
				         "c.id = prc.course.id and prc.person.id = " + professor.getId();
			List<Course> partialResults = getSession().createQuery(hql).list();
			if(!partialResults.isEmpty()){
				java.util.Iterator<Course> iPartialResults = partialResults.iterator();
				while(iPartialResults.hasNext()){
					results.add(iPartialResults.next());
				}
			}
		}
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getCoursesByInitialDate(Date initialDate){
		
		String hqlQuery = "select c from Course c where c.initialCourseDate = :date ";
		Query objQuery = getSession().createQuery(hqlQuery);  
		objQuery.setDate("date", initialDate); 
		List<Course> results = objQuery.list();
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getCoursesByFinalDate(Date finalDate){
		
		String hqlQuery = "select c from Course c where c.finalCourseDate = :date ";
		Query objQuery = getSession().createQuery(hqlQuery);  
		objQuery.setDate("date", finalDate); 
		List<Course> results = objQuery.list();
		
		return results;
	}
	
	public List<Course> getCoursesByAdvancedRule(String name, String professorName, Date initialDate, Date finalDate){
		Course course = new Course();
		course.setName(name);
		course.setInitialCourseDate(initialDate);
		course.setFinalCourseDate(finalDate);

		Example courseExample = Example.create(course)
			.setPropertySelector(new NotNullOrBlankPropertySelector()) //elimina da consulta as propriedades que s�o nulas ou vazias
			.excludeZeroes()
			.ignoreCase()
			.enableLike(MatchMode.ANYWHERE);
			
		Criteria crit = getSession().createCriteria(Course.class);
		crit.add(courseExample)
			.createCriteria("professor")
				.add(Restrictions.ilike("name", professorName, MatchMode.ANYWHERE));
		List<Course> results = crit.list();
		
		return results;
	}
	
	@Override
	public Course makePersistent(Course course) {
		if(course.getId() == 0) {
			getSession().save(course);
		} else {
			getSession().merge(course);
		}
		return course;
	}
	
	/**
	 * increase the popularity of the keyword.
	 * 
	 * @param course
	 */
	public void incrementPopularityKeyword(int courseId, Set<Keyword> keywords) {
		for (Keyword keyword : keywords) {
/*old:			Integer idMyKeyword = (Integer) getSession().createSQLQuery("SELECT keywords_id from keywordsofcourse where " +
					"course_id = "+ courseId + " and keywords_id = "+ keyword.getId() +";").uniqueResult();
			*/
			String hqlQuery = "select k.id from Course c join c.keywords k where k.id =  "+keyword.getId()+
			" and c.id = "+courseId;
			Integer idMyKeyword = (Integer)getSession().createQuery(hqlQuery).uniqueResult();
			if(idMyKeyword == null) {
				keyword.setPopularity(keyword.getPopularity() + 1);
			}
		}
	}
	
	/**
	 * Decrementa a popularity da keyword
	 * a keyword.
	 * 
	 * @param course
	 */
	@SuppressWarnings("unchecked")
	public void decrementPopularityKeyword(int courseId, Set<Keyword> keywords) {
		try {
			Set<Keyword> newListKeywordsOfCourse = keywords;
			//Obt�m todas as keywords relacionada a este curso no bd.
			/*old: List<Keyword> oldListKeywordsOfCourseBD = getSession().createSQLQuery("SELECT k.* from keyword k " +
					"join keywordsofcourse kc on(kc.keywords_id = k.id and kc.course_id = "
					+ courseId+");").addEntity(Keyword.class).list();
			*/
			String hqlQuery = "select c.keywords from Course c where c.id = "+courseId;
			List<Keyword> oldListKeywordsOfCourseBD = getSession().createQuery(hqlQuery).list();
			
			for (Keyword keywordDB : oldListKeywordsOfCourseBD) {
				if (!this.listContainsKeyword(newListKeywordsOfCourse, keywordDB)) {
					keywordDB.setPopularity(keywordDB.getPopularity() - 1);
					getSession().update(keywordDB);
				}
			}
			
			this.deleteKeywordsOrphan();
			
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Verifica se uma determinada keyword existe em uma determinada 
	 * lista de keywords pelo seu id.
	 * 
	 * @param keywords
	 * @param keyword
	 * @return true or false
	 */
	public boolean listContainsKeyword(Set<Keyword> keywords, Keyword keyword) {
		boolean retorno = false;
		for (Keyword k : keywords) {
			if(k.getId() == keyword.getId()) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}

	public boolean courseNameExist(Course course) {
		boolean retorno = false;
		
		try {
			Criteria crit = getSession().createCriteria(getPersistentClass());
			crit.add( eq("name", course.getName()).ignoreCase() );
			
			Course c = (Course) crit.uniqueResult();
			
			if(c != null && c.getId() != course.getId()) {
				retorno = true;
			}
		} catch (NonUniqueResultException e) {
			retorno = true;
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public void deleteKeywordsOrphan(){//TODO Discutir sobre esse m�todo ele n�o faz sentido.
		getSession().flush();
//		List<Keyword> keywordOrphan = getSession().createSQLQuery(
//				"select * from keyword "+
//				"left join keywordsofcourse kc on(keyword.id = kc.keywords_id)" +
//				"where keywords_id is null")
//				.addEntity(Keyword.class).list();
		
		String hql = "select k from Course c right outer join c.keywords k where c.id = null";
		List<Keyword> keywordOrphan = getSession().createQuery(hql).list();
		for (Keyword kOrphan : keywordOrphan) {
			getSession().delete(kOrphan);
		}
	}

	
}
