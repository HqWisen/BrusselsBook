package be.brusselsbook.sql.access;

import be.brusselsbook.data.Establishment;

public abstract class EstablishmentAccess<T extends Establishment> extends DataAccess<T> {

	protected EstablishmentAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public T createEstablishment(String name, String phoneNumber, String website){
		return create(name, phoneNumber, website);
	}
	
	public abstract T withEid(Long eid);

	public abstract T withEid(String eid);

}
