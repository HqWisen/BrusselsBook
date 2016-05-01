package be.brusselsbook.sql;

import be.brusselsbook.data.Establishment;

public abstract class EstablishmentAccess<T extends Establishment> extends DataAccess<T> {

	protected EstablishmentAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public abstract T withEid(Long eid);

	public abstract T withEid(String eid);

}
