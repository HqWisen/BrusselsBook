package be.brusselsbook.sql.data;

import java.sql.Timestamp;

public class Tag extends Describer {
	
	private Long did;
	private String tagName;
	private Long uid;
	private Timestamp CreationDate;
	
	
	public Tag(Describer describer) {
		super(describer);
	}

	
	
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Timestamp getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		CreationDate = creationDate;
	}
	
	



}
