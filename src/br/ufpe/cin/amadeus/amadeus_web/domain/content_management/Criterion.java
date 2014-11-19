/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * Classe que encapsula os dados de um critério de uma avaliação de curso
 * 
 * @author yuri
 * 
 */

@Entity
public class Criterion {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;

	@Enumerated(EnumType.ORDINAL)
	private CriterionType ctype;
	
	@OneToMany
//	@JoinColumn(name="CRITERION_ID", nullable = false)
	private Set<CriterionAnswers> answers = new HashSet<CriterionAnswers>();

	public Criterion() {

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CriterionType getCtype() {
		return ctype;
	}

	public void setCtype(CriterionType ctype) {
		this.ctype = ctype;
	}

	public Set<CriterionAnswers> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<CriterionAnswers> answers) {
		this.answers = answers;
	}

}
