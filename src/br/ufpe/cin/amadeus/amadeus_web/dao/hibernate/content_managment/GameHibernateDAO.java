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
import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.GameDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

public class GameHibernateDAO extends GenericHibernateDAO<Game, Integer>
		implements GameDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getAllGameFromCourse(Course course) {
		// TODO Auto-generated method stub
		String hqlQuery = "select g from Course c join c.modules m join Game g" +
		" where c.id = "+course.getId();
		List<Game> game = getSession().createQuery(hqlQuery).list();

		return game;
	}
<<<<<<< HEAD
=======
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean verificarStatusPorGame(List<Person> alunos, Game game) {

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
		
		String hql = "select distinct l.idUser.id from Log l where l.codigo = 7 AND l.idObjeto = " + game.getId() + persons;
		List<Int> posts = getSession().createQuery(hql).list();
		
		boolean retorno = alunos.size() == posts.size();
		
		
		return retorno;
	}
>>>>>>> 661708b07f533da1f47ab2b8c362cb287fdf4631

}
