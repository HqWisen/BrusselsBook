package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;


import be.brusselsbook.sql.data.DescriberModification;
import be.brusselsbook.sql.data.EstablishmentModification;
import be.brusselsbook.utils.BrusselsBookUtils;

public class DescriberModificationAccess extends DataAccess<DescriberModification> {

	private static final String OLDDID = "OldDID";
	private static final String NEWDID = "NewDID";
	private static final String UID = "UID";
	private static final String MODIFICATIONDATE = "ModificationDate";

	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(OLDDID,NEWDID,UID);
	private static final String TABLE = "DescriberModification";

	
	
	protected DescriberModificationAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public DescriberModification withId(Long id) {
		return withEid(id);
	}

	@Override
	protected DescriberModification map(ResultSet resultSet) throws SQLException {
		DescriberModification describerModification = new DescriberModification();
		describerModification.setUid(resultSet.getLong(UID));
		describerModification.setOldDID(resultSet.getLong(OLDDID));
		describerModification.setNewDID(resultSet.getLong(NEWDID));
		describerModification.setModificationDate(resultSet.getTimestamp(MODIFICATIONDATE));
		return describerModification;
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

	public DescriberModification createDescriberModification(Long oldDID,Long newDID,Long uid ){
		return createNoGeneratedId(oldDID, newDID,uid);
	}



	public DescriberModification withDid(String OldDID) {
		return withQuery(SELECTBY(OLDDID), OldDID);
	}
	
	public DescriberModification withEid(Long OldDID) {
		return withDid(OldDID.toString());
	}



}
