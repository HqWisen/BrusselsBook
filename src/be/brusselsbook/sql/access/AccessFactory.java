package be.brusselsbook.sql.access;

import java.sql.Connection;
import java.sql.SQLException;

import be.brusselsbook.sql.DatabaseAccess;
import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.sql.data.Describer;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.sql.data.EstablishmentCreation;
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
		return new AdministratorAccess(this);
	}

	public EstablishmentAccess<Establishment> getEstablishmentAccess(){
		return new EstablishmentAccessImpl(this);
	}
	
	public AddressAccess getAddressAccess(){
		return new AddressAccess(this);
	}
	
	public RestaurantAccess getRestaurantAccess(){
		return new RestaurantAccess(this);
	}

	public CafeAccess getCafeAccess() {
		return new CafeAccess(this);
	}
	
	public EstablishmentCreationAccess getEstablishmentCreationAccess(){
		return new EstablishmentCreationAccess(this);
	}

	public DescriberAccess<Describer> getDescriberAccess(){
		return new DescriberAccessImpl(this);
	}

	public BookCommentAccess getBookCommentAccess(){
		return new BookCommentAccess(this);
	}

	public TagAccess getTagAccess(){
		return new TagAccess(this);
	}

	public HotelAccess getHotelAccess(){
		return new HotelAccess(this);
	}

	public UserSignalAccess getUserSignalAccess(){
		return new UserSignalAccess(this);
	}

}