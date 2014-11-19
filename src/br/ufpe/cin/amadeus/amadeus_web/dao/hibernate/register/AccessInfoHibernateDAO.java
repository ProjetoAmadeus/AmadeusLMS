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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.AccessInfoDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;

public class AccessInfoHibernateDAO extends
		GenericHibernateDAO<AccessInfo, Integer> implements AccessInfoDAO {
	
	public AccessInfoHibernateDAO(){
		super();
	}
	
	@SuppressWarnings("unchecked")
	public List<AccessInfo> getUsersByLogin(String login){
/*old:
	List<AccessInfo> results = getSession().createSQLQuery("SELECT * from accessinfo where login = '"+ login + "'").addEntity(AccessInfo.class).list();
 */		
		Criteria criteria = getSession().createCriteria(AccessInfo.class);
		criteria.add(Restrictions.eq("login", login));
		List<AccessInfo> results = criteria.list();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<AccessInfo> getAllUserOrderByName(){
		String hqlQuery = "select a from AccessInfo as a order by a.person.name asc";
		List<AccessInfo> results = getSession().createQuery(hqlQuery).list();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<AccessInfo> searchUsers(String userName, Integer userType, Integer courseId) {
		
		String hql = "from AccessInfo as a where";
		
		if(!userName.trim().equals("")) {
			hql += " a.person.name like '%"+userName+"%' and";
		}
		if(userType != -1) {
			hql += " a.typeProfile = "+userType+" and";
		}
		
		if(hql.substring(hql.length() - 5, hql.length()).equals("where")){
			hql = hql.substring(0, hql.length() - 5);
		}
		if(hql.substring(hql.length() - 3, hql.length()).equals("and")){
			hql = hql.substring(0, hql.length() - 3);
		}
		
		Query query = getSession().createQuery(hql);
		
		return query.list();
	}
	
}
