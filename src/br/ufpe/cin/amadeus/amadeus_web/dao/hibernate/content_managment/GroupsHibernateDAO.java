package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.GroupsDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Groups;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Log;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Person_Groups;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.syncronize.TimelineItem;

public class GroupsHibernateDAO extends GenericHibernateDAO<Groups, Integer>
implements GroupsDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getLogsByDayAndGroup(String data, int groupId) {
		String sqlQuery = "SELECT l.* as frequency FROM Log l, Person p, Person_Groups pg, Groups g " +
				" WHERE pg.group_id = " + groupId +
				" AND pg.person_id = p.id " +
				" AND l.person_id = p.id " +
				" AND '" + data + " 00:00:00' <= l.date " +
				" AND '" + data + " 23:59:59' >= l.date " +
				" AND g.id = " + groupId +
				" AND g.date < l.date " +
				" ORDER BY l.date ASC";
		
		List<Log>  list = getSession().createSQLQuery(sqlQuery).addEntity("l", Log.class).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Groups> getGroupsByCourseID(int id) {
		String hqlQuery = "SELECT g FROM Groups g WHERE g.curso.id = " + id;
		List<Groups> results = (List<Groups>) getSession().createQuery(hqlQuery).list();
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getPersonByGroupsID(int id) {
		String hqlQuery = "SELECT p FROM Person_Groups pg, Person p WHERE pg.groups.id = " + id + " AND pg.pessoa.id = p.id";
		List<Person> results = getSession().createQuery(hqlQuery).list();

		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TimelineItem> getTimelineByGroupsID(int id) {
		
		String hqlQuery = "SELECT g.id as idGroup, to_char(date_trunc('day', l.date),'DD/MM/YYYY') as date, count(l.*) as frequency" +
				" FROM Log l, Person p, Person_Groups pg, Groups g where pg.group_id = " + id
				+ " AND pg.person_id = p.id"
				+ " AND l.person_id = p.id"
				+ " AND g.id = pg.group_id"
				+ " AND g.date < l.date"
				+ " GROUP BY date_trunc('day', l.date), g.id ORDER BY date_trunc('day', l.date);";
		List<Object[]> results = getSession().createSQLQuery(hqlQuery).list();
		
		List<TimelineItem> retorno = new ArrayList<TimelineItem>();
		
		for(int i=0;i<results.size();i++){
			TimelineItem item = new TimelineItem();
			Object[] temp = results.get(i);
			item.setIdGroup((Integer)temp[0]);
			item.setDate((String)temp[1]);
			item.setFrequency(((BigInteger)temp[2]).intValue());
			
			retorno.add(item);
		}
		
		return retorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean personHaveGroup(int courseId, int personId) {
		
		String hqlQuery = "Select p from Person_Groups p where p.pessoa.id = " + personId + "  and p.groups.curso.id = " + courseId;
		
		List<Person_Groups> list = getSession().createQuery(hqlQuery).list();
		return list.size() == 0;
	}

	@Override
	public Module getUltimoModulo(int courseId) {
		
		String hqlQuery = "SELECT m FROM Module m WHERE m.id IN (SELECT MAX(m.id) FROM Module m WHERE m.course.id = " + courseId + ")";
		
		Module ultimoModulo = (Module) getSession().createQuery(hqlQuery).uniqueResult();
		
		return ultimoModulo;
	}
}
