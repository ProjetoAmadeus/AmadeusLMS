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
import java.util.Comparator;

public class NoticeComparator implements Comparator<NoticeMobile> {

	/**
	 * Method that compares 2 objects that are Notice
	 * checking if they have the same creation date
	 * @param arg0 - first object
	 * @param arg1 - second object
	 * @return - result of the comparsion. 
	 */
	public int compare(NoticeMobile arg0, NoticeMobile arg1) {
		Date d1 = arg0.getDateCreation();
		Date d2 = arg1.getDateCreation();
		int retorno = -1;
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