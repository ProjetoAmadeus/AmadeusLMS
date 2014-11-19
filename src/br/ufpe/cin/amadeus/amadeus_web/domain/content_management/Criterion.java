/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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
 * Classe que encapsula os dados de um critÃ©rio de uma avaliaÃ§Ã£o de curso
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
