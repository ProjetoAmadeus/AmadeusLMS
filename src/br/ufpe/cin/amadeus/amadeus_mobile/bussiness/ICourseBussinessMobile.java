/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.bussiness;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile;


public interface ICourseBussinessMobile {
	/**
	 * Method that Searches for all of the User courses, 
	 * returning a list of Courses
	 * @param login - User login to search for
	 * @return - Course List
	 */
	public List<CourseMobile> listCourses(String login);
	/**
	 * Method that gets a specific User course
	 * @param idCourse - Desired Course ID
	 * if the user has this course registered at the Mobile Database,
	 * it sets the course details, like if he wants to receive SMS and the color.
	 * If the user doesn't have the course registered at the Mobile Database,
	 * Adds the course to the database
	 * @param login - login of the user that is searching the course for
	 * @return - Course information 
	 */
	public CourseMobile getCourse(int idCourse, String login);
	/** 
	 * Method that returns a specific Module requested
	 * @param idModulo - Module Id
	 * @return - the requested Module
	 */
	public ModuleMobile getModule(int idModulo);
	/**
	 * Method that updates a User Course
	 * @param c - Course to be updated
	 * @param login - User login that has the couse
	 */
	public void updateCourse(CourseMobile c, String login);
	
	
	public List<Object> getAllCoursesActivities(String login);
	
	public List<MaterialMobile> getAllCoursesMaterials(String login);
}
