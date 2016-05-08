package be.brusselsbook.main;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


import be.brusselsbook.sql.data.Restaurant;
import be.brusselsbook.sql.data.Cafe;
import be.brusselsbook.parser.CafeXml;
import be.brusselsbook.parser.Cafes;
import be.brusselsbook.parser.CommentXml;
import be.brusselsbook.parser.RestaurantXml;
import be.brusselsbook.parser.Restaurants;
import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.AdministratorAccess;

import be.brusselsbook.sql.access.BookCommentAccess;

import be.brusselsbook.sql.access.CafeAccess;
import be.brusselsbook.sql.access.RestaurantAccess;
import be.brusselsbook.sql.access.TagAccess;
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

	private BookCommentAccess bookCommentAccess;
	
	public XmlDataCreator(AccessFactory factory) {
		this.restaurantAccess = factory.getRestaurantAccess();
		this.administratorAccess = factory.getAdminstratorAccess();
		this.cafeAccess = factory.getCafeAccess();
		this.bookCommentAccess = factory.getBookCommentAccess();
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
			Cafe cafe = cafeAccess.createCafeFromAdmin(admin.getAid(), cx.getCafeInfos());
			List<CommentXml> commentList = cx.getCommentList();
			if (commentList != null){
				for (CommentXml cox : commentList  ){
					if (cox != null){
						Administrator commenter = createAdministrator(cox.getNickname());
						Long uid = commenter.getUid();
						Long eid = cafe.getEid();
						bookCommentAccess.createBookComment(uid, eid, cox.getScore(), cox.getContent());
					}	
				}
			}
			
		
		}
	}
	
	private void parseRestaurants() throws IOException{
		String fileContent = BrusselsBookUtils.readFileFromResource(RESTAURANTSXML);
		Restaurants restaurants = BrusselsBookUtils.unmarshal(fileContent, Restaurants.class);
		List<RestaurantXml> restaurantList = restaurants.getRestaurantList();		
		for (RestaurantXml rx : restaurantList) {
			Administrator admin = createAdministrator(rx.getNickname());
			Restaurant restaurant = restaurantAccess.createRestaurantFromAdmin(admin.getAid(), rx.getRestoInfos());
			List<CommentXml> commentList = rx.getCommentList();
			if (commentList != null){
				for (CommentXml cx : commentList  ){
					if (cx!=null){
						Administrator commenter = createAdministrator(cx.getNickname());
						Long uid = commenter.getUid();
						Long eid = restaurant.getEid();
						bookCommentAccess.createBookComment(uid, eid, cx.getScore(), cx.getContent());
					}
				}
			}
			
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
