package br.ufpe.cin.amadeus.amadeus_web.domain.register;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufpe.cin.amadeus.amadeus_web.util.TweetInteractionType;

/**
 * Classe responsável por representar os Tweets monitorados através da
 * ferramenta de Interação Social. Desenvolvida em trabalho conjunto com o Prof.
 * Petrônio do IFPB.
 * 
 * @author Nailson Cunha
 * 
 */

@Entity
@XmlRootElement
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@ManyToOne
	@JoinColumn(name = "sender", nullable = true)
	private Person userSender;

	@ManyToOne
	@JoinColumn(name = "targetid", nullable = true)
	private Person userTarget;

	private Date dateOfTweet;
	private TweetInteractionType interactionType;
	private String tweetText;

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}

	public Tweet() {

	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Person getUserSender() {
		return userSender;
	}

	public void setUserSender(Person useSender) {
		this.userSender = useSender;
	}

	public Date getDateOfTweet() {
		return dateOfTweet;
	}

	public void setDateOfTweet(Date dateOfTweet) {
		this.dateOfTweet = dateOfTweet;
	}

	public TweetInteractionType getInteractionType() {
		return interactionType;
	}

	public void setInteractionType(TweetInteractionType interactionType) {
		this.interactionType = interactionType;
	}

	public Person getUserTarget() {
		return userTarget;
	}

	public void setUserTarget(Person userTarget) {
		this.userTarget = userTarget;
	}

	@Override
	public String toString() {
		return "Tweet [Id=" + Id + ", userSender=" + userSender.getName()
				+ ", userTarget=" + userTarget.getName() + ", dateOfTweet=" + dateOfTweet
				+ ", interactionType=" + interactionType + ", tweetText="
				+ tweetText + "]";
	}
	
}
