
package com.cm.bullsandcows.service;

import com.cm.bullsandcows.model.Game;
import com.cm.bullsandcows.model.Round;
import java.util.List;

/**
 *
 * @author chris
 */
public interface BCService {
    
    public List<Game> getAllGames();
    
    public Game getGameById(int gameId);
    
    public List<Round> getGameRounds(int gameId);
    
    public int newGame(Game newGame);
    
    public Round guessNumber(Round currentRound, Game currentGame);
    
}
