/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.batterup.BatterUp.repositories;

import com.cm.batterup.BatterUp.entities.Player;
import com.cm.batterup.BatterUp.entities.Team;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TeamRepositoryTest {


    @Autowired
    private TeamRepository repo;

    public TeamRepositoryTest() {
    }

    @Test
    public void testFindAll() {
        assertTrue(repo.findAll().size() > 0);
    }

    @Test
    public void testFindById() {

        Team team = repo.findById(1)
                .orElse(null);

        assertNotNull(team);
        System.out.println(team);
    }

    @Test
    public void testCreate() {

        Team team = makeTestTeam();

        team = repo.save(team);

        Team actual = repo.findById(2)
                .orElse(null);

        assertNotNull(actual);

        repo.deleteById(2);
    }

    @Test
    public void testUpdate() {

        Team team = makeTestTeam();
        team = repo.save(team);

        Team toUpdate = makeTestTeam();
        toUpdate.setCity("Monroe");

        toUpdate = repo.save(toUpdate);

        Team actual = repo.findById(2).orElse(null);
        assertNotNull(actual);
        assertEquals("Monroe", actual.getCity());

        repo.deleteById(2);
    }

    private Team makeTestTeam() {
        Team team = new Team();
        team.setTeamid(2);
        team.setTeamname("Panthers");



        Player player = new Player();
        player.setPlayerid(1);
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

        return team;
    }

}

