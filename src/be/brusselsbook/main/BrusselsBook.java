package be.brusselsbook.main;

import java.io.IOException;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.EstablishmentAccess;
import be.brusselsbook.sql.data.Establishment;

public class BrusselsBook {

	public static void main(String[] args) throws IOException {
		System.out.println("Running ...");
		AccessFactory factory = AccessFactory.getInstance();
		EstablishmentAccess<Establishment> eAccess = factory.getEstablishmentAccess();
		eAccess.updateModified(false, 32L);
	}
}
