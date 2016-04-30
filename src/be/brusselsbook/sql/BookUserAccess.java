package be.brusselsbook.sql;

import be.brusselsbook.data.BookUser;

public interface BookUserAccess {

	void create(BookUser bookUser);

	BookUser userWithEmail(String email);

}
