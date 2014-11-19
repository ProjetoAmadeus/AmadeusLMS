/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanComparator;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.EvaluationRealized;

/**
 * Classe que encapsula os dados de uma avaliação do aluno
 * 
 * @author yuri
 * 
 */

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Evaluation implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(length = 8000)
	private String description;

	private Date start;

	private Date finish;
	
    @ManyToMany(
            targetEntity=Question.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
        )
        @JoinTable(
            name="EVALUATION_QUESTION",
            joinColumns=@JoinColumn(name="EVALUATION_ID"),
            inverseJoinColumns=@JoinColumn(name="QUESTION_ID")
        )
	private List<Question> questions = new ArrayList<Question>();
	
	private boolean afterdeadlineachieved;

	@ManyToOne
    @JoinColumn(name="MODULE_ID", insertable=false, updatable=false)
	private Module module;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "EVALUATION_ID", nullable = false)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<EvaluationRealized> evaluationsRealized = new ArrayList<EvaluationRealized>();
	
	
	public Evaluation() {}

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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getFinish() {
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}

	public boolean isAfterdeadlineachieved() {
		return afterdeadlineachieved;
	}

	public void setAfterdeadlineachieved(boolean afterdeadlineachieved) {
		this.afterdeadlineachieved = afterdeadlineachieved;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@SuppressWarnings("unchecked")
	public List<Question> getQuestions() {
		Comparator priorityOrder = new BeanComparator("id");
		Collections.sort(questions, priorityOrder); 
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<EvaluationRealized> getEvaluationsRealized() {
		return evaluationsRealized;
	}

	public void setEvaluationsRealized(List<EvaluationRealized> evaluationRealized) {
		this.evaluationsRealized = evaluationRealized;
	}
	
}
