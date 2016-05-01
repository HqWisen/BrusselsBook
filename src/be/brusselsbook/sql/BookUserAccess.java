package be.brusselsbook.sql;

import be.brusselsbook.data.BookUser;

public interface BookUserAccess {

	public static final String EMAILADDRESS = "EmailAddress";
	public static final String USERNAME = "Username";
	public static final String UID = "UID";

	public static String SELECTBY(String by) {
		return "SELECT * FROM BookUser WHERE " + by + " = ?";
	}

	public static String INSERT() {
		return "INSERT INTO BookUser (EmailAddress, Username, Pwd) VALUES (?, ?, ?)";
	}

	BookUser create(String email, String username, String password);

	BookUser userWithEmail(String email);

	BookUser userWithUsername(String username);

	BookUser userWithUid(Long uid);

	BookUser userWithUid(String uid);

}
