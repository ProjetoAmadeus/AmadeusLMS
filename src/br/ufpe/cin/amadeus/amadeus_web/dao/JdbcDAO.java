package br.ufpe.cin.amadeus.amadeus_web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import br.ufpe.cin.amadeus.amadeus_web.util.TweetInteractionType;

public class JdbcDAO {

	private static final String DATABASE_NAME = "jdbc:postgresql://localhost:5433/amadeus_web";
	private static final String DATABASE_USER = "postgres";
	private static final String DATABASE_PASSWORD = "postgres";

	private static Connection con;

	protected static Connection getConnection() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(DATABASE_NAME, DATABASE_USER,
						DATABASE_PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	public void closeConnection() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean userTwitterExists(String userTwitter) {
		try {
			Statement stmt = getConnection().createStatement();
			stmt.setMaxRows(1);
			ResultSet rs = stmt
					.executeQuery("SELECT id FROM person WHERE twitterlogin='"
							+ userTwitter + "'");
			if (rs.next()) {
				// closeConnection();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getUserIdByScreenName(String screenName) {
		int userId = 0;
		try {
			Statement stmt = getConnection().createStatement();
			stmt.setMaxRows(1);
			ResultSet rs = stmt
					.executeQuery("SELECT id FROM person WHERE twitterlogin='"
							+ screenName + "'");
			while (rs.next()) {
				userId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	public void saveTweet(Date dateOfTweet, TweetInteractionType tit,
			String tweetText, int userSenderId, int userTargetId) {

		Connection con = getConnection();

		String insertQuery = "INSERT INTO tweet values(nextval('hibernate_sequence'),?,?,?,?,?)";

		try {
			con.setAutoCommit(false);
			PreparedStatement pst = con.prepareStatement(insertQuery);
			pst.setTimestamp(1, new java.sql.Timestamp(dateOfTweet.getTime()));
			pst.setInt(2, tit.ordinal());
			pst.setString(3, tweetText);
			pst.setInt(4, userSenderId);
			pst.setInt(5, userTargetId);
			pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
