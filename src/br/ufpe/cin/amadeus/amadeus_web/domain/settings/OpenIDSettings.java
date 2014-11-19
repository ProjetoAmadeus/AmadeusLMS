/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.settings;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

@SuppressWarnings("serial")
public final class OpenIDSettings extends XMLConfiguration {
	
	private static OpenIDSettings instance;
	
	private String urlGoogle;
	
	private String parameterMode = "openid.mode";
	private String parameterNS = "openid.ns";
	private String parameterReturnTo = "openid.return_to";
	private String parameterAssocHandle = "openid.assoc_handle";
	private String parameterClaimedId = "openid.claimed_id";
	private String parameterIdentity = "openid.identity";
	private String parameterRealm = "openid.realm";
	private String parameterNSAX = "openid.ns.ax";
	private String parameterAXMode = "openid.ax.mode";
	private String parameterAXRequired = "openid.ax.required";
	private String parameterAXTypeEmail = "openid.ax.type.email";
	private String parameterAXTypeFirstName = "openid.ax.type.firstname";
	private String parameterAXTypeLanguage = "openid.ax.type.language";
	private String parameterAXTypeLastName = "openid.ax.type.lastname";
	private String parameterEXT1ValueEmail = "openid.ext1.value.email";
	private String parameterEXT1ValueFirstName = "openid.ext1.value.firstname";
	private String parameterEXT1ValueLastName = "openid.ext1.value.lastname";
	
	private String valueMode;
	private String valueNS;
	private String valueReturnTo;
	private String valueAssocHandle;
	private String valueClaimedId;
	private String valueIdentity;
	private String valueRealm;
	private String valueNSAX;
	private String valueAXMode;
	private String valueAXRequired;
	private String valueAXTypeEmail;
	private String valueAXTypeFirstName;
	private String valueAXTypeLanguage;
	private String valueAXTypeLastName;
	
	private OpenIDSettings(String path) throws ConfigurationException {
		super(path);
	}
	
	public static OpenIDSettings getInstance() {
		if(instance == null) {
			try {
				instance = new OpenIDSettings("settings/amadeus.openid.settings.xml");
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public String getParameterMode() {
		return parameterMode;
	}

	public String getParameterNS() {
		return parameterNS;
	}

	public String getParameterReturnTo() {
		return parameterReturnTo;
	}

	public String getParameterAssocHandle() {
		return parameterAssocHandle;
	}

	public String getParameterClaimedId() {
		return parameterClaimedId;
	}

	public String getParameterIdentity() {
		return parameterIdentity;
	}

	public String getParameterRealm() {
		return parameterRealm;
	}

	public String getParameterNSAX() {
		return parameterNSAX;
	}

	public String getParameterAXMode() {
		return parameterAXMode;
	}

	public String getParameterAXRequired() {
		return parameterAXRequired;
	}

	public String getParameterAXTypeEmail() {
		return parameterAXTypeEmail;
	}

	public String getParameterAXTypeFirstName() {
		return parameterAXTypeFirstName;
	}

	public String getParameterAXTypeLanguage() {
		return parameterAXTypeLanguage;
	}

	public String getParameterAXTypeLastName() {
		return parameterAXTypeLastName;
	}

	public String getParameterEXT1ValueEmail() {
		return parameterEXT1ValueEmail;
	}

	public String getParameterEXT1ValueFirstName() {
		return parameterEXT1ValueFirstName;
	}

	public String getParameterEXT1ValueLastName() {
		return parameterEXT1ValueLastName;
	}

	public String getValueMode() {
		this.valueMode = this.getString("openid..mode");
		return valueMode;
	}

	public void setValueMode(String valueMode) {
		this.valueMode = valueMode;
	}

	public String getValueNS() {
		this.valueNS = this.getString("openid..ns");
		return valueNS;
	}

	public void setValueNS(String valueNS) {
		this.valueNS = valueNS;
	}

	public String getValueReturnTo(HttpServletRequest request) {
		this.valueReturnTo = this.getUrlSystem(request) + this.getString("openid..return_to");
		return valueReturnTo;
	}

	public void setValueReturnTo(String valueReturnTo) {
		this.valueReturnTo = valueReturnTo;
	}

	public String getValueAssocHandle() {
		this.valueAssocHandle = this.getString("openid..assoc_handle");
		return valueAssocHandle;
	}

	public void setValueAssocHandle(String valueAssocHandle) {
		this.valueAssocHandle = valueAssocHandle;
	}

	public String getValueClaimedId() {
		this.valueClaimedId = this.getString("openid..claimed_id");
		return valueClaimedId;
	}

	public void setValueClaimedId(String valueClaimedId) {
		this.valueClaimedId = valueClaimedId;
	}

	public String getValueIdentity() {
		this.valueIdentity = this.getString("openid..identity");
		return valueIdentity;
	}

	public void setValueIdentity(String valueIdentity) {
		this.valueIdentity = valueIdentity;
	}

	public String getValueRealm(HttpServletRequest request) {
		this.valueRealm = this.getUrlSystem(request) + this.getString("openid..realm");
		return valueRealm;
	}

	public void setValueRealm(String valueRealm) {
		this.valueRealm = valueRealm;
	}

	public String getValueNSAX() {
		this.valueNSAX = this.getString("openid..ns..ax");
		return valueNSAX;
	}

	public void setValueNSAX(String valueNSAX) {
		this.valueNSAX = valueNSAX;
	}

	public String getValueAXMode() {
		this.valueAXMode = this.getString("openid..ax..mode");
		return valueAXMode;
	}

	public void setValueAXMode(String valueAXMode) {
		this.valueAXMode = valueAXMode;
	}

	@SuppressWarnings("unchecked")
	public String getValueAXRequired() {
		List<String> listValues = this.getList("openid..ax..required");
		valueAXRequired = "";
		
		for (String value : listValues) {
			valueAXRequired += value +",";
		}
		valueAXRequired = valueAXRequired.substring(0, valueAXRequired.length()-1).trim();
	
		return valueAXRequired;
	}

	public void setValueAXRequired(String valueAXRequired) {
		this.valueAXRequired = valueAXRequired;
	}

	public String getValueAXTypeEmail() {
		this.valueAXTypeEmail = this.getString("openid..ax..type..email");
		return valueAXTypeEmail;
	}

	public void setValueAXTypeEmail(String valueAXTypeEmail) {
		this.valueAXTypeEmail = valueAXTypeEmail;
	}

	public String getValueAXTypeFirstName() {
		this.valueAXTypeFirstName = this.getString("openid..ax..type..firstname");
		return valueAXTypeFirstName;
	}

	public void setValueAXTypeFirstName(String valueAXTypeFirstName) {
		this.valueAXTypeFirstName = valueAXTypeFirstName;
	}

	public String getValueAXTypeLanguage() {
		this.valueAXTypeLanguage = this.getString("openid..ax..type..language");
		return valueAXTypeLanguage;
	}

	public void setValueAXTypeLanguage(String valueAXTypeLanguage) {
		this.valueAXTypeLanguage = valueAXTypeLanguage;
	}

	public String getValueAXTypeLastName() {
		this.valueAXTypeLastName = this.getString("openid..ax..type..lastname");
		return valueAXTypeLastName;
	}

	public void setValueAXTypeLastName(String valueAXTypeLastName) {
		this.valueAXTypeLastName = valueAXTypeLastName;
	}

	public String getUrlGoogle() {
		this.urlGoogle = this.getString("openid..url_google");
		return urlGoogle;
	}

	public String getUrlOpenID(HttpServletRequest request) {
		
		String urlGoogle = this.getUrlGoogle();
		urlGoogle += "?" + this.getParameterMode() 				+"="+ this.getValueMode(); 
		urlGoogle += "&" + this.getParameterNS() 				+"="+ this.getValueNS(); 
		urlGoogle += "&" + this.getParameterReturnTo() 			+"="+ this.getValueReturnTo(request);
		urlGoogle += "&" + this.getParameterAssocHandle() 		+"="+ this.getValueAssocHandle();
		urlGoogle += "&" + this.getParameterClaimedId() 		+"="+ this.getValueClaimedId();
		urlGoogle += "&" + this.getParameterIdentity() 			+"="+ this.getValueIdentity();	
		urlGoogle += "&" + this.getParameterRealm() 			+"="+ this.getValueRealm(request);
		urlGoogle += "&" + this.getParameterNSAX() 				+"="+ this.getValueNSAX();
		urlGoogle += "&" + this.getParameterAXMode() 			+"="+ this.getValueAXMode();
		urlGoogle += "&" + this.getParameterAXRequired() 		+"="+ this.getValueAXRequired();
		urlGoogle += "&" + this.getParameterAXTypeEmail() 		+"="+ this.getValueAXTypeEmail();
		urlGoogle += "&" + this.getParameterAXTypeFirstName() 	+"="+ this.getValueAXTypeFirstName();
		urlGoogle += "&" + this.getParameterAXTypeLanguage() 	+"="+ this.getValueAXTypeLanguage();
		urlGoogle += "&" + this.getParameterAXTypeLastName() 	+"="+ this.getValueAXTypeLastName();
	
		return urlGoogle;
	}
	
	private String getUrlSystem(HttpServletRequest request) {
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
}
