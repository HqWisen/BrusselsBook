package be.brusselsbook.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.data.Administrator;
import be.brusselsbook.data.BookUser;

public class AdministratorAccessImpl extends AdministratorAccess {

	protected static final String UID = "UID";
	protected static final String AID = "AID";
	
	@Override
	protected String SELECTBY(String by) {
		return "SELECT * FROM Administrator WHERE " + by + " = ?";
	}

	@Override
	protected String SELECTALL(){
		return "SELECT * FROM Administrator";
	}
	
	@Override
	protected String INSERT() {
		return "INSERT INTO Administrator (UID) VALUES (?)";
	}

	private BookUserAccess<BookUser> bookUserAccess;

	public AdministratorAccessImpl(AccessFactory accessFactory) {
		super(accessFactory);
		this.bookUserAccess = accessFactory.getBookUserAccess();
	}
	
	@Override
	public Administrator create(Object... objects) {
		BookUser bookUser = bookUserAccess.create(objects);
		Long uid = bookUser.getUid();
		return super.create(uid);
	}

	@Override
	public Administrator withId(Long id) {
		return withAid(id);
	}
	
	@Override
	public Administrator withEmail(String email) {
		BookUser bookUser = bookUserAccess.withEmail(email);
		return bookUser != null ? withUid(bookUser.getUid()) : null;
	}

	@Override
	public Administrator withUsername(String username) {
		BookUser bookUser = bookUserAccess.withUsername(username);
		return bookUser != null ? withUid(bookUser.getUid()) : null;
	}

	@Override
	public Administrator withUid(Long uid) {
		return withUid(uid.toString());
	}

	@Override
	public Administrator withUid(String uid) {
		return with(SELECTBY(UID), uid);
	}

	@Override
	public Administrator withAid(Long aid) {
		return withAid(aid.toString());
	}

	@Override
	public Administrator withAid(String aid) {
		return with(SELECTBY(AID), aid);
	}
	
	@Override
	protected Administrator map(ResultSet resultSet) throws SQLException {
		Long uid = resultSet.getLong("UID");
		BookUser bookUser = bookUserAccess.withUid(uid);
		Administrator administrator = new Administrator(bookUser);
		administrator.setAid(resultSet.getLong("AID"));
		return administrator;
	}

}
