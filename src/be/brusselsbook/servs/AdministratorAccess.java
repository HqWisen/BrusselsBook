package be.brusselsbook.servs;

import be.brusselsbook.data.Administrator;
import be.brusselsbook.sql.AccessFactory;
import be.brusselsbook.sql.BookUserAccess;

public abstract class AdministratorAccess extends BookUserAccess<Administrator> {
	
	protected AdministratorAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public abstract Administrator withAid(Long aid);

	public abstract Administrator withAid(String aid);
}
