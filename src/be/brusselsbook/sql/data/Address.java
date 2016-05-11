package be.brusselsbook.sql.data;

public class Address {

	private Long eid;
	private String street;
	private String number;
	private String locality;
	private String postalCode;
	private Float latitude;
	private Float longitude;

	public Address(String street, String number, String locality, String zip, Float lat, Float lng) {
		this.street = street;
		this.number = number;
		this.locality = locality;
		this.postalCode = zip;
		this.latitude = lat;
		this.longitude = latitude;
	}

	public Address() {
		super();
	}

	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		String string = "";
		string += street + " n°" + number + ", ";
		string += locality + " " + postalCode;
		return string;
	}

}
