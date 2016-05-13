package be.brusselsbook.main;

import java.io.IOException;

import be.brusselsbook.sql.access.AccessFactory;
import be.brusselsbook.sql.access.EstablishmentModificationAccess;

public class BrusselsBook {

	public static void main(String[] args) throws IOException {
		System.out.println("Running ...");
		AccessFactory factory = AccessFactory.getInstance();
		EstablishmentModificationAccess mAccess = factory.getEstablishmentModifiacationAccess();
		mAccess.createEstablishmentModification(26L, 27L, 1L);
	}
}
