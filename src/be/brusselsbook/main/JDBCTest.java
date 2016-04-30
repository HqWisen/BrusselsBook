package be.brusselsbook.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.DatabaseAccess;

public class JDBCTest {

	private static final String QUERY = "select * from BookUser;";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		System.out.println("Running jdbc testing...");
		DatabaseAccess databaseAccess = new DatabaseAccess();
		Connection connection = databaseAccess.getConnection();
		PreparedStatement statement = connection.prepareStatement(QUERY);
		ResultSet resultSet = statement.executeQuery();
		Class.forName("com.mysql.jdbc.Driver"); // chargement du driver JDBC
	
		while (resultSet.next()) {
			String email = resultSet.getString("EmailAddress");
			int UID = resultSet.getInt("UID");
			System.out.println(email + " > " + UID);
		}
		
		connection.close();
		databaseAccess.close();
	}

}
