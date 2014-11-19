/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.repository;

import java.util.Date;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.HomeworkMobile;


public class HomeworkRepositoryMobile implements IHomeworkRepositoryMobile{

	/**
	 * Method that returns a Homework, giving it´s Id
	 * @param id - HomeWork Id
	 * @return Homework 
	 */
	public HomeworkMobile getHomework(int id) {
		Date d = new Date(10,10,10);
		HomeworkMobile h1 = new HomeworkMobile(1,"exercicio 1","fazer os exercicios da classe 1",d,d,new Date(),1);
		return h1;
	}

}
