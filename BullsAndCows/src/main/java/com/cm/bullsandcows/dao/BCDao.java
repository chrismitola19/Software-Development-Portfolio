
package com.cm.bullsandcows.dao;

import com.cm.bullsandcows.model.Game;
import com.cm.bullsandcows.model.Round;
import java.util.List;



/**
 *
 * @author chris
 */
public interface BCDao {
    
    public List<Game> getAllGames();
    
    public Game getGameById(int gameId);
    
    public List<Round> getGameRounds(int gameId);
    
    //to get a new game
    public int newGame(Game newGame);
    
    //to make a guess
    public Round guessNumber(Round currentRound, Game currentGame);
    
    public void deleteData();
}
