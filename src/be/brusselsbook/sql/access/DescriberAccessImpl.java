package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.sql.data.Describer;

public class DescriberAccessImpl extends DescriberAccess<Describer> {

	protected static final String DID = "DID";
	private static final String TABLE = "Describer";
	
	protected DescriberAccessImpl(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public Describer withId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Describer withDid(Long uid) {
		return null;
	}


	@Override
	protected Describer map(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getTable() {
		return TABLE;
	}

	@Override
	protected String[] getCreationParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getNumberOfCreationParameters() {
		// TODO Auto-generated method stub
		return 0;
	}

}
