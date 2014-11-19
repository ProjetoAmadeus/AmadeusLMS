/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
/**
 * Classe que encapsula dados de uma questão de uma avaliacão
 * 
 * @author yuri
 * 
 */

@Entity
@XmlRootElement
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Question {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(length = 8000)
	private String description;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			mappedBy="questions",
			targetEntity=Evaluation.class)
			@JoinTable(
				name="EVALUATION_QUESTION",
		        joinColumns=@JoinColumn(name="QUESTION_ID"),
		        inverseJoinColumns=@JoinColumn(name="EVALUATION_ID")
			)
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();

	@ManyToOne
	@JoinColumn(name = "COURSE_ID", nullable = false,
			updatable = false, insertable = false)
	private Course course;
	
	public Question() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
	
}
