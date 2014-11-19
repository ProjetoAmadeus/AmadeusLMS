package br.ufpe.cin.amadeus.amadeus_web.util;

import java.util.Date;

/**
 * Classe criada para servir como espelho da classe MessengerMessage afim de
 * facilitar a serializacao para json
 * 
 * @author Nailson Cunha
 * 
 */
public class MessengerMessageMirror {

	private int id;
	private Date date;
	private String title;
	private String content;
	private String sender;
	private int senderId;
	private boolean toAll;
	private int courseId;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public int getSenderId() {
		return senderId;
	}
	
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	
	public boolean isToAll() {
		return toAll;
	}
	
	public void setToAll(boolean toAll) {
		this.toAll = toAll;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
}
