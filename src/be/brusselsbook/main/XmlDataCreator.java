package be.brusselsbook.main;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import be.brusselsbook.parser.CafeXml;
import be.brusselsbook.parser.Cafes;
import be.brusselsbook.parser.RestaurantXml;
import be.brusselsbook.parser.Restaurants;
import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.AdministratorAccess;
import be.brusselsbook.sql.access.CafeAccess;
import be.brusselsbook.sql.access.RestaurantAccess;
import be.brusselsbook.utils.BrusselsBookUtils;

public class XmlDataCreator {

	private static final Logger LOGGER = Logger.getLogger(XmlDataCreator.class.getName());
	
	private static final String RESTAURANTSXML = "Restaurants.xml";
	private static final String CAFESXML = "Cafes.xml";
	
	
	private RestaurantAccess restaurantAccess;
	private AdministratorAccess administratorAccess;
	private CafeAccess cafeAccess;

	public XmlDataCreator(AccessFactory factory) {
		this.restaurantAccess = factory.getRestaurantAccess();
		this.administratorAccess = factory.getAdminstratorAccess();
		this.cafeAccess = factory.getCafeAccess();
	}

	private void createAdministrator(String nickname) {
		LOGGER.info("xml creator: create admin: " + nickname);
		String email = BrusselsBookUtils.generateEmail(nickname);
		if (administratorAccess.withEmail(email) == null) {
			administratorAccess.createAdministrator(email, nickname, "azerty");
		}
	}

	private void parseCafes() throws IOException{
		String fileContent = BrusselsBookUtils.readFileFromResource(CAFESXML);
		Cafes cafes = BrusselsBookUtils.unmarshal(fileContent, Cafes.class);
		List<CafeXml> cafeList = cafes.getCafeList();		
		for (CafeXml cx : cafeList) {
			cafeAccess.createCafe(cx.getCafeInfos());
			createAdministrator(cx.getNickname());
		}
	}
	
	private void parseRestaurants() throws IOException{
		String fileContent = BrusselsBookUtils.readFileFromResource(RESTAURANTSXML);
		Restaurants restaurants = BrusselsBookUtils.unmarshal(fileContent, Restaurants.class);
		List<RestaurantXml> restaurantList = restaurants.getRestaurantList();		
		for (RestaurantXml rx : restaurantList) {
			restaurantAccess.createRestaurant(rx.getRestoInfos());
			createAdministrator(rx.getNickname());
		}
		
	}
	
	public void run() throws IOException {
		parseRestaurants();
		parseCafes();
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Running creator testing...");
		AccessFactory factory = AccessFactory.getInstance();
		new XmlDataCreator(factory).run();
	}

}
