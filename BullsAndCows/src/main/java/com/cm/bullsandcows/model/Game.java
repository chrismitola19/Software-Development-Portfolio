
package com.cm.bullsandcows.model;

import java.util.Objects;

/**
 *
 * @author chris
 */
public class Game {
    private int gameId;
    private String answerNumber;
    private boolean isCorrect;
    private int gameRounds;
    
    public int getGameId() {
        return gameId;
    }
    
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    public String getAnswerNumber() {
        return answerNumber;
    }
    
    public void setAnswerNumber(String answerNumber) {
        this.answerNumber = answerNumber;
    }
    
    public boolean getIsCorrect() {
        return isCorrect;
    }
    
    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    public int getGameRounds() {
        return gameRounds;
    }
    
    public void setGameRounds(int gameRounds) {
        this.gameRounds = gameRounds;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.gameId;
        hash = 53 * hash + Objects.hashCode(this.answerNumber);
        hash = 53 * hash + (this.isCorrect ? 1 : 0);
        hash = 53 * hash + this.gameRounds;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.isCorrect != other.isCorrect) {
            return false;
        }
        if (this.gameRounds != other.gameRounds) {
            return false;
        }
        if (!Objects.equals(this.answerNumber, other.answerNumber)) {
            return false;
        }
        return true;
    }

   
    }
