package be.brusselsbook.main;

import java.io.IOException;
import java.sql.ResultSet;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.EstablishmentAccess;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.utils.AccessUtils;
import be.brusselsbook.utils.BrusselsBookUtils;

public class BrusselsBook {

	public static void main(String[] args) throws IOException {
		String fileContent = BrusselsBookUtils.readFileFromResource("Cafes.xml");
		System.out.println("Running ...");
		String SQL = "select * from Establishment where EName like ?";
		String value = "";
		ResultSet set = AccessUtils.executeLikeQuery(AccessFactory.getInstance(), SQL, value);
		while(AccessUtils.next(set)){
			EstablishmentAccess<Establishment> eAccess = AccessFactory.getInstance().getEstablishmentAccess();
			Establishment e = eAccess.safeMap(set);
			System.out.println(e);
		}
	}
}
