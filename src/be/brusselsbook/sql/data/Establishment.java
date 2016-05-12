package be.brusselsbook.sql.data;

public class Establishment {

	private static final int MAXURLSIZE = 40;
	private Long eid;
	private String name;
	private String phoneNumber;
	private Boolean modified;
	private String webSite;
	private String formattedUrl;
	private EstablishmentType type;
	private Integer typeId;
	
	
	public EstablishmentType getType() {
		return type;
	}

	public void setType(EstablishmentType type) {
		this.type = type;
		this.typeId = type.getId();
	}

	public int getTypeId(){
		return typeId;
	}
	
	public Establishment(){
		super();
	}
	
	public Establishment(Establishment other){
		this.eid = other.eid;
		this.name = other.name;
		this.phoneNumber = other.phoneNumber;
		this.modified = other.modified;
		this.type = other.type;
		this.typeId = other.typeId;
		setWebSite(other.webSite);
	}
	
	private String buildFormattedUrl() {
		if(webSite != null && webSite.length() > MAXURLSIZE){
			return webSite.substring(0, MAXURLSIZE) + "...";
		}
		return webSite;
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

	public String getUrl(){
		return webSite;
	}
	
	public String getFormattedUrl(){
		return formattedUrl;
	}
	
	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = addHttpPrefix(webSite);
		this.formattedUrl = buildFormattedUrl();
	}

	private String addHttpPrefix(String webSite) {
		if(webSite != null && !webSite.startsWith("http://")){
			return "http://"+webSite;
		}
		return webSite;
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
