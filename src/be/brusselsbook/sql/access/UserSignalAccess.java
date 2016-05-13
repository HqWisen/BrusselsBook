package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.UserSignal;
import be.brusselsbook.utils.BrusselsBookUtils;

public class UserSignalAccess extends DataAccess<UserSignal> {

	protected static final String DID = "DID";
	protected static final String SIGNALERUID = "SignalerUid";

	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(DID, SIGNALERUID);
	private static final String TABLE = "UserSignal";

	protected UserSignalAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public UserSignal withId(Long id) {
		return withDid(id);
	}

	public UserSignal withDidAndUid(Long did, Long uid){
		return withQuery(SELECTBYSEVERALAND(DID, SIGNALERUID), did, uid);
	}
	
	public UserSignal withDid(Long did) {
		return withDid(did.toString());
	}

	public UserSignal withDid(String did) {
		return withQuery(SELECTBY(DID), did);
	}

	@Override
	protected UserSignal map(ResultSet resultSet) throws SQLException {
		UserSignal userSignal = new UserSignal();
		userSignal.setDid(resultSet.getLong(DID));
		userSignal.setSignalerUid(resultSet.getLong(SIGNALERUID));
		return userSignal;
	}

	public UserSignal createUserSignal(Long did, Long signalerUid) {
		return createNoGeneratedId(did, did, signalerUid);
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

	public void hardDeleteWithDid(Long did) {
		update(DELETEFROM(DID), did);
	}

}
