package be.brusselsbook.sql.access;

import java.sql.Connection;
import java.sql.SQLException;

import be.brusselsbook.data.BookUser;
import be.brusselsbook.data.Establishment;
import be.brusselsbook.sql.DatabaseAccess;
import be.brusselsbook.sql.access.impl.AddresAccessImpl;
import be.brusselsbook.sql.access.impl.AdministratorAccessImpl;
import be.brusselsbook.sql.access.impl.BookUserAccessImpl;
import be.brusselsbook.sql.access.impl.EstablishementAccessImpl;
import be.brusselsbook.sql.access.impl.RestaurantAccessImpl;
import be.brusselsbook.sql.exception.DatabaseAccessException;

public class AccessFactory {

	private static AccessFactory accessFactory;
	private DatabaseAccess databaseAccess;

	private AccessFactory(DatabaseAccess databaseAccess) {
		this.databaseAccess = databaseAccess;
	}

	public static AccessFactory getInstance() throws DatabaseAccessException {
		if (accessFactory == null) {
			try {
				accessFactory = new AccessFactory(new DatabaseAccess());
			} catch (SQLException e) {
				throw new DatabaseAccessException(
						"Cannot create the DatabaseAccess due to pool configurations issues: " + e.getMessage());
			}
		}
		return accessFactory;
	}

	public Connection getConnection() throws SQLException {
		return databaseAccess.getConnection();
	}

	public BookUserAccess<BookUser> getBookUserAccess() {
		return new BookUserAccessImpl(this);
	}

	public AdministratorAccess getAdminstratorAccess() {
		return new AdministratorAccessImpl(this);
	}

	public EstablishmentAccess<Establishment> getEstablishmentAccess(){
		return new EstablishementAccessImpl(this);
	}
	
	public AddressAccess getAddressAccess(){
		return new AddresAccessImpl(this);
	}
	
	public RestaurantAccess getRestaurantAccess(){
		return new RestaurantAccessImpl(this);
	}
	
}