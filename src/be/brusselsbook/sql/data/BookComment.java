package be.brusselsbook.sql.data;

import java.sql.Timestamp;

public class BookComment extends Describer {
	
	private Long uid;
	private Long eid;
	private Timestamp CreationDate;
	private Integer score;
	private String text;
	
	
	public BookComment(Describer describer) {
		super(describer);
	}

	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public Timestamp getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		CreationDate = creationDate;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
