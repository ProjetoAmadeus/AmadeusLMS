/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.repository;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile;


public interface INoticeRepositoryMobile {
	
	/**
	 * Method that returns a Notice List from the User´s Course
	 * @param idCouse - Course Id
	 * @param login -  User login
	 * @return - User´s Course Notice List
	 */
	public List<NoticeMobile> getNoticesCourse(int idCourse, String login);
	/**
	 *  Method that returns a Notice List from the User´s Module
	 * @param idModule - Module Id
	 * @param login -  User login
	 * @return - User´s Module Notice List
	 */
	public List<NoticeMobile> getNoticesModule(int idModule, String login);
	/**
	 * Method that returns the User Notice requested
	 * @param idNotice - Notice Id
	 * @param login	- User login that has the Notice Id
	 * @return - Notice
	 */
	public NoticeMobile getNotice(int idNotice, String login);
	/**
	 * Method that returns the AMADeUs Notice List for the User
	 * @param login	- User login
	 * @return - Notice List
	 */
	public List<NoticeMobile> getNoticesAmadeus(String login);
	/**
	 * Method that Adds a Notice
	 * @param notice - Notice to add
	 */
	public void addNotice(NoticeMobile notice);
	/**
	 * Method that Updates a User Notice
	 * @param notice - Notice Updated
	 * @param login - User Login that has the Notice
	 */
	public void updateNotice(NoticeMobile n, String login);
	/**
	 * Method that Checks if a Notice was sent
	 * @param idNotice - Notice Id
	 * @return - Boolean if the Notice was sent or not
	 */
	public boolean sent(int idNotice);
	/**
	 * Method that returns the Notice requested
	 * @param idNotice - Notice Id
	 * @return - Notice
	 */
	public NoticeMobile getNoticeSMS(int idNotice);
	/**
	 * Method that checks if there is a Notice. If exists, returns the Notice id.
	 * If it doesn't exist, returns -1
	 * @param not - Notice to be searched
	 * @return - Notice Id
	 */
	public int existNotice(NoticeMobile not);
	
	/**
	 * Method that returns the last id of a notice of a determinated course inserted 
	 * @param - idCurse
	 * @return - Notice Id
	 */
	public int getLastId(int idCourse);
}
