
package com.cm.bullsandcows.dao;

import com.cm.bullsandcows.TestApplicationConfiguration;
import com.cm.bullsandcows.model.Game;
import com.cm.bullsandcows.model.Round;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author chris
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class BCDBDaoImplTest {
    @Autowired
    BCDao dao;
    
   //deletes all existing data so tests can run properly 
    @Before
    public void setUp() {
        dao.deleteData();
    }
    
    /**
     * Test of newGame method, of class GuessNumberDBDaoImpl.
     */
    @Test
    public void testNewGameGetGame() {
        Game game = new Game();
        game.setAnswerNumber("5632");
        game.setGameId(1);
        game.setGameRounds(0);
        game.setIsCorrect(false);
        dao.newGame(game);
        
        Game fromDao = dao.getGameById(game.getGameId());
        
        assertEquals(game, fromDao);
    }
    
    /**
     * Test of getAllGames method, of class GuessNumberDBDaoImpl.
     */
    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setAnswerNumber("7890");
        game.setGameId(1);
        game.setGameRounds(0);
        game.setIsCorrect(false);
        dao.newGame(game);

        Game game2 = new Game();
        game2.setAnswerNumber("4532");
        game2.setGameId(2);
        game2.setGameRounds(0);
        game2.setIsCorrect(false);
        dao.newGame(game2);        
        
        //lists the two games added above
        List<Game> games = dao.getAllGames();
        
        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    /**
     * Test of getGameRounds method, of class GuessNumberDBDaoImpl.
     */
    @Test
    public void testAddGetRounds() {
        Game game = new Game();
        game.setAnswerNumber("6789");
        game.setGameId(1);
        game.setGameRounds(0);
        game.setIsCorrect(false);
        dao.newGame(game);        
        
        Round round1 = new Round();
        round1.setExact(0);
        round1.setPartial(0);
        round1.setGameId(game.getGameId());
        round1.setGuessNumber("1234");
        round1.setRoundId(1);
        round1.setRoundNumber(1);
        round1.setTime("10:15:10");
        dao.guessNumber(round1, game);
        
        Round round2 = new Round();
        round2.setExact(0);
        round2.setPartial(0);
        round2.setGameId(game.getGameId());
        round2.setGuessNumber("1456");
        round2.setRoundId(2);
        round2.setRoundNumber(2);
        round2.setTime("10:20:05");
        dao.guessNumber(round2, game);
        
        Game fromDao = dao.getGameById(game.getGameId());
        List<Round> roundsFromDao = dao.getGameRounds(fromDao.getGameId());
        
        assertEquals(2, roundsFromDao.size());
    }

    /**
     * Test of finishGame method, of class GuessNumberDBDaoImpl.
     */
    @Test
    public void testDeleteData() {
        Game game = new Game();
        game.setAnswerNumber("5632");
        game.setGameId(1);
        game.setGameRounds(0);
        game.setIsCorrect(false);
        dao.newGame(game);        
        
        Round round1 = new Round();
        round1.setExact(0);
        round1.setPartial(0);
        round1.setGameId(game.getGameId());
        round1.setGuessNumber("3454");
        round1.setRoundId(1);
        round1.setRoundNumber(1);
        round1.setTime("12:01:30");
        dao.guessNumber(round1, game);
        
        Round round2 = new Round();
        round2.setExact(0);
        round2.setPartial(0);
        round2.setGameId(game.getGameId());
        round2.setGuessNumber("1278");
        round2.setRoundId(2);
        round2.setRoundNumber(2);
        round2.setTime("10:10:10");
        dao.guessNumber(round2, game);

        dao.deleteData();
        
        List<Game> gamesFromDao = dao.getAllGames();
        List<Round> roundsFromDao1 = dao.getGameRounds(1);
        List<Round> roundsFromDao2 = dao.getGameRounds(2);
        
        assertEquals(0, gamesFromDao.size());
        assertEquals(0, roundsFromDao1.size());
        assertEquals(0, roundsFromDao2.size());
    }
    
}
