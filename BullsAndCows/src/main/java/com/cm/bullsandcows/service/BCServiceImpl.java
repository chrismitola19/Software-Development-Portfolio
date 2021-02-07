
package com.cm.bullsandcows.service;

import com.cm.bullsandcows.dao.BCDao;
import com.cm.bullsandcows.model.Game;
import com.cm.bullsandcows.model.Round;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author chris
 */

@ControllerAdvice
@Service
public class BCServiceImpl implements BCService {

    @Autowired
    private BCDao dao;
    
    @Override
    public List<Game> getAllGames() {
        
        return dao.getAllGames();
    }

    @Override
    public Game getGameById(int gameId) {
        return dao.getGameById(gameId);
    }

    @Override
    public List<Round> getGameRounds(int gameId) {
        return dao.getGameRounds(gameId);
    }

    @Override
    public int newGame(Game newGame) {
        return dao.newGame(newGame);
    }

    @Override
    public Round guessNumber(Round currentRound, Game currentGame) {

        Game checkGame = dao.getGameById(currentGame.getGameId());
        if (checkGame.getIsCorrect()){
            currentRound.setGuessNumber(null);   
        }
        
        if (currentRound.getGuessNumber().length() != 4) {
        
        
        } else {
           
          try{
            int i = Integer.parseInt(currentRound.getGuessNumber());
        } catch (NumberFormatException e) {
             
        }
        }
        
        return dao.guessNumber(currentRound, currentGame);
        
    }
    
    //exception messages for postman 
    private static final String CONSTRAINT_MESSAGE = "Could not save your guess."
            + " Make sure you provide an existing gameId and a correct 4 digit guess.";
   
    private static final String SOLVED_MESSAGE = "This game has been solved."; 
    
    private static final String DBNF_MESSAGE = "Database not found.";
   
    private static final String LEAD_ZERO_MESSAGE = "Enter guess as a String if leading with a zero.";
   
       
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public final ResponseEntity<com.cm.bullsandcows.service.Error> InvalidGameOrGuess(
            SQLSyntaxErrorException ex,
            WebRequest request) {

        com.cm.bullsandcows.service.Error err = new com.cm.bullsandcows.service.Error();
        err.setMessage(DBNF_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
   
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<com.cm.bullsandcows.service.Error> InvalidGameGuess(
            SQLIntegrityConstraintViolationException e,
            WebRequest request) {

        com.cm.bullsandcows.service.Error err = new com.cm.bullsandcows.service.Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }    
   
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<com.cm.bullsandcows.service.Error> LeadingZeroes(
            HttpMessageNotReadableException e,
            WebRequest request) {

        com.cm.bullsandcows.service.Error err = new com.cm.bullsandcows.service.Error();
        err.setMessage(LEAD_ZERO_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }        
   
    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<com.cm.bullsandcows.service.Error> GameOver(
            NullPointerException e,
            WebRequest request) {

        com.cm.bullsandcows.service.Error err = new com.cm.bullsandcows.service.Error();
        err.setMessage(SOLVED_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.OK);
    }            

    
    
    
}
