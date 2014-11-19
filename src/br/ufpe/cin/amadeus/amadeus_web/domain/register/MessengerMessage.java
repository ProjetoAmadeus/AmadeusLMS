package br.ufpe.cin.amadeus.amadeus_web.domain.register;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Classe responsável por representar a mensagem no sistema de comunicação via
 * mensagens assíncronas do amadeus.
 * 
 * @author Nailson Cunha
 * 
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class MessengerMessage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Date date;
	
	@Column(nullable=true)
	private int responseTo;
	
	private String title;
	private String content;
	private boolean read;
	private boolean toAll;
	
	@ManyToOne
	@JoinColumn(name = "SENDER_ID", nullable = true,
			updatable = false, insertable = false)
	private Person sender;

	@ManyToOne
	@JoinColumn(name = "RECEIVER_ID", nullable = true,
			updatable = false, insertable = false)
	private Person receiver;
	
	public MessengerMessage() {
		this.read = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getResponseTo() {
		return responseTo;
	}

	public void setResponseTo(int responseTo) {
		this.responseTo = responseTo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean isRead() {
		return read;
	}
	
	public void setRead(boolean read) {
		this.read = read;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Person getSender() {
		return sender;
	}
	
	public void setSender(Person sender) {
		this.sender = sender;
	}
	
	public Person getReceiver() {
		return receiver;
	}
	
	public void setReceiver(Person receiver) {
		this.receiver = receiver;
	}

	public boolean isToAll() {
		return toAll;
	}
	
	public void setToAll(boolean toAll) {
		this.toAll = toAll;
	}
	
	@Override
	public String toString() {
		return "MessengerMessage [date=" + date + ", responseTo=" + responseTo
				+ ", title=" + title + ", read=" + read;
	}
	
	
}
