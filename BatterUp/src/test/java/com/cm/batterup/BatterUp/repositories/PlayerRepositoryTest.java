/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.batterup.BatterUp.repositories;


import com.cm.batterup.BatterUp.entities.Player;
import com.cm.batterup.BatterUp.entities.Team;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PlayerRepositoryTest {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private PlayerRepository playerRepo;


   

    public PlayerRepositoryTest() {
    }
    
   
     @Test
    public void testCreate() {

        Team team = teamRepo.findAll().get(0);
        

        Player player = new Player();
        player.setPlayerid(2);
        player.setFirstname("Jim");
        player.setLastname("Johnson");
        player.setAtbats(33);
        player.setHits(20);
        player.setRuns(13);
        player.setSingles(10);
        player.setDoubles(5);
        player.setTriples(3);
        player.setHomeruns(2);
        player.setWalks(3);
        player.setSacfly(3);
        player.setSacbunt(2);
        player.setStolenbase(5);
        player.setBattingaverage(0);
        player.setOnbasepercentage(0);
        player.setSluggingpercentage(0);
        player.setBattingposition(0);
        player.setTeam(team);

        Player actual = playerRepo.save(player);
        assertTrue(actual.getPlayerid() > 0);

        actual = playerRepo.findById(actual.getPlayerid())
                .orElse(null);

        assertNotNull(actual);

        System.out.println(actual);

        playerRepo.deleteById(actual.getPlayerid());
    }

    @Test
    public void testFindAll() {
        List<Player> all = playerRepo.findAll();
        assertEquals(1, all.size());
        for (Player a : all) {
            System.out.println(a);
        }
    }

    @Test
    public void testFindByPlayerIdentifier() {
        assertEquals(true, playerRepo.findById(1).isPresent());
       
    }

   
}
    

