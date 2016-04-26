
# Create a user too access to brusselsbook database
CREATE USER 'bbadmin'@'localhost' IDENTIFIED BY 'common';

# Create the database
CREATE DATABASE brusselsbook;

# Give access to the user
GRANT ALL PRIVILEGES ON `brusselsbook`.* TO 'bbadmin'@'localhost';

# Use the new created database
USE brusselsbook;

CREATE TABLE Establishment(
	EID INT NOT NULL AUTO_INCREMENT,
	EName VARCHAR(20) NOT NULL,
	Latitude FLOAT(10, 4) NOT NULL,
	Longitude FLOAT(10, 4) NOT NULL,
	PhoneNumber VARCHAR(20) NOT NULL,
	Modified TINYINT(1) DEFAULT 0,
	Website VARCHAR(50),
	PRIMARY KEY(EID)
);

CREATE TABLE Restaurant(
	EID INT,
	PriceMinimum INT NOT NULL,
	PriceMaximum INT NOT NULL,
	HasTakeaway TINYINT(1) NOT NULL,
	MakeDelivry TINYINT(1) NOT NULL,
	HalfDaysOff ARRAY(),
	FOREIGN KEY (EID) REFERENCES Establishment(EID)
);

CREATE TABLE Bar(
	EID INT,
	CanSmoke TINYINT(1) NOT NULL,
	MakeRestoration TINYINT(1) NOT NULL,
	FOREIGN KEY (EID) REFERENCES Establishment(EID)
);

CREATE TABLE Hotel(
	EID INT,
	NoStars SMALLINT NOT NULL,
	NoRooms SMALLINT NOT NULL,
	PriceForTwo FLOAT(10, 2) NOT NULL,
	FOREIGN KEY (EID) REFERENCES Establishment(EID)
);

CREATE TABLE Address(
	EID INT,
	Street VARCHAR(50) NOT NULL,
	StreetNumber SMALLINT NOT NULL, 
	Locality VARCHAR(50) NOT NULL,
	PostalCode SMALLINT NOT NULL,
	FOREIGN KEY (EID) REFERENCES Establishment(EID)
);

CREATE TABLE BookUser(
	UID INT NOT NULL AUTO_INCREMENT,
	EmailAdress VARCHAR(50) NOT NULL UNIQUE KEY,
	Username VARCHAR(15) NOT NULL UNIQUE KEY,
	Pwd VARCHAR(16) NOT NULL,
	RegistrationDate DATE NOT NULL, 
    PRIMARY KEY(UID)
);

CREATE TABLE Administrator(
	AID INT NOT NULL AUTO_INCREMENT,
	UID INT,
    PRIMARY KEY(AID),
	FOREIGN KEY (UID) REFERENCES BookUser(UID)
);

# Even if Describer is not in the relationnal model,
# we choose to create this table (less difficult to manage the modifications)
CREATE TABLE Describer(
	DID INT NOT NULL AUTO_INCREMENT,
    Modified TINYINT(1) DEFAULT 0,
    PRIMARY KEY(DID)
);

CREATE TABLE BookComment(
	DID INT,
    UID INT,
    EID INT,
	CreationDate DATETIME NOT NULL,
    Score TINYINT NOT NULL,
	BookText TEXT NOT NULL,
    UNIQUE KEY(DID, UID, CreationDate),
	FOREIGN KEY (DID) REFERENCES Describer(DID),
	FOREIGN KEY (UID) REFERENCES BookUser(UID),
    FOREIGN KEY (EID) REFERENCES Establishment(EID)
);

CREATE TABLE Tag(
	DID INT,
	TagName VARCHAR(20) NOT NULL UNIQUE KEY,
    UID INT,
    CreationDate DATE NOT NULL, 
	FOREIGN KEY (DID) REFERENCES Describer(DID),
	FOREIGN KEY (UID) REFERENCES BookUser(UID)
);

CREATE TABLE TagDescribe(
	TagName VARCHAR(20),
	EID INT,
	UID INT,
    FOREIGN KEY (TagName) REFERENCES Tag(TagName),
	FOREIGN KEY (EID) REFERENCES Establishment(EID),     
	FOREIGN KEY (UID) REFERENCES BookUser(UID)
);

CREATE TABLE UserSignal(
	DID INT,
	SignalerUID INT,
 	FOREIGN KEY (DID) REFERENCES Describer(DID),    
	FOREIGN KEY (SignalerUID) REFERENCES BookUser(UID)       
);

CREATE TABLE EstablishmentModification(
	OldEID INT,
	NewEID INT,
	AID INT,
	ModificationDate DATETIME NOT NULL,
	FOREIGN KEY (OldEID) REFERENCES Establishment(EID),
	FOREIGN KEY (NewEID) REFERENCES Establishment(EID),
	FOREIGN KEY (AID) REFERENCES Administrator(AID)
);

CREATE TABLE EstablishmentDeletion(
	EID INT,
	AID INT,
	DeletionDate DATETIME NOT NULL,
	FOREIGN KEY (EID) REFERENCES Establishment(EID),
	FOREIGN KEY (AID) REFERENCES Administrator(AID)
);

CREATE TABLE UserDeletion(
	UID INT,
	AID INT,
	DeletionDate DATETIME NOT NULL,
    FOREIGN KEY (EID) REFERENCES BookUser(UID),
	FOREIGN KEY (AID) REFERENCES Administrator(AID)
);

CREATE TABLE DescriberDeletion(
	DID INT,
	UID INT,
	DeletionDate DATETIME NOT NULL,
    FOREIGN KEY (DID) REFERENCES Describer(DID),
	FOREIGN KEY (UID) REFERENCES BookUser(UID)
);

CREATE TABLE DescriberModification(
	OldDID INT,
	NewDID INT,
	UID INT,
	ModificationDate DATETIME NOT NULL,
	FOREIGN KEY (OldDID) REFERENCES Describer(DID),
	FOREIGN KEY (NewDID) REFERENCES Describer(DID),
	FOREIGN KEY (UID) REFERENCES BookUser(UID)
);
	
