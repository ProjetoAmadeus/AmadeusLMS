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

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.RoleDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

public class RoleHibernateDAO extends GenericHibernateDAO<Role, Integer> 
implements RoleDAO{

	@SuppressWarnings("unchecked")
	public List<Role> getRoleByPersonInCourse(Person person, Course course) {
/*old:
 		List<Role> results = getSession().createSQLQuery("SELECT * from role where " +
				"id in (SELECT role_id from person_role_course where course_id = " + course.getId() + " AND person_id= " + person.getId() + ")").addEntity(Role.class).list();
*/		
		String hqlQuery = "select r from Role as r, PersonRoleCourse as prc" +
						  " where r.id = prc.role.id" +
						  " and prc.course.id = " + course.getId() +
						  " and prc.person.id = " + person.getId();
		
		List<Role> results = getSession().createQuery(hqlQuery).list();
		
		return results;
	}
	
}
