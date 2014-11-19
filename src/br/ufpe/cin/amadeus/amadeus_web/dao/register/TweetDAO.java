package br.ufpe.cin.amadeus.amadeus_web.dao.register;

import java.util.Date;
import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.dao.GenericDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Tweet;

/**
 * Interface que define os métodos específicos de acesso ao banco para a classe
 * Tweet
 * 
 * @author Nailson Cunha
 * 
 */
public interface TweetDAO extends GenericDAO<Tweet, Integer> {

	List<Tweet> getTweetBetweenDates(Date inicio, Date fim);

}
