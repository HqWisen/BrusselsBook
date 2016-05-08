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
import be.brusselsbook.sql.data.Administrator;
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

	private Administrator createAdministrator(String nickname) {
		LOGGER.info("xml creator: create admin: " + nickname);
		String email = BrusselsBookUtils.generateEmail(nickname);
		Administrator admin = administratorAccess.withEmail(email);
		if(admin == null){
			return administratorAccess.createAdministrator(email, nickname, "azerty");	
		}
		return admin;
	}

	private void parseCafes() throws IOException{
		String fileContent = BrusselsBookUtils.readFileFromResource(CAFESXML);
		Cafes cafes = BrusselsBookUtils.unmarshal(fileContent, Cafes.class);
		List<CafeXml> cafeList = cafes.getCafeList();		
		for (CafeXml cx : cafeList) {
			Administrator admin = createAdministrator(cx.getNickname());
			cafeAccess.createCafeFromAdmin(admin.getAid(), cx.getCafeInfos());
		}
	}
	
	private void parseRestaurants() throws IOException{
		String fileContent = BrusselsBookUtils.readFileFromResource(RESTAURANTSXML);
		Restaurants restaurants = BrusselsBookUtils.unmarshal(fileContent, Restaurants.class);
		List<RestaurantXml> restaurantList = restaurants.getRestaurantList();		
		for (RestaurantXml rx : restaurantList) {
			Administrator admin = createAdministrator(rx.getNickname());
			restaurantAccess.createRestaurantFromAdmin(admin.getAid(), rx.getRestoInfos());
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
