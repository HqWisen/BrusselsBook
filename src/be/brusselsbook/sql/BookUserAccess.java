package be.brusselsbook.sql;

import be.brusselsbook.data.BookUser;

public interface BookUserAccess {

	public static final String EMAILADDRESS = "EmailAddress";
	public static final String USERNAME = "Username";
	public static final String UID = "UID";

	public static String SELECTBY(String by){
		return "SELECT UID, EmailAddress, Username, Pwd, RegistrationDate FROM BookUser WHERE " + by + " = ?";
	}
	
	void create(BookUser bookUser);

	BookUser userWithEmail(String email);

	BookUser userWithUsername(String username);

	BookUser userWithUid(Long uid);

	BookUser userWithUid(String uid);

}
