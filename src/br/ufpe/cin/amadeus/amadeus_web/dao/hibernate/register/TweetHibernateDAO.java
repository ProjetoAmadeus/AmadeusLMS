package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.register;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.register.TweetDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Tweet;

/**
 * Classe que contém os métodos específicos de acesso ao banco para a classe
 * Tweet
 * 
 * @author Nailson Cunha
 * 
 */
public class TweetHibernateDAO extends GenericHibernateDAO<Tweet, Integer>
		implements TweetDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Tweet> getTweetBetweenDates(Date inicio, Date fim) {
		Criteria criteria = getSession().createCriteria(Tweet.class);
		criteria.add(Restrictions.between("dateOfTweet", inicio,fim));
		return (List<Tweet>) criteria.list();
		
	}

}
