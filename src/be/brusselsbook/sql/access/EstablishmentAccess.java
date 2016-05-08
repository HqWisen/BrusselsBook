package be.brusselsbook.sql.access;



import be.brusselsbook.sql.data.Address;
import be.brusselsbook.parser.AddressXml;
import be.brusselsbook.parser.EstablishmentInfos;
import be.brusselsbook.sql.data.Establishment;

public abstract class EstablishmentAccess<T extends Establishment> extends DataAccess<T> {

	protected EstablishmentAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public T createEstablishment(Long aid, EstablishmentInfos infos){
		EstablishmentCreationAccess creationAccess = accessFactory.getEstablishmentCreationAccess();
		T establishment = createEstablishment(infos);
		creationAccess.createEstablishmentCreation(establishment.getEid(), aid);
		return establishment;
	}
	
	public T createEstablishmentForNoXml(Long aid, String name, String phoneNumber, String website, 
			Address address){
		EstablishmentCreationAccess creationAccess = accessFactory.getEstablishmentCreationAccess();
		T establishment = createEstablishmentFromAddress(name,  phoneNumber,  website,address);
		creationAccess.createEstablishmentCreation(establishment.getEid(), aid);
		return establishment;
	}

	
	
	
	
	public T createEstablishment(EstablishmentInfos infos){
		return createEstablishment(infos.getName(), infos.getTel(), infos.getSiteLink(), infos.getAddress());
	}
	
	public T createEstablishment(String name, String phoneNumber, String website, AddressXml addressXml){
		T establishment = create(name, phoneNumber, website);
		AddressAccess addressAccess = accessFactory.getAddressAccess();
		addressAccess.createAddress(establishment.getEid(), addressXml);
		return establishment;
	}
	
	public T createEstablishmentFromAddress(String name, String phoneNumber, String website, Address address){
		T establishment = create(name, phoneNumber, website);
		AddressAccess addressAccess = accessFactory.getAddressAccess();
		addressAccess.createAddress(establishment.getEid(), address.getStreet(),address.getNumber(),
				address.getLocality(),address.getPostalCode(),address.getLatitude(),address.getLongitude());
		return establishment;
	}

	public abstract T withEid(Long eid);

	public abstract T withEid(String eid);
	
	
}
