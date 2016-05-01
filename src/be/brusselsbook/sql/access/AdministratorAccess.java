package be.brusselsbook.sql.access;

import be.brusselsbook.data.Administrator;

public abstract class AdministratorAccess extends BookUserAccess<Administrator> {
	
	protected AdministratorAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public abstract Administrator withAid(Long aid);

	public abstract Administrator withAid(String aid);
}
