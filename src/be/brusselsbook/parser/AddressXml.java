package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlElement;

public class AddressXml {
	
	@XmlElement(name = "Street")
	private String street;
	@XmlElement(name = "Num")
	private String num;
	@XmlElement(name = "Zip")
	private String zip;
	@XmlElement(name = "City")
	private String city;
	@XmlElement(name = "Longitude")
	private Float longitude;
	@XmlElement(name = "Latitude")
	private Float latitude;

	public void setStreet(String street) {
		this.street = street;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getStreet() {
		return street;
	}

	public String getNum() {
		return num;
	}

	public String getZip() {
		return zip;
	}

	public String getCity() {
		return city;
	}

	public Float getLongitude() {
		return longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	@Override
	public String toString() {
		String string = "";
		string += street;
		string += " n°" + num;
		return string;
	}
	
}
