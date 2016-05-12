package be.brusselsbook.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.BookCommentAccess;
import be.brusselsbook.sql.access.BookUserAccess;
import be.brusselsbook.sql.access.TagDescribeAccess;
import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.BookComment;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.sql.data.Tag;
import be.brusselsbook.sql.exception.DatabaseAccessException;

public final class AccessUtils {

	private static final Logger LOGGER = Logger.getLogger(AccessUtils.class.getName());
	private static final AccessFactory aFactory;
	static {
		LOGGER.setLevel(Level.INFO);
		aFactory = AccessFactory.getInstance();
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

	public static ResultSet executeLikeQuery(AccessFactory accessFactory, String sqlQuery, Object... values) {
		LOGGER.info("executing like query " + sqlQuery + " with " + Arrays.asList(values));
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = accessFactory.getConnection();
			preparedStatement = AccessUtils.createLikePreparedStatement(connection, sqlQuery, values);
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

	public static PreparedStatement createLikePreparedStatement(Connection connection, String sqlQuery, Object... values)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		for (int i = 0; i < values.length; i++) {
			String value = likeSanitize((String)values[i]);
			preparedStatement.setString(i + 1, "%" + value + "%");
		}
		return preparedStatement;
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

	public static String likeSanitize(String input) {
		return input.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");
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

	public static Map<Long, Address> getAddressFor(List<Establishment> establishments) {
		Map<Long, Address> map = new HashMap<>();
		for (Establishment establishment : establishments) {
			Long eid = establishment.getEid();
			map.put(eid, getAddressFor(eid));
		}
		return map;
	}

	public static Map<Long, Integer> getNumberOfCommentsFor(List<Establishment> establishments) {
		Map<Long, Integer> map = new HashMap<>();
		for (Establishment establishment : establishments) {
			Long eid = establishment.getEid();
			map.put(eid, getNumberOfCommentsFor(eid));
		}
		return map;
	}

	public static Map<Long, Integer> getNumberOfCommentsForUsers(List<BookUser> users) {
		Map<Long, Integer> map = new HashMap<>();
		for (BookUser user : users) {
			Long uid =user.getUid();
			map.put(uid, getNumberOfCommentsForUser(uid));
		}
		return map;
	}

	private static Integer getNumberOfCommentsFor(Long eid) {
		BookCommentAccess bookCommentAccess = aFactory.getBookCommentAccess();
		List<BookComment> comments = bookCommentAccess.withEid(eid);
		return comments.size();
	}

	private static Integer getNumberOfCommentsForUser(Long uid) {
		BookCommentAccess bookCommentAccess = aFactory.getBookCommentAccess();
		List<BookComment> comments = bookCommentAccess.withUid(uid);
		return comments.size();
	}

	public static Address getAddressFor(Long eid) {
		return aFactory.getAddressAccess().withEid(eid);
	}

	public static Address getAddresFor(Establishment establishment) {
		return getAddressFor(establishment.getEid());
	}

	public static Map<Long, Integer> getAverageScoresFor(List<Establishment> establishments) {
		Map<Long, Integer> map = new HashMap<>();
		for (Establishment establishment : establishments) {
			Long eid = establishment.getEid();
			map.put(eid, getAverageScoreFor(eid));
		}
		return map;
	}

	private static Integer getAverageScoreFor(Long eid) {
		BookCommentAccess bookCommentAccess = AccessFactory.getInstance().getBookCommentAccess();
		List<BookComment> comments = bookCommentAccess.withEid(eid);
		int total = 0;
		for (BookComment comment : comments) {
			total += comment.getScore();
		}
		return comments.isEmpty() ? 0 : (int) Math.ceil(total / comments.size());
	}

	public static Map<Long, String> getAuthorsFor(List<BookComment> comments) {
		Map<Long, String> map = new HashMap<>();
		for (BookComment comment : comments) {
			Long did = comment.getDid();
			map.put(did, getCommentAuthorFor(comment));
		}
		return map;
	}

	private static String getCommentAuthorFor(BookComment comment) {
		BookUserAccess<BookUser> bAccess = aFactory.getBookUserAccess();
		return bAccess.withUid(comment.getUid()).getUsername();
	}

	public static void setAttribute(HttpSession session, String key, String value) {
		session.setAttribute(key, value);
	}

	public static Map<String, Integer> getCountersFor(List<Tag> tags, Long eid) {
		TagDescribeAccess tAccess = aFactory.getTagDescribeAccess();
		Map<String, Integer> map = new HashMap<>();
		for(Tag tag : tags){
			String name = tag.getTagName();
			Integer counter = tAccess.withEidAndTagName(eid, name).size(); 
			map.put(name, counter);
		}
		return map;
	}

	public static Map<String, Boolean> getApposedFor(List<Tag> tags, Long eid, Long uid) {
		TagDescribeAccess tAccess = aFactory.getTagDescribeAccess();
		Map<String, Boolean> map = new HashMap<>();
		for(Tag tag : tags){
			String tagName = tag.getTagName();
			map.put(tagName, tAccess.withEidAndUidAndTagName(eid, uid, tagName) != null);
		}
		return map;
	}
}
