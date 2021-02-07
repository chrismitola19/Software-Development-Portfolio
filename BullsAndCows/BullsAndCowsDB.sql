DROP DATABASE IF EXISTS bullsandcowsDB;

CREATE DATABASE bullsandcowsDB;

USE bullsandcowsDB;

CREATE TABLE Games (
gameId INT PRIMARY KEY AUTO_INCREMENT,
isCorrect BOOLEAN NOT NULL DEFAULT 0,
answerNumber VARCHAR(4) NOT NULL
);

CREATE TABLE Rounds (
roundId INT PRIMARY KEY AUTO_INCREMENT,
roundNumber INT NOT NULL,
guessNumber VARCHAR(4) NOT NULL,
gameId INT NOT NULL,
exactGuess INT,
partialGuess INT,
`time` TIME,
FOREIGN KEY fk_rounds_gameId(gameId) REFERENCES Games(gameId));

INSERT INTO Games(isCorrect, answerNumber) VALUES
(0, 3146);

INSERT INTO Rounds(roundNumber, guessNumber, gameId, exactGuess, partialGuess, `time`) VALUES
(1, 3614, 1, 1, 3, '12:22:05'),
(2, 3164, 1, 2, 2, '12:23:22');
use bullsandcowsDB;
SELECT *
FROM Games;

SELECT 
g.*,
COUNT(r.roundNumber)  NumberOfRounds
FROM Games g
INNER JOIN Rounds r ON g.gameID = r.gameId
GROUP BY g.gameId;