package be.brusselsbook.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.brusselsbook.sql.AccessFactory;
import be.brusselsbook.sql.exception.DatabaseAccessException;

public final class AccessUtils {

	private static final Logger LOGGER = Logger.getLogger(AccessUtils.class.getName());

	static {
		LOGGER.setLevel(Level.INFO);
	}

	public static boolean next(ResultSet resultSet) throws DatabaseAccessException {
		try {
			return resultSet.next();
		} catch (SQLException e) {
			throw new DatabaseAccessException(e);
		}
	}

	public static Long getLongFirstColumn(ResultSet resultSet) throws DatabaseAccessException {
		try {
			return resultSet.getLong(1);
		} catch (SQLException e) {
			throw new DatabaseAccessException(e);
		}
	}

	public static ResultSet executeQuery(AccessFactory accessFactory, String sqlQuery, Object... values)
			throws DatabaseAccessException {
		LOGGER.info("executing query " + sqlQuery + " with " + Arrays.asList(values));
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = accessFactory.getConnection();
			preparedStatement = AccessUtils.createPreparedStatement(connection, sqlQuery, values);
			resultSet = preparedStatement.executeQuery();
			AccessUtils.close(connection);
		} catch (SQLException e) {
			throw new DatabaseAccessException(e);
		}
		return resultSet;
	}

	public static ResultSet executeInsert(AccessFactory accessFactory, String sqlQuery, Object... values)
			throws DatabaseAccessException {
		LOGGER.info("executing insert " + sqlQuery + " with " + Arrays.asList(values));
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = accessFactory.getConnection();
			preparedStatement = AccessUtils.createPreparedStatement(true, connection, sqlQuery, values);
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DatabaseAccessException("Failed to create entity in database. Nothing added.");
			}
			resultSet = preparedStatement.getGeneratedKeys();
			AccessUtils.close(connection);
		} catch (SQLException e) {
			throw new DatabaseAccessException(e);
		}
		return resultSet;
	}

	public static PreparedStatement createPreparedStatement(Connection connection, String sqlQuery, Object... values)
			throws SQLException {
		return createPreparedStatement(false, connection, sqlQuery, values);
	}

	public static PreparedStatement createPreparedStatement(boolean returnGeneratedKeys, Connection connection,
			String sqlQuery, Object... values) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery,
				returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(i + 1, values[i]);
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
