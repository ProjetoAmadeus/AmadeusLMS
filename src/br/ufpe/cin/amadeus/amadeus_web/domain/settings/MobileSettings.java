package br.ufpe.cin.amadeus.amadeus_web.domain.settings;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

@SuppressWarnings("serial")
public class MobileSettings extends XMLConfiguration {

	private static MobileSettings instance;
	
	private String login;
	private String password;
	private String email;
	private String smsMaterial;
	private String smsForum;
	private String smsGame;
	private String smsLearningObject;
	private String smsPoll;
	private String smsVideo;
	
	
	private MobileSettings(String path) throws ConfigurationException {
		super(path);
	}
	
	public static MobileSettings getInstance() {
		if(instance == null) {
			try {
				instance = new MobileSettings("settings/amadeus.mobile.settings.xml");
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public static void setInstance(MobileSettings instance) {
		MobileSettings.instance = instance;
	}
	
	public String getSmsMaterial() {
		this.smsMaterial = this.getString("sms-data.sms-material");
		return smsMaterial;
	}
	
	public void setSmsMaterial(String sms) {
		this.setAutoSave(true);
		this.setProperty("sms-data.sms-material", sms);
		this.setAutoSave(false);
		this.smsMaterial = sms;
	}

	
	public String getSmsForum() {
		this.smsForum = this.getString("sms-data.sms-forum");
		return smsForum;
	}

	public void setSmsForum(String smsForum) {
		this.setAutoSave(true);
		this.setProperty("sms-data.sms-forum", smsForum);
		this.setAutoSave(false);
		this.smsForum = smsForum;
	}

	public String getSmsGame() {
		this.smsGame = this.getString("sms-data.sms-game");
		return smsGame;
	}

	public void setSmsGame(String smsGame) {
		this.setAutoSave(true);
		this.setProperty("sms-data.sms-game", smsGame);
		this.setAutoSave(false);
		this.smsGame = smsGame;
	}

	public String getSmsLearningObject() {
		this.smsLearningObject = this.getString("sms-data.sms-learningobject");
		return smsLearningObject;
	}

	public void setSmsLearningObject(String smsLearningObject) {
		this.setAutoSave(true);
		this.setProperty("sms-data.sms-learningobject", smsLearningObject);
		this.setAutoSave(false);
		this.smsLearningObject = smsLearningObject;
	}

	public String getSmsPoll() {
		this.smsPoll = this.getString("sms-data.sms-poll");
		return smsPoll;
	}

	public void setSmsPoll(String smsPoll) {
		this.setAutoSave(true);
		this.setProperty("sms-data.sms-poll", smsPoll);
		this.setAutoSave(false);
		this.smsPoll = smsPoll;
	}

	public String getSmsVideo() {
		this.smsVideo = this.getString("sms-data.sms-video");
		return smsVideo; 
	}

	public void setSmsVideo(String smsVideo) {
		this.setAutoSave(true);
		this.setProperty("sms-data.sms-video", smsVideo);
		this.setAutoSave(false);
		this.smsVideo = smsVideo;
	}

	public String getLogin() {
		this.login = this.getString("sms-data.login");
		return login;
	}
	public void setLogin(String login) {
		this.setAutoSave(true);
		this.setProperty("sms-data.login", login);
		this.setAutoSave(false);
		this.login = login;
	}
	public String getPassword() {
		this.password = this.login = this.getString("sms-data.password");
		return password;
	}
	public void setPassword(String password) {
		this.setAutoSave(true);
		this.setProperty("sms-data.password", password);
		this.setAutoSave(false);
		this.password = password;
	}
	public String getEmail() {
		this.email = this.getString("sms-data.email");
		return email;
	}
	public void setEmail(String email) {
		this.setAutoSave(true);
		this.setProperty("sms-data.email", email);
		this.setAutoSave(false);
		this.email = email;
	}
	
}
