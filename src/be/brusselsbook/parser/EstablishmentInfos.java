package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlElement;


public class EstablishmentInfos {

	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "Address")
	private AddressXml address;
	
	@XmlElement(name = "Tel")
	private String tel;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(AddressXml address) {
		this.address = address;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public void setSite(String link) {
		this.site = new Site();
		this.site.setLink(link);
	}

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
