package be.brusselsbook.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/brusselsbook";
	private static final String DBUSER = "bbadmin";
	private static final String DBPWD = "common";
	private static final String QUERY = "select * from BookUser";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Running jdbc testing...");
		Class.forName(DRIVERCLASS);
		Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPWD);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(QUERY);
		
		System.out.println(resultSet);
		
		connection.close();
		
		
	}

}
