
package com.cm.bullsandcows.model;

import java.util.Objects;

/**
 *
 * @author chris
 */
public class Round {

    private int gameId;
    private int roundId;
    private int roundNumber;
    private String guessNumber;
    private int exact;
    private int partial;
    private String time;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public String getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(String guessNumber) {
        this.guessNumber = guessNumber;
    }

    public int getExact() {
        return exact;
    }

    public void setExact(int exact) {
        this.exact = exact;
    }

    public int getPartial() {
        return partial;
    }

    public void setPartial(int partial) {
        this.partial = partial;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.gameId;
        hash = 13 * hash + this.roundId;
        hash = 13 * hash + this.roundNumber;
        hash = 13 * hash + Objects.hashCode(this.guessNumber);
        hash = 13 * hash + this.exact;
        hash = 13 * hash + this.partial;
        hash = 13 * hash + Objects.hashCode(this.time);
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
        final Round other = (Round) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.roundNumber != other.roundNumber) {
            return false;
        }
        if (this.exact != other.exact) {
            return false;
        }
        if (this.partial != other.partial) {
            return false;
        }
        if (!Objects.equals(this.guessNumber, other.guessNumber)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

}
