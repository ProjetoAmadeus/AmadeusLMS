/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Question;
/**
 * Classe que encapsula dados de uma questÃ£o de uma avaliacÃ£o
 * 
 * @author yuri
 * 
 */

@Entity
@XmlRootElement
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class QuestionRealized {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
    @JoinColumn(name="EVALUATIONREALIZED_ID", nullable = false,
			updatable = false, insertable = false)
	//@ManyToOne NÃO é o owner da relação (caso especial)
    private EvaluationRealized evaluationRealized;
	
	@ManyToOne
    @JoinColumn(name="QUESTION_ID")
    //@ManyToOne é o owner da relação (default)
    private Question question;
	
	@Column(length = 8000)
	private String comment;
	
	private float grade;
	
	public QuestionRealized() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EvaluationRealized getEvaluationRealized() {
		return evaluationRealized;
	}

	public void setEvaluationRealized(EvaluationRealized evaluationRealized) {
		this.evaluationRealized = evaluationRealized;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}
	
}
