package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Message implements Serializable{
	
	private int id;
	private String body;
	private Date date;
	private String personName;
	private String personLogin;
	private int forum_id;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getForum_id() {
		return forum_id;
	}
	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}
	public String getPersonLogin() {
		return personLogin;
	}
	public void setPersonLogin(String personLogin) {
		this.personLogin = personLogin;
	}
	

}
