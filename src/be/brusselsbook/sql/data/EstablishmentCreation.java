package be.brusselsbook.sql.data;

import java.sql.Timestamp;

public class EstablishmentCreation {

	private Long eid;
	private Long aid;
	private Timestamp creationDate;

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

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

}
