/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.content_managment;

import java.util.Date;
import java.util.List;
import java.util.Set;

import br.ufpe.cin.amadeus.amadeus_web.dao.GenericDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

public interface CourseDAO extends GenericDAO <Course, Integer>{
	
	public List<Course> searchCoursesByUser(AccessInfo userInfo);
	public List<Course> getCoursesByUser(AccessInfo userInfo);
	public List<Course> getCoursesByKeyword(Keyword key);
	public List<Course> getCoursesByContent(String cont);
	public List<Course> getCoursesByName(String name);
	public List<Course> getCoursesByProfessors(List<Person> professors);
	public List<Course> getCoursesByObjectives(String objs);
	public List<Course> getCoursesByInitialDate(Date initialDate);
	public List<Course> getCoursesByFinalDate(Date finalDate);
	public List<Course> getCoursesByAdvancedRule(String name, String professorName, Date initialDate, Date finalDate);
	public boolean courseNameExist(Course course);
	public void deleteKeywordsOrphan();
	public void incrementPopularityKeyword(int courseId, Set<Keyword> keywords);
	public void decrementPopularityKeyword(int courseId, Set<Keyword> keywords);

	
}
