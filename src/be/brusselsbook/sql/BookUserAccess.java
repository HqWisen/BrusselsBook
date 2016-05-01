package be.brusselsbook.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.brusselsbook.data.BookUser;
import be.brusselsbook.sql.exception.DatabaseAccessException;
import be.brusselsbook.utils.AccessUtils;

public abstract class BookUserAccess<T extends BookUser> {

	protected final AccessFactory accessFactory;

	protected BookUserAccess(AccessFactory accessFactory) {
		this.accessFactory = accessFactory;
	}

	protected abstract String SELECTBY(String by);
	
	protected abstract String SELECTALL();
	
	protected abstract String INSERT();	
	
	public abstract T create(String email, String username, String password);
	
	public abstract T withEmail(String email);

	public abstract T withUsername(String username);

	public abstract T withUid(Long uid);

	public abstract T withUid(String uid);

	protected abstract T map(ResultSet resultSet) throws SQLException;

	public List<T> getObjects() throws DatabaseAccessException{
		List<T> objects = new ArrayList<T>();
		ResultSet resultSet = AccessUtils.executeQuery(accessFactory, SELECTALL());
		try {
			while(resultSet.next()){
				objects.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DatabaseAccessException(e);
		}
		return objects;
	}
	
	protected T with(String sqlQuery, Object... objects) throws DatabaseAccessException {
		T user = null;
		ResultSet resultSet = AccessUtils.executeQuery(accessFactory, sqlQuery, objects);
		try {
			if (resultSet.next()) {
				user = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DatabaseAccessException(e);
		}
		AccessUtils.close(resultSet);
		return user;
	}

}
