package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.MessengerMessageDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.MessengerMessage;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

/**
 * Classe que contém os métodos específicos de acesso ao banco para a classe MessengerMessage
 * @author Nailson Cunha
 *
 */
public class MessengerMessageHibernateDAO extends GenericHibernateDAO<MessengerMessage, Integer>
	implements MessengerMessageDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<MessengerMessage> getAllUnreadFromPerson(Person person) {
		Criteria criteria = getSession().createCriteria(MessengerMessage.class);
		criteria.add(Restrictions.eq("receiver", person))
			.add(Restrictions.eq("read", false));
		criteria.addOrder(Order.desc("date"));
		return (List<MessengerMessage>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessengerMessage> getAllMessengerMessageByPerson(Person person) {
		Criteria criteria = getSession().createCriteria(MessengerMessage.class);
		criteria.add(Restrictions.eq("receiver", person));
		criteria.addOrder(Order.desc("date"));
		return (List<MessengerMessage>) criteria.list();
	}

}
