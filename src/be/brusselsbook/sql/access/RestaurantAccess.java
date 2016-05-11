package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.parser.AddressXml;
import be.brusselsbook.parser.RestaurantInfos;
import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.sql.data.EstablishmentType;
import be.brusselsbook.sql.data.Restaurant;
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

	public Restaurant createRestaurantFromAdmin(Long aid, String name, String phoneNumber, String website,
			String street, String streetNumber, String locality, String postalCode, Integer priceRange,
			Integer banquetPlaces, Boolean takeAway, Boolean delivery) {
		RestaurantInfos infos = new RestaurantInfos();
		AddressXml address = new AddressXml();
		// FIXME latitude longitude not given
		infos.setAddress(address);
		infos.setName(name);
		infos.setTel(phoneNumber);
		infos.setSite(website);
		infos.setPriceRange(priceRange);
		infos.setBanquet(banquetPlaces);
		infos.setTakeAway(takeAway ? "" : null);
		infos.setDelivery(delivery ? "" : null);
		address.setStreet(street);
		address.setNum(streetNumber);
		address.setCity(locality);
		address.setZip(postalCode);
		address.setLatitude(0.0f);
		address.setLongitude(0.0f);
		return createRestaurantFromAdmin(aid, infos);
	}

	public Restaurant createRestaurantFromAdmin(Long aid, RestaurantInfos infos) {
		Establishment establishment = establishmentAccess.createEstablishment(aid, infos,
				EstablishmentType.RESTAURANT.getId());
		return createRestaurant(establishment.getEid(), infos);
	}

	public Restaurant createRestaurant(RestaurantInfos infos) {
		Establishment establishment = establishmentAccess.createEstablishment(infos,
				EstablishmentType.RESTAURANT.getId());
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

	public Restaurant editRestaurant(Long aid, Long oldEID, String name, String tel, String site, Address address,
			int type, Integer priceRange, Integer banquetPlaces, Boolean hasTakeAway, Boolean makeDelivery,
			String closedDays) {
		Establishment establishment = establishmentAccess.editEstablishment(aid, oldEID, name, tel, site, address,
				type);
		return createRestaurant(establishment.getEid(), priceRange, banquetPlaces, hasTakeAway, makeDelivery,
				closedDays);
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
