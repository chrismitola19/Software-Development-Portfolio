DROP DATABASE IF EXISTS baseballTest;

CREATE DATABASE baseballTest;

USE baseballTest;

CREATE TABLE team (
teamid INT PRIMARY KEY auto_increment NOT NULL,
teamname VARCHAR(50) NOT NULL,
city VARCHAR(20),
stateabr VARCHAR(2)
);

CREATE TABLE player (
playerid INT PRIMARY KEY auto_increment NOT NULL,
teamid INT NOT NULL,
firstname VARCHAR(30) NOT NULL,
lastname VARCHAR(30) NOT NULL,
atbats INT,
hits INT,
runs INT,
singles INT,
doubles INT,
triples INT,
homeruns INT,
walks INT,
sacfly INT,
sacbunt INT,
stolenbase INT,
battingaverage DECIMAL(4,3), -- AS (hits / atbats),
onbasepercentage DECIMAL(4,3), -- AS ((hits + walks) / (atbats + walks + sacfly)),
sluggingpercentage DECIMAL(4,3), -- AS ((singles * 1 + doubles * 2 + triples * 3 + homeruns * 4) / atbats),
battingposition DECIMAL(4,3),
FOREIGN KEY player_ibfk_1(teamid)
REFERENCES team(teamid)
);

CREATE TABLE coach (
coachid INT PRIMARY KEY auto_increment NOT NULL,
teamid INT NOT NULL,
firstname VARCHAR(30) NOT NULL,
lastname VARCHAR(30) NOT NULL,
FOREIGN KEY coach_ibfk_1(teamid)
REFERENCES team(teamid)
);


INSERT INTO team (teamid, teamname, city, stateabr) VALUES
(1, "Ducks", "Charlotte", "NC");

INSERT INTO coach (coachid, teamid, firstname, lastname ) VALUES
(1, 1, "John", "Kick");

INSERT INTO player (
playerid,
teamid,
firstname,
lastname,
atbats,
hits,
runs,
singles,
doubles,
triples,
homeruns,
walks,
sacfly,
sacbunt,
stolenbase,
battingaverage,
onbasepercentage,
sluggingpercentage,
battingposition) VALUES
(1, 1, "Chris", "Mitola", 50, 20, 7, 9, 6, 2, 3, 5, 2, 2, 3, 0.023, 0.222, 0.343, 0.234);
