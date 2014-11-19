package br.ufpe.cin.amadeus.amadeus_web.domain.settings;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

@SuppressWarnings("serial")
public class WebSettings extends XMLConfiguration {

	private static WebSettings instance;
	
	private String systemGeneralVersion;
	private String systemGeneralLanguage;
	private String systemGeneralEncoding;
	private String systemGeneralThemes;
	private String systemGeneralDomain;
	
	private String mailDescription;
	private String mailFrom;
	private String mailPassword;
	private String mailSmtpHost;
	private boolean mailSmtpAuth;
	private boolean mailDebug;
	private int mailPort;
	private String mailSocketFactoryClass;
	private boolean mailSocketFactoryFallback;
	private boolean mailSSLEnable;
	private boolean mailStartTLSEnable;
	private boolean mailStartTLSRequired;
	
	
	private String securityCryptographyKey;
	private boolean securityAutoSigning;
	
	private WebSettings(String path) throws ConfigurationException {
		super(path);
	}
	
	public static WebSettings getInstance() {
		if(instance == null) {
			try {
				instance = new WebSettings("settings/amadeus.web.settings.xml");
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public static void setInstance(WebSettings instance) {
		WebSettings.instance = instance;
	}
	
	public String getSystemGeneralVersion() {
		this.systemGeneralVersion = this.getString("system.general..version");
		return systemGeneralVersion;
	}

	public void setSystemGeneralVersion(String systemGeneralVersion) {
		this.systemGeneralVersion = systemGeneralVersion;
	}
	
	public String getSystemGeneralLanguage() {
		this.systemGeneralLanguage = this.getString("system.general..language");
		return systemGeneralLanguage;
	}

	public void setSystemGeneralLanguage(String systemGeneralLanguage) {
		this.systemGeneralLanguage = systemGeneralLanguage;
	}

	public String getSystemGeneralEncoding() {
		this.systemGeneralEncoding = this.getString("system.general..encoding");
		return systemGeneralEncoding;
	}

	public void setSystemGeneralEncoding(String systemGeneralEncoding) {
		this.systemGeneralEncoding = systemGeneralEncoding;
	}
	
	public String getSystemGeneralThemes() {
		this.systemGeneralThemes = this.getString("system.general..themes");
		return systemGeneralThemes;
	}
	
	public void setSystemGeneralThemes(String systemGeneralThemes) {
		this.systemGeneralThemes = systemGeneralThemes;
	}
	
	public String getSystemGeneralDomain() {
		this.systemGeneralDomain= this.getString("system.general..domain");
		return systemGeneralDomain;
	}
	
	public void setSystemGeneralDomain(String systemGeneralDomain) {
		this.systemGeneralDomain= systemGeneralDomain;
	}

	/************************  MAIL SENDER	*******************************/
	
	public String getMailDescription() {
		this.mailDescription = this.getString("mailsender.mail..description");
		return mailDescription;
	}

	public void setMailDescription(String mailDescription) {
		this.setAutoSave(true);
		this.setProperty("mailsender.mail..description", mailDescription);
		this.setAutoSave(false);
		this.mailDescription = mailDescription;
	}

	public String getMailFrom() {
		this.mailFrom = this.getString("mailsender.mail..from");
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.setAutoSave(true);
		this.setProperty("mailsender.mail..from", mailFrom);
		this.setAutoSave(false);
		this.mailFrom = mailFrom;
	}

	public String getMailPassword() {
		this.mailPassword = this.getString("mailsender.mail..password");
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.setAutoSave(true);
		this.setProperty("mailsender.mail..password", mailPassword);
		this.setAutoSave(false);
		this.mailPassword = mailPassword;
	}

	public String getMailSmtpHost() {
		this.mailSmtpHost = this.getString("mailsender.mail..smtp..host");
		return mailSmtpHost;
	}

	public void setMailSmtpHost(String mailSmtpHost) {
		this.setAutoSave(true);
		this.setProperty("mailsender.mail..smtp..host", mailSmtpHost);
		this.setAutoSave(false);
		this.mailSmtpHost = mailSmtpHost;
	}

	public boolean isMailSmtpAuth() {
		this.mailSmtpAuth = this.getBoolean("mailsender.mail..smtp..auth");
		return mailSmtpAuth;
	}

	public void setMailSmtpAuth(boolean mailSmtpAuth) {
		this.setAutoSave(true);
		this.setProperty("mailsender.mail..smtp..auth", mailSmtpAuth);
		this.setAutoSave(false);
		this.mailSmtpAuth = mailSmtpAuth;
	}

	public boolean isMailDebug() {
		this.mailDebug = this.getBoolean("mailsender.mail..debug");
		return mailDebug;
	}

	public int getMailPort() {
		this.mailPort = this.getInt("mailsender.mail..smtp..port");
		return mailPort;
	}

	public void setMailPort(int mailPort) {
		this.setAutoSave(true);
		this.setProperty("mailsender.mail..smtp..port", mailPort);
		this.setAutoSave(false);
		this.mailPort = mailPort;
	}

	public String getMailSocketFactoryClass() {
		this.mailSocketFactoryClass = this.getString("mailsender.mail..smtp..socketFactory..class");
		return mailSocketFactoryClass;
	}

	public boolean isMailSocketFactoryFallback() {
		this.mailSocketFactoryFallback = this.getBoolean("mailsender.mail..smtp..socketFactory..fallback");
		return mailSocketFactoryFallback;
	}
	
	public boolean isMailSSLEnable() {
		this.mailSSLEnable = this.getBoolean("mailsender.mail..smtp..ssl..enable");
		return mailSSLEnable;
	}

	public void setMailSSLEnable(boolean mailSSLEnable) {
		this.setAutoSave(true);
		this.setProperty("mailsender.mail..smtp..ssl..enable", mailSSLEnable);
		this.setAutoSave(false);
		this.mailSSLEnable = mailSSLEnable;
	}

	public boolean isMailStartTLSEnable() {
		this.mailStartTLSEnable = this.getBoolean("mailsender.mail..smtp..starttls..enable");
		return mailStartTLSEnable;
	}

	public void setMailStartTLSEnable(boolean mailStartTLSEnable) {
		this.setAutoSave(true);
		this.setProperty("mailsender.mail..smtp..starttls..enable", mailStartTLSEnable);
		this.setAutoSave(false);
		this.mailStartTLSEnable = mailStartTLSEnable;
	}

	public boolean isMailStartTLSRequired() {
		this.mailStartTLSRequired = this.getBoolean("mailsender.mail..smtp..starttls..required");
		return mailStartTLSRequired;
	}

	public void setMailStartTLSRequired(boolean mailStartTLSRequired) {
		this.setAutoSave(true);
		this.setProperty("mailsender.mail..smtp..starttls..required", mailStartTLSRequired);
		this.setAutoSave(false);
		this.mailStartTLSRequired = mailStartTLSRequired;
	}

	public boolean isMailWithoutSecurityConnection() {
		boolean mailWithoutSecurityConnection = false;
		
		if(!this.mailSSLEnable && !this.mailStartTLSEnable && !this.mailStartTLSRequired) {
			mailWithoutSecurityConnection = true;
		}
		
		return mailWithoutSecurityConnection;
	}
	
	/************************  END MAIL SENDER	****************************/
	

	public String getSecurityCryptographyKey() {
		this.securityCryptographyKey = this.getString("security.cryptography.key");
		return this.securityCryptographyKey;
	}
	
	public boolean isSecurityAutoSigning() {
		this.securityAutoSigning = this.getBoolean("security.autoSigning");
		return securityAutoSigning;
	}

	public void setSecurityAutoSigning(boolean securityAutoSigning) {
		this.setAutoSave(true);
		this.setProperty("security.autoSigning", securityAutoSigning);
		this.setAutoSave(false);
		this.securityAutoSigning = securityAutoSigning;
	}
}
