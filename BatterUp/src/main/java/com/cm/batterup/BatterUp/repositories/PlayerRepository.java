/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.batterup.BatterUp.repositories;

import com.cm.batterup.BatterUp.entities.Player;
import com.cm.batterup.BatterUp.entities.Team;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chris
 */

@Repository
public interface PlayerRepository extends JpaRepository <Player, Integer> {
    List findByTeam(Team team);
    
   @Transactional
   @Modifying
   @Query(value = "UPDATE player SET battingaverage = hits/atbats", nativeQuery = true)        
  void findByBattingAverage(); 
  
   @Transactional
   @Modifying
   @Query(value = "UPDATE player SET onbasepercentage = (hits+walks) / (atbats + walks + sacfly)", nativeQuery = true)        
  void findByOnBasePercentage();
  
   @Transactional
   @Modifying
   @Query(value = "UPDATE player SET sluggingpercentage = ((singles * 1 + doubles * 2 + triples * 3 + homeruns * 4) / atbats)", nativeQuery = true)        
  void findBySluggingPercentage();
 
   @Transactional
   @Modifying
   @Query(value = " UPDATE player SET battingposition = (((hits / atbats) + ((hits + walks) / (atbats + walks + sacfly)) + ((singles * 1 + doubles * 2 + triples * 3 + homeruns * 4) / atbats)) / 3)", nativeQuery = true)        
  void findByBattingPosition();

  @Transactional
   @Query(value = "SELECT * FROM player WHERE teamid = :teamid ORDER BY battingposition DESC", nativeQuery = true)
    List<Player> findPlayerByDesc(
    @Param("teamid") Integer teamid);
}
