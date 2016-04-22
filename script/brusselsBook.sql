use brusselsbook;

create table Establishment(
	EID int() PRIMARY KEY,
	EName varchar(),
	Latitude double(),
	Longitude double(),
	PhoneNumber varchar(),
	Modified bool(),
	Website varchar()
)

create table Restaurant(
	EID int() ,
	PriceMinimum int(),
	PriceMaximum int(),
	HasTakeaway bool(),
	MakeDelivry bool(),
	HalfDaysOff array(),
	constraint fk_restauranteid           
        foreign key (EID)              
        references Establishment(EID)         
)

	
)

create table Bar(
	EID int(),
	CanSmoke bool(),
	MakeRestoration bool(),
	constraint fk_bareid           
        foreign key (EID)              
        references Establishment(EID)         

)

create table Hotel(
	EID int(),
	NoStars int(),
	NoRooms int(),
	PriceForTwo int()
	constraint fk_hoteleid           
        foreign key (EID)              
        references Establishment(EID)         

)

create table Adress(
	EID int(),
	Street varchar(),
	Number int (), 
	Locality varchar (),
	PostalCode double()
	constraint fk_adresseid           
        foreign key (EID)              
        references Establishment(EID)         

)

create table BookUser(
	UID int() primary key,
	EmailAdress varchar() unique,
	Username varchar() unique,
	Pwd varchar() ,#Password
	RegistrationDate varchar()
)

create table Administrator(
	AID int() primary key,
	UID int(),
	constraint fk_adminsitratorid           
        foreign key (UID)              
        references BookUser(EID)         

)

create table EstablishmentModification(
	OldEID int(),
	NewEID int(),
	AID int(),
	ModificationDate varchar()
	constraint fk_establishmentModificationOld           
        foreign key (oldEID)              
        references Establishment(EID)         
	constraint fk_establishmentModificationNew           
        foreign key (newEID)              
        references Establishment(EID)
	constraint fk_establishmentModificationAid           
        foreign key (AID)              
        references Administrator(AID)         
		


)

create table EstablishmentDeletion(
	EID int(),
	AID int(),
	DeletionDate varchar()
)

create table UserDeletion(
	EID int(),
	AID int(),
	DeletionDate varchar()
)

create table DescriberDeletion(
	DID int(),
	UID int(),
	DeletionDate varchar()
)

create table DescriberModification(
	OldDID int(),
	NewDID int(),
	UID int(),
	ModificationDate varchar()
)

create table BookComment(
	DID int(),
	UID int(),
	EID int(),
	CreationDate varchar(),
	Score int(),
	BookText string(),
	Modified bool()
)

create table Tag(
	DID int(),
	TagName varchar(),
	UID int(),
	CreationDate varchar(),
	Modified bool()
)

create table UserSignal(
	DID int(),
	SignalerUID int()
)

create table TagDescribe(
	TagName varchar(),
	EID int(),
	UID int()
)



	
	