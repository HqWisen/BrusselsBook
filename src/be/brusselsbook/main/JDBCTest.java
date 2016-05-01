package be.brusselsbook.main;

import java.sql.SQLException;

import be.brusselsbook.data.BookUser;
import be.brusselsbook.data.Establishment;
import be.brusselsbook.sql.AccessFactory;
import be.brusselsbook.sql.AdministratorAccess;
import be.brusselsbook.sql.BookUserAccess;
import be.brusselsbook.sql.EstablishmentAccess;

public class JDBCTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Running jdbc testing...");
		AccessFactory factory = AccessFactory.getInstance();
		AdministratorAccess administratorAccess = factory.getAdminstratorAccess();
		BookUserAccess<BookUser> bookUserAccess = factory.getBookUserAccess();
		EstablishmentAccess<Establishment> establishmentAccess = factory.getEstablishmentAccess();
		System.out.println(bookUserAccess.SELECTBY("UID"));
		System.out.println(administratorAccess.withUsername("jordan"));
		System.out.println(administratorAccess.withUsername("kevin"));
		System.out.println(administratorAccess.withEmail("jimmyjam@brusselsbook.be"));
		System.out.println(administratorAccess.withUid("3"));
		System.out.println(administratorAccess.withAid("1"));
		System.out.println(bookUserAccess.withEmail("kevinspacey@brusselsbook.be"));
		System.out.println(bookUserAccess.withUsername("kevin"));
		System.out.println(bookUserAccess.withUid(1L));
		establishmentAccess.create("Jimmy Burger Burger", "02322222", "http://jimmyburger.com");
		System.out.println(establishmentAccess.getObjects());
		//BookUser bookUser = administratorAccess.create("hakimaa@brusselsbook.be", "hakimaa", "nous");
		//System.out.println(bookUser);
	}
	
}
