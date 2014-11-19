/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.bussiness;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.MaterialMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.facade.AmadeusFacade;
import br.ufpe.cin.amadeus.amadeus_mobile.repository.ICourseRepositoryMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.repository.CourseRepositoryMobile;


public class CourseBussinessMobile implements ICourseBussinessMobile{
	private ICourseRepositoryMobile repCourse;
	
	private String[] colors;
	
	private AmadeusFacade amadeusFacade = AmadeusFacade.getInstance();
	
	public CourseBussinessMobile(){
		repCourse = new CourseRepositoryMobile();
		this.fillColors();
	}
	
	private void fillColors() {
		this.colors = new String[8];
		this.colors[0] = "#FFDAB9";
		this.colors[1] = "#778899";
		this.colors[2] = "#87cefa";
		this.colors[3] = "#00FF00";
		this.colors[4] = "#FFFF00";        
		this.colors[5] = "#D2691E";                      
		this.colors[6] = "#FF0000";
		this.colors[7] = "#8B8989";
		
	}
	
	/**
	 * Method that searches if the user has a color defined.
	 * if not, create one for the user
	 * @param colors - User color list
	 * @return - a color
	 */
	private String verifyColor(List<String> colors) {
		String color = "";
		String colorTemp = "";
		String newColor = "";
		boolean find = false;
		for(int i = 0; i < this.colors.length; i++) {
			color = this.colors[i];
			find = false;
			for (int j = 0; j < colors.size(); j++) {
				colorTemp = colors.get(j);
				if(colorTemp!= null && colorTemp.equals(color)) {
					find = true;
					break;
				}
			}
			if(!find) {
				newColor = color;
				break;
			}
		}
		return newColor;
	}

	/**
	 * Method that Searches for all of the User courses, 
	 * returning a list of Courses
	 * @param login - User login to search for
	 * @return - Course List
	 */
	public List<CourseMobile> listCourses(String login) {
		return repCourse.listCourses(login);
	}

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
	public CourseMobile getCourse(int idCourse, String login) {
		AmadeusFacade f = AmadeusFacade.getInstance();
		CourseMobile c = repCourse.getCourse(idCourse, login);
		CourseMobile d = f.getCourse(idCourse); 
		if(c == null){
			repCourse.insertCourse(d, login);
		  	List<String> colors = repCourse.getUserColors(login);
		  	if(colors.size() < 8) {
		  		d.setColor(verifyColor(colors));
		  		d.setSms(true);
		  		updateCourse(d, login);
		  	}else{
		  		d.setColor(this.colors[0]);
		  		updateCourse(d, login);
		  	}
		  	
		}else {
			d.setColor(c.getColor());
			d.setSms(c.getSms());
			d.setCount(c.getCount());
		}
		return d;
	}
	
	/** 
	 * Method that returns a specific Module requested
	 * @param idModulo - Module Id
	 * @return - the requested Module
	 */
	public ModuleMobile getModule(int idModulo) {
		return repCourse.getModule(idModulo);
	}

	/**
	 * Method that updates a User Course
	 * @param c - Course to be updated
	 * @param login - User login that has the couse
	 */
	public void updateCourse(CourseMobile c, String login) {
		repCourse.updateCourse(c, login);		
	}
	

	//@Override
	public List<Object> getAllCoursesActivities(String login) {
		ArrayList<Object> retorno = new ArrayList<Object>();
		
		List<CourseMobile> cursos = amadeusFacade.listCourses(login);
		for (CourseMobile cm : cursos){
			List<ModuleMobile> modules = cm.getModules();
			for (ModuleMobile mm : modules) {
				retorno.addAll(mm.getHomeworks());
			}
		}

		return retorno;
	}

	//@Override
	public List<MaterialMobile> getAllCoursesMaterials(String login) {
		List<MaterialMobile> retorno = new ArrayList<MaterialMobile>();
		
		List<CourseMobile> cursos = amadeusFacade.listCourses(login);
		for (CourseMobile cm : cursos) {
			List<ModuleMobile> modules = cm.getModules();
			for (ModuleMobile mm : modules) {
				retorno.addAll(mm.getMaterials());
			}
		}
		
		return retorno;
	}
}
