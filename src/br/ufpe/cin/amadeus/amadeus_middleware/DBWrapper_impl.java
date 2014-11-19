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
import br.ufpe.cin.amadeus.amadeus_web.facade.Controller;

/**
 * Corresponde ao Facade anterior.
 * 
 * Ficará do lado "servidor" do serviço de banco de dados,
 * e será invocada pelo skeleton.
 * 
 * @author Bruno Barros (blbs at cin ufpe br)
 *
 */
public class DBWrapper_impl implements DBWrapper {

	private static final long serialVersionUID = 1L;

	private Controller controller = null;

	public DBWrapper_impl() {
		this.controller = Controller.getInstance();
	}

	public Person editUser(Person person) throws Exception {
		return controller.editUser(person);
	}

	public boolean existEmail(String email) {
		return controller.existEmail(email);
	}

	public boolean existLogin(String login) {
		return controller.existLogin(login);
	}

	public List<Course> getCoursesByKeyword(String keyword) {
		return controller.getCoursesByKeyword(keyword);
	}

	public List<Course>[] getCoursesByRule(String rule) {
		return controller.getClassifiedCoursesByRule(rule);
	}

	public List<Keyword> getMostPopularKeywords() {
		return controller.getMostPopularKeywords();
	}

	public int getNumberOfPendingTasks(AccessInfo userInfo) {
		return controller.getNumberOfPendingTasks(userInfo);
	}

	public Course insertCourse(Course c) throws CourseInvalidException {
		return controller.insertCourse(c);
	}

	public Keyword insertKeyword(Keyword keyword) {
		return controller.insertKeyword(keyword);
	}

	public Person insertPerson(Person p) throws InvalidUserException {
		return controller.insertPerson(p);
	}

	public AccessInfo logon(AccessInfo accessInfo) throws InvalidLogonException {
		return controller.logon(accessInfo);
	}

	public String remindPassword(String email) throws MessagingException, IOException {
		return controller.remindPassword(email);
	}

	public List<Course> searchCoursesByAccessInfo(AccessInfo userInfo) {
		return controller.searchCoursesByAccessInfo(userInfo);
	}

	public AccessInfo searchUserById(int id) {
		return controller.searchUserById(id);
	}

	public AccessInfo searchUserByLogin(String login) {
		return controller.searchUserByLogin(login);
	}

	public Course updateCourse(Course c) {
		return controller.updateCourse(c);
	}

	public AccessInfo updateUser(AccessInfo ai) {
		return controller.updateUser(ai);
	}

	public void validateCourseStepOne(Course c) throws CourseInvalidException {
		controller.validateCourseStepOne(c);
	}

}
