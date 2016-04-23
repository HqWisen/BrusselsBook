use brusselsbook;

create table Establishment(
	EID int(),
	EName varchar(),
	Latitude double(),
	Longitude double(),
	PhoneNumber varchar(),
	Modified bool(),
	Website varchar(),
    primary key(EID)
)

create table Restaurant(
	EID int() ,
	PriceMinimum int(),
	PriceMaximum int(),
	HasTakeaway bool(),
	MakeDelivry bool(),
	HalfDaysOff array(),
	foreign key (EID) references Establishment(EID)         
)

	
)

create table Bar(
	EID int(),
	CanSmoke bool(),
	MakeRestoration bool(),
	foreign key (EID) references Establishment(EID)         

)

create table Hotel(
	EID int(),
	NoStars int(),
	NoRooms int(),
	PriceForTwo int(),
	foreign key (EID) references Establishment(EID)         

)

create table Address(
	EID int(),
	Street varchar(),
	StreetNumber int (), 
	Locality varchar (),
	PostalCode double()          
	foreign key (EID) references Establishment(EID)         

)

create table BookUser(
	UID int(),
	EmailAdress varchar() unique,
	Username varchar() unique,
	Pwd varchar(),
	RegistrationDate varchar(),
    primary key(UID)
)

create table Administrator(
	AID int(),
	UID int(),
    primary key(AID),
	foreign key (UID) references BookUser(UID)         

)

create table EstablishmentModification(
	OldEID int(),
	NewEID int(),
	AID int(),
	ModificationDate varchar(),
	foreign key (OldEID) references Establishment(EID),        
	foreign key (NewEID) references Establishment(EID),
	foreign key (AID) references Administrator(AID)         
)

create table EstablishmentDeletion(
	EID int(),
	AID int(),
	DeletionDate varchar(),
	foreign key (EID) references Establishment(EID),
	foreign key (AID) references Administrator(AID)         

)

create table UserDeletion(
	EID int(),
	AID int(),
	DeletionDate varchar()	
    foreign key (EID) references Establishment(EID),
	foreign key (AID) references Administrator(AID)     
)

create table DescriberDeletion(
	DID int(),
	UID int(),
	DeletionDate varchar(),
    foreign key (DID) references Establishment(EID),
	foreign key (AID) references Administrator(AID)   
)

create table DescriberModification(
	OldDID int(),
	NewDID int(),
	UID int(),
	ModificationDate varchar()
	foreign key (EID) references Establishment(EID),       
	foreign key (AID) references Administrator(AID)    
)

create table BookComment(
	DID int(),
	UID int(),
	EID int(),
	CreationDate varchar(),
	Score int(),
	BookText string(),
	Modified bool(),
    primary key(DID),
	foreign key (UID) references BookUser(UID),      
	foreign key (EID) references Establishment(EID)    
)

create table Tag(
	DID int(),
	TagName varchar() unique,
	UID int(),
	CreationDate varchar(),
	Modified bool(),
    primary key(DID),
	foreign key (UID) references BookUser(UID)     
)

create table UserSignal(
	DID int(),
	SignalerUID int()
 	foreign key (DID) references BookComment(DID),    
	foreign key (SignalerUID) references Administrator(AID)       
)

create table TagDescribe(
	TagName varchar(),
	EID int(),
	UID int()
	foreign key (EID) references Establishment(EID),     
	foreign key (AID) references Administrator(AID)    
)



	
	