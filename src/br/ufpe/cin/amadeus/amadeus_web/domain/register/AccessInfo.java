/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.register;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ProfileType;
import br.ufpe.cin.amadeus.amadeus_web.util.Cryptography;

/**
 * Classe que encapsula os dados de um usuário para acesso ao sistema
 * 
 * @author yuri
 * 
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class AccessInfo implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;

	@Column(unique = true)
	private String login;

	private String password;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="PROFILE_TYPE", nullable=false, updatable=true)
	private ProfileType typeProfile;

	@OneToOne(mappedBy = "accessInfo")
	private Person person;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "ACCESSINFO_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<OpenID> openIDs = new ArrayList<OpenID>();
	
	public AccessInfo() {

	}

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
		if(login != null) {
			this.login = login.trim().toLowerCase();
		} else {
			this.login = login;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password != null)
			this.password = password.trim();
		try {
			this.password = Cryptography.encrypt(password);
		} catch (Exception e) {
			System.out.println("Error when trying cryptography the password.");
		}
	}

	public ProfileType getTypeProfile() {
		return typeProfile;
	}

	public void setTypeProfile(ProfileType typeProfile) {
		this.typeProfile = typeProfile;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<OpenID> getOpenIDs() {
		return openIDs;
	}

	public void setOpenIDs(List<OpenID> openIDs) {
		this.openIDs = openIDs;
	}
	
}
