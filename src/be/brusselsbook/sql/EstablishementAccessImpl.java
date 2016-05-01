package be.brusselsbook.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.data.Establishment;

public class EstablishementAccessImpl extends EstablishmentAccess<Establishment> {
	
	// FIXME see if SQL variable cannot be put in constant
	// FIXME see if SELECT() INSERT() ... cannot be implement in abstact
	// FIXME see if create cannot be generic
	
	protected static final String EID = "EID";
	
	protected EstablishementAccessImpl(AccessFactory accessFactory) {
		super(accessFactory);
	}

	@Override
	protected String SELECTBY(String by) {
		return "SELECT * FROM Establishment WHERE " + by + " = ?";
	}

	@Override
	protected String SELECTALL() {
		return  "SELECT * FROM Establishment";
	}

	@Override
	protected String INSERT() {
		return "INSERT INTO Establishment (EName, PhoneNumber, Website) VALUES (?, ?, ?)";
	}
	
	@Override
	public Establishment withId(Long id) {
		return withEid(id);
	}
	
	@Override
	public Establishment withEid(Long eid) {
		return withEid(eid.toString());
	}

	@Override
	public Establishment withEid(String eid) {
		return with(SELECTBY(EID), eid);
	}

	@Override
	protected Establishment map(ResultSet resultSet) throws SQLException {
		Establishment establishment = new Establishment();
		establishment.setEid(resultSet.getLong("EID"));
		establishment.setName(resultSet.getString("EName"));
		establishment.setPhoneNumber(resultSet.getString("PhoneNumber"));
		establishment.setModified(resultSet.getBoolean("Modified"));
		establishment.setWebSite("Website");
		return establishment;
	}

}
