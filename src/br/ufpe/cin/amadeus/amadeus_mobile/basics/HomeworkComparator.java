/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.basics;

import java.util.Date;
import java.util.Comparator;

public class HomeworkComparator implements Comparator<Object> {

	/**
	 * Method that compares 2 objects that can be Homework or Poll
	 * if it's a homework, checks if both objects have the same deadline
	 * if it's a Poll, checks if both objects have the same FinishDate
	 * @param arg0 - first object
	 * @param arg1 - second object
	 * @return - result of the comparsion. 
	 */
	public int compare(Object arg0, Object arg1) {
		
		Date d1 = null;
		Date d2 = null;
		int retorno = -1;
		if (arg0 instanceof PollMobile) {
		  d1 = ((PollMobile)arg0).getFinishDate();			
		}
		else if (arg0 instanceof HomeworkMobile) {
			d1 = ((HomeworkMobile) arg0).getDeadlineDate();			
		}
		
		if (arg1 instanceof PollMobile) {
		  d2 = ((PollMobile)arg0).getFinishDate();						
		}
		else if (arg1 instanceof HomeworkMobile) {
		  d2 = ((HomeworkMobile) arg1).getDeadlineDate();			
		}
		
		if (d1!= null && d2!= null) {
		  retorno = d1.compareTo(d2);
		} 
		else if((d1!= null && d2 ==null) || (d2!= null && d1==null)) {
		  retorno = -1;
		} 
		else {
		  retorno = 1;
		}
		return retorno;
	}
	
}