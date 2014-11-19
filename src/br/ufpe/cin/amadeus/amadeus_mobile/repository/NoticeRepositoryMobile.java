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
import java.util.Date;
import java.util.List;

import br.ufpe.cin.amadeus.amadeus_mobile.basics.NoticeMobile;
import br.ufpe.cin.amadeus.amadeus_mobile.facade.AmadeusFacade;


public class NoticeRepositoryMobile extends RepositoryMobile implements INoticeRepositoryMobile{

	public NoticeRepositoryMobile(){
		this.createConnectionDotReply();
	}
	
	/**
	 * Method that returns a Notice List from the User´s Course
	 * @param idCouse - Course Id
	 * @param login -  User login
	 * @return - User´s Course Notice List
	 */
	public List<NoticeMobile> getNoticesCourse(int idCourse, String login) {
		Connection con = this.getConnectionDotReply();
		List<NoticeMobile> notices = new ArrayList<NoticeMobile>();
		try {
			
			String SQL = "SELECT * FROM notice notice, login_notice login " +
			"WHERE notice.id = login.idnotice AND login.login = ? AND notice.idcourse = ? " +
			"ORDER BY notice.date_creation"; 
			
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setString(1, login);
			pstmt.setInt(2, idCourse);
			
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			NoticeMobile notice = null;
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int type = rs.getInt("type");
				boolean lido = rs.getBoolean("isread");
				Date data= rs.getDate("date_creation");
				notice = new NoticeMobile();
				notice.setId(id);
				notice.setTitle(title);
				notice.setContent(content);
				notice.setType(type);
				notice.setRead(lido);
				notice.setDateCreation(data);
				notices.add(notice);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return notices;
	}
	/**
	 *  Method that returns a Notice List from the User´s Module
	 * @param idModule - Module Id
	 * @param login -  User login
	 * @return - User´s Module Notice List
	 */
	public List<NoticeMobile> getNoticesModule(int idModule, String login) {
		Connection con = this.getConnectionDotReply();
		List<NoticeMobile> notices = new ArrayList<NoticeMobile>();
		try {
			
			String SQL = "SELECT * FROM notice notice, login_notice login " +
			"WHERE notice.id = login.idnotice AND login.login = ? AND notice.idmodule = ? " +
			"ORDER BY notice.date_creation"; 
			
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setString(1, login);
			pstmt.setInt(2, idModule);
			
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			NoticeMobile notice = null;
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int type = rs.getInt("type");
				boolean lido = rs.getBoolean("isread");
				Date data= rs.getDate("date_creation");
				notice = new NoticeMobile();
				notice.setId(id);
				notice.setTitle(title);
				notice.setContent(content);
				notice.setType(type);
				notice.setRead(lido);
				notice.setDateCreation(data);
				notices.add(notice);
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return notices;
	}

	/**
	 * Method that returns the User Notice requested
	 * @param idNotice - Notice Id
	 * @param login	- User login that has the Notice Id
	 * @return - Notice
	 */
	public NoticeMobile getNotice(int idNotice, String login) {
		Connection con = this.getConnectionDotReply();
		NoticeMobile notice = null;
		try {
			String SQL = " select * from notice notice, login_notice login" +
					" where login.idnotice = ? and " +
					"login.login like ? and notice.id = login.idnotice "; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setInt(1, idNotice);
			pstmt.setString(2, login);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int idModule = rs.getInt("idModule");
				int idElemento = rs.getInt("idelement");
				int type = rs.getInt("type");
				boolean read = rs.getBoolean("isread");
				Date data= rs.getDate("date_creation");
				notice = new NoticeMobile();
				notice.setId(id);
				notice.setTitle(title);
				notice.setContent(content);
				notice.setType(type);
				notice.setIdModule(idModule);
				notice.setIdElement(idElemento);
				notice.setRead(read);
				notice.setDateCreation(data);
			}
			pstmt.close();
			if(!notice.isRead()){
			String sqlUpdate = "UPDATE login_notice SET isread=TRUE WHERE login=? AND idnotice=?";
			PreparedStatement pstmtUpdate = (PreparedStatement) con.prepareStatement(sqlUpdate);
			pstmtUpdate.setString(1, login);
			pstmtUpdate.setInt(2, idNotice);
			pstmtUpdate.executeUpdate();
			pstmtUpdate.close();
			notice.setRead(true);
			}
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notice;
		
	}
	
	/**
	 * Method that returns the Notice requested
	 * @param idNotice - Notice Id
	 * @return - Notice
	 */
	public NoticeMobile getNoticeSMS(int idNotice) {
		Connection con = this.getConnectionDotReply();
		NoticeMobile notice = null;
		try {
			String SQL = " select * from notice notice where notice.id like ?"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setInt(1, idNotice);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int idModule = rs.getInt("idModule");
				int type = rs.getInt("type");
				//boolean read = rs.getBoolean("isread");
				notice = new NoticeMobile();
				notice.setId(id);
				notice.setTitle(title);
				notice.setContent(content);
				notice.setType(type);
				notice.setIdModule(idModule);
				//notice.setRead(read);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notice;
		
	}

	/**
	 * Method that returns the AMADeUs Notice List for the User
	 * @param login	- User login
	 * @return - Notice List
	 */
	public List<NoticeMobile> getNoticesAmadeus(String login) {
		Connection con = this.getConnectionDotReply();
		NoticeMobile notice = null;
		List<NoticeMobile> notices = new ArrayList<NoticeMobile>();
		try {
			String SQL = " select * from notice notice, login_notice login" +
					" where notice.type = ? and " +
					"login.login like ? and notice.id = login.idnotice"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setInt(1, NoticeMobile.TIPO_AMADEUS);
			pstmt.setString(2, login);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int idModule = rs.getInt("idModule");
				int type = rs.getInt("type");
				boolean read = rs.getBoolean("isread");
				Date data= rs.getDate("date_creation");
				notice = new NoticeMobile();
				notice.setId(id);
				notice.setTitle(title);
				notice.setContent(content);
				notice.setType(type);
				notice.setIdModule(idModule);
				notice.setRead(read);
				notice.setDateCreation(data);
				notices.add(notice);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notices;
	}

	/**
	 * Method that Adds a Notice
	 * @param notice - Notice to add
	 * Notice.Type => [Atividade] = 0
	 * 				  [Curso] = 1
	 * 				  [Material] = 2
	 * 				  [Enquete] = 3
	 */
	public void addNotice(NoticeMobile notice) {
		Connection con = this.getConnectionDotReply();
		java.sql.Date date = new java.sql.Date(notice.getDateCreation().getTime());
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO notice(id, title, content, " +
					"idmodule, idcourse, type, idelement, date_creation, sent) values (nextval('sequence_notice'), " +
					"?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getContent());
			ps.setInt(3, notice.getIdModule());
			ps.setInt(4, notice.getIdCourse());
			ps.setInt(5, notice.getType());
			ps.setInt(6, notice.getIdElement());
			ps.setDate(7, date);
			ps.setBoolean(8, false);
			ps.executeUpdate();
			
			ArrayList<String> logins = AmadeusFacade.getInstance().findLoginsByCourse(notice.getIdCourse());
			
			PreparedStatement ps1 = (PreparedStatement) con.prepareStatement("INSERT INTO login_notice(id_key, login, idnotice, isread)" +
					" VALUES (nextval('sequencia_login_notice'),?,?,?)");
			
			for (String login : logins){
			   ps1.setString(1, login);
			   ps1.setInt(2, notice.getId());
			   ps1.setBoolean(3, notice.isRead());
			   ps1.executeUpdate();
			   ps1.clearParameters();
			}
			
			ps.close();
			ps1.close();
			con.close();
		} catch(Exception e) {
	     e.printStackTrace();
		}
	}

	/**
	 * Method that Updates a User Notice
	 * @param notice - Notice Updated
	 * @param login - User Login that has the Notice
	 */
	public void updateNotice(NoticeMobile notice, String login) {
		Connection con = this.getConnectionDotReply();
		
		try {
			String SQL = " update notice set title = ?, content = ?, idmodule = ?, idcourse = ?, type = ?, idelement = ?, date_creation = ?, sent = ? where id = ? and login = ?"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3, notice.getIdModule());
			pstmt.setInt(4, notice.getIdCourse());
			pstmt.setInt(5, notice.getType());
			pstmt.setInt(6, notice.getIdElement());
			pstmt.setDate(7, new java.sql.Date(notice.getDateCreation().getTime()));
			pstmt.setBoolean(8, false);
			pstmt.setInt(9, notice.getId());
			pstmt.setString(10, login);
			pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method that Checks if a Notice was sent
	 * @param idNotice - Notice Id
	 * @return - Boolean if the Notice was sent or not
	 */
	public boolean sent(int idNotice) {
		boolean sent = false;
		Connection con = this.getConnectionDotReply();
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM notice WHERE id = ?");
			ps.setInt(1, idNotice);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				sent = rs.getBoolean("sent");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sent;
	}
	
	/**
	 * Method that checks if there is a Notice. If exists, returns the Notice id.
	 * If it doesn't exist, returns -1
	 * @param not - Notice to be searched
	 * @return - Notice Id
	 */
	public int existNotice(NoticeMobile not){
		int retorno = -1;
		
		Connection con = this.getConnectionDotReply();
		try {
			String SQL = "SELECT id FROM notice WHERE title=? AND content=? AND " +
				  "idmodule=? AND idcourse=? AND type=? AND idelement=? AND date_creation=?"; 
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setString(1, not.getTitle());
			pstmt.setString(2, not.getContent());
			pstmt.setInt(3, not.getIdModule());
			pstmt.setInt(4, not.getIdCourse());
			pstmt.setInt(5, not.getType());
			pstmt.setInt(6, not.getIdElement());
			java.sql.Date dt = new java.sql.Date(not.getDateCreation().getTime());
			pstmt.setDate(7, dt);
			
			ResultSet rs = (ResultSet) pstmt.executeQuery();			
			if (rs.next()){
			  retorno = rs.getInt("id");
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public static void main(String[] args) {
		NoticeMobile n1 = new NoticeMobile(1, "Teste de usuarios","estamos atrasados",1, 1500, 3, false, 0, new Date(2005, 10, 23));
		NoticeRepositoryMobile rn = new NoticeRepositoryMobile();
		rn.addNotice(n1);
	}
	
	/**
	 * Method that returns the last id of a course notice
	 * @param idCouse - Course Id
	 * @param login -  User login
	 * @return - User´s Course Notice List
	 */
	public int getLastId(int idCourse) {
		Connection con = this.getConnectionDotReply();
		int id = -1;
		try {
			
			String SQL = "SELECT MAX(notice.id) as id FROM notice " +
			"WHERE notice.idcourse = ?"; 
			
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(SQL);
			pstmt.setInt(1, idCourse);
			
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}