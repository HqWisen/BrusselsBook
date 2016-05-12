package be.brusselsbook.parser;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class RestaurantInfos extends EstablishmentInfos {

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
	
	public Boolean hasTakeAway(){
		return getTakeAway() != null;
	}

	private String getDelivery(){
		return delivery;
	}

	public Boolean makeDelivery(){
		return getDelivery() != null;
	}
	
	public Integer getPriceRange() {
		return priceRange;
	}

	public Banquet getBanquet() {
		return banquet;
	}

	public List<ClosedDay> getClosedDayList() {
		return closedDayList;
	}

	public Integer getBanquetPlaces(){
		return banquet.getCapacity();
	}
	
	public String getClosedDays(){
		// TODO find a way to modelize the closed day in the db
		return "NOT IMPLEMENT";
	}
	
	public void setClosedDayList(List<ClosedDay> closedDayList) {
		this.closedDayList = closedDayList;
	}

	public void setTakeAway(String takeAway) {
		this.takeAway = takeAway;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public void setPriceRange(int priceRange) {
		this.priceRange = priceRange;
	}

	public void setBanquet(Banquet banquet) {
		this.banquet = banquet;
	}

	public void setBanquetPlaces(int banquet) {
		this.banquet = new Banquet();
		this.banquet.setCapacity(banquet);
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
