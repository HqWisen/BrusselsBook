package be.brusselsbook.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import be.brusselsbook.data.Establishment;
import be.brusselsbook.parser.RestaurantXml;
import be.brusselsbook.parser.Restaurants;
import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.EstablishmentAccess;
import be.brusselsbook.utils.BrusselsBookUtils;

public class JDBCTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		System.out.println("Running jdbc testing...");
		String fileContent = BrusselsBookUtils.readFileFromResource("Restaurants.xml");
		Restaurants restaurants = BrusselsBookUtils.unmarshal(fileContent, Restaurants.class);
		List<RestaurantXml> restaurantList = restaurants.getRestaurantList();
		AccessFactory factory = AccessFactory.getInstance();
		EstablishmentAccess<Establishment> establishmentAccess = factory.getEstablishmentAccess();
		for(RestaurantXml rx : restaurantList){
			establishmentAccess.createEstablishement(rx.getRestoInfos());	
		}
		/*AdministratorAccess administratorAccess = factory.getAdminstratorAccess();
		BookUserAccess<BookUser> bookUserAccess = factory.getBookUserAccess();
		EstablishmentAccess<Establishment> establishmentAccess = factory.getEstablishmentAccess();
		AddressAccess addressAccess = factory.getAddressAccess();
		System.out.println(addressAccess.withEid(1L));
		System.out.println(establishmentAccess.createEstablishment("Jimmy Burger", "02488371627", "http://jimmyburger.be"));
		System.out.println(addressAccess.createAddress(1L, "Rue steens", "7", "Saint-Gilles", "1060", 4.4567f, 50.65498f));
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
		BookUser bookUser = administratorAccess.create("hakimaa@brusselsbook.be", "hakimaa", "nous");
		System.out.println(bookUser);*/
		
	}
	
	
}
