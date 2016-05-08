package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.Describer;
import be.brusselsbook.sql.data.Tag;
import be.brusselsbook.sql.data.TagDescribe;
import be.brusselsbook.sql.data.UserSignal;
import be.brusselsbook.utils.BrusselsBookUtils;

public class TagDescribeAccess extends DataAccess<TagDescribe> {

	protected static final String UID = "UID";
	protected static final String EID = "EID";
	protected static final String TAGNAME = "TagName";
	
	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(UID,EID,TAGNAME);
	private static final String TABLE = "TagDescribe";

	
	protected TagDescribeAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public TagDescribe withId(Long id) {	
		return withEid(id);
	}

	
	
	public TagDescribe withEid(Long eid) {
		return withEid(eid.toString());
	}

	public TagDescribe withEid(String eid) {
		return withQuery(SELECTBY(EID), eid);
	}

	
	@Override
	protected TagDescribe map(ResultSet resultSet) throws SQLException {
		TagDescribe tagDescribe = new TagDescribe();
		tagDescribe.setUid(resultSet.getLong(UID));
		tagDescribe.setEid(resultSet.getLong(EID));
		tagDescribe.setTagName(resultSet.getString(TAGNAME));
		return tagDescribe;
	}

	public TagDescribe createTagDescribe ( Long eid, Long uid,String tagName){
		return createNoGeneratedId(uid, uid,eid,tagName);
		
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
