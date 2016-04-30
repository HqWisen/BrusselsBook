package be.brusselsbook.sql;

import be.brusselsbook.data.BookUser;

public interface BookUserAccess {

	public static final String SQL_SELECT_BY_EMAIL = "SELECT UID, EmailAddress, Username, Pwd, RegistrationDate FROM BookUser WHERE EmailAddress = ?";

	void create(BookUser bookUser);

	BookUser userWithEmail(String email);

}
