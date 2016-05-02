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
	}
	
	
}
