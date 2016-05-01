package be.brusselsbook.data;

public class Cafe extends Establishment {

	private Boolean smoke;
	private Boolean restoration;

	public Cafe(Establishment other) {
		super(other);
	}

	public Boolean getSmoke() {
		return smoke;
	}

	public void setSmoke(Boolean smoke) {
		this.smoke = smoke;
	}

	public Boolean getRestoration() {
		return restoration;
	}

	public void setRestoration(Boolean restoration) {
		this.restoration = restoration;
	}

}
