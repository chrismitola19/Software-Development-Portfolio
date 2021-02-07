DROP DATABASE IF EXISTS bullsandcowsDBtest;

CREATE DATABASE bullsandcowsDBtest;

USE bullsandcowsDBtest;

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
FOREIGN KEY fk_Rounds_gameId(gameId) REFERENCES Games(gameId));