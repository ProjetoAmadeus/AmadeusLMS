/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.ChoiceMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.PollMobile;


public class PoolRepositoryMobile implements IPoolRepositoryMobile{
	
	/**
	 * Method that returns a Poll
	 * @param id - PollId
	 * @return - Poll
	 */
	public PollMobile getPool(int id) {
		PollMobile p = new PollMobile();
		p.setId(1);
		p.setName("Linguagens para projeto");
		p.setFinishDate(new Date(Calendar.getInstance().getTimeInMillis()));
		p.setInitDate(new Date(Calendar.getInstance().getTimeInMillis()));
		p.setQuestion("Qual a liguagem de sua prefer�ncia para este projeto?");
		List<ChoiceMobile> choices = new ArrayList<ChoiceMobile>();
		ChoiceMobile c1 = new ChoiceMobile(1, "Java",0, 0);
		ChoiceMobile c2 = new ChoiceMobile(2, "C++",0, 0);
		ChoiceMobile c3 = new ChoiceMobile(3, "Python",0, 0);
		ChoiceMobile c4 = new ChoiceMobile(4, "Outra",0, 0);
		choices.add(c1);
		choices.add(c2);
		choices.add(c3);
		choices.add(c4);
		p.setChoices(choices);
		return p;
	}

}
