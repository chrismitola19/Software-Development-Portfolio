/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.batterup.BatterUp.repositories;

import com.cm.batterup.BatterUp.entities.Coach;
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
public class CoachRepositoryTest {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private CoachRepository coachRepo;


   

    public CoachRepositoryTest() {
    }
    
    
     @Test
    public void testCreate() {

        Team team = teamRepo.findAll().get(0);
        

        Coach coach = new Coach();
        coach.setCoachid(2);
        coach.setFirstname("Jim");
        coach.setLastname("Johnson");
       
        coach.setTeam(team);

        Coach actual = coachRepo.save(coach);
        assertTrue(actual.getCoachid() > 0);

        actual = coachRepo.findById(actual.getCoachid())
                .orElse(null);

        assertNotNull(actual);

        System.out.println(actual);

        coachRepo.deleteById(actual.getCoachid());
    }

    @Test
    public void testFindAll() {
        List<Coach> all = coachRepo.findAll();
        assertEquals(1, all.size());
        for (Coach a : all) {
            System.out.println(a);
        }
    }

    @Test
    public void testFindByCoachIdentifier() {
        assertEquals(true, coachRepo.findById(1).isPresent());
       
    }

   
}
