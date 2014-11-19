/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.settings;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class MobileSettings extends XMLConfiguration {
	
	public static MobileSettings instance;
	
	//Database connection settings
	private String databaseDriverClass;
	private String databaseUrl;
	private String databaseUsername;
	private String databasePassword;
	
	
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

	public String getDatabaseDriverClass() {
		this.databaseDriverClass = this.getString("database-connection.driver-class");
		return this.databaseDriverClass;
	}

	public void setDatabaseDriverClass(String driverClass) {
		this.databaseDriverClass = driverClass;
	}

	public String getDatabaseUrl() {
		this.databaseUrl = this.getString("database-connection.url");
		return this.databaseUrl;
	}

	public void setDatabaseUrl(String url) {
		this.databaseUrl = url;
	}

	public String getDatabaseUsername() {
		this.databaseUsername = this.getString("database-connection.username");
		return this.databaseUsername;
	}

	public void setDatabaseUsername(String username) {
		this.databaseUsername = username;
	}

	public String getDatabasePassword() {
		this.databasePassword = this.getString("database-connection.password");
		return databasePassword;
	}

	public void setDatabasePassword(String password) {
		this.databasePassword = password;
	}
		
}
