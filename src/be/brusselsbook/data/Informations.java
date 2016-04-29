package be.brusselsbook.data;

import javax.xml.bind.annotation.XmlElement;

public class Informations {

	@XmlElement(name = "Name")
	private String name;
	@XmlElement(name = "Address")
	private Address address;
	@XmlElement(name = "Tel")
	private String tel;
	@XmlElement(name = "Site")
	private Site site;

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
	
	@Override
	public String toString() {
		String string = "";
		string += name + ": ";
		string += address + "; ";
		string += tel;
		string += " | " + site;
		return string;
	}
}
