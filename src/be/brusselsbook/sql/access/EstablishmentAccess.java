package be.brusselsbook.sql.access;

import be.brusselsbook.parser.AddressXml;
import be.brusselsbook.parser.EstablishmentInfos;
import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.Establishment;

public abstract class EstablishmentAccess<T extends Establishment> extends DataAccess<T> {

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
		T establishment = create(name, phoneNumber, website);
		AddressAccess addressAccess = accessFactory.getAddressAccess();
		addressAccess.createAddress(establishment.getEid(), address.getStreet(), address.getNumber(),
				address.getLocality(), address.getPostalCode(), address.getLatitude(), address.getLongitude());
		return establishment;
	}

	
	
	public T editEstablishment(Long aid,Long oldEID, String name , String phoneNumber,String website,
			Address address,int type){
		T establishment = createEstablishmentFromAddress(name, phoneNumber, website, address, type);
		EstablishmentModificationAccess modificationAccess = accessFactory.getEstablishmentModifiacationAccess();
		EstablishmentCreationAccess creationAccess = accessFactory.getEstablishmentCreationAccess();
		Long newEID  = establishment.getEid();
		creationAccess.createEstablishmentCreation(newEID, aid);
		modificationAccess.createEstablishmentModification(oldEID, newEID, aid);
		return establishment;
		
		
	}
	
	
	
	public abstract T withEid(Long eid);

	public abstract T withEid(String eid);

}
