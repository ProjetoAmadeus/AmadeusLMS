package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.AmadeusDroidHistoricDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.AmadeusDroidHistoric;

public class AmadeusDroidHistoricHibernateDAO extends GenericHibernateDAO<AmadeusDroidHistoric, Integer> implements
		AmadeusDroidHistoricDAO {

	@Override
	public AmadeusDroidHistoric findById(Integer id, boolean lock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AmadeusDroidHistoric> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AmadeusDroidHistoric> findByExample(
			AmadeusDroidHistoric exampleInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AmadeusDroidHistoric> findByExample(
			AmadeusDroidHistoric exampleInstance, String[] excludeProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmadeusDroidHistoric findUniqueByExample(
			AmadeusDroidHistoric exampleInstance, String[] excludeProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmadeusDroidHistoric findUniqueByExample(
			AmadeusDroidHistoric exampleInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmadeusDroidHistoric makePersistent(AmadeusDroidHistoric historic) {
		if (historic.getId() == 0) {
			getSession().save(historic);
		}else{
			getSession().merge(historic);
		}
		return historic;
	}

	@Override
	public AmadeusDroidHistoric merge(AmadeusDroidHistoric entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeTransient(AmadeusDroidHistoric entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AmadeusDroidHistoric> getHistoric() {
		StringBuilder hql = new StringBuilder();
		hql.append("From AmadeusDroidHistoric");
		
		List<AmadeusDroidHistoric> result = getSession().createQuery(hql.toString()).list();
		return result;
	}

}
