package be.brusselsbook.sql.access;

import be.brusselsbook.parser.AddressXml;
import be.brusselsbook.parser.CafeInfos;
import be.brusselsbook.parser.EstablishmentInfos;
import be.brusselsbook.parser.RestaurantInfos;
import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.Establishment;

public abstract class EstablishmentAccess<T extends Establishment> extends DataAccess<T> {

	public static RestaurantInfos createRestaurantInfos(String name, String phoneNumber, String website, String street,
			String streetNumber, String locality, String postalCode, Float lat, Float lng, Integer priceRange,
			Integer banquetPlaces, Boolean takeAway, Boolean delivery) {
		RestaurantInfos infos = new RestaurantInfos();
		setInfos(infos, name, phoneNumber, website, street, streetNumber, locality, postalCode, lat, lng);
		infos.setRPriceRange(priceRange);
		infos.setBanquetPlaces(banquetPlaces);
		infos.setRTakeAway(takeAway ? "" : null);
		infos.setRDelivery(delivery ? "" : null);
		return infos;

	}

	public static CafeInfos createCafeInfos(String name, String phoneNumber, String website, String street,
			String streetNumber, String locality, String postalCode, Float lat, Float lng, Boolean smoking,
			Boolean snack) {
		CafeInfos infos = new CafeInfos();
		setInfos(infos, name, phoneNumber, website, street, streetNumber, locality, postalCode, lat, lng);
		infos.setSmoking(smoking ? "" : null);
		infos.setSnack(snack ? "" : null);
		return infos;
	}

	private static void setInfos(EstablishmentInfos infos, String name, String phoneNumber, String website,
			String street, String streetNumber, String locality, String postalCode, Float lat, Float lng) {
		AddressXml address = new AddressXml();
		infos.setEAddress(address);
		infos.setEName(name);
		infos.setETel(phoneNumber);
		infos.setSiteLink(website);
		address.setAStreet(street);
		address.setANum(streetNumber);
		address.setACity(locality);
		address.setAZip(postalCode);
		address.setALatitude(lat);
		address.setALongitude(lng);

	}

	public static Address createAddress(String street, String number, String locality, String zip, Float lat,
			Float lng) {
		return new Address(street, number, locality, zip, lat, lng);
	}

	protected EstablishmentAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public T createEstablishment(Long aid, EstablishmentInfos infos, int type) {
		EstablishmentCreationAccess creationAccess = accessFactory.getEstablishmentCreationAccess();
		T establishment = createEstablishment(infos, type);
		creationAccess.createEstablishmentCreation(establishment.getEid(), aid);
		return establishment;
	}

	public T createEstablishmentForNoXml(Long aid, String name, String phoneNumber, String website, Address address,
			int type) {
		EstablishmentCreationAccess creationAccess = accessFactory.getEstablishmentCreationAccess();
		T establishment = createEstablishmentFromAddress(name, phoneNumber, website, address, type);
		creationAccess.createEstablishmentCreation(establishment.getEid(), aid);
		return establishment;
	}

	public T createEstablishment(EstablishmentInfos infos, int type) {
		return createEstablishment(infos.getName(), infos.getTel(), infos.getSiteLink(), infos.getAddress(), type);
	}

	private T createEstablishment(String name, String phoneNumber, String website, AddressXml addressXml, int type) {
		T establishment = create(name, phoneNumber, website, type);
		AddressAccess addressAccess = accessFactory.getAddressAccess();
		addressAccess.createAddress(establishment.getEid(), addressXml);
		return establishment;
	}

	public T createEstablishmentFromAddress(String name, String phoneNumber, String website, Address address,
			int type) {
		T establishment = create(name, phoneNumber, website, type);
		AddressAccess addressAccess = accessFactory.getAddressAccess();
		addressAccess.createAddress(establishment.getEid(), address.getStreet(), address.getNumber(),
				address.getLocality(), address.getPostalCode(), address.getLatitude(), address.getLongitude());
		return establishment;
	}

	public T editEstablishment(Long aid, Long oldEID, String name, String phoneNumber, String website, Address address,
			int type) {
		T establishment = createEstablishmentFromAddress(name, phoneNumber, website, address, type);
		EstablishmentModificationAccess modificationAccess = accessFactory.getEstablishmentModifiacationAccess();
		EstablishmentCreationAccess creationAccess = accessFactory.getEstablishmentCreationAccess();
		Long newEID = establishment.getEid();
		creationAccess.createEstablishmentCreation(newEID, aid);
		modificationAccess.createEstablishmentModification(oldEID, newEID, aid);
		return establishment;
	}

	public abstract T withEid(Long eid);

	public abstract T withEid(String eid);

}
