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

import org.apache.poi.hssf.record.formula.functions.Int;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.ForumDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

public class ForumHibernateDAO extends GenericHibernateDAO<Forum, Integer> implements ForumDAO {

	@Override
	public List<Forum> getListForum() {
		
		StringBuilder hql = new StringBuilder();
		hql.append("Select f from Forum f");
		
		List<Forum> results = getSession().createQuery(hql.toString()).list();
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean verificarStatusPorForum(List<Person> alunos, Forum forum) {

		String persons = " AND l.idUser.id IN (";
		
		String ids = "";
		
		for (int i = 0; i < alunos.size(); i++)
		{
			if(ids.length() != 0)
			{
				ids += ",";
			}
			ids += alunos.get(i).getId();
		}
		
		persons += ids + ")";
		
		String hql = "select distinct l.idUser.id from Log l where l.codigo = 4 AND l.idObjeto = " + forum.getId() + persons;
		List<Int> posts = getSession().createQuery(hql).list();
		
		boolean retorno = alunos.size() == posts.size();
		
		
		return retorno;
	}

}
