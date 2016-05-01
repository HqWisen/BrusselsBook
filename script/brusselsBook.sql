
# Create a user too access to brusselsbook database
CREATE USER 'bbadmin'@'localhost' IDENTIFIED BY 'common';

# Create the database
CREATE DATABASE brusselsbook;

# Give access to the user
GRANT ALL PRIVILEGES ON `brusselsbook`.* TO 'bbadmin'@'localhost';

# Use the new created database
USE brusselsbook;

# FIXME make cascade delete (example: delete Adminstrator should delete also the BookUser)

# Latitude and Longitude has been put in Address (indentation problem in the model)
CREATE TABLE Establishment(
	EID INT UNSIGNED NOT NULL AUTO_INCREMENT,
	EName VARCHAR(20) NOT NULL,
	PhoneNumber VARCHAR(20) NOT NULL,
	Modified TINYINT(1) DEFAULT 0,
	Website VARCHAR(50),
	PRIMARY KEY(EID)
);

CREATE TABLE Restaurant(
	EID INT UNSIGNED NOT NULL,
	PriceMinimum INT UNSIGNED NOT NULL,
	PriceMaximum INT UNSIGNED NOT NULL,
	HasTakeaway TINYINT(1) NOT NULL,
	MakeDelivry TINYINT(1) NOT NULL,
	# String format : XXXXXXXXXXXXXX where X can be F(alse) or T(rue)
    # To know if the restau. is open in the half day
    HalfDaysOff VARCHAR(14) NOT NULL,
	FOREIGN KEY (EID) REFERENCES Establishment(EID)
);

CREATE TABLE Bar(
	EID INT UNSIGNED NOT NULL,
	CanSmoke TINYINT(1) NOT NULL,
	MakeRestoration TINYINT(1) NOT NULL,
	FOREIGN KEY (EID) REFERENCES Establishment(EID)
);

CREATE TABLE Hotel(
	EID INT UNSIGNED NOT NULL,
	NoStars SMALLINT UNSIGNED NOT NULL,
	NoRooms SMALLINT UNSIGNED NOT NULL,
	PriceForTwo FLOAT(10, 2) NOT NULL,
	FOREIGN KEY (EID) REFERENCES Establishment(EID)
);

CREATE TABLE Address(
	EID INT UNSIGNED NOT NULL,
	Street VARCHAR(50) NOT NULL,
	StreetNumber MEDIUMINT UNSIGNED NOT NULL, 
	Locality VARCHAR(50) NOT NULL,
	PostalCode SMALLINT UNSIGNED NOT NULL,
	Latitude FLOAT(10, 4) NOT NULL,
	Longitude FLOAT(10, 4) NOT NULL,
	FOREIGN KEY (EID) REFERENCES Establishment(EID)
);

CREATE TABLE BookUser(
	UID INT UNSIGNED NOT NULL AUTO_INCREMENT,
	EmailAddress VARCHAR(50) NOT NULL UNIQUE KEY,
	Username VARCHAR(15) NOT NULL UNIQUE KEY,
	Pwd VARCHAR(16) NOT NULL,
	RegistrationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(UID)
);

CREATE TABLE Administrator(
	AID INT UNSIGNED NOT NULL AUTO_INCREMENT,
	UID INT UNSIGNED NOT NULL,
    PRIMARY KEY(AID),
	FOREIGN KEY (UID) REFERENCES BookUser(UID)
);

# Even if Describer is not in the relationnal model,
# we choose to create this table (less difficult to manage the modifications)
CREATE TABLE Describer(
	DID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    Modified TINYINT(1) DEFAULT 0,
    PRIMARY KEY(DID)
);

CREATE TABLE BookComment(
	DID INT UNSIGNED NOT NULL,
    UID INT UNSIGNED NOT NULL,
    EID INT UNSIGNED NOT NULL,
	CreationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    Score TINYINT UNSIGNED NOT NULL,
	BookText TEXT NOT NULL,
    UNIQUE KEY(DID, UID, CreationDate),
	FOREIGN KEY (DID) REFERENCES Describer(DID),
	FOREIGN KEY (UID) REFERENCES BookUser(UID),
    FOREIGN KEY (EID) REFERENCES Establishment(EID)
);

CREATE TABLE Tag(
	DID INT UNSIGNED NOT NULL,
	TagName VARCHAR(20) NOT NULL UNIQUE KEY,
    UID INT UNSIGNED NOT NULL,
    CreationDate DATETIME DEFAULT CURRENT_TIMESTAMP, 
	FOREIGN KEY (DID) REFERENCES Describer(DID),
	FOREIGN KEY (UID) REFERENCES BookUser(UID)
);

CREATE TABLE TagDescribe(
	TagName VARCHAR(20),
	EID INT UNSIGNED NOT NULL,
	UID INT UNSIGNED NOT NULL,
    FOREIGN KEY (TagName) REFERENCES Tag(TagName),
	FOREIGN KEY (EID) REFERENCES Establishment(EID),     
	FOREIGN KEY (UID) REFERENCES BookUser(UID)
);

CREATE TABLE UserSignal(
	DID INT UNSIGNED NOT NULL,
	SignalerUID INT UNSIGNED NOT NULL,
 	FOREIGN KEY (DID) REFERENCES Describer(DID),    
	FOREIGN KEY (SignalerUID) REFERENCES BookUser(UID)       
);

CREATE TABLE EstablishmentModification(
	OldEID INT UNSIGNED NOT NULL,
	NewEID INT UNSIGNED NOT NULL,
	AID INT UNSIGNED NOT NULL,
	ModificationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (OldEID) REFERENCES Establishment(EID),
	FOREIGN KEY (NewEID) REFERENCES Establishment(EID),
	FOREIGN KEY (AID) REFERENCES Administrator(AID)
);

CREATE TABLE EstablishmentDeletion(
	EID INT UNSIGNED NOT NULL,
	AID INT UNSIGNED NOT NULL,
	DeletionDate DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (EID) REFERENCES Establishment(EID),
	FOREIGN KEY (AID) REFERENCES Administrator(AID)
);

CREATE TABLE UserDeletion(
	UID INT UNSIGNED NOT NULL,
	AID INT UNSIGNED NOT NULL,
	DeletionDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UID) REFERENCES BookUser(UID),
	FOREIGN KEY (AID) REFERENCES Administrator(AID)
);

CREATE TABLE DescriberDeletion(
	DID INT UNSIGNED NOT NULL,
	UID INT UNSIGNED NOT NULL,
	DeletionDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (DID) REFERENCES Describer(DID),
	FOREIGN KEY (UID) REFERENCES BookUser(UID)
);

CREATE TABLE DescriberModification(
	OldDID INT UNSIGNED NOT NULL,
	NewDID INT UNSIGNED NOT NULL,
	UID INT UNSIGNED NOT NULL,
	ModificationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (OldDID) REFERENCES Describer(DID),
	FOREIGN KEY (NewDID) REFERENCES Describer(DID),
	FOREIGN KEY (UID) REFERENCES BookUser(UID)
);
	
