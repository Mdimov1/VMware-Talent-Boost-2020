CREATE DATABASE networkofgiving;

CREATE TABLE IF NOT EXISTS Users(
    Users_ID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(100) NOT NULL,
    Name VARCHAR(100) NOT NULL,
    Age INT NOT NULL,
    Gender  VARCHAR(100) NULL,
    Location VARCHAR(100) NULL
);

CREATE TABLE IF NOT EXISTS Charities(
    Charities_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Thumbnail_Name VARCHAR(100) NOT NULL,
    Description TEXT NOT NULL,
    Donation_Required INT NULL,
    Participants_Required INT NULL,
    Author_ID INT
);

CREATE TABLE IF NOT EXISTS UsersCharities(
    UsersCharities_ID INT AUTO_INCREMENT PRIMARY KEY,
    Charities_ID INT,
    Users_ID INT
);

CREATE TABLE IF NOT EXISTS Donations(
    Donations_ID INT AUTO_INCREMENT PRIMARY KEY,
    Charities_ID INT,
    Donated_Amount INT
);

ALTER TABLE Charities
ADD FOREIGN KEY (Author_ID)
REFERENCES Users (Users_ID) 
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE UsersCharities
ADD FOREIGN KEY (Users_ID)
REFERENCES Users (Users_ID) 
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE UsersCharities
ADD FOREIGN KEY (Charities_ID)
REFERENCES Charities(Charities_ID)
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE Donations
ADD FOREIGN KEY (Charities_ID)
REFERENCES Charities (Charities_ID) 
ON UPDATE CASCADE
ON DELETE CASCADE;
