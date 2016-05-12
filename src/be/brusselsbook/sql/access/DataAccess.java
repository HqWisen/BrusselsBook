package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.brusselsbook.sql.Indexable;
import be.brusselsbook.sql.exception.DatabaseAccessException;
import be.brusselsbook.utils.AccessUtils;

public abstract class DataAccess<T> implements Indexable<T>{

	private static String wildCards(int n){
		String result = "";
		if (n > 0){
			for(int i = 0; i < n - 1; i++){
				result += "?, ";
			}
			result += "?";
		}
		return result;
	}
	
	private static String parameters(String[] array){
		String result = "";
		int size = array.length;
		if (size > 0){
			for(int i = 0; i < array.length - 1; i++){
			result += array[i]+", ";
			}
			result += array[array.length-1];
		}
		return result;		
	}
	
	protected final AccessFactory accessFactory;

	protected DataAccess(AccessFactory accessFactory) {
		this.accessFactory = accessFactory;
	}
	
	protected abstract T map(ResultSet resultSet) throws SQLException;

	protected abstract String getTable();
	
	/**
	 * 
	 * @return An array of the parameters that should be added in the table
	 */
	protected abstract String[] getCreationParameters();
	
	protected abstract int getNumberOfCreationParameters();
	
	public String SELECTBY(String by) {
		return "SELECT * FROM " + getTable() + " WHERE " + by + " = ?";
	}

	public String UPDATEBY(String by, String idCol) {
		return "UPDATE " + getTable() + " SET " + by +"= ? WHERE " + idCol + "= ?";
	}
	
	public String DELETEFROM(String by){
		return "DELETE"  + "FROM" + getTable() + "WHERE" + by + " = ?" + "LIMIT 1";
		
	}
	
	
	public String SELECTBYSEVERALAND(String... cols) {
		String string = "SELECT * FROM " + getTable() + " WHERE ";
		for(int i = 0; i < cols.length - 1; i++){
			 string +=  cols[i] + " = ? AND ";		
		}
		string += cols[cols.length - 1] + " = ?";
		return string;
	}

	public String SELECTALL(){
		return "SELECT * FROM " + getTable();
	}
	
	public String INSERT() {
		String wildCards = wildCards(getNumberOfCreationParameters());
		String parameters = parameters(getCreationParameters());
		return "INSERT INTO " + getTable() + " (" + parameters + ") VALUES (" + wildCards + ")";
	}
	
	public T safeMap(ResultSet resultSet) throws DatabaseAccessException{
		try {
			return map(resultSet);
		} catch (SQLException e) {
			throw new DatabaseAccessException(e);
		}
	}
	
	public List<T> getObjects() throws DatabaseAccessException{
		List<T> objects = new ArrayList<T>();
		ResultSet resultSet = AccessUtils.executeQuery(accessFactory, SELECTALL());
		while(AccessUtils.next(resultSet)){
			objects.add(safeMap(resultSet));	
		}
		return objects;
	}
	
	protected T withQuery(String sqlQuery, Object... values) throws DatabaseAccessException {
		T data = null;
		ResultSet resultSet = AccessUtils.executeQuery(accessFactory, sqlQuery, values);
		if (AccessUtils.next(resultSet)) {
			data = safeMap(resultSet);
		}
		AccessUtils.close(resultSet);
		return data;
	}

	protected List<T> severalWithQuery(String sqlQuery, Object... values) throws DatabaseAccessException {
		List<T> list = new ArrayList<>();
		ResultSet resultSet = AccessUtils.executeQuery(accessFactory, sqlQuery, values);
		while(AccessUtils.next(resultSet)) {
			list.add(safeMap(resultSet));
		}
		AccessUtils.close(resultSet);
		return list;
	}

	protected void update(String sqlQuery, Object... values) throws DatabaseAccessException {
		AccessUtils.executeUpdate(accessFactory, sqlQuery, values);
	}
	
	@Override
	public T create(Object... values){
		T data = null;
		Long id = null;
		ResultSet autoGeneratedValues = AccessUtils.executeInsert(accessFactory, INSERT(), values);
		if (AccessUtils.next(autoGeneratedValues)) {
			id = AccessUtils.getLongFirstColumn(autoGeneratedValues);
		} else{
			throw new DatabaseAccessException("Failed to generate the Data ID.");
		}
		data = withId(id);
		AccessUtils.close(autoGeneratedValues);
		return data;
	}
	
	@Override
	public T createNoGeneratedId(Long id, Object... values) {
		AccessUtils.executeInsert(accessFactory, INSERT(), values);
		return withId(id);
	}

}
