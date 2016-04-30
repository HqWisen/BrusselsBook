package be.brusselsbook.main;

import java.io.IOException;

import be.brusselsbook.data.Cafe;
import be.brusselsbook.data.Cafes;
import be.brusselsbook.data.CafesInfos;
import be.brusselsbook.data.Informations;
import be.brusselsbook.data.Restaurant;
import be.brusselsbook.data.RestaurantInfos;
import be.brusselsbook.data.Restaurants;
import be.brusselsbook.utils.BrusselsBookUtils;

public class BrusselsBook {

	public static void main(String[] args) throws IOException {
		String fileContent = BrusselsBookUtils.readFileFromResource("Cafes.xml");
		System.out.println("Running ...");
		Cafes cf = BrusselsBookUtils.unmarshal(fileContent, Cafes.class);
		for(Cafe cafe : cf.getCafeList()){
			//CafesInfos informations = cafe.getCafeInfos();
			System.out.println(cafe);
		}
	}
}
