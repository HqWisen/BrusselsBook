package be.brusselsbook.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import be.brusselsbook.parser.CafeXml;
import be.brusselsbook.parser.Cafes;
import be.brusselsbook.parser.RestaurantXml;
import be.brusselsbook.parser.Restaurants;
import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.CafeAccess;
import be.brusselsbook.sql.access.RestaurantAccess;
import be.brusselsbook.utils.BrusselsBookUtils;

public class JDBCTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		System.out.println("Running jdbc testing...");
		String fileContent = BrusselsBookUtils.readFileFromResource("Restaurants.xml");
		Restaurants restaurants = BrusselsBookUtils.unmarshal(fileContent, Restaurants.class);
		List<RestaurantXml> restaurantList = restaurants.getRestaurantList();
		String fileContent1 = BrusselsBookUtils.readFileFromResource("Cafes.xml");
		Cafes cafes = BrusselsBookUtils.unmarshal(fileContent1, Cafes.class);
		List<CafeXml> cafeList = cafes.getCafeList();
		AccessFactory factory = AccessFactory.getInstance();
		RestaurantAccess restaurantAccess = factory.getRestaurantAccess();
		CafeAccess cafeAccess = factory.getCafeAccess();
		for(RestaurantXml rx : restaurantList){
			restaurantAccess.createRestaurant(rx.getRestoInfos());	
		}
		for(CafeXml cx : cafeList){
			cafeAccess.createCafe(cx.getCafeInfos());
		}
		/*AdministratorAccess administratorAccess = factory.getAdminstratorAccess();
		BookUserAccess<BookUser> bookUserAccess = factory.getBookUserAccess();
		EstablishmentAccess<Establishment> establishmentAccess = factory.getEstablishmentAccess();
		AddressAccess addressAccess = factory.getAddressAccess();
		System.out.println(addressAccess.withEid(1L));
		System.out.println(establishmentAccess.createEstablishment("Jimmy Burger", "02488371627", "http://jimmyburger.be"));
<<<<<<< HEAD
		//System.out.println(addressAccess.createAddress(1L, "Rue steens", "7", "Saint-Gilles", "1060", 4.4567f, 50.65498f));
		/*System.out.println(bookUserAccess.SELECTBY("UID"));
=======
		System.out.println(addressAccess.createAddress(1L, "Rue steens", "7", "Saint-Gilles", "1060", 4.4567f, 50.65498f));
		System.out.println(bookUserAccess.SELECTBY("UID"));
>>>>>>> 719edb514a464393a7d8453396449c87b6f401df
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
