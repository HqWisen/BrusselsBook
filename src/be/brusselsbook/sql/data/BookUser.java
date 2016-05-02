package be.brusselsbook.sql.data;

import java.sql.Timestamp;

public class BookUser {

	private Long uid;
	private String emailAddress;
	private String username;
	private String password;
	private Timestamp registrationDate;

	public BookUser() {
		super();
	}

	public BookUser(BookUser other) {
		this.uid = other.uid;
		this.emailAddress = other.emailAddress;
		this.username = other.username;
		this.password = other.password;
		this.registrationDate = (Timestamp) other.registrationDate.clone();
	}

	public Long getUid() {
		return uid;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		String string = "";
		string += "(" + uid + ")" + username + ":" + password + " > ";
		string += emailAddress + " on ";
		string += registrationDate;
		return string;
	}

}
