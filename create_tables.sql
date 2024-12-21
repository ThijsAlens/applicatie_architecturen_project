USE testdb;

DROP TABLE IF EXISTS biedingen;
DROP TABLE IF EXISTS klus;
DROP TABLE IF EXISTS klusjesman;
DROP TABLE IF EXISTS klant;
DROP TABLE IF EXISTS people;


CREATE TABLE people (
    USERNAME VARCHAR(50),
    PASSWORD VARCHAR(50) NOT NULL,
    VOORNAAM VARCHAR(50),
    ACHTERNAAM VARCHAR(50),
    PRIMARY KEY(USERNAME)
);

CREATE TABLE klant (
    KLANT_ID INT AUTO_INCREMENT NOT NULL,
    PEOPLE_ID  VARCHAR(50) NOT NULL,
    PRIMARY KEY (KLANT_ID),
    FOREIGN KEY (PEOPLE_ID) REFERENCES people(USERNAME)
);

CREATE TABLE klusjesman (
    KLUSJESMAN_ID INT AUTO_INCREMENT NOT NULL,
    PEOPLE_ID VARCHAR(50)  NOT NULL,
    PRIMARY KEY (KLUSJESMAN_ID),
    FOREIGN KEY (PEOPLE_ID) REFERENCES people(USERNAME)
);

CREATE TABLE klus (
    KLUS_ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    KLANT_ID INT NOT NULL,
    PRIJS INT NOT NULL,
    BESCHRIJVING VARCHAR(255),
    STATUS ENUM('BESCHIKBAAR', 'GEBODEN', 'TOEGEWEZEN', 'UITGEVOERD', 'BEOORDEELD') NOT NULL,
    KLUSJESMAN_ID INT,
    RATING INT,
    PRIMARY KEY (KLUS_ID),
    FOREIGN KEY (KLANT_ID) REFERENCES klant (KLANT_ID),
    FOREIGN KEY (KLUSJESMAN_ID) REFERENCES klusjesman (KLUSJESMAN_ID)
);

CREATE TABLE biedingen (
    KLUS_ID INT NOT NULL,
    KLUSJESMAN_ID INT NOT NULL,
    PRIMARY KEY (KLUS_ID, KLUSJESMAN_ID),
    FOREIGN KEY (KLUS_ID) REFERENCES klus(KLUS_ID),
    FOREIGN KEY (KLUSJESMAN_ID) REFERENCES klusjesman(KLUSJESMAN_ID)
);


INSERT INTO people (USERNAME, PASSWORD, VOORNAAM, ACHTERNAAM) VALUES ('stijn', 'cool', 'Stijn', 'Is cool');
INSERT INTO people (USERNAME, PASSWORD, VOORNAAM, ACHTERNAAM) VALUES ('thijs', 'leuk', 'Thijs', 'Is leuk');

INSERT INTO klant (PEOPLE_ID) VALUES ('thijs');
INSERT INTO klusjesman (PEOPLE_ID) VALUES ('stijn');

INSERT INTO klus (NAME, KLANT_ID, PRIJS, BESCHRIJVING, STATUS) 
VALUES ('knuffel komen geven', (SELECT KLANT_ID FROM klant WHERE PEOPLE_ID = 'thijs'), 0, 'ZEER DRINGEND!', 'GEBODEN');

INSERT INTO biedingen (KLUS_ID, KLUSJESMAN_ID)
VALUES ((SELECT KLUS_ID FROM klus WHERE NAME = 'knuffel komen geven'),
        (SELECT KLUSJESMAN_ID FROM klusjesman WHERE PEOPLE_ID = 'stijn'));
