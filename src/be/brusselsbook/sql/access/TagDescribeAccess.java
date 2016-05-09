package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import be.brusselsbook.sql.data.TagDescribe;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.BrusselsBookUtils;

//FIXME this class as multiple problem with return of create and withId
public class TagDescribeAccess extends DataAccess<TagDescribe> {

	protected static final String UID = "UID";
	protected static final String EID = "EID";
	protected static final String TAGNAME = "TagName";

	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(UID, EID, TAGNAME);
	private static final String TABLE = "TagDescribe";

	protected TagDescribeAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	public TagDescribe withId(Long id) {
		return null;
	}
	
	public TagDescribe withId(Object...values) {
		return withQuery(SELECTBYSEVERALAND(UID, EID, TAGNAME), values);
	}

	@Override
	public TagDescribe createNoGeneratedId(Long id, Object... values) {
		AccessUtils.executeInsert(accessFactory, INSERT(), values);
		return withId(values);
	}
	
	public List<TagDescribe> withEid(Long eid) {
		return withEid(eid.toString());
	}

	public List<TagDescribe> withEid(String eid) {
		return severalWithQuery(SELECTBY(EID), eid);
	}
	
	public List<TagDescribe> withEidAndTagName(Long eid, String tagName){
		return severalWithQuery(SELECTBYSEVERALAND(EID, TAGNAME), eid, tagName);
	}
	
	@Override
	protected TagDescribe map(ResultSet resultSet) throws SQLException {
		TagDescribe tagDescribe = new TagDescribe();
		tagDescribe.setUid(resultSet.getLong(UID));
		tagDescribe.setEid(resultSet.getLong(EID));
		tagDescribe.setTagName(resultSet.getString(TAGNAME));
		return tagDescribe;
	}

	public TagDescribe createTagDescribe(Long eid, Long uid, String tagName) {
		return createNoGeneratedId(eid, uid, eid, tagName);

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
