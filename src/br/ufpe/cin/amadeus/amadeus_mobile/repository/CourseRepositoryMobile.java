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
import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.CourseMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.ModuleMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.basics.UserMobile;


public class CourseRepositoryMobile extends RepositoryMobile implements ICourseRepositoryMobile{
	/**
	 * Constructor
	 * Creates a Database connection to the .reply Mobile DB
	 */
	public CourseRepositoryMobile(){
		this.createConnectionDotReply();
	}
	
	/**
	 * Method that returns the User Course list
	 * @param login - User login
	 * @return - User course list
	 */
	public List<CourseMobile> listCourses(String login) {
		Connection con = this.getConnectionDotReply();
		List<CourseMobile> cursos = new ArrayList<CourseMobile>();
		try {
			String SQL = " select * from Curso where login like ?"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setString(1, login);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				String finalName = rs.getString("nome");
				List<UserMobile> users = new ArrayList<UserMobile>();
				CourseMobile u = new CourseMobile();
				cursos.add(u);
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cursos;
	}

	/**
	 * Method that returns a specific User Course
	 * @param idCouse - Course ID
	 * @param login - User login
	 * @return - Course requested 
	 */
	public CourseMobile getCourse(int idCourse, String login) {
		Connection con = this.getConnectionDotReply();
		CourseMobile retorno  = null;
		try {
			String SQL = " select * from Course where idCourse = ? and login like ?"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setInt(1, idCourse);
			pstmt.setString(2, login);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				retorno = new CourseMobile();
				int id = rs.getInt("id");
				String color = rs.getString("color");
				boolean sms = rs.getBoolean("sms");
				String loginTemp = rs.getString("login");
				int idTemp = rs.getInt("idCourse");
				retorno.setColor(color);
				retorno.setId(id);
				retorno.setSms(sms);
				retorno.setLogin(loginTemp);
				retorno.setIdCourse(idTemp);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}
	
	/**
	 * Method that returns a the User Color List, used to display the information
	 * to the user it's predefined colored coursed 
	 *  @param login - User login
	 *  @return - Color list (string)
	 */
	public List<String> getUserColors(String login) {
		Connection con = this.getConnectionDotReply();
		List<String> cores = new ArrayList<String>();
		try {
			String SQL = " select distinct(color) from course where login = ? and color is not null"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setString(1, login);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				String color = rs.getString("color");
				cores.add(color);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cores;
	
	}

	/**
	 * Method that returns a specific module, by its Id
	 * @param idModule - Module Id
	 * @return - requested module
	 */
	public ModuleMobile getModule(int idModule) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method that updates a User Course
	 * @param c - Course settings to be updated
	 * It's updated the Color and the user wants to receive the SMS for this course
	 * @param login - User that has the course
	 */
	public void updateCourse(CourseMobile c, String login) {
		Connection con = this.getConnectionDotReply();
		
		try {
			String SQL = " update course set sms = ?, color = ? where idcourse = ? and login = ?"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setBoolean(1, c.getSms());
			pstmt.setString(2, c.getColor());
			pstmt.setInt(3, c.getId());
			pstmt.setString(4, login);
			pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method that inserts a reference in the database adding a course to the user
	 * @param c - course which the user added
	 * @param login - user login 
	 */
	public void insertCourse(CourseMobile c, String login) {
		Connection con = this.getConnectionDotReply();
		try {
			String SQL = " insert into course(id,idcourse,login) values (nextval('sequencia_curso'),?,?)"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setInt(1, c.getId());
			pstmt.setString(2, login);
			pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
