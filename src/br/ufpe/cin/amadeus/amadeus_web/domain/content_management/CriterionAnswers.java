/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Classe que encapsula os dados das respostas de uma avaliação de curso.
 * 
 * @author yuri
 * 
 */
@Entity
public class CriterionAnswers {

	// Os campos que contêm o prefixo 'd' são da freqüência desejada
	// Os campos que contêm o prefixo 'a' são da freqüência atual
	@Id @GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;

	private int dTotalVotes;

	private int dAlmostNeverVotes;

	private int dRarelyVotes;

	private int dSometimesVotes;

	private int dFrequentlyVotes;

	private int dAlmostAlwaysVotes;

	private int aTotalVotes;

	private int aAlmostNeverVotes;

	private int aRarelyVotes;

	private int aSometimesVotes;

	private int aFrequentlyVotes;

	private int aAlmostAlwaysVotes;

	public CriterionAnswers() {

	}

	public int getAAlmostAlwaysVotes() {
		return aAlmostAlwaysVotes;
	}

	public void setAAlmostAlwaysVotes(int almostAlwaysVotes) {
		aAlmostAlwaysVotes = almostAlwaysVotes;
	}

	public int getAAlmostNeverVotes() {
		return aAlmostNeverVotes;
	}

	public void setAAlmostNeverVotes(int almostNeverVotes) {
		aAlmostNeverVotes = almostNeverVotes;
	}

	public int getAFrequentlyVotes() {
		return aFrequentlyVotes;
	}

	public void setAFrequentlyVotes(int frequentlyVotes) {
		aFrequentlyVotes = frequentlyVotes;
	}

	public int getARarelyVotes() {
		return aRarelyVotes;
	}

	public void setARarelyVotes(int rarelyVotes) {
		aRarelyVotes = rarelyVotes;
	}

	public int getASometimesVotes() {
		return aSometimesVotes;
	}

	public void setASometimesVotes(int sometimesVotes) {
		aSometimesVotes = sometimesVotes;
	}

	public int getATotalVotes() {
		return aTotalVotes;
	}

	public void setATotalVotes(int totalVotes) {
		aTotalVotes = totalVotes;
	}

	public int getDAlmostAlwaysVotes() {
		return dAlmostAlwaysVotes;
	}

	public void setDAlmostAlwaysVotes(int almostAlwaysVotes) {
		dAlmostAlwaysVotes = almostAlwaysVotes;
	}

	public int getDAlmostNeverVotes() {
		return dAlmostNeverVotes;
	}

	public void setDAlmostNeverVotes(int almostNeverVotes) {
		dAlmostNeverVotes = almostNeverVotes;
	}

	public int getDFrequentlyVotes() {
		return dFrequentlyVotes;
	}

	public void setDFrequentlyVotes(int frequentlyVotes) {
		dFrequentlyVotes = frequentlyVotes;
	}

	public int getDRarelyVotes() {
		return dRarelyVotes;
	}

	public void setDRarelyVotes(int rarelyVotes) {
		dRarelyVotes = rarelyVotes;
	}

	public int getDSometimesVotes() {
		return dSometimesVotes;
	}

	public void setDSometimesVotes(int sometimesVotes) {
		dSometimesVotes = sometimesVotes;
	}

	public int getDTotalVotes() {
		return dTotalVotes;
	}

	public void setDTotalVotes(int totalVotes) {
		dTotalVotes = totalVotes;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}