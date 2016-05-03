package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.BookUser;
import be.brusselsbook.sql.data.Describer;

public class DescriberAccessImpl extends DescriberAccess<Describer> {

	protected static final String DID = "DID";
	private static final String CREATIONDATE = "RegistrationDate";
	
	private static final String TABLE = "Describer";
	
	protected DescriberAccessImpl(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public Describer withId(Long id) {
		return withDid(id);
	}

	@Override
	public Describer withDid(Long did) {
		return withDid(did.toString());
	}

	@Override
	public Describer withDid(String did) {
		return withQuery(SELECTBY(DID), did);
	}

	
	@Override
	protected Describer map(ResultSet resultSet) throws SQLException {
		Describer describer = new Describer();
		describer.setDid(resultSet.getLong(DID));
		describer.setCreationDate(resultSet.getTimestamp(CREATIONDATE));
		return describer;
	}

	@Override
	protected String getTable() {
		return TABLE;
	}

	//TODO return 0 parameters .
	@Override
	protected String[] getCreationParameters() {
		return null;
	}

	@Override
	protected int getNumberOfCreationParameters() {
		return 0;
	}

}
