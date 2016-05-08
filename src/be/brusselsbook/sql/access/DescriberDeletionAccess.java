package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.DescriberDeletion;
import be.brusselsbook.utils.BrusselsBookUtils;

public class DescriberDeletionAccess extends DataAccess<DescriberDeletion> {

	private static final String DID = "DID";
	private static final String UID = "UID";
	private static final String DELETIONDATE = "DeletionDate";

	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(DID, UID);
	private static final String TABLE = "DescriberDeletion";

	
	
	
	
	protected DescriberDeletionAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public DescriberDeletion withId(Long id) {
		return withDid(id);
	}

	@Override
	protected DescriberDeletion map(ResultSet resultSet) throws SQLException {
		DescriberDeletion describerDeletion =  new DescriberDeletion();
		describerDeletion.setDid(resultSet.getLong(DID));
		describerDeletion.setUid(resultSet.getLong(UID));
		describerDeletion.setDeletionDate(resultSet.getTimestamp(DELETIONDATE));
		return describerDeletion;
	}

	public DescriberDeletion createDescriberDeletion (Long did, Long uid){
		return createNoGeneratedId(did, did,uid);
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

	public DescriberDeletion withDid(String did) {
		return withQuery(SELECTBY(DID), did);
	}
	public DescriberDeletion withDid(Long did) {
		return withDid(did.toString());
	}


}
