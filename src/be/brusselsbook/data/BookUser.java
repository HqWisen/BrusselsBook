package be.brusselsbook.data;
import java.sql.Timestamp;

public class BookUser {

	private Long uid;
	private String emailAddress;
	private String username;
	private String password;
	private Timestamp registrationDate;

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

}
