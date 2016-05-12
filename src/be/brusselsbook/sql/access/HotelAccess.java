package be.brusselsbook.sql.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.brusselsbook.sql.data.Address;
import be.brusselsbook.sql.data.Establishment;
import be.brusselsbook.sql.data.EstablishmentType;
import be.brusselsbook.sql.data.Hotel;
import be.brusselsbook.utils.BrusselsBookUtils;

public class HotelAccess extends EstablishmentAccess<Hotel> {

	private static final String EID = "EID";
	private static final String NOSTARS = "NoStars";
	private static final String NOROOMS = "NoRooms";
	private static final String PRICEFORTWO = "PriceForTwo";

	private EstablishmentAccess<Establishment> establishmentAccess;
	
	private static final String[] PARAMETERS = BrusselsBookUtils.createArrayFrom(EID, NOSTARS, NOROOMS,
			PRICEFORTWO);
	private static final String TABLE = "Hotel";

	
	
	protected HotelAccess(AccessFactory accessFactory) {
		super(accessFactory);
		this.establishmentAccess = accessFactory.getEstablishmentAccess();
	}

	@Override
	public Hotel withId(Long id) {
		return withEid(id);
	}

	@Override
	public Hotel withEid(Long eid) {
		 return withEid(eid.toString());
	}

	@Override
	public Hotel withEid(String eid) {
		return  withQuery(SELECTBY(EID), eid);
	}

	@Override
	protected Hotel map(ResultSet resultSet) throws SQLException {
		Long eid = resultSet.getLong(EID);
		Establishment establishment = establishmentAccess.withEid(eid);
		Hotel hotel = new Hotel(establishment);
		hotel.setNoStars(resultSet.getInt(NOSTARS));
		hotel.setNoRooms(resultSet.getInt(NOROOMS));
		hotel.setPriceForTwo(resultSet.getFloat(PRICEFORTWO));	
		return hotel;
	}

	public Hotel createHotel(String name , String tel ,String site , Address address,
			Integer noStars,Integer noRooms,Float priceForTwo){
		Establishment establishment = establishmentAccess.createEstablishmentFromAddress(name,tel,site,address,
				EstablishmentType.HOTEL.getId());		
		return createHotel (establishment.getEid(), noStars, noRooms, priceForTwo);
	}
	

	
	
	public Hotel createHotel (Long eid,Integer noStars,Integer noRooms,Float priceForTwo){	
		return createNoGeneratedId(eid, eid,noStars,noRooms,priceForTwo);
	}	
	

	public Hotel createHotelForAdmin(Long aid,String name , String tel ,String site , Address address,
			Integer noStars,Integer noRooms,Float priceForTwo){
		Establishment establishment = establishmentAccess.createEstablishmentForNoXml(aid,name,tel,site,address,
				EstablishmentType.HOTEL.getId());
		return createHotel (establishment.getEid(),noStars,noRooms,priceForTwo);
	}
	
	public Hotel editHotel(Long aid, Long oldEID, String name, String tel, String site, Address address,
			int type,Integer noStars,Integer noRooms,Float priceForTwo){
		Establishment establishment = establishmentAccess.editEstablishment(aid, oldEID, name, tel,
				site, address, type);
		return createHotel (establishment.getEid(),noStars,noRooms,priceForTwo);
		
	}
	
	@Override
	protected String getTable() {
		return TABLE;
	}

	@Override
	protected String[] getCreationParameters() {
		return PARAMETERS;
	}

	@Override
	protected int getNumberOfCreationParameters() {
		return PARAMETERS.length;
	}

	@Override
	public void updateModified(Boolean modified, Long eid) {
		establishmentAccess.updateModified(modified, eid);
	}

}
