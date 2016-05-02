package be.brusselsbook.sql.access;

import be.brusselsbook.data.Establishment;
import be.brusselsbook.parser.AddressXml;
import be.brusselsbook.parser.EstablishmentInfos;

public abstract class EstablishmentAccess<T extends Establishment> extends DataAccess<T> {

	protected EstablishmentAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public T createEstablishement(EstablishmentInfos infos){
		return createEstablishment(infos.getName(), infos.getTel(), infos.getSiteLink(), infos.getAddress());
	}
	
	public T createEstablishment(String name, String phoneNumber, String website, AddressXml addressXml){
		T establishment = create(name, phoneNumber, website);
		AddressAccess addressAccess = accessFactory.getAddressAccess();
		addressAccess.createAddress(establishment.getEid(), addressXml);
		return establishment;
	}
	
	public abstract T withEid(Long eid);

	public abstract T withEid(String eid);

}
