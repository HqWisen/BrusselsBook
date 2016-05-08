package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.EstablishmentCreation;
import be.brusselsbook.utils.BrusselsBookUtils;

public class EstablishmentCreationAccess extends DataAccess<EstablishmentCreation> {

	private static final String EID = "EID";
	private static final String AID = "AID";
	
	private static final String CREATIONDATE = "CreationDate";
	
	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(EID, AID);
	private static final String TABLE = "EstablishmentCreation";

	public EstablishmentCreationAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public EstablishmentCreation createEstablishmentCreation(Long eid, Long aid){
		return createNoGeneratedId(eid, eid, aid);
	}
	
	@Override
	public EstablishmentCreation withId(Long id) {
		return withEid(id);
	}

	@Override
	protected EstablishmentCreation map(ResultSet resultSet) throws SQLException {
		EstablishmentCreation creation = new EstablishmentCreation();
		creation.setEid(resultSet.getLong(EID));
		creation.setAid(resultSet.getLong(AID));
		creation.setCreationDate(resultSet.getTimestamp(CREATIONDATE));
		return creation;
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

	public EstablishmentCreation withEid(Long eid){
		return withEid(eid.toString());
	}
	
	public EstablishmentCreation withEid(String eid){
		return withQuery(SELECTBY(EID), eid);
	}
}
