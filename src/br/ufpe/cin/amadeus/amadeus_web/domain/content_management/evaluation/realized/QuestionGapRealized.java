/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.CollectionOfElements;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class QuestionGapRealized extends QuestionRealized {

	@CollectionOfElements(fetch=FetchType.EAGER)
	@JoinTable(
	    name = "GapAnswer",
	    joinColumns = @JoinColumn(name = "QUESTIONREALIZED_ID"))
	@Column(
	   name = "answer",
	   nullable = false
	) 
    private List<String> gapAnswers = new ArrayList<String>();

	public List<String> getGapAnswers() {
		return gapAnswers;
	}

	public void setGapAnswers(List<String> gapAnswers) {
		this.gapAnswers = gapAnswers;
	}
	
}
