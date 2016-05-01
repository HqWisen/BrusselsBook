package be.brusselsbook.main;

import java.sql.SQLException;

import be.brusselsbook.data.BookUser;
import be.brusselsbook.servs.AdministratorAccess;
import be.brusselsbook.sql.AccessFactory;
import be.brusselsbook.sql.BookUserAccess;

public class JDBCTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Running jdbc testing...");
		AccessFactory factory = AccessFactory.getInstance();
		AdministratorAccess administratorAccess = factory.getAdminstratorAccess();
		BookUserAccess<BookUser> bookUserAccess = factory.getBookUserAccess();
		/*System.out.println(administratorAccess.withUsername("jordan"));
		System.out.println(administratorAccess.withUsername("kevin"));
		System.out.println(administratorAccess.withEmail("jimmyjam@brusselsbook.be"));
		System.out.println(administratorAccess.withUid("3"));
		System.out.println(administratorAccess.withAid("1"));
		System.out.println(bookUserAccess.withEmail("kevinspacey@brusselsbook.be"));
		System.out.println(bookUserAccess.withUsername("kevin"));
		System.out.println(bookUserAccess.withUid(1L));*/
		System.out.println(administratorAccess.getObjects());
		//BookUser bookUser = administratorAccess.create("hakim@brusselsbook.be", "hakim", "nous");
		//System.out.println(bookUser);
	}
	
}
