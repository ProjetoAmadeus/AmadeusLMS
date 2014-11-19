/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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