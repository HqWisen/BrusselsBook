package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.data.Address;
import be.brusselsbook.parser.AddressXml;
import be.brusselsbook.utils.BrusselsBookUtils;

public class AddressAccess extends DataAccess<Address> {

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
	
	protected AddressAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public Address createAddress(Long eid, String street, String number, String locality, String postalCode,
			Float latitude, Float longitude) {
		// the first EID if for getting the created Address because it does not
		// generate
		// an id, the only way to get the address is with the EID.
		// The second EID will be passed as a value in the SQL query.
		return createNoGeneratedId(eid, eid, street, number, locality, postalCode, latitude, longitude);
	}

	public Address createAddress(Long eid, AddressXml addressXml) {
		return createAddress(eid, addressXml.getStreet(), addressXml.getNum(), addressXml.getCity(),
				addressXml.getZip(), addressXml.getLatitude(), addressXml.getLongitude());
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

	public Address withEid(Long eid) {
		return withEid(eid.toString());
	}

	public Address withEid(String eid) {
		return withQuery(SELECTBY(EID), eid);
	}
	
}
