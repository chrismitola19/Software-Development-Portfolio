DROP DATABASE IF EXISTS baseball;

CREATE DATABASE baseball;

USE baseball;

CREATE TABLE `user` (
user_Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_Name VARCHAR(20) NOT NULL,
user_Password VARCHAR(20) NOT NULL,
first_Name VARCHAR(30) NOT NULL,
last_Name VARCHAR(30) NOT NULL,
address VARCHAR(30) NOT NULL,
phone VARCHAR(15) NOT NULL,
roles VARCHAR(20) NOT NULL,
`active` BOOLEAN NOT NULL
);


CREATE TABLE team (
teamid INT PRIMARY KEY auto_increment NOT NULL,
teamname VARCHAR(50) NOT NULL,
city VARCHAR(25),
stateabr VARCHAR(25)
);

CREATE TABLE player (
playerid INT PRIMARY KEY auto_increment NOT NULL,
teamid INT NOT NULL,
firstname VARCHAR(30) NOT NULL,
lastname VARCHAR(30) NOT NULL,
atbats INT,
`hits` INT,
runs INT,
singles INT,
doubles INT,
triples INT,
homeruns INT,
walks INT,
sacfly INT,
sacbunt INT,
stolenbase INT,
battingaverage DECIMAL(10,3), -- AS (hits / atbats),
onbasepercentage DECIMAL(10,3), -- AS ((hits + walks) / (atbats + walks + sacfly)),
sluggingpercentage DECIMAL(10,3),--  AS -- ((singles * 1 + doubles * 2 + triples * 3 + homeruns * 4) / atbats),
battingposition DECIMAL(10,3), -- AS (((hits / atbats) + ((hits + walks) / (atbats + walks + sacfly)) + 
-- ((singles * 1 + doubles * 2 + triples * 3 + homeruns * 4) / atbats)) / 3),
FOREIGN KEY player_ibfk_1(teamid)
REFERENCES team(teamid)
);

 UPDATE player
 SET battingaverage = hits/atbats;
 
 UPDATE player
 SET onbasepercentage = (hits+walks) / (atbats + walks + sacfly);
 
 UPDATE player
 SET sluggingpercentage = ((singles * 1 + doubles * 2 + triples * 3 + homeruns * 4) / atbats);
 
 UPDATE player
 SET battingposition = (((hits / atbats) + ((hits + walks) / (atbats + walks + sacfly)) + 
 ((singles * 1 + doubles * 2 + triples * 3 + homeruns * 4) / atbats)) / 3);



CREATE TABLE coach (
coachid INT PRIMARY KEY auto_increment NOT NULL,
teamid INT NOT NULL,
firstname VARCHAR(30) NOT NULL,
lastname VARCHAR(30) NOT NULL,
FOREIGN KEY coach_ibfk_1(teamid)
REFERENCES team(teamid)
);


INSERT INTO `user` (user_Name, user_Password, first_Name, last_Name, address, phone, roles, `active`) VALUES
("baseball", "yanks", "Chris", "Mitola", "111, Wind Rd", "222-222-2222", "admin", true);  

INSERT INTO team (teamid, teamname, city, stateabr) VALUES
(1, "Ducks", "Charlotte", "NC");



INSERT INTO player (
playerid,
teamid,
firstname,
lastname,
atbats,
`hits`,
runs,
singles,
doubles,
triples,
homeruns,
walks,
sacfly,
sacbunt,
stolenbase) VALUES
(1, 1, "Chris", "Mitola", 51, 21, 7, 10, 6, 2, 3, 5, 2, 2, 3),
(2, 1, "Skim", "Milk", 48, 15, 6, 6, 3, 3, 3, 5, 2, 2, 1),
(3, 1, "Jack", "Johnson", 48, 12, 8, 9, 3, 0, 0, 5, 2, 2, 3),
(4, 1, "Derek", "Jeter", 47, 19, 7, 9, 6, 2, 2, 5, 2, 2, 3),
(5, 1, "Max", "Telman", 46, 16, 5, 7, 6, 2, 1, 5, 2, 1, 3),
(6, 1, "Jimmy", "Butler", 50, 19, 4, 9, 6, 2, 3, 5, 2, 2, 3),
(7, 1, "Sam", "Samson", 45, 13, 4, 6, 5, 1, 1, 6, 2, 2, 3),
(8, 1, "Tim", "O'Riley", 47, 18, 1, 7, 6, 2, 3, 5, 2, 1, 2),
(9, 1, "Pablo", "Sanchez", 44, 14, 2, 8, 5, 1, 0, 5, 1, 2, 1),
(10, 1, "Sandy", "Trees", 44, 14, 2, 8, 5, 1, 0, 5, 1, 2, 1);  

SELECT 
playerid,
atbats,
hits,
firstname,
lastname,
battingaverage  -- coalesce returns 0 if null 
FROM player
GROUP BY playerid;

SELECT 
playerid,
atbats,
hits,
runs,
singles,
doubles,
triples,
homeruns,
firstname,
lastname,
sluggingpercentage
FROM player
GROUP BY playerid;

SELECT 
playerid,
atbats,
hits,
runs,
singles,
doubles,
triples,
homeruns,
walks,
sacfly,
firstname,
lastname,
onbasepercentage
FROM player
GROUP BY playerid;

SELECT *
FROM player
ORDER BY battingposition DESC;

SELECT *
FROM team;

SELECT *
FROM player;

SELECT * 
FROM `user`;
