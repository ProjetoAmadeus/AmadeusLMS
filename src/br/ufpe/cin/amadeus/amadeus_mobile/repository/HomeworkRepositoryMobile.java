/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.repository;

import java.util.Date;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile;


public class HomeworkRepositoryMobile implements IHomeworkRepositoryMobile{

	/**
	 * Method that returns a Homework, giving it�s Id
	 * @param id - HomeWork Id
	 * @return Homework 
	 */
	public HomeworkMobile getHomework(int id) {
		Date d = new Date(10,10,10);
		HomeworkMobile h1 = new HomeworkMobile(1,"exercicio 1","fazer os exercicios da classe 1",d,d,new Date(),1);
		return h1;
	}

}
