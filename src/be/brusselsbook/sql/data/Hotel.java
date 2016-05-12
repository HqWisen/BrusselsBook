package be.brusselsbook.sql.data;

public class Hotel extends Establishment {
	
	private Integer noStars;
	private Integer noRooms;
	private Float priceForTwo;	
	
	public Hotel(Establishment establishment) {
		super(establishment);
	}

	public Integer getNoStars() {
		return noStars;
	}
	public void setNoStars(Integer noStars) {
		this.noStars = noStars;
	}
	public Integer getNoRooms() {
		return noRooms;
	}
	public void setNoRooms(Integer noRooms) {
		this.noRooms = noRooms;
	}
	public Float getPriceForTwo() {
		return priceForTwo;
	}
	public void setPriceForTwo(Float priceForTwo) {
		this.priceForTwo = priceForTwo;
	}
	
	
	

}
