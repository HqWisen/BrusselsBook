package be.brusselsbook.main;

import java.sql.SQLException;
import java.sql.Timestamp;

import be.brusselsbook.data.BookUser;
import be.brusselsbook.sql.AccessFactory;
import be.brusselsbook.sql.BookUserAccess;

public class JDBCTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Running jdbc testing...");
		AccessFactory factory = AccessFactory.getInstance();
		BookUserAccess bookUserAccess = factory.getBookUserAccess();
		System.out.println(bookUserAccess.userWithEmail("kevinspacey@brusselsbook.be"));
		System.out.println(bookUserAccess.userWithUsername("jordan"));
		System.out.println(bookUserAccess.userWithUid("f1sL"));
	}

	
}
