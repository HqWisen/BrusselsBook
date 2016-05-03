package be.brusselsbook.sql.data;

import java.sql.Timestamp;
       
public class Describer {
	
	private Long did;
	private Timestamp creationDate;
	
	public Long getDid() {
		return did;
	}
	
	public void setDid(Long did) {
		this.did = did;
	}
	
	public Timestamp getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
