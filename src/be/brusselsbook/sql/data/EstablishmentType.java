package be.brusselsbook.sql.data;

public enum EstablishmentType{
    HOTEL(0), RESTAURANT(1), CAFE(2);
   
    private int id;
   
    EstablishmentType(int id){
        this.id = id;  
    }
   
    public int getId(){
        return id;
    }

	public static EstablishmentType parseType(int typeId) {

		for (EstablishmentType  type : EstablishmentType.values()){
			if (type.getId() == typeId){
				return type;
			}
		}
		return null;
	}
}	
