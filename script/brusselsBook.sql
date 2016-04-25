use brusselsbook;

create table Establishment(
	EID int(),
	EName varchar(),
	Latitude double(),
	Longitude double(),
	PhoneNumber varchar(),
	Modified bool(),
	Website varchar(), #NULL
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
	RegistrationDate date(), #varchar  , peut importe son heure d'enrigstration 
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
	ModificationDate datetime(),# varchar  , le temps est important ; on peut éviter les secondes 
	foreign key (OldEID) references Establishment(EID),        
	foreign key (NewEID) references Establishment(EID),
	foreign key (AID) references Administrator(AID)         
)

create table EstablishmentDeletion(
	EID int(),
	AID int(),
	DeletionDate datetime(),#varchar, on pourrait éviter un jeu ou un suprrime et l'autre ajoute , laisser un laps de temps ....
	foreign key (EID) references Establishment(EID),
	foreign key (AID) references Administrator(AID)         

)

create table UserDeletion(
	EID int(),
	AID int(),
	DeletionDate datetime()	#varchar
    foreign key (EID) references Establishment(EID),
	foreign key (AID) references Administrator(AID)     
)

create table DescriberDeletion(
	DID int(),
	UID int(),
	DeletionDate datetime(),
    foreign key (DID) references Establishment(EID),
	foreign key (AID) references Administrator(AID)   
)

create table DescriberModification(
	OldDID int(),
	NewDID int(),
	UID int(),
	ModificationDate datetime()
	foreign key (EID) references Establishment(EID),       
	foreign key (AID) references Administrator(AID)    
)

create table BookComment(
	DID int(),
	UID int(),
	EID int(),
	CreationDate datetime(),
	Score int(),
	BookText text(), #string , ne pas limiter la taille du text .
	Modified bool(),
    primary key(DID),
	foreign key (UID) references BookUser(UID),      
	foreign key (EID) references Establishment(EID)    
)

create table Tag(
	DID int(),
	TagName varchar() unique,
	UID int(),
	CreationDate date(),#pas besoin du time 
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



	
	