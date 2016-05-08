package be.brusselsbook.sql.access;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.brusselsbook.parser.AddressXml;
import be.brusselsbook.parser.EstablishmentInfos;
import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.BookComment;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.sql.data.EstablishmentType;

public abstract class EstablishmentAccess<T extends Establishment> extends DataAccess<T> {

	public static Map<Long, Address> getAddressFor(List<Establishment> establishments){
		Map<Long, Address> map = new HashMap<>();
		for(Establishment establishment : establishments){
			Long eid = establishment.getEid();
			map.put(eid, getAddressFor(eid));
		}
		return map;
	}
	
	public static Map<Long, Integer> getNumberOfCommentsFor(List<Establishment> establishments){
		Map<Long, Integer> map = new HashMap<>();
		for(Establishment establishment : establishments){
			Long eid = establishment.getEid();
			map.put(eid, getNumberOfCommentsFor(eid));
		}
		return map;		
	}
	
	private static Integer getNumberOfCommentsFor(Long eid) {
		BookCommentAccess bookCommentAccess = AccessFactory.getInstance().getBookCommentAccess();
		List<BookComment> comments = bookCommentAccess.withEid(eid); 
		return comments.size();
}

	public static Address getAddressFor(Long eid) {
		return AccessFactory.getInstance().getAddressAccess().withEid(eid);
	}

	public static Address getAddresFor(Establishment establishment){
		return getAddressFor(establishment.getEid());
	}
	
	public static Map<Long, Integer> getAverageScoresFor(List<Establishment> establishments) {
		Map<Long, Integer> map = new HashMap<>();
		for(Establishment establishment : establishments){
			Long eid = establishment.getEid();
			map.put(eid, getAverageScoreFor(eid));
		}
		return map;				
	}

	
	private static Integer getAverageScoreFor(Long eid) {
		BookCommentAccess bookCommentAccess = AccessFactory.getInstance().getBookCommentAccess();
		List<BookComment> comments = bookCommentAccess.withEid(eid); 
		int total = 0;
		for(BookComment comment : comments){
			total += comment.getScore();
		}
		return comments.isEmpty() ? 0 : (int)Math.ceil(total / comments.size());
	}

	protected EstablishmentAccess(AccessFactory accessFactory) {
		super(accessFactory);
	}

	public T createEstablishment(Long aid, EstablishmentInfos infos, int type){
		EstablishmentCreationAccess creationAccess = accessFactory.getEstablishmentCreationAccess();
		T establishment = createEstablishment(infos,type);
		creationAccess.createEstablishmentCreation(establishment.getEid(), aid);
		return establishment;
	}
	
	public T createEstablishmentForNoXml(Long aid, String name, String phoneNumber, String website, 
			Address address, int type){
		EstablishmentCreationAccess creationAccess = accessFactory.getEstablishmentCreationAccess();
		T establishment = createEstablishmentFromAddress(name,  phoneNumber,  website,address,type);
		creationAccess.createEstablishmentCreation(establishment.getEid(), aid);
		return establishment;
	}

	
	
	
	
	public T createEstablishment(EstablishmentInfos infos, int type){
		return createEstablishment(infos.getName(), infos.getTel(), infos.getSiteLink(), infos.getAddress(),type);
	}
	
	public T createEstablishment(String name, String phoneNumber, String website, AddressXml addressXml,
			int type){
		T establishment = create(name, phoneNumber, website,type);
		AddressAccess addressAccess = accessFactory.getAddressAccess();
		addressAccess.createAddress(establishment.getEid(), addressXml);
		return establishment;
	}
	
	public T createEstablishmentFromAddress(String name, String phoneNumber, String website, Address address
			, int type){
		T establishment = create(name, phoneNumber, website);
		AddressAccess addressAccess = accessFactory.getAddressAccess();
		addressAccess.createAddress(establishment.getEid(), address.getStreet(),address.getNumber(),
				address.getLocality(),address.getPostalCode(),address.getLatitude(),address.getLongitude());
		return establishment;
	}

	public abstract T withEid(Long eid);

	public abstract T withEid(String eid);
	
	
}
