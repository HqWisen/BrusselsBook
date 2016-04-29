package be.brusselsbook.data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Restaurants")
public class Restaurants {
	
	@XmlElement(name = "Restaurant")
	List<Restaurant> restaurantList;
	
	private Restaurants(){	
	}
	
	public List<Restaurant> getRestaurantList() {
		return restaurantList;
	}

}
