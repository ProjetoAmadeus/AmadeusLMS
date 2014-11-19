/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
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
	 * Method that verifies the existence of a user and it�s password.
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
