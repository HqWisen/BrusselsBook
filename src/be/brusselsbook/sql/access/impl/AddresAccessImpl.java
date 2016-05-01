package be.brusselsbook.sql.access.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.data.Address;
import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.AddressAccess;
import be.brusselsbook.utils.BrusselsBookUtils;

public class AddresAccessImpl extends AddressAccess {

	private static final String EID = "EID";
	private static final String STREET = "Street";
	private static final String NUMBER = "StreetNumber";
	private static final String LOCALITY = "Locality";
	private static final String POSTALCODE = "PostalCode";
	private static final String LATITUDE = "Latitude";
	private static final String LONGITUDE = "Longitude";

	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(EID, STREET, NUMBER, LOCALITY,
			POSTALCODE, LATITUDE, LONGITUDE);
	private static final String TABLE = "Address";

	public AddresAccessImpl(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public Address withId(Long id) {
		return withEid(id);
	}

	@Override
	protected Address map(ResultSet resultSet) throws SQLException {
		Address address = new Address();
		address.setEid(resultSet.getLong(EID));
		address.setStreet(resultSet.getString(STREET));
		address.setNumber(resultSet.getString(NUMBER));
		address.setLocality(resultSet.getString(LOCALITY));
		address.setPostalCode(resultSet.getString(POSTALCODE));
		address.setLatitude(resultSet.getFloat(LATITUDE));
		address.setLongitude(resultSet.getFloat(LONGITUDE));
		return address;
	}

	@Override
	protected String getTable() {
		return TABLE;
	}

	@Override
	protected String[] getCreationParameters() {
		return PARAMETERS;
	}

	@Override
	protected int getNumberOfCreationParameters() {
		return PARAMETERS.length;
	}

	@Override
	public Address withEid(Long eid) {
		return withEid(eid.toString());
	}

	@Override
	public Address withEid(String eid) {
		return with(SELECTBY(EID), eid);
	}

}
