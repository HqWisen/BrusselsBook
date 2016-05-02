package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.sql.data.Describer;

public abstract class DescriberAccess<T extends Describer > extends DataAccess<T> {

	protected DescriberAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}
	
	public abstract T withDid(Long did);

	






}
