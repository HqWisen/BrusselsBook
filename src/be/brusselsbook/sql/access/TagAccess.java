package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import be.brusselsbook.sql.data.Describer;
import be.brusselsbook.sql.data.Tag;
import be.brusselsbook.utils.BrusselsBookUtils;

public class TagAccess extends DescriberAccess<Tag> {

	protected static final String DID = "DID";
	protected static final String TAGNAME = "TagName";
	protected static final String UID = "UID";
	private static final String CREATIONDATE = "CreationDate";
	
	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(DID,UID,TAGNAME);
	private static final String TABLE = "Tag";

	private DescriberAccess<Describer> describerAccess;

	
	
	
	
	
	protected TagAccess(AccessFactory accessFactory) {
		super(accessFactory);
		this.describerAccess = accessFactory.getDescriberAccess();
	}

	
	
	
	
	public Tag withTagName(String tagName) {
		return withQuery(SELECTBY(TAGNAME), tagName);
	}

	
	
	@Override
	public Tag withId(Long id) {
		return withDid(id);
	}

	@Override
	public Tag withDid(Long did) {
		return withDid(did.toString());
	}

	@Override
	public Tag withDid(String did) {
		return withQuery(SELECTBY(DID), did);
	}

	@Override
	protected Tag map(ResultSet resultSet) throws SQLException {
		Long did = resultSet.getLong(DID);
		Describer describer = describerAccess.withDid(did);
		Tag tag = new Tag(describer);
		tag.setUid(resultSet.getLong(UID));
		tag.setTagName(resultSet.getString(TAGNAME));
		tag.setCreationDate(resultSet.getTimestamp(CREATIONDATE));
		return tag;
	}

	
	public Tag createTag (Long uid , String tagName){
		Describer describer = describerAccess.create();
		Long did = describer.getDid();
		return createNoGeneratedId(did, did,uid,tagName);
		
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
