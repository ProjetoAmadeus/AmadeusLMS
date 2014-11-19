package br.ufpe.cin.amadeus.amadeus_web.util;

public class MailMessage {
	
	private String from;
	private String to;
	private String subject;
	private String message;
	
	public String getFrom() {
		return from;
	}
	
	public String[] getTo() {
		return to.split(";");
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
