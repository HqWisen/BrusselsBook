package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.EstablishmentDeletion;
import be.brusselsbook.utils.BrusselsBookUtils;

public class EstablishmentDeletionAccess extends DataAccess<EstablishmentDeletion> {

	private static final String EID = "EID";
	private static final String AID = "AID";
	private static final String DELETIONDATE = "DeletionDate";

	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(EID, AID);
	private static final String TABLE = "EstablishmentDeletion";

	
	
	
	protected EstablishmentDeletionAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	
	
	
	@Override
	public EstablishmentDeletion withId(Long id) {
		return withEid(id);
	}

	
	public EstablishmentDeletion createEstablishmentDeletion(Long eid, Long aid){
		return createNoGeneratedId(eid, eid, aid);
	}

	
	@Override
	protected EstablishmentDeletion map(ResultSet resultSet) throws SQLException {
		EstablishmentDeletion establishmentDeletion = new EstablishmentDeletion();
		establishmentDeletion.setAid(resultSet.getLong(AID));
		establishmentDeletion.setEid(resultSet.getLong(EID));
		establishmentDeletion.setDeletionDate(resultSet.getTimestamp(DELETIONDATE));
		return establishmentDeletion;
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
	
	public EstablishmentDeletion withEid(Long eid){
		return withEid(eid.toString());
	}
	
	public EstablishmentDeletion withEid(String eid){
		return withQuery(SELECTBY(EID), eid);
	}

	public void hardDeleteWithEid(Long eid){
		update(DELETEFROM(EID), eid);
	}

}
