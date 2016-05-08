package be.brusselsbook.sql.data;

import java.sql.Timestamp;

public class DescriberDeletion {

	private Long did;
	private Long uid;
	private Timestamp deletionDate;
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Timestamp getDeletionDate() {
		return deletionDate;
	}
	public void setDeletionDate(Timestamp deletionDate) {
		this.deletionDate = deletionDate;
	}

	


}
