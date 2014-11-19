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
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.KeywordDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword;

public class KeywordHibernateDAO extends GenericHibernateDAO<Keyword, Integer> implements
		KeywordDAO {

	@SuppressWarnings("unchecked")
	public List<Keyword> getMostPopularKeywords() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Keyword.class);
		criteria.setMaxResults(50);
		criteria.addOrder(Order.desc("popularity"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public Keyword getKeywordByName(String name){
		Keyword result = new Keyword();
		Criteria crit = getSession().createCriteria(Keyword.class);
		crit.add(Restrictions.ilike("name", name, MatchMode.EXACT));
		List<Keyword> query = crit.list();
		
		if(query.isEmpty()){
			Criteria crit2 = getSession().createCriteria(Keyword.class);
			crit2.add(Restrictions.ilike("name", name, MatchMode.END));
			query = crit2.list();
		}
		if(!query.isEmpty()){
			result = query.get(0);
		}
		
		return result;
	}
	
	public Keyword getKeywordWithIdByName(String name){
		Keyword result; 
		
		Criteria crit = getSession().createCriteria(Keyword.class);
		crit.add(Restrictions.ilike("name", name.trim(), MatchMode.EXACT));
		result = (Keyword) crit.uniqueResult();
		
		if(result == null) {
			result = new Keyword();
			result.setName(name);
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Keyword> getKeywordsByCourse(Course c){
		
/* *old:
        List<Keyword> result = new ArrayList<Keyword>();
 		result = getSession().createSQLQuery("SELECT * from keyword where " +
				"id in (SELECT keywords_id from keywordsofcourse where course_id = "
				+ c.getId() + ")").addEntity(Keyword.class).list();
*/		
		Criteria criteria = getSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("id",c.getId()));
		List<Keyword> result = new ArrayList<Keyword>(((List<Course>)criteria.list()).get(0).getKeywords());
		
		return result;
	}

}
