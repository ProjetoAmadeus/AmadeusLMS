/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.repository;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile;


public interface ICourseRepositoryMobile {
	
	/**
	 * Method that returns the User Course list
	 * @param login - User login
	 * @return - User course list
	 */
	public List<CourseMobile> listCourses(String login);
	/**
	 * Method that returns a specific User Course
	 * @param idCouse - Course ID
	 * @param login - User login
	 * @return - Course requested 
	 */
	public CourseMobile getCourse(int idCourse, String login);
	/**
	 * Method that returns a specific module, by its Id
	 * @param idModule - Module Id
	 * @return - requested module
	 */
	public ModuleMobile getModule(int idModule);
	/**
	 * Method that updates a User Course
	 * @param c - Course settings to be updated
	 * It's updated the Color and the user wants to receive the SMS for this course
	 * @param login - User that has the course
	 */
	public void updateCourse(CourseMobile c, String login);
	/**
	 * Method that inserts a reference in the database adding a course to the user
	 * @param c - course which the user added
	 * @param login - user login 
	 */
	public void insertCourse(CourseMobile c, String login);
	/**
	 * Method that returns a the User Color List, used to display the information
	 * to the user it's predefined colored coursed 
	 *  @param login - User login
	 *  @return - Color list (string)
	 */
	public List<String> getUserColors(String login);
	
}
