package be.brusselsbook.sql.data;

import java.sql.Timestamp;

public class DescriberModification {
	
	private Long oldDID;
	private Long newDID;
	private Long uid;
	private Timestamp modificationDate;
	public Long getOldDID() {
		return oldDID;
	}
	public void setOldDID(Long oldDID) {
		this.oldDID = oldDID;
	}
	public Long getNewDID() {
		return newDID;
	}
	public void setNewDID(Long newDID) {
		this.newDID = newDID;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Timestamp getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Timestamp modificationDate) {
		this.modificationDate = modificationDate;
	}
	
}
