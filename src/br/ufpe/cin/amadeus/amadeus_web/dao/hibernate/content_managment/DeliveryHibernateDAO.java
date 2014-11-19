/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.DeliveryDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Delivery;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;

public class DeliveryHibernateDAO extends GenericHibernateDAO<Delivery, Integer> 
implements DeliveryDAO{

	@SuppressWarnings("unchecked")
	public int getNumberOfDoneHomeworks(AccessInfo userInfo, Role role) {
/* *old: 				
		List<Delivery> results = getSession().createSQLQuery("select * from delivery where " +
						"person_id =" +	userInfo.getPerson().getId() +" and homework_id in " +
						"(select id from homework where module_id in " +
						"(select id from module where course_id in " +
						"(select course_id from person_role_course where role_id = " + role.getId() +
						" and person_id =" + userInfo.getPerson().getId() + ")))").addEntity(Delivery.class).list();
*/		
		String hqlQuery = "select d from Delivery d,Homework as h,Module as m,PersonRoleCourse as prc where "+
        "d.person.id = " + userInfo.getPerson().getId()+ 
        " h.module.id = m.id and prc.course.id = m.course.id and prc.role.id = " + role.getId() +
        "prc.person.id = " + userInfo.getPerson().getId();
		
		List<Delivery> results = (List<Delivery>) getSession().createQuery(hqlQuery);
		
		return results.size();
	}
	
}
