CREATE TABLE IF NOT EXISTS Donations(
    Donations_ID INT AUTO_INCREMENT PRIMARY KEY,
    Charities_ID INT,
    Donated_Amount INT
);

INSERT INTO Donations(Charities_ID, Donated_Amount) VALUES(1, 20);
INSERT INTO Donations(Charities_ID, Donated_Amount) VALUES(2, 20);