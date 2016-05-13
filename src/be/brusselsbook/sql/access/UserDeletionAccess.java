package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.UserDeletion;
import be.brusselsbook.utils.BrusselsBookUtils;

public class UserDeletionAccess extends DataAccess<UserDeletion> {
	
	private static final String UID = "UID";
	private static final String AID = "AID";
	private static final String DELETIONDATE = "DeletionDate";

	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(UID, AID);
	private static final String TABLE = "UserDeletion";

	
	
	protected UserDeletionAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public UserDeletion withId(Long id) {
		return withUid(id);
	}

	@Override
	protected UserDeletion map(ResultSet resultSet) throws SQLException {
		UserDeletion userDeletion = new UserDeletion();
		userDeletion.setEid(resultSet.getLong(UID));
		userDeletion.setAid(resultSet.getLong(AID));
		userDeletion.setDeletionDate(resultSet.getTimestamp(DELETIONDATE));
		return userDeletion;
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

	public UserDeletion createUserDeletion(Long uid,Long aid){
		return createNoGeneratedId(uid,uid, aid);
	}
	
	public UserDeletion withUid(Long uid){
		return withUid(uid.toString());
	}
	
	public UserDeletion withUid(String uid){
		return withQuery(SELECTBY(UID), uid);
	}

	public void hardDeleteWithUid(Long uid){
		update(DELETEFROM(UID), uid);
	}


}
