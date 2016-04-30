package be.brusselsbook.data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Informations {

	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "Address")
	private Address address;
	
	@XmlElement(name = "Tel")
	private String tel;
	
	@XmlElement(name = "Site")
	private Site site;
	
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
	
	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}

	public Site getSite() {
		return site;
	}

	public List<ClosedDay> getClosedDayList() {
		return closedDayList;
	}

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

	
}
