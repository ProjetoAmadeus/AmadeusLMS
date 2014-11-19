/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanComparator;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Question;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.UserRequest;

/**
 * Classe que encapsula os dados de um curso no sistema
 * 
 * @author yuri
 * 
 */

@SuppressWarnings("serial")
@Entity
public class Course implements Serializable {
	

	@Id @GeneratedValue (strategy=GenerationType.AUTO) 
	private int id;

	private int maxAmountStudents;
	
	@Transient
	private int numberOfStudentsInCourse;

	private String name;

	@Column(length=2000)
	private String objectives;

	@Column(length=2000)
	private String content;

	private Date creationDate;

	private Date initialRegistrationDate;

	private Date finalRegistrationDate;

	private Date initialCourseDate;

	private Date finalCourseDate;

	@OneToOne
	@JoinColumn(name="PROFESSOR_ID")
	private Person professor;

	@ManyToMany(targetEntity = Keyword.class, fetch = FetchType.LAZY)
	@JoinTable(name = "keywordsOfCourse",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "keywords_id"))
	@org.hibernate.annotations.Cascade(
			value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE,
					org.hibernate.annotations.CascadeType.MERGE})
	private Set<Keyword> keywords = new HashSet<Keyword>();

	@OneToMany
	private Set<CourseScores> studentsScores = new HashSet<CourseScores>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "COURSE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<Module> modules = new ArrayList<Module>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "COURSE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<PersonRoleCourse> personsRolesCourse = new ArrayList<PersonRoleCourse>();

	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "COURSE_ID", nullable = true)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<UserRequest> userRequest = new ArrayList<UserRequest>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "COURSE_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<Question> questions = new ArrayList<Question>();
	
	@Transient
	private List<Person> participants = new ArrayList<Person>();

	public Course() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date dataCriacao) {
		this.creationDate = dataCriacao;
	}
	public Date getFinalCourseDate() {
		return finalCourseDate;
	}
	public void setFinalCourseDate(Date dataFim) {
		this.finalCourseDate = dataFim;
	}
	public Date getFinalRegistrationDate() {
		return finalRegistrationDate;
	}
	public void setFinalRegistrationDate(Date dataFimInscricao) {
		this.finalRegistrationDate = dataFimInscricao;
	}
	public Date getInitialCourseDate() {
		return initialCourseDate;
	}
	public void setInitialCourseDate(Date dataInicio) {
		this.initialCourseDate = dataInicio;
	}
	public Date getInitialRegistrationDate() {
		return initialRegistrationDate;
	}
	public void setInitialRegistrationDate(Date dataInicioInscricao) {
		this.initialRegistrationDate = dataInicioInscricao;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	public int getMaxAmountStudents() {
		return maxAmountStudents;
	}

	public void setMaxAmountStudents(int maxAmountStudents) {
		this.maxAmountStudents = maxAmountStudents;
	}

	@SuppressWarnings("unchecked")
	public List<Module> getModules() {
		Comparator priorityOrder = new BeanComparator("position");
		Collections.sort(modules, priorityOrder); 
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public Set<CourseScores> getStudentsScores() {
		return studentsScores;
	}

	public void setStudentsScores(Set<CourseScores> studentsScores) {
		this.studentsScores = studentsScores;
	}

	public Person getProfessor() {
		return professor;
	}

	public void setProfessor(Person professor) {
		this.professor = professor;
	}
	
	public int getNumberOfStudentsInCourse() {
		return numberOfStudentsInCourse;
	}

	public void setNumberOfStudentsInCourse(int numberOfStudentsInCourse) {
		this.numberOfStudentsInCourse = numberOfStudentsInCourse;
	}

	public List<PersonRoleCourse> getPersonsRolesCourse() {
		return personsRolesCourse;
	}

	public void setPersonsRolesCourse(List<PersonRoleCourse> personsRolesCourse) {
		this.personsRolesCourse = personsRolesCourse;
	}
	
	public List<UserRequest> getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(List<UserRequest> userRequest) {
		this.userRequest = userRequest;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public List<Person> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Person> participants) {
		this.participants = participants;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		
		if(obj instanceof Course && obj != null
				&& ((Course)obj).getId() == this.id ){
			retorno = true;
		}
		
		return retorno;
	}


}
