package be.brusselsbook.sql.data;

import java.sql.Timestamp;

public class Describer {
	
	private Long did;
	private Boolean modified;
	
	
	public Describer(Describer other) {
		this.did = other.did;
		this.modified = other.modified;
	}

	public Describer(){
		super();
	}
	public Long getDid() {
		return did;
	}
	
	public void setDid(Long did) {
		this.did = did;
	}

	public Boolean getModified() {
		return modified;
	}

	public void setModified(Boolean modified) {
		this.modified = modified;
	}
	
	
}
