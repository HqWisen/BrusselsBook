package be.brusselsbook.sql.access.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.data.Establishment;
import be.brusselsbook.data.Restaurant;
import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.EstablishmentAccess;
import be.brusselsbook.sql.access.RestaurantAccess;
import be.brusselsbook.utils.BrusselsBookUtils;

public class RestaurantAccessImpl extends RestaurantAccess {

	private static final String EID = "EID";
	private static final String PRICEMINIMUM = "PriceMinimum";
	private static final String PRICEMAXIMUM = "PriceMaximum";
	private static final String TAKEAWAY = "HasTakeaway";
	private static final String DELIVERY = "MakeDelivery";
	private static final String HALFDAYSOFF = "HalfDaysOff";

	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(EID, PRICEMINIMUM, PRICEMAXIMUM,
			TAKEAWAY, DELIVERY, HALFDAYSOFF);
	private static final String TABLE = "Restaurant";

	private EstablishmentAccess<Establishment> establishmentAccess;

	public RestaurantAccessImpl(AccessFactory accessFactory) {
		super(accessFactory);
		this.establishmentAccess = accessFactory.getEstablishmentAccess();
	}

	@Override
	public Restaurant withId(Long id) {
		return withEid(id);
	}

	@Override
	public Restaurant withEid(Long eid) {
		return withEid(eid.toString());
	}

	@Override
	public Restaurant withEid(String eid) {
		return withQuery(SELECTBY(EID), eid);
	}

	@Override
	protected Restaurant map(ResultSet resultSet) throws SQLException {
		Long eid = resultSet.getLong(EID);
		Establishment establishment = establishmentAccess.withEid(eid);
		Restaurant restaurant = new Restaurant(establishment);
		restaurant.setPriceMinimum(resultSet.getInt(PRICEMINIMUM));
		restaurant.setPriceMaximum(resultSet.getInt(PRICEMAXIMUM));
		restaurant.setTakeaway(resultSet.getBoolean(TAKEAWAY));
		restaurant.setDelivery(resultSet.getBoolean(DELIVERY));
		restaurant.setHalfDaysOff(resultSet.getString(HALFDAYSOFF));
		return restaurant;
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
