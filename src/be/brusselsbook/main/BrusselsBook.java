package be.brusselsbook.main;

import java.io.IOException;

import be.brusselsbook.data.Informations;
import be.brusselsbook.data.Restaurant;
import be.brusselsbook.data.Restaurants;
import be.brusselsbook.utils.BrusselsBookUtils;

public class BrusselsBook {

	public static void main(String[] args) throws IOException {
		String fileContent = BrusselsBookUtils.readFileFromResource("Restaurants.xml");
		System.out.println("Running ...");
		Restaurants rs = BrusselsBookUtils.unmarshal(fileContent, Restaurants.class);
		for(Restaurant restaurant : rs.getRestaurantList()){
			Informations informations = restaurant.getInformations();
			System.out.println(informations.getBanquet());
		}
	}
}
