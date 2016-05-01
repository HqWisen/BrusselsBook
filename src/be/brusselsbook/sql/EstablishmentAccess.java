package be.brusselsbook.sql;

import be.brusselsbook.data.Establishment;

public abstract class EstablishmentAccess<T extends Establishment> extends DataAccess<T> {

	protected EstablishmentAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}
	
	public abstract T create(String name, String phoneNumber, String webSite);
	
	public abstract T withEid(Long eid);

	public abstract T withEid(String eid);

	public T create(String name, String phoneNumber){
		return create(name, phoneNumber, "");
	}
	
}
