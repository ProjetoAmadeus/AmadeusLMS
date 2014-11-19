/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_middleware;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.exception.CourseInvalidException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidLogonException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidUserException;
import br.ufpe.cin.middleware.exceptions.AmadeusMiddlewareException;
import br.ufpe.cin.middleware.services.naming.RemoteInterface;

public interface DBWrapper extends RemoteInterface {

	List<Keyword> getMostPopularKeywords() throws AmadeusMiddlewareException;
	Person insertPerson(Person person) throws InvalidUserException, AmadeusMiddlewareException;
	Course insertCourse(Course course) throws CourseInvalidException, AmadeusMiddlewareException;
	Course updateCourse(Course course) throws AmadeusMiddlewareException;
	void validateCourseStepOne(Course c) throws CourseInvalidException, AmadeusMiddlewareException;
	Keyword insertKeyword(Keyword keyword) throws AmadeusMiddlewareException;
	String remindPassword(String email) throws MessagingException, IOException, AmadeusMiddlewareException;
	List<Course> getCoursesByKeyword(String keyword) throws AmadeusMiddlewareException;
	List<Course>[] getCoursesByRule(String rule) throws AmadeusMiddlewareException;
	boolean existLogin(String login) throws AmadeusMiddlewareException;
	AccessInfo searchUserByLogin(String login) throws AmadeusMiddlewareException;
	AccessInfo searchUserById(int id) throws AmadeusMiddlewareException;
	Person editUser(Person person) throws Exception, AmadeusMiddlewareException;
	boolean existEmail(String email) throws AmadeusMiddlewareException;
	AccessInfo logon(AccessInfo accessInfo) throws InvalidLogonException, AmadeusMiddlewareException;
	int getNumberOfPendingTasks(AccessInfo userInfo) throws AmadeusMiddlewareException;
	List<Course> searchCoursesByAccessInfo(AccessInfo userInfo) throws AmadeusMiddlewareException;

}