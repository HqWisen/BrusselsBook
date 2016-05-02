package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.data.Establishment;
import be.brusselsbook.data.Restaurant;
import be.brusselsbook.parser.RestaurantInfos;
import be.brusselsbook.utils.BrusselsBookUtils;

public class RestaurantAccess extends EstablishmentAccess<Restaurant> {

	private static final String EID = "EID";
	private static final String PRICERANGE = "PriceRange";
	private static final String BANQUETPLACES = "BanquetPlaces";
	private static final String TAKEAWAY = "HasTakeaway";
	private static final String DELIVERY = "MakeDelivery";
	private static final String HALFDAYSOFF = "HalfDaysOff";

	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(EID, PRICERANGE, BANQUETPLACES,
			TAKEAWAY, DELIVERY, HALFDAYSOFF);
	private static final String TABLE = "Restaurant";
	
	private EstablishmentAccess<Establishment> establishmentAccess;

	protected RestaurantAccess(AccessFactory accessFactory) {
		super(accessFactory);
		this.establishmentAccess = accessFactory.getEstablishmentAccess();
	}

	public Restaurant createRestaurant(RestaurantInfos infos) {
		Establishment establishment = establishmentAccess.createEstablishement(infos);
		return createRestaurant(establishment.getEid(), infos);
	}

	private Restaurant createRestaurant(Long eid, RestaurantInfos infos) {
		return createRestaurant(eid, infos.getPriceRange(), infos.getBanquetPlaces(), infos.hasTakeAway(),
				infos.makeDelivery(), infos.getClosedDays());
	}

	private Restaurant createRestaurant(Long eid, Integer priceRange, Integer banquetPlaces, Boolean hasTakeAway,
			Boolean makeDelivery, String closedDays) {
		// the first EID if for getting the created Restaurant
		// because it does not generate
		// an id, the only way to get the restaurant is with the EID.
		// The second EID will be passed as a value in the SQL query.
		return createNoGeneratedId(eid, eid, priceRange, banquetPlaces, hasTakeAway, makeDelivery, closedDays);
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
		restaurant.setPriceRange(resultSet.getInt(PRICERANGE));
		restaurant.setBanquetPlaces(resultSet.getInt(BANQUETPLACES));
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
