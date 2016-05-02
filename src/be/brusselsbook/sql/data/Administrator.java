package be.brusselsbook.sql.data;

public class Administrator extends BookUser {

	private Long aid;

	public Administrator(BookUser bookUser) {
		super(bookUser);
	}

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	@Override
	public String toString() {
		return "(" + aid + ")" + super.toString();
	}
}
