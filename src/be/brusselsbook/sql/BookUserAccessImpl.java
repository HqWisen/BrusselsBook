package be.brusselsbook.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.data.BookUser;
import be.brusselsbook.sql.exception.DatabaseAccessException;
import static be.brusselsbook.sql.BookUserAccess.*;

public class BookUserAccessImpl implements BookUserAccess {

	private static BookUser map(ResultSet resultSet) throws SQLException {
		BookUser bookUser = new BookUser();
		bookUser.setUid(resultSet.getLong("UID"));
		bookUser.setEmailAddress(resultSet.getString("EmailAddress"));
		bookUser.setUsername(resultSet.getString("Username"));
		bookUser.setPassword(resultSet.getString("Pwd"));
		bookUser.setRegistrationDate(resultSet.getTimestamp("RegistrationDate"));
		return bookUser;
	}
	
	private AccessFactory accessFactory;

	public BookUserAccessImpl(AccessFactory accessFactory) {
		this.accessFactory = accessFactory;
	}
	
	private BookUser userWith(String sqlQuery, Object object) throws DatabaseAccessException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BookUser bookUser = null;
		try {
			connection = accessFactory.getConnection();
			preparedStatement = AccessUtils.createPreparedStatement(connection, sqlQuery,
					object);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bookUser = map(resultSet);
			}
			AccessUtils.close(preparedStatement, connection, resultSet);
		} catch (SQLException e) {
			throw new DatabaseAccessException(e);
		}
		return bookUser;
	}

	@Override
	public void create(BookUser bookUser) {
	}

	@Override
	public BookUser userWithEmail(String email) throws DatabaseAccessException {
		return userWith(SELECTBY(EMAILADDRESS), email);
	}

	@Override
	public BookUser userWithUsername(String username) {
		return userWith(SELECTBY(USERNAME), username);
	}

	@Override
	public BookUser userWithUid(Long uid) {
		return userWithUid(uid.toString());
	}

	@Override
	public BookUser userWithUid(String uid) {
		return userWith(SELECTBY(UID), uid);
	}

}
