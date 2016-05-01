package be.brusselsbook.sql.access;

import be.brusselsbook.data.Restaurant;
import be.brusselsbook.parser.RestaurantInfos;

public abstract class RestaurantAccess extends EstablishmentAccess<Restaurant> {

	protected RestaurantAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public Restaurant createRestaurant(RestaurantInfos informations){
		return null;
	}
	
}
