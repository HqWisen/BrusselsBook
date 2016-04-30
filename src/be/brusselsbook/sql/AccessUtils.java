package be.brusselsbook.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.brusselsbook.sql.exception.DatabaseAccessException;

public final class AccessUtils {

	public static PreparedStatement createPreparedStatement(Connection connection, String sqlQuery, Object... objects)
			throws SQLException {
		return createPreparedStatement(false, connection, sqlQuery, objects);
	}

	public static PreparedStatement createPreparedStatement(boolean returnGeneratedKeys, Connection connection,
			String sqlQuery, Object... objects) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery,
				returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		for (int i = 0; i < objects.length; i++) {
			preparedStatement.setObject(i + 1, objects[i]);
		}
		return preparedStatement;
	}

	public static void close(AutoCloseable closeable) {
		try {
			closeable.close();
		} catch (Exception e) {
			throw new DatabaseAccessException("Unable to close a resource: " + e.getMessage());
		}
	}

	public static void close(Statement statement, Connection connection) {
		close(statement);
		close(connection);
	}

	public static void close(Statement statement, Connection connection, ResultSet resultSet) {
		close(statement, connection);
		close(resultSet);
	}
}
