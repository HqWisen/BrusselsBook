package be.brusselsbook.main;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import be.brusselsbook.parser.CafeXml;
import be.brusselsbook.parser.Cafes;
import be.brusselsbook.parser.CommentXml;
import be.brusselsbook.parser.RestaurantXml;
import be.brusselsbook.parser.Restaurants;
import be.brusselsbook.parser.TagXml;
import be.brusselsbook.parser.Tagger;
import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.AdministratorAccess;
import be.brusselsbook.sql.access.BookCommentAccess;
import be.brusselsbook.sql.access.BookUserAccess;
import be.brusselsbook.sql.access.CafeAccess;
import be.brusselsbook.sql.access.RestaurantAccess;
import be.brusselsbook.sql.access.TagAccess;
import be.brusselsbook.sql.access.TagDescribeAccess;
import be.brusselsbook.sql.data.Administrator;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.sql.data.Cafe;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.sql.data.Restaurant;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.BrusselsBookUtils;

public class XmlDataCreator {

	private static final Logger LOGGER = Logger.getLogger(XmlDataCreator.class.getName());

	private static final String RESTAURANTSXML = "Restaurants.xml";
	private static final String CAFESXML = "Cafes.xml";

	private RestaurantAccess restaurantAccess;
	private AdministratorAccess administratorAccess;
	private CafeAccess cafeAccess;
	private BookCommentAccess bookCommentAccess;
	private TagAccess tagAccess;
	private TagDescribeAccess tagDescribeAccess;
	private BookUserAccess<BookUser> bookUserAccess;
	public XmlDataCreator(AccessFactory factory) {
		this.restaurantAccess = factory.getRestaurantAccess();
		this.administratorAccess = factory.getAdminstratorAccess();
		this.cafeAccess = factory.getCafeAccess();
		this.bookCommentAccess = factory.getBookCommentAccess();
		this.tagAccess = factory.getTagAccess();
		this.tagDescribeAccess = factory.getTagDescribeAccess();
		this.bookUserAccess = factory.getBookUserAccess();
	}

	public Administrator createAdministrator(String nickname) {
		LOGGER.info("xml creator: create admin: " + nickname);
		String email = BrusselsBookUtils.generateEmail(nickname);
		BookUser user = bookUserAccess.withEmail(email);
		if (user == null) {
			return administratorAccess.createAdministrator(email, nickname, "common");
		}else{
			Administrator admin = administratorAccess.withEmail(email);
			if(admin == null){
				AccessUtils.executeInsert(AccessFactory.getInstance(), "INSERT INTO Administrator (UID) VALUES(?)", user.getUid());
			}
			return administratorAccess.withEmail(email);
		}
		
	}

	public BookUser createUser(String nickname) {
		LOGGER.info("xml creator: create user: " + nickname);
		String email = BrusselsBookUtils.generateEmail(nickname);
		BookUser user = bookUserAccess.withEmail(email);
		if (user == null) {
			return bookUserAccess.createUser(email, nickname, "common");
		}
		return user;
	}

	private void parseCafes() throws IOException {
		String fileContent = BrusselsBookUtils.readFileFromResource(CAFESXML);
		Cafes cafes = BrusselsBookUtils.unmarshal(fileContent, Cafes.class);
		List<CafeXml> cafeList = cafes.getCafeList();
		for (CafeXml cx : cafeList) {
			Administrator admin = createAdministrator(cx.getNickname());
			Cafe cafe = cafeAccess.createCafeFromAdmin(admin.getAid(), cx.getCafeInfos());
			List<TagXml> tagList = cx.getTagList();
			createTag(tagList, cafe);

			List<CommentXml> commentList = cx.getCommentList();
			createComment(commentList, cafe);

		}
	}

	private void parseRestaurants() throws IOException {
		String fileContent = BrusselsBookUtils.readFileFromResource(RESTAURANTSXML);
		Restaurants restaurants = BrusselsBookUtils.unmarshal(fileContent, Restaurants.class);
		List<RestaurantXml> restaurantList = restaurants.getRestaurantList();
		for (RestaurantXml rx : restaurantList) {
			Administrator admin = createAdministrator(rx.getNickname());
			Restaurant restaurant = restaurantAccess.createRestaurantFromAdmin(admin.getAid(), rx.getRestoInfos());

			List<TagXml> tagList = rx.getTagList();
			createTag(tagList, restaurant);
			List<CommentXml> commentList = rx.getCommentList();
			createComment(commentList, restaurant);
		}

	}

	public void createComment(List<CommentXml> commentList, Establishment establishment) {
		if (commentList != null) {
			for (CommentXml cx : commentList) {
				if (cx != null) {
					BookUser commenter = createUser(cx.getNickname());
					Long uid = commenter.getUid();
					Long eid = establishment.getEid();
					bookCommentAccess.createBookComment(uid, eid, cx.getScore(), cx.getContent());
				}
			}
		}

	}

	public void createTag(List<TagXml> tagList, Establishment establishment) {
		if (tagList != null) {
			for (TagXml tx : tagList) {
				if (tx != null) {
					List<Tagger> taggerList = tx.getTaggerList();
					for (int i = 0; i < taggerList.size(); i++) {
						BookUser tagger = createUser(taggerList.get(i).getNickname());
						Long uid = tagger.getUid();
						Long eid = establishment.getEid();
						if (tagAccess.withTagName(tx.getName()) == null){
							tagAccess.createTag(uid, tx.getName());
						}
						else{
							tagDescribeAccess.createTagDescribe(eid, uid, tx.getName());
						}
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
		
		XmlDataCreator creator = new XmlDataCreator(factory);
		creator.run();

	}

}
