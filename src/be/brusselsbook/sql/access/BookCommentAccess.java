package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import be.brusselsbook.sql.data.BookComment;
import be.brusselsbook.sql.data.Describer;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.BrusselsBookUtils;

public class BookCommentAccess extends DescriberAccess<BookComment> {

	protected static final String DID = "DID";
	protected static final String UID = "UID";
	protected static final String EID = "EID";
	private static final String CREATIONDATE = "CreationDate";
	protected static final String SCORE = "Score";
	protected static final String TEXT = "BookText";
	
	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(DID,UID,EID,SCORE,TEXT);
	private static final String TABLE = "BookComment";

	private DescriberAccess<Describer> describerAccess;
	
	protected BookCommentAccess(AccessFactory accessFactory) {
		super(accessFactory);
		this.describerAccess = accessFactory.getDescriberAccess();
	}

	@Override
	public BookComment withId(Long id) {
		return withDid(id);
	}

	@Override
	public BookComment withDid(Long did) {
		return withDid(did.toString());
	}

	public List<BookComment> withEid(Long eid){
		return severalWithQuery(SELECTBY(EID), eid);
	}	

	public List<BookComment> withUid(Long uid){
		return severalWithQuery(SELECTBY(UID), uid);
	}	
	
	public Integer avgScoreWithEid(Long eid){
		Integer score = 0;
		String query = "select AVG(b.Score) from " + getTable() + " b where b.EID = " + eid;
		ResultSet resultSet = AccessUtils.executeQuery(accessFactory, query, eid);
		if(AccessUtils.next(resultSet)){
			Long longScore = AccessUtils.getLongFirstColumn(resultSet);
			score = (int)Math.ceil(longScore);
		}
		return score;
	}
	
	public BookComment createBookComment (Long uid , Long eid,Integer score,String text){
		Describer describer = describerAccess.create();
		Long did = describer.getDid();
		return createNoGeneratedId(did, did,uid,eid,score,text);
		
	}
	
	@Override
	public BookComment withDid(String did) {
		return withQuery(SELECTBY(DID), did);
	}

	@Override
	protected BookComment map(ResultSet resultSet) throws SQLException {
		Long did = resultSet.getLong(DID);
		Describer describer = describerAccess.withDid(did);
		BookComment bookComment = new BookComment(describer);
		bookComment.setEid(resultSet.getLong(EID));
		bookComment.setScore(resultSet.getInt(SCORE));
		bookComment.setText(resultSet.getString(TEXT));
		bookComment.setUid(resultSet.getLong(UID));
		bookComment.setCreationDate(resultSet.getTimestamp(CREATIONDATE));
		return bookComment;
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
