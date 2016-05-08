package be.brusselsbook.sql.data;

import java.sql.Timestamp;

public class UserDeletion {
	
	private Long uid;
	private Long aid;
	private Timestamp deletionDate;
	
	public Long getEid() {
		return uid;
	}
	public void setEid(Long eid) {
		this.uid = eid;
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
