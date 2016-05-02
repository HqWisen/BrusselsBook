package be.brusselsbook.main;

import java.io.IOException;

import be.brusselsbook.parser.CafeXml;
import be.brusselsbook.parser.Cafes;
import be.brusselsbook.utils.BrusselsBookUtils;

public class BrusselsBook {

	public static void main(String[] args) throws IOException {
		String fileContent = BrusselsBookUtils.readFileFromResource("Cafes.xml");
		System.out.println("Running ...");
		Cafes cf = BrusselsBookUtils.unmarshal(fileContent, Cafes.class);
		for(CafeXml cafe : cf.getCafeList()){
			//CafesInfos informations = cafe.getCafeInfos();
			System.out.println(cafe);
		}
	}
}
