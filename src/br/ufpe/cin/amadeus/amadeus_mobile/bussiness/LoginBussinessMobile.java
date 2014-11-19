/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.bussiness;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.UserMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.repository.ILoginRepositoryMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.repository.LoginRepositoryMobile;

public class LoginBussinessMobile implements ILoginBussinessMobile{
	
	private ILoginRepositoryMobile repLogin;
	
	public LoginBussinessMobile(){
		repLogin = new LoginRepositoryMobile();
	}
	
	/**
	 * Method that verifies the existence of a user and it´s password.
	 * If the user and password match on the database, returns the User
	 * Else returns null.
	 * @param login - User login
	 * @param password - User password
	 * @return - User 
	 */
	public UserMobile verifyLogin(String login, String password) {
		return repLogin.verifyLogin(login, password);
	}
	
	/**
	 * Method that update a cell number for the login passed
	 * @param login - User login
	*/
	
	public void updateLogin(PersonMobile p){
		repLogin.updateLogin(p);
	}
	
	/**
	 * Method that verifies the existence of a login.
	 * If the login match on the database, returns the login
	 * Else returns null.
	 * @param login - User login
	 * @return - String login 
	 */
	
	public PersonMobile getLogin(String login) {
		return repLogin.getLogin(login);
	}
	
	/**
	 * Method that insert a login for the login passed
	 * @param login - User login
	*/
	public void insertLogin(String login){
		repLogin.insertLogin(login);
	}

}
