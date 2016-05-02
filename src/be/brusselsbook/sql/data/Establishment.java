package be.brusselsbook.sql.data;

public class Establishment {

	private Long eid;
	private String name;
	private String phoneNumber;
	private Boolean modified;
	private String webSite;

	public Establishment(){
		super();
	}
	
	public Establishment(Establishment other){
		this.eid = other.eid;
		this.name = other.name;
		this.phoneNumber = other.phoneNumber;
		this.modified = other.modified;
		this.webSite = other.webSite;
	}
	
	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getModified() {
		return modified;
	}

	public void setModified(Boolean modified) {
		this.modified = modified;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public boolean hasWebsite(){
		return webSite != null;
	}
	
	@Override
	public String toString() {
		String string = "";
		string += name + " | " + phoneNumber + " > " + webSite;
		return string;
	}
	
}
