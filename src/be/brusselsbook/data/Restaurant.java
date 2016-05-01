package be.brusselsbook.data;

public class Restaurant extends Establishment {

	private Integer priceMinimum;
	private Integer priceMaximum;
	private Boolean takeaway;
	private Boolean delivery;
	private String halfDaysOff;

	public Restaurant(Establishment establishment) {
		super(establishment);
	}

	public Integer getPriceMinimum() {
		return priceMinimum;
	}

	public void setPriceMinimum(Integer priceMinimum) {
		this.priceMinimum = priceMinimum;
	}

	public Integer getPriceMaximum() {
		return priceMaximum;
	}

	public void setPriceMaximum(Integer priceMaximum) {
		this.priceMaximum = priceMaximum;
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
