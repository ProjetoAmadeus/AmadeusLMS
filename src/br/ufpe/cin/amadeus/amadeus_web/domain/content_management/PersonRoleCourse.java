/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

@Entity
@Table(name = "PERSON_ROLE_COURSE")
public class PersonRoleCourse {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;
	
	@OneToOne
	@JoinColumn(name="PERSON_ID")
	private Person person;

	@OneToOne
	@JoinColumn(name="ROLE_ID")
	private Role role;

	@ManyToOne
	@JoinColumn(name = "COURSE_ID", nullable = false,
			updatable = false, insertable = false)
	private Course course;

	public PersonRoleCourse() {

	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int hashCode(){
		StringBuilder builder = new StringBuilder();
		builder.append(this);
		return builder.toString().hashCode();
	}
	
	public boolean equals(Object obj){
		boolean result = false;
		if(obj != null && obj instanceof PersonRoleCourse){
			PersonRoleCourse thatPRC = (PersonRoleCourse) obj;
			result = thatPRC.getId() == this.getId();
		}
		return result;
	}

}
