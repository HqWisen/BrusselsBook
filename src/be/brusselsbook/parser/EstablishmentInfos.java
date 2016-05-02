package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlElement;


public class EstablishmentInfos {

	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "Address")
	private AddressXml address;
	
	@XmlElement(name = "Tel")
	private String tel;
	
	@XmlElement(name = "Site")
	private Site site;
	
	
	public String getName() {
		return name;
	}

	public AddressXml getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}

	public Site getSite() {
		return site;
	}
	
	public String getSiteLink(){
		if(site != null){
			return site.getLink();	
		}
		return null;
	}	
}
