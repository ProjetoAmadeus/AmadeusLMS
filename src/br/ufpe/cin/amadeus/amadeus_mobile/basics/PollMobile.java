/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.basics;

import java.util.Date;
import java.util.List;

public class PollMobile {
	
	private int id;

	private String name;

	private String question;

	private Date initDate;

	private Date finishDate;

	private List<ChoiceMobile> choices;
	
	private List<AnswerMobile> ansewered;
	
	private boolean answered;

	public PollMobile() {}
	
	public PollMobile(int id, String name, String question, Date initDate, Date finishDate, List<ChoiceMobile> choices, List<AnswerMobile> ansewered, boolean answered) {
		this.id = id;
		this.name = name;
		this.question = question;
		this.initDate = initDate;
		this.finishDate = finishDate;
		this.choices = choices;
		this.ansewered = ansewered;
		this.answered = answered;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}

	public List<ChoiceMobile> getChoices() {
		return choices;
	}

	public void setChoices(List<ChoiceMobile> choices) {
		this.choices = choices;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AnswerMobile> getAnsewered() {
		return ansewered;
	}

	public void setAnsewered(List<AnswerMobile> ansewered) {
		this.ansewered = ansewered;
	}
	
}