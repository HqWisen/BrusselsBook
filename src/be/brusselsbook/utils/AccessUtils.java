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
	
	static{
		LOGGER.setLevel(Level.OFF);
	}
	
	public static ResultSet executeQuery(AccessFactory accessFactory, String sqlQuery, Object... objects)
			throws DatabaseAccessException {
		LOGGER.info("executing query " + sqlQuery + " with " + Arrays.asList(objects));
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = accessFactory.getConnection();
			preparedStatement = AccessUtils.createPreparedStatement(connection, sqlQuery, objects);
			resultSet = preparedStatement.executeQuery();
			AccessUtils.close(connection);
		} catch (SQLException e) {
			throw new DatabaseAccessException(e);
		}
		return resultSet;
	}

	public static ResultSet executeInsert(AccessFactory accessFactory, String sqlQuery, Object... objects)
			throws DatabaseAccessException {
		LOGGER.info("executing insert " + sqlQuery + " with " + Arrays.asList(objects));
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = accessFactory.getConnection();
			preparedStatement = AccessUtils.createPreparedStatement(true, connection, sqlQuery, objects);
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