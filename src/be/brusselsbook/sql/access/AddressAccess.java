package be.brusselsbook.sql.access;

import be.brusselsbook.data.Address;
import be.brusselsbook.parser.AddressXml;

public abstract class AddressAccess extends DataAccess<Address> {

	protected AddressAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public Address createAddress(Long EID, String street, String number, String locality, String postalCode,
			Float latitude, Float longitude) {
		// the first EID if for getting the created Address because it does not
		// generate
		// an id, the only way to get the address is with the EID.
		// The second EID will be passed as a value in the SQL query.
		return createNoGeneratedId(EID, EID, street, number, locality, postalCode, latitude, longitude);
	}

	public Address createAddress(Long eid, AddressXml addressXml) {
		return createAddress(eid, addressXml.getStreet(), addressXml.getNum(), addressXml.getCity(),
				addressXml.getZip(), addressXml.getLatitude(), addressXml.getLongitude());
	}

	public abstract Address withEid(Long eid);

	public abstract Address withEid(String eid);

}
