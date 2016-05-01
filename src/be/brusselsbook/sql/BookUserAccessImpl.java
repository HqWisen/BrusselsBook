package be.brusselsbook.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.data.BookUser;
import be.brusselsbook.sql.exception.DatabaseAccessException;

public class BookUserAccessImpl extends BookUserAccess<BookUser> {

	protected static final String EMAILADDRESS = "EmailAddress";
	protected static final String USERNAME = "Username";
	protected static final String UID = "UID";
	
	@Override
	protected String SELECTBY(String by) {
		return "SELECT * FROM BookUser WHERE " + by + " = ?";
	}

	@Override
	protected String SELECTALL() {
		return  "SELECT * FROM BookUser";
	}

	@Override
	protected String INSERT() {
		return "INSERT INTO BookUser (EmailAddress, Username, Pwd) VALUES (?, ?, ?)";
	}
	
	public BookUserAccessImpl(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public BookUser withId(Long id) {
		return withUid(id);
	}
	
	@Override
	public BookUser withEmail(String email) throws DatabaseAccessException {
		return with(SELECTBY(EMAILADDRESS), email);
	}

	@Override
	public BookUser withUsername(String username) {
		return with(SELECTBY(USERNAME), username);
	}

	@Override
	public BookUser withUid(Long uid) {
		return withUid(uid.toString());
	}

	@Override
	public BookUser withUid(String uid) {
		return with(SELECTBY(UID), uid);
	}

	@Override
	protected BookUser map(ResultSet resultSet) throws SQLException {
		BookUser bookUser = new BookUser();
		bookUser.setUid(resultSet.getLong("UID"));
		bookUser.setEmailAddress(resultSet.getString("EmailAddress"));
		bookUser.setUsername(resultSet.getString("Username"));
		bookUser.setPassword(resultSet.getString("Pwd"));
		bookUser.setRegistrationDate(resultSet.getTimestamp("RegistrationDate"));
		return bookUser;
	}

}
