package be.brusselsbook.sql.data;

import java.sql.Timestamp;

public class EstablishmentDeletion {
	
	private Long eid;
	private Long aid;
	private Timestamp deletionDate;
	
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public Long getAid() {
		return aid;
	}
	public void setAid(Long aid) {
		this.aid = aid;
	}
	public Timestamp getDeletionDate() {
		return deletionDate;
	}
	public void setDeletionDate(Timestamp deletionDate) {
		this.deletionDate = deletionDate;
	}
	
	
	
}
