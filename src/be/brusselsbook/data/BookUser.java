package be.brusselsbook.data;

import java.sql.Timestamp;

public class BookUser {

	private Long uid;
	private String emailAddress;
	private String username;
	private String password;
	private Timestamp registrationDate;

	public BookUser() {
	}

	public BookUser(Long uid, String emailAddress, String username, String password, Timestamp registrationDate) {
		this.uid = uid;
		this.emailAddress = emailAddress;
		this.username = username;
		this.password = password;
		this.registrationDate = registrationDate;
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
		string += username + " | ";
		string += emailAddress + " on ";
		string += registrationDate + "\n";
		return string;
	}

}
