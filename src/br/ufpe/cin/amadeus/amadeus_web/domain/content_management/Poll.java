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
/**
 * Classe que encapsula os dados de uma enquete
 * 
 * @author yuri
 * 
 */

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Poll implements Serializable{

	@Id @GeneratedValue (strategy=GenerationType.AUTO) 
	private int id;

	private String name;

	private String question;

	private Date creationDate;

	private Date finishDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "POLL_ID", nullable = false)
	@org.hibernate.annotations.IndexColumn(name = "POSITION", base = 0)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )
	private List<Choice> choices = new ArrayList<Choice>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "POLL_ID", nullable = false)
	@org.hibernate.annotations.IndexColumn(name = "POSITION", base = 0)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN )	
	private List<Answer> answers = new ArrayList<Answer>();

	@ManyToOne
	@JoinColumn(name = "MODULE_ID", nullable = false,
			updatable = false, insertable = false)
	private Module module;
	
	public Poll() {

	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date initDate) {
		this.creationDate = initDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
