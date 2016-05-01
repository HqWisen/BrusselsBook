package be.brusselsbook.parser;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class RestaurantInfos extends Informations {

	@XmlElementWrapper(name = "Closed")
	@XmlElement(name = "On")
	private List<ClosedDay> closedDayList;

	@XmlElement(name = "TakeAway")
	private String takeAway; // null if none, empty string if has.
	
	@XmlElement(name = "Delivery")
	private String delivery;
	
	@XmlElement(name = "PriceRange")
	private int priceRange;
	
	@XmlElement(name = "Banquet")
	private Banquet banquet;
	
	private String getTakeAway(){
		return takeAway;
	}
	
	public boolean hasTakeAway(){
		return getTakeAway() != null;
	}

	private String getDelivery(){
		return delivery;
	}

	public boolean makeDelivery(){
		return getDelivery() != null;
	}
	
	public int getPriceRange() {
		return priceRange;
	}

	public Banquet getBanquet() {
		return banquet;
	}

	public List<ClosedDay> getClosedDayList() {
		return closedDayList;
	}

	@Override
	public String toString() {
		String string = "Name: ";
		string += super.getName() ;
		string += "; ";
		string+= banquet;
		return string;
	}

}
