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
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.PersonForum;

/**
 * Classe que encapsula os dados de um usuário do sistema
 * 
 * @author yuri
 * 
 */

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@org.hibernate.annotations.Entity (dynamicUpdate = true)
public class Person implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;

	private String name;

	private String city;

	private String state;

	@Column(unique = true)
	private String email;

	private String cpf;

	private String phoneNumber;

	private Date birthDate;
	
	private char gender;
	
	private String twitterLogin;
	
	private String facebookLogin;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="ACCESS_INFO_ID")
	private AccessInfo accessInfo;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="RESUME_ID")
	private Resume resume;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="IMAGE_ID")
	private Image image;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "SENDER_ID", nullable = true)
	private List<MessengerMessage> sent;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RECEIVER_ID", nullable = true)
	private List<MessengerMessage> received;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.person",
			 cascade = { CascadeType.PERSIST, CascadeType.MERGE })
			 @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			 org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<PersonForum> foruns = new ArrayList<PersonForum>();

	public Person() {
		gender = ' ';
		this.sent = new ArrayList<MessengerMessage>();
		this.received = new ArrayList<MessengerMessage>();
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email != null) {
			this.email = email.trim().toLowerCase();
		} else {
			this.email = email;
		}
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public AccessInfo getAccessInfo() {
		return accessInfo;
	}

	public void setAccessInfo(AccessInfo accessInfo) {
		this.accessInfo = accessInfo;
	}
	
	public boolean equals(Object obj){
		boolean result = false;
		if(obj != null && obj instanceof Person){
			Person thatPerson = (Person) obj;
			result = thatPerson.getId() == this.getId();
		}
		return result;
	}
	
	/*public int hashCode(){
		StringBuilder builder = new StringBuilder();
		builder.append(this);
		return builder.toString().hashCode();
	}*/

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getTwitterLogin() {
		return twitterLogin;
	}

	public void setTwitterLogin(String twitterLogin) {
		this.twitterLogin = twitterLogin;
	}

	public String getFacebookLogin() {
		return facebookLogin;
	}

	public void setFacebookLogin(String facebookLogin) {
		this.facebookLogin = facebookLogin;
	}

	public List<PersonForum> getForuns() {
		return foruns;
	}

	public void setForuns(List<PersonForum> foruns) {
		this.foruns = foruns;
	}
	
	public List<MessengerMessage> getReceived() {
		return received;
	}
	
	public void setReceived(List<MessengerMessage> received) {
		this.received = received;
	}
	
	public List<MessengerMessage> getSent() {
		return sent;
	}
	
	public void setSent(List<MessengerMessage> sent) {
		this.sent = sent;
	}

}
