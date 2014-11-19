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

<<<<<<< HEAD
=======
import org.apache.poi.hssf.record.formula.functions.Int;

>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
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

<<<<<<< HEAD
=======
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

>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631
}
