package be.brusselsbook.parser;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Restaurants")
public class Restaurants {
	
	@XmlElement(name = "Restaurant")
	List<RestaurantXml> restaurantList;
	
	public List<RestaurantXml> getRestaurantList() {
		return restaurantList;
	}

}
