package be.brusselsbook.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.data.BookUser;
import be.brusselsbook.sql.exception.DatabaseAccessException;

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

	@Override
	public void create(BookUser bookUser) {
	}

	@Override
	public BookUser userWithEmail(String email) throws DatabaseAccessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BookUser bookUser = null;
		try {
			connection = accessFactory.getConnection();
			preparedStatement = AccessUtils.createPreparedStatement(connection, BookUserAccess.SQL_SELECT_BY_EMAIL,
					email);
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

}
