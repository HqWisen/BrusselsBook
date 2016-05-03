package be.brusselsbook.sql.access;

import be.brusselsbook.sql.data.Describer;

public abstract class DescriberAccess<T extends Describer > extends DataAccess<T> {

	protected DescriberAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}
	
	public abstract T withDid(Long did);

	public abstract T withDid(String did);

}
