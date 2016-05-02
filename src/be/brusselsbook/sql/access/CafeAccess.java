package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.data.Cafe;
import be.brusselsbook.data.Establishment;
import be.brusselsbook.parser.CafeInfos;
import be.brusselsbook.utils.BrusselsBookUtils;

public class CafeAccess extends EstablishmentAccess<Cafe> {

	private static final String EID = "EID";
	private static final String SMOKE = "CanSmoke";
	private static final String RESTORATION = "MakeRestoration";
	
	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(EID, SMOKE, RESTORATION);
	private static final String TABLE = "Bar";
	
	protected EstablishmentAccess<Establishment> establishmentAccess;
	
	public CafeAccess(AccessFactory accessFactory) {
		super(accessFactory);
		this.establishmentAccess = accessFactory.getEstablishmentAccess();
	}

	public Cafe createCafe(CafeInfos infos) {
		Establishment establishment = establishmentAccess.createEstablishement(infos);
		return createCafe(establishment.getEid(), infos);
	}

	private Cafe createCafe(Long eid, CafeInfos infos) {
		return createCafe(eid, infos.canSmoke(), infos.canSnack());
	}

	private Cafe createCafe(Long eid, Boolean canSmoke, Boolean makeRestoration) {
		// the first EID if for getting the created Cafe
		// because it does not generate
		// an id, the only way to get the cafe is with the EID.
		// The second EID will be passed as a value in the SQL query.
		return createNoGeneratedId(eid, eid, canSmoke, makeRestoration);
	}
	
	@Override
	public Cafe withId(Long id) {
		return withEid(id);
	}

	@Override
	protected Cafe map(ResultSet resultSet) throws SQLException {
		Long eid = resultSet.getLong(EID);
		Establishment establishment = establishmentAccess.withEid(eid);
		Cafe cafe = new Cafe(establishment);
		cafe.setSmoke(resultSet.getBoolean(SMOKE));
		cafe.setRestoration(resultSet.getBoolean(RESTORATION));
		return cafe;
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
	
	@Override
	public Cafe withEid(Long eid) {
		return withEid(eid.toString());
	}

	@Override
	public Cafe withEid(String eid) {
		return withQuery(SELECTBY(EID), eid);
	}
	
}
