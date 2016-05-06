package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.Describer;
import be.brusselsbook.sql.data.UserSignal;
import be.brusselsbook.utils.BrusselsBookUtils;

public class UserSignalAccess extends DescriberAccess<UserSignal> {

	
	protected static final String DID = "DID";
	protected static final String SIGNALERUID = "SignalerUid";
	
	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(DID,SIGNALERUID);
	private static final String TABLE = "UserSignal";

	
	private DescriberAccess<Describer> describerAccess;
	
	protected UserSignalAccess(AccessFactory accessFactory) {
		super(accessFactory);
		this.describerAccess = accessFactory.getDescriberAccess();
	}

	@Override
	public UserSignal withId(Long id) {
		return withDid(id);
	}

	@Override
	public UserSignal withDid(Long did) {
		return withDid(did.toString());
	}

	@Override
	public UserSignal withDid(String did) {
		return withQuery(SELECTBY(DID), did);
	}

	@Override
	protected UserSignal map(ResultSet resultSet) throws SQLException {
		Long did = resultSet.getLong(DID);
		Describer describer = describerAccess.withDid(did);
		UserSignal userSignal = new UserSignal(describer);
		userSignal.setSignalerUid(resultSet.getLong(SIGNALERUID));
		return userSignal;
	}

	
	public UserSignal createUserSignal (Long signalerUid ){
		Describer describer = describerAccess.create();
		Long did = describer.getDid();
		return createNoGeneratedId(did, did,signalerUid);
		
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

}
