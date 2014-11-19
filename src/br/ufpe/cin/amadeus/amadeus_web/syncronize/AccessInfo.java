package br.ufpe.cin.amadeus.amadeus_web.syncronize;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class AccessInfo implements Serializable{
	
	private int id;
	private String login;
	private String password;
	//private ProfileType typeProfile;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public ProfileType getTypeProfile() {
		return typeProfile;
	}
	public void setTypeProfile(ProfileType typeProfile) {
		this.typeProfile = typeProfile;
	}*/
		

}
