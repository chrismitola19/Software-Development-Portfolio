/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.batterup.BatterUp.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/**
 *
 * @author chris
 */

@Entity
public class Team implements Serializable {
    
  //  @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int teamid;
    
    @NotBlank(message = "Must have a team name.")
    @Size(max = 50, message = "Team name must be less than 50 characters.")
    private String teamname;
   
   @Size(max = 25, message = "City name must be less than 50 characters.")
    private String city;
    
   @Size(max = 25, message = "State name must be less than 50 characters.")
    private String stateabr;

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateabr() {
        return stateabr;
    }

    public void setStateabr(String stateabr) {
        this.stateabr = stateabr;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.teamid;
        hash = 89 * hash + Objects.hashCode(this.teamname);
        hash = 89 * hash + Objects.hashCode(this.city);
        hash = 89 * hash + Objects.hashCode(this.stateabr);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        if (this.teamid != other.teamid) {
            return false;
        }
        if (!Objects.equals(this.teamname, other.teamname)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.stateabr, other.stateabr)) {
            return false;
        }
        return true;
    }

}