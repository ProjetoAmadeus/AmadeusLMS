/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_middleware;

import br.ufpe.cin.middleware.proxies.Stub_abstract;
import javax.mail.MessagingException;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.exception.CourseInvalidException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidLogonException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidUserException;

import java.util.List;
import java.io.IOException;
import br.ufpe.cin.middleware.exceptions.AmadeusMiddlewareException;

/**
 * Respons�vel por receber as chamadas dos m�todos
 * do lado cliente, construir mensagens correspondentes
 * e enviar estas requisi��es ao skeleton do lado
 * servidor.
 * 
 * Ficar� do lado "cliente" do servi�o de banco de dados.
 * 
 * @author Bruno Barros (blbs at cin ufpe br)
 *
 */
public class DBWrapper_stub extends Stub_abstract implements DBWrapper {

	private static final long serialVersionUID = 1L;

	public DBWrapper_stub(String host, int port) throws AmadeusMiddlewareException {
		super(host, port);
	}

	@SuppressWarnings("unchecked")
	public List<Keyword> getMostPopularKeywords() throws AmadeusMiddlewareException {
		return (List<Keyword>) this.process("getMostPopularKeywords");
	}

	public Person insertPerson(Person p0) throws InvalidUserException, AmadeusMiddlewareException {
		return (Person) this.process("insertPerson",p0);
	}

	public Course insertCourse(Course p0) throws CourseInvalidException, AmadeusMiddlewareException {
		return (Course) this.process("insertCourse",p0);
	}

	public Course updateCourse(Course p0) throws AmadeusMiddlewareException {
		return (Course) this.process("updateCourse",p0);
	}

	public void validateCourseStepOne(Course p0) throws CourseInvalidException, AmadeusMiddlewareException {
		this.process("validateCourseStepOne",p0);
	}

	public Keyword insertKeyword(Keyword p0) throws AmadeusMiddlewareException {
		return (Keyword) this.process("insertKeyword",p0);
	}

	public String remindPassword(String p0) throws MessagingException, IOException, AmadeusMiddlewareException {
		return (String) this.process("remindPassword",p0);
	}

	@SuppressWarnings("unchecked")
	public List<Course> getCoursesByKeyword(String p0) throws AmadeusMiddlewareException {
		return (List<Course>) this.process("getCoursesByKeyword",p0);
	}

	@SuppressWarnings("unchecked")
	public List<Course>[] getCoursesByRule(String p0) throws AmadeusMiddlewareException {
		return (List<Course>[]) this.process("getCoursesByRule",p0);
	}

	public boolean existLogin(String p0) throws AmadeusMiddlewareException {
		return (Boolean) this.process("existLogin",p0);
	}

	public AccessInfo searchUserByLogin(String p0) throws AmadeusMiddlewareException {
		return (AccessInfo) this.process("searchUserByLogin",p0);
	}

	public AccessInfo searchUserById(int p0) throws AmadeusMiddlewareException {
		return (AccessInfo) this.process("searchUserById",p0);
	}

	public Person editUser(Person p0) throws Exception, AmadeusMiddlewareException {
		return (Person) this.process("editUser",p0);
	}

	public boolean existEmail(String p0) throws AmadeusMiddlewareException {
		return (Boolean) this.process("existEmail",p0);
	}

	public AccessInfo logon(AccessInfo accessInfo) throws InvalidLogonException, AmadeusMiddlewareException {
		return (AccessInfo) this.process("logon", accessInfo);
	}

	public int getNumberOfPendingTasks(AccessInfo p0) throws AmadeusMiddlewareException {
		return (Integer) this.process("getNumberOfPendingTasks",p0);
	}

	@SuppressWarnings("unchecked")
	public List<Course> searchCoursesByAccessInfo(AccessInfo p0) throws AmadeusMiddlewareException {
		return (List<Course>) this.process("searchCoursesByAccessInfo",p0);
	}

}
