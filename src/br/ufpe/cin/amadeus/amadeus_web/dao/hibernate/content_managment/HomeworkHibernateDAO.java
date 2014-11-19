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

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.HomeworkDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Homework;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;

public class HomeworkHibernateDAO extends GenericHibernateDAO<Homework, Integer> 
		implements HomeworkDAO {

		
	@SuppressWarnings("unchecked")
	public int getTotalUserHomeworks(AccessInfo userInfo, Role studentRole) {
		
		int ret = 0;
/* *old:		
		List<Homework> results =getSession().createSQLQuery("select * from homework where id in " +
					"(select id from homework where module_id in " +
					"(select id from module where course_id in " +
					"(select course_id from person_role_course where role_id = " +
					studentRole.getId()+" and person_id = " + userInfo.getPerson().getId() +
					")))").addEntity(Homework.class).list();
*/		
//		select h.* from  homework as h, module as m, person_role_course as prc where
//		 h.module_id = m.id and prc.course_id = m.course_id and  prc.role_id = 1 and prc.person_id = 170
		String hqlQuery = "select h from Homework as h,Module as m,PersonRoleCourse as prc where "+
		                  "h.module.id = m.id and prc.course.id = m.course.id and prc.role.id = " + studentRole.getId() +
		                  "prc.person.id = " + userInfo.getPerson().getId();
		List<Homework> results = (List<Homework>)getSession().createQuery(hqlQuery).list();
		if(results != null)
			ret = results.size();
		
		return ret;
	}
	public static void main(String args[]){
		
	}
}


