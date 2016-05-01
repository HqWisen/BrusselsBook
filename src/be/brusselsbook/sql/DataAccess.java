package be.brusselsbook.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.brusselsbook.sql.exception.DatabaseAccessException;
import be.brusselsbook.utils.AccessUtils;

public abstract class DataAccess<T> {

	protected final AccessFactory accessFactory;

	protected DataAccess(AccessFactory accessFactory) {
		this.accessFactory = accessFactory;
	}
	
	protected abstract String SELECTBY(String by);

	protected abstract String SELECTALL();

	protected abstract String INSERT();

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
		T data = null;
		ResultSet resultSet = AccessUtils.executeQuery(accessFactory, sqlQuery, objects);
		try {
			if (resultSet.next()) {
				data = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DatabaseAccessException(e);
		}
		AccessUtils.close(resultSet);
		return data;
	}

}
