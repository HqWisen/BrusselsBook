package be.brusselsbook.sql;

import be.brusselsbook.data.Address;

public abstract class AddressAccess extends DataAccess<Address> {

	protected AddressAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public Address createAddress(Long EID, String street, String number, String locality, String postalCode, Float latitude,
			Float longitude) {
		// the first EID if for getting the created Address because it does not generate 
		// an id, the only way to get the address is with the EID.
		// The second EID will be passed as a value in the SQL query.
		return create(EID, EID, street, number, locality, postalCode, latitude,
				longitude);
	}
	
	public abstract Address withEid(Long eid);

	public abstract Address withEid(String eid);

}
