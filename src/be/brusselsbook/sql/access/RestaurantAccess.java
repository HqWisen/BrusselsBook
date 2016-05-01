package be.brusselsbook.sql.access;

import be.brusselsbook.data.Establishment;
import be.brusselsbook.data.Restaurant;
import be.brusselsbook.parser.RestaurantInfos;

public abstract class RestaurantAccess extends EstablishmentAccess<Restaurant> {

	private EstablishmentAccess<Establishment> establishmentAccess;

	protected RestaurantAccess(AccessFactory accessFactory) {
		super(accessFactory);
		this.establishmentAccess = accessFactory.getEstablishmentAccess();
	}

	public Restaurant createRestaurant(RestaurantInfos infos) {
		Establishment establishment = establishmentAccess.createEstablishement(infos);
		return createRestaurant(establishment.getEid(), infos);
	}

	public Restaurant createRestaurant(Long eid, RestaurantInfos infos) {
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

}
