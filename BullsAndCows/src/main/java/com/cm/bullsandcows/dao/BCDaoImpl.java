
package com.cm.bullsandcows.dao;

import com.cm.bullsandcows.model.Game;
import com.cm.bullsandcows.model.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chris
 */
@Repository
public class BCDaoImpl implements BCDao {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT g.*, COUNT(r.RoundNumber) NumberOfRounds FROM "
                + "Games g LEFT OUTER JOIN Rounds r ON g.gameId = r.gameId GROUP BY "
                + "g.gameId;";
        return jdbcTemplate.query(sql, new gameMapper());
    }

    @Override
    public Game getGameById(int gameId) {
        final String sql = "SELECT g.*, COUNT(r.roundNumber) NumberOfRounds FROM "
                + "Games g LEFT OUTER JOIN Rounds r ON g.gameId = r.gameId "
                + "WHERE g.gameId = ?;";

        return jdbcTemplate.queryForObject(sql, new gameMapper(), gameId);
    }

    @Override
    public List<Round> getGameRounds(int gameId) {
       final String sql = "SELECT r.* "
                + "FROM Rounds r WHERE r.gameId = ? ORDER BY `time` ASC;";

        return jdbcTemplate.query(sql, new roundMapper(), gameId);
    }

    @Override
    @Transactional
    public int newGame(Game newGame) {
        final String sql = "INSERT INTO Games(isCorrect, answerNumber) "
                + "VALUES (?, ?);";
        GeneratedKeyHolder key = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            statement.setBoolean(1, newGame.getIsCorrect());
            statement.setString(2, newGame.getAnswerNumber());
            return statement;
        }, key);

        newGame.setGameId(key.getKey().intValue());

        return newGame.getGameId();
    }

    @Override
    @Transactional
    public Round guessNumber(Round currentRound, Game currentGame) {
        final String sql = "INSERT INTO Rounds(roundNumber, guessNumber, exactGuess, partialGuess, `time`, gameId) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        GeneratedKeyHolder key = new GeneratedKeyHolder();

        if (currentRound.getGuessNumber().length() != 4) {
            currentRound.setGuessNumber(null);
        } else {
            try {
                int i = Integer.parseInt(currentRound.getGuessNumber());
            } catch (NumberFormatException e) {
                currentRound.setGuessNumber(null);
            }
        }

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, currentRound.getRoundNumber());
            statement.setString(2, currentRound.getGuessNumber());
            statement.setInt(3, currentRound.getExact());            try {
                int i = Integer.parseInt(currentRound.getGuessNumber());
            } catch (NumberFormatException e) {
                currentRound.setGuessNumber(null);
            }
            statement.setInt(4, currentRound.getPartial());
            statement.setString(5, currentRound.getTime());
            statement.setInt(6, currentRound.getGameId());
            return statement;
        }, key);

        currentRound.setRoundId(key.getKey().intValue());

        final String sqlGame = "UPDATE Games "
                + "SET isCorrect = ? WHERE gameId = ?;";

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sqlGame, Statement.RETURN_GENERATED_KEYS);

            statement.setBoolean(1, currentGame.getIsCorrect());
            statement.setInt(2, currentGame.getGameId());
            return statement;
        });

        return currentRound;
    }

    @Override
    @Transactional
    public void deleteData() {
        final String sqlSafetyOff = "SET SQL_SAFE_UPDATES = 0;"; 
        final String sqlDeleteRounds = "DELETE FROM Rounds;";
        final String sqlDeleteGames = "DELETE FROM Games;";
        final String sqlSafetyOn = "SET SQL_SAFE_UPDATES = 1;";
        jdbcTemplate.update(sqlSafetyOff);
        jdbcTemplate.update(sqlDeleteRounds);
        jdbcTemplate.update(sqlDeleteGames);
        jdbcTemplate.update(sqlSafetyOn);
    }

    private static final class gameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game g = new Game();
            g.setGameId(rs.getInt("gameId"));
            g.setIsCorrect(rs.getBoolean("isCorrect"));
            g.setAnswerNumber(rs.getString("answerNumber"));
            g.setGameRounds(rs.getInt("NumberOfRounds"));
            return g;
        }
    }

    private static final class roundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round r = new Round();
            r.setGameId(rs.getInt("gameId"));
            r.setExact(rs.getInt("exactGuess"));
            r.setPartial(rs.getInt("partialGuess"));
            r.setGuessNumber(rs.getString("guessNumber"));
            r.setRoundNumber(rs.getInt("roundNumber"));
            r.setRoundId(rs.getInt("roundId"));
            r.setTime(rs.getString("time"));
            return r;
        }
    }
    
 
}
