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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Classe que encapsula os dados de uma keyword
 * 
 * @author yuri
 * 
 */
@Entity
@XmlRootElement
public class Keyword implements Comparable<Keyword>, Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;

	private int popularity;
	
	@Transient
	private int keywordGroup;

	@Column(name = "name", unique = true, updatable = false)
	private String name;

	public Keyword() {
		
	}
	
	public int getKeywordGroup() {
		return keywordGroup;
	}

	public void setKeywordGroup(int keywordGroup) {
		this.keywordGroup = keywordGroup;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim().toLowerCase();
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
			
	public int compareTo(Keyword keyword){
		return this.name.compareToIgnoreCase(keyword.getName());
	}
	
	public boolean equals(Object o){
		boolean retorno = false;
		if (o != null && o instanceof Keyword) {
			Keyword that = (Keyword) o;
			retorno = this.name.equals(that.getName());
		}
		return retorno;
	}
	
	public int hashCode(){
		return this.getName().hashCode();
	}

}
