/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo é parte do programa Amadeus Sistema de Gestão de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS é um software livre; você pode redistribui-lo e/ou modifica-lo dentro dos termos da Licença Pública Geral GNU como
publicada pela Fundação do Software Livre (FSF); na versão 2 da Licença.
 
Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU para maiores detalhes.
 
Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o título "LICENCA.txt", junto com este programa, se não, escreva para a Fundação do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_mobile.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.PersonMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.UserMobile;



public class LoginRepositoryMobile extends RepositoryMobile implements ILoginRepositoryMobile{
	
	public LoginRepositoryMobile(){
		this.createConnectionDotReply();
	}

	/**
	 * Method that verifies the existence of a user and it´s password.
	 * If the user and password match on the database, returns the User
	 * Else returns null.
	 * @param login - User login
	 * @param password - User password
	 * @return - User 
	 */
	public UserMobile verifyLogin(String login, String senha) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void updateLogin(PersonMobile p) {
		Connection con = this.getConnectionDotReply();
		
		try {
			String SQL = " update login_numero set numero_celular = ? where login = ?"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setString(1, p.getPhoneNumber());
			pstmt.setString(2, p.getName());
			pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public PersonMobile getLogin(String login) {
		Connection con = this.getConnectionDotReply();
		PersonMobile retorno  = null;
		try {
			String SQL = " select * from login_numero where login like ? "; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setString(1, login);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				retorno = new PersonMobile();
				int id = rs.getInt("id");
				String loginTemp = rs.getString("login");
				String numero = rs.getString("numero_celular");
				retorno.setId(id);
				retorno.setName(loginTemp);
				retorno.setPhoneNumber(numero);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}
	
	public void insertLogin(String login) {
		Connection con = this.getConnectionDotReply();
		try {
			String SQL = " insert into login_numero(id,login,numero_celular) values (nextval('sequencia_login_numero'),?,?)"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setString(1, login);
			pstmt.setString(2, null);
			pstmt.executeUpdate();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
