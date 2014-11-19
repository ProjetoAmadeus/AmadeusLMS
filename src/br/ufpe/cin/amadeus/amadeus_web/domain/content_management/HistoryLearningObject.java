/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

@Entity
public class HistoryLearningObject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private Date dateAccess;
	
	private Date dateEndAccess;
	
	private Time timeAccess;
	
	private String score;
	
	@ManyToOne
	@JoinColumn(name = "PERSON_ID", nullable=false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.ALL )
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "LEARNING_OBJECT_ID" , nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.ALL )
	private LearningObject learningObject;


	
	public HistoryLearningObject() {

	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateAccess() {
		return dateAccess;
	}

	public void setDateAccess(Date dateAccess) {
		this.dateAccess = dateAccess;
	}

	public Date getDateEndAccess() {
		return dateEndAccess;
	}

	public void setDateEndAccess(Date dateEndAccess) {
		this.dateEndAccess = dateEndAccess;
	}

	public Time getTimeAccess() {
		return timeAccess;
	}

	public void setTimeAccess(Time timeAccess) {
		this.timeAccess = timeAccess;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public LearningObject getLearningObject() {
		return learningObject;
	}

	public void setLearningObject(LearningObject learning) {
		this.learningObject = learning;
	}

	
	
}
