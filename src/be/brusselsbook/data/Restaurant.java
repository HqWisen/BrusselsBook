package be.brusselsbook.data;

public class Restaurant extends Establishment {

	private Integer priceRange;
	private Integer banquetPlaces;
	private Boolean takeaway;
	private Boolean delivery;
	private String halfDaysOff;

	public Restaurant(Establishment establishment) {
		super(establishment);
	}

	public Integer getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(Integer priceRange) {
		this.priceRange = priceRange;
	}

	public Integer getBanquetPlaces() {
		return banquetPlaces;
	}

	public void setBanquetPlaces(Integer banquetPlaces) {
		this.banquetPlaces = banquetPlaces;
	}

	public Boolean getTakeaway() {
		return takeaway;
	}

	public void setTakeaway(Boolean takeaway) {
		this.takeaway = takeaway;
	}

	public Boolean getDelivery() {
		return delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	public String getHalfDaysOff() {
		return halfDaysOff;
	}

	public void setHalfDaysOff(String halfDaysOff) {
		this.halfDaysOff = halfDaysOff;
	}

}
