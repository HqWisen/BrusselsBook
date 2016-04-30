package be.brusselsbook.sql;

import be.brusselsbook.data.BookUser;

public class BookUserAccessImpl implements BookUserAccess {

	private AccessFactory accessFactory;

	public BookUserAccessImpl(AccessFactory accessFactory) {
		this.accessFactory = accessFactory;
	}

	@Override
	public void create(BookUser bookUser) {
	}

	@Override
	public BookUser userWithEmail(String email) {
		return null;
	}
}
