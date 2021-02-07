/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.batterup.BatterUp.repositories;

import com.cm.batterup.BatterUp.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chris
 */

@Repository
public interface TeamRepository extends JpaRepository <Team, Integer> {
    
}
