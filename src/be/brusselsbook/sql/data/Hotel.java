package be.brusselsbook.sql.data;

public class Hotel extends Establishment {
	
	private Integer NoStars;
	private Integer NoRooms;
	private Float PriceForTwo;	
	
	public Hotel(Establishment establishment) {
		super(establishment);
	}

	public Integer getNoStars() {
		return NoStars;
	}
	public void setNoStars(Integer noStars) {
		NoStars = noStars;
	}
	public Integer getNoRooms() {
		return NoRooms;
	}
	public void setNoRooms(Integer noRooms) {
		NoRooms = noRooms;
	}
	public Float getPriceForTwo() {
		return PriceForTwo;
	}
	public void setPriceForTwo(Float priceForTwo) {
		PriceForTwo = priceForTwo;
	}
	
	
	

}
