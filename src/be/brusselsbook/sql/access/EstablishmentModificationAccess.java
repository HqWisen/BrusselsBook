package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.EstablishmentModification;
import be.brusselsbook.utils.BrusselsBookUtils;

public class EstablishmentModificationAccess extends DataAccess<EstablishmentModification> {
	
	private static final String OLDEID = "OldEID";
	private static final String NEWEID = "NewEID";
	private static final String AID = "AID";
	private static final String MODIFICATIONDATE = "ModificationDate";
	

	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(OLDEID,NEWEID,AID);
	private static final String TABLE = "EstablishmentModification";

	
	
	protected EstablishmentModificationAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public EstablishmentModification withId(Long id) {
		return null;
	}

	@Override
	protected EstablishmentModification map(ResultSet resultSet) throws SQLException {
		EstablishmentModification establishmentModification = new EstablishmentModification();
		establishmentModification.setOldEID(resultSet.getLong(OLDEID));
		establishmentModification.setNewEID(resultSet.getLong(NEWEID));
		establishmentModification.setAid(resultSet.getLong(AID));
		establishmentModification.setModificationDate(resultSet.getTimestamp(MODIFICATIONDATE));
		return establishmentModification;
	}


	public EstablishmentModification createEstablishmentModification(Long oldEID,Long newEID,Long aid ){
		return createNoGeneratedId(oldEID, newEID,aid);
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

	public EstablishmentModification withEid(String OldEID) {
		return withQuery(SELECTBY(OLDEID), OldEID);
	}
	
	public EstablishmentModification withEid(Long OldEID) {
		return withEid(OldEID.toString());
	}



}
