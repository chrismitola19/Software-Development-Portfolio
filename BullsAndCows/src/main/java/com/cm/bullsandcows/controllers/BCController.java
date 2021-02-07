
package com.cm.bullsandcows.controllers;

//import com.cm.bullsandcows.dao.BCDao;
import com.cm.bullsandcows.model.Game;
import com.cm.bullsandcows.model.Round;
import com.cm.bullsandcows.service.BCService;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chris
 */

@RestController
@RequestMapping("/api")
public class BCController {

   // private BCDao dao;
    private BCService service;
            
    public BCController(BCService service) {
        this.service = service;
    }

    @GetMapping("/game")
    public List<Game> allGames() {
        List<Game> fullView = service.getAllGames();
        for (Game g : fullView) {
            if (g.getIsCorrect() == false) {
                g.setAnswerNumber("****");
            }
        }
        return fullView;
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> gameById(@PathVariable int gameId) {
        Game g = service.getGameById(gameId);
        if (g.getGameId() == 0) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            if (g.getIsCorrect() == false) {
                g.setAnswerNumber("****");
            }
        }
        return ResponseEntity.ok(g);
    }

    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> roundsPerGame(@PathVariable int gameId) {
        List<Round> fullView = service.getGameRounds(gameId);
        if (fullView.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);  
        }
        return ResponseEntity.ok(fullView);
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int beginGame(@RequestBody Game newGame) {

        String answer = generateAnswer();
        newGame.setAnswerNumber(answer);
        newGame.setIsCorrect(false);

        service.newGame(newGame);

        return newGame.getGameId();
    }

    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round makeGuess(@RequestBody Round newRound) {
        int exactCount, partialCount;
        String guess = newRound.getGuessNumber();
        Game currentGame = service.getGameById(newRound.getGameId());

        exactCount = calculateExact(guess, currentGame);
        partialCount = calculatePartial(guess, currentGame);


        newRound.setExact(exactCount);
        if (exactCount == 4) {
            currentGame.setIsCorrect(true);
        }
        newRound.setPartial(partialCount);
        newRound.setRoundNumber(currentGame.getGameRounds() + 1);
        LocalTime time = LocalTime.now();
        newRound.setTime(time.truncatedTo(ChronoUnit.SECONDS).toString());

        return service.guessNumber(newRound, currentGame);

  
    }

    private String generateAnswer() {
        Random generator = new Random();
        String answer = "";
        while (answer.length() < 4) {
            int randomNumber = generator.nextInt(10);
            if (answer.contains(Integer.toString(randomNumber))) {
               
            } else {
                answer += randomNumber;
            }
        }
        return answer;
    }

    private int calculateExact(String guess, Game currentGame) {
        if (guess == null || currentGame.getGameId() == 0) {
            return -1;
        }
        int exact = 0;
        String actualAnswer = currentGame.getAnswerNumber();

        for (int i = 0; i < actualAnswer.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (i == j && actualAnswer.charAt(i) == guess.charAt(j)) {
                    exact++;
                }
            }
        }
        return exact;
    }

    private int calculatePartial(String guess, Game currentGame) {
        if (guess == null || currentGame.getGameId() == 0) {
            return -1;
        }
        int partial = 0;
        String actualAnswer = currentGame.getAnswerNumber();

        for (int i = 0; i < actualAnswer.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (i != j && actualAnswer.charAt(i) == guess.charAt(j)) {
                    partial++;
                }
            }
        }
        return partial;
    }
    
}
