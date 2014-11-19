/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment.evaluation;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.evaluation.EvaluationDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;

public class EvaluationHibernateDAO extends GenericHibernateDAO<Evaluation, Integer> implements EvaluationDAO {

	//@Override
	@SuppressWarnings("unchecked")
	public List<Evaluation> getAllEvaluationFromCourse(Course course) {
		//TODO
		/*old:List<Evaluation> evaluations = getSession().createSQLQuery("select e.* from evaluation e " +
				
				"join module m on(e.module_id = m.id) " +
				"join course c on(m.course_id = c.id) " +
				"where c.id = "+course.getId()+";").addEntity(Evaluation.class).list();
*/
		String hqlQuery = "select e from Course c join c.modules m join Evaluation e" +
						  " where c.id = "+course.getId();
		List<Evaluation> evaluations = getSession().createQuery(hqlQuery).list();
		
		return evaluations;
	}
}
