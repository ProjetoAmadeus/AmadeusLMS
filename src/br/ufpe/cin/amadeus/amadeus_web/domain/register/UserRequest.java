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
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Status;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class UserRequest implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="PERSON_ID")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "COURSE_ID", nullable = true,
			updatable = false, insertable = false)
	private Course course;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="STATUS_TYPE", nullable=false, updatable=true)
	private Status statusType;
	
	private Date userRequestDate;
	
	private String interest;
	
	private boolean teachingRequest;
	
	public UserRequest(){
		
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Status getStatus() {
		return statusType;
	}

	public void setStatus(Status status) {
		this.statusType = status;
	}

	public boolean isTeachingRequest() {
		return teachingRequest;
	}

	public void setTeachingRequest(boolean teachingRequest) {
		this.teachingRequest = teachingRequest;
	}

	public Date getUserRequestDate() {
		return userRequestDate;
	}

	public void setUserRequestDate(Date userRequestDate) {
		this.userRequestDate = userRequestDate;
	}
	
	

}
