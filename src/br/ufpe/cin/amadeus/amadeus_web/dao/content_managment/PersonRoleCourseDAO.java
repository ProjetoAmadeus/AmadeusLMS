/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.content_managment;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.dao.GenericDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.PersonRoleCourse;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

public interface PersonRoleCourseDAO extends GenericDAO<PersonRoleCourse, Integer>{
	public List<Person> searchUsersByCoursesAndRole(Course course, Role role);
	public int getNumberOfStudentsInCourse(Course c, Role role);	
	public boolean isRegisteredUser(Person user, Course c);
	public List<PersonRoleCourse> findUsersRegisteredInCourseByRole(Course course, Role role);
	public List<PersonRoleCourse> canAssistanceRequest(Person person, Course course, Role role);
	public boolean isStudent(Person person, Course course, Role role);
	public List<PersonRoleCourse> getPossibleTeacherOrAssistantsInCourse(Person person, Role roleA, Role roleT, Course course);
	public List<PersonRoleCourse> getByRoleInCourse(Person person, Course course, Role role);
	public List<PersonRoleCourse> getStudentRolesCourses(int studentId);
	public List<PersonRoleCourse> getStudentByUser(AccessInfo accessInfo);
}
