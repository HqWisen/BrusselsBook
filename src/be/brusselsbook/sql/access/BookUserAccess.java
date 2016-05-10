package be.brusselsbook.sql.access;

import be.brusselsbook.sql.data.BookUser;

public abstract class BookUserAccess<T extends BookUser> extends DataAccess<T>{

	protected BookUserAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}
	
	public abstract T withEmail(String email);

	public abstract T withUsername(String username);

	public abstract T withUid(Long uid);

	public abstract T withUid(String uid);

	public BookUser createUser(String email, String username, String password){
		return create(email, username, password);
	}
	
	public boolean isEmailUsed(String email) {
		return withEmail(email) != null;
	}

	public boolean isUsernamUsed(String username) {
		return withUsername(username) != null;
	}

	public boolean match(String identifier, String password) {
		T user = withIdentifier(identifier);
		if(user == null){
			return false;
		}
		return password.equals(user.getPassword());
	}

	public T withIdentifier(String identifier) {
		T user = withEmail(identifier);
		user = user != null ? user : withUsername(identifier);
		return user;
	}

}
