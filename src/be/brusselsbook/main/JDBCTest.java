package be.brusselsbook.main;

import java.sql.SQLException;

import be.brusselsbook.data.BookUser;
import be.brusselsbook.sql.AccessFactory;
import be.brusselsbook.sql.BookUserAccess;

public class JDBCTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Running jdbc testing...");
		AccessFactory factory = AccessFactory.getInstance();
		BookUserAccess bookUserAccess = factory.getBookUserAccess();
		BookUser bookUser = bookUserAccess.create("hakim@brusselsbook.be", "hakim", "nous");
		System.out.println(bookUser);
		System.out.println(bookUserAccess.userWithUsername("hakim"));
	}

	
}
