package be.brusselsbook.sql.data;

import java.sql.Timestamp;

public class EstablishmentModification {
	
	private Long oldEID;
	private Long newEID;
	private Long aid;
	private Timestamp modificationDate;
	
	public Long getOldEID() {
		return oldEID;
	}
	public void setOldEID(Long oldEID) {
		this.oldEID = oldEID;
	}
	public Long getNewEID() {
		return newEID;
	}
	public void setNewEID(Long newEID) {
		this.newEID = newEID;
	}
	public Long getAid() {
		return aid;
	}
	public void setAid(Long aid) {
		this.aid = aid;
	}
	public Timestamp getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Timestamp modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	


}
