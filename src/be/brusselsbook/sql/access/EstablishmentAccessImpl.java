package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.sql.data.EstablishmentType;
import be.brusselsbook.utils.BrusselsBookUtils;

public class EstablishmentAccessImpl extends EstablishmentAccess<Establishment> {
	
	private static final String NAME = "EName";
	private static final String PHONENUMBER = "PhoneNumber";
	private static final String MODIFIED = "Modified";
	private static final String WEBSITE = "Website";
	private static final String EID = "EID";
	private static final String TYPE = "Type";
	// UID, Modified generated by sql
	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(NAME, PHONENUMBER, WEBSITE,
			TYPE);
	private static final String TABLE = "Establishment";
	
	public EstablishmentAccessImpl(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	protected String getTable() {
		return TABLE;
	}
	
	@Override
	protected String[] getCreationParameters() {
		return PARAMETERS;
	}
	
	@Override
	protected int getNumberOfCreationParameters() {
		return PARAMETERS.length;
	}
	
	@Override
	public Establishment withId(Long id) {
		return withEid(id);
	}
	
	@Override
	public Establishment withEid(Long eid) {
		return withEid(eid.toString());
	}

	@Override
	public Establishment withEid(String eid) {
		return withQuery(SELECTBY(EID), eid);
	}
	

	@Override
	public void updateModified(Boolean modified, Long eid) {
		update(UPDATEBY(MODIFIED, EID), modified, eid);
	}

	@Override
	public void hardDeleteWithEid(Long eid) {
		update(DELETEFROM(EID), eid);
	}
	
	public Establishment withName(String name){
		return withQuery(SELECTBY(NAME), name);
	}
	
	@Override
	protected Establishment map(ResultSet resultSet) throws SQLException {
		Establishment establishment = new Establishment();
		establishment.setEid(resultSet.getLong(EID));
		establishment.setName(resultSet.getString(NAME));
		establishment.setPhoneNumber(resultSet.getString(PHONENUMBER));
		establishment.setModified(resultSet.getBoolean(MODIFIED));
		establishment.setWebSite(resultSet.getString(WEBSITE));
		int typeId = resultSet.getInt(TYPE);
		establishment.setType(EstablishmentType.parseType(typeId));
		return establishment;
	}


}
