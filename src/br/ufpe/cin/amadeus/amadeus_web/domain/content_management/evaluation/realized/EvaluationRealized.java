/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanComparator;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

/**
 * Classe que encapsula os dados de uma avaliação do aluno
 * 
 * @author yuri
 * 
 */

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class EvaluationRealized implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
    @JoinColumn(name="EVALUATION_ID", nullable = false,
			updatable = false, insertable = false)
    
	private Evaluation evaluation;
	
	@ManyToOne
    @JoinColumn(name="PERSON_ID")
	private Person student;
	
	private Date realizedDate;
	
	private Date correctedDate;
	
	private float grade;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "EVALUATIONREALIZED_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	
	private List<QuestionRealized> questionsRealized = new ArrayList<QuestionRealized>();
	
	public EvaluationRealized() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public Person getStudent() {
		return student;
	}

	public void setStudent(Person student) {
		this.student = student;
	}

	public Date getRealizedDate() {
		return realizedDate;
	}

	public void setRealizedDate(Date realizedDate) {
		this.realizedDate = realizedDate;
	}

	public Date getCorrectedDate() {
		return correctedDate;
	}

	public void setCorrectedDate(Date correctedDate) {
		this.correctedDate = correctedDate;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	@SuppressWarnings("unchecked")
	public List<QuestionRealized> getQuestionsRealized() {
		Comparator priorityOrder = new BeanComparator("id");
		Collections.sort(questionsRealized, priorityOrder); 
		return questionsRealized;
	}

	public void setQuestionsRealized(List<QuestionRealized> questionsRealized) {
		this.questionsRealized = questionsRealized;
	}
	
}
