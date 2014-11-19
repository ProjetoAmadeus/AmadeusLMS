package br.ufpe.cin.amadeus.amadeus_web.dao.register;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.dao.GenericDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.MessengerMessage;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

/**
 * Interface que define os métodos específicos de acesso ao banco para a classe MessengerMessage
 * @author Nailson Cunha
 *
 */
public interface MessengerMessageDAO extends GenericDAO<MessengerMessage, Integer> {

	public List<MessengerMessage> getAllUnreadFromPerson(Person person);

	public List<MessengerMessage> getAllMessengerMessageByPerson(Person person);
}
