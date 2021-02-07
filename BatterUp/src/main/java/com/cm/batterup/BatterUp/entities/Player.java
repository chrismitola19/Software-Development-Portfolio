/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.batterup.BatterUp.entities;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author chris
 */

@Entity
public class Player {

@Id   
int playerid;

@NotBlank(message = "First name must not be blank.")
@Size(max = 30, message = "First name must be less than 30 characters.")
String firstname;

@NotBlank(message = "Last name must not be blank.")
@Size(max = 30, message = "Last name must be less than 30 characters.")
String lastname;

@NotNull(message = "At Bats cannot be null.")
@Min(value = (1), message = "Minimum At Bats is 1.")
@Max(value = (1000), message = "Maximum input is 1000.")
Integer atbats;

@NotNull(message = "Hits cannot be null.")
@Min(value = (0), message = "Minimum Hits input is 0.")
@Max(value = (1000), message = "Maximum input is 1000.")        
Integer hits;

@NotNull(message = "Runs cannot be null.")
@Min(value = (0), message = "Minimum Runs input is 0.")
@Max(value = (1000), message = "Maximum input is 1000.")
Integer runs;

@NotNull(message = "Singles cannot be null.")
@Min(value = (0), message = "Minimum Singles input is 0.")
@Max(value = (1000), message = "Maximum input is 1000.")       
Integer singles;

@NotNull(message = "Doubles cannot be null.")
@Min(value = (0), message = "Minimum Doubles input is 0.")
@Max(value = (1000), message = "Maximum input is 1000.")
Integer doubles;

@NotNull(message = "Triples cannot be null.")
@Min(value = (0), message = "Minimum Triples input is 0.")
@Max(value = (1000), message = "Maximum input is 1000.")        
Integer triples;

@NotNull(message = "Home Runs cannot be null.")
@Min(value = (0), message = "Minimum Home Runs input is 0.")
@Max(value = (1000), message = "Maximum input is 1000.")       
Integer homeruns;

@NotNull(message = "Walks cannot be null.")
@Min(value = (0), message = "Minimum Walks input is 0.")
@Max(value = (1000), message = "Maximum input is 1000.")        
Integer walks;

@NotNull(message = "Sac Flys cannot be null.")
@Min(value = (0), message = "Minimum Sac Flys input is 0.")
@Max(value = (1000), message = "Maximum input is 1000.")        
Integer sacfly;

@NotNull(message = "Sac Bunts cannot be null.")
@Min(value = (0), message = "Minimum Sac Bunts input is 0.")
@Max(value = (1000), message = "Maximum input is 1000.")        
Integer sacbunt;

@NotNull(message = "Stolen Bases cannot be null.")
@Min(value = (0), message = "Minimum Stolen Bases input is 0.")
@Max(value = (1000), message = "Maximum input is 1000.")        
Integer stolenbase;

double battingaverage;
double sluggingpercentage;
double onbasepercentage;
double battingposition;

@ManyToOne
    @JoinColumn(name = "teamid")
    private Team team;

    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAtbats() {
        return atbats;
    }

    public void setAtbats(Integer atbats) {
        this.atbats = atbats;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public Integer getSingles() {
        return singles;
    }

    public void setSingles(Integer singles) {
        this.singles = singles;
    }

    public Integer getDoubles() {
        return doubles;
    }

    public void setDoubles(Integer doubles) {
        this.doubles = doubles;
    }

    public Integer getTriples() {
        return triples;
    }

    public void setTriples(Integer triples) {
        this.triples = triples;
    }

    public Integer getHomeruns() {
        return homeruns;
    }

    public void setHomeruns(Integer homeruns) {
        this.homeruns = homeruns;
    }

    public Integer getWalks() {
        return walks;
    }

    public void setWalks(Integer walks) {
        this.walks = walks;
    }

    public Integer getSacfly() {
        return sacfly;
    }

    public void setSacfly(Integer sacfly) {
        this.sacfly = sacfly;
    }

    public Integer getSacbunt() {
        return sacbunt;
    }

    public void setSacbunt(Integer sacbunt) {
        this.sacbunt = sacbunt;
    }

    public Integer getStolenbase() {
        return stolenbase;
    }

    public void setStolenbase(Integer stolenbase) {
        this.stolenbase = stolenbase;
    }

    public double getBattingaverage() {
        return battingaverage;
    }

    public void setBattingaverage(double battingaverage) {
        this.battingaverage = battingaverage;
    }

    public double getSluggingpercentage() {
        return sluggingpercentage;
    }

    public void setSluggingpercentage(double sluggingpercentage) {
        this.sluggingpercentage = sluggingpercentage;
    }

    public double getOnbasepercentage() {
        return onbasepercentage;
    }

    public void setOnbasepercentage(double onbasepercentage) {
        this.onbasepercentage = onbasepercentage;
    }

    public double getBattingposition() {
        return battingposition;
    }

    public void setBattingposition(double battingposition) {
        this.battingposition = battingposition;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.playerid;
        hash = 59 * hash + Objects.hashCode(this.firstname);
        hash = 59 * hash + Objects.hashCode(this.lastname);
        hash = 59 * hash + Objects.hashCode(this.atbats);
        hash = 59 * hash + Objects.hashCode(this.hits);
        hash = 59 * hash + Objects.hashCode(this.runs);
        hash = 59 * hash + Objects.hashCode(this.singles);
        hash = 59 * hash + Objects.hashCode(this.doubles);
        hash = 59 * hash + Objects.hashCode(this.triples);
        hash = 59 * hash + Objects.hashCode(this.homeruns);
        hash = 59 * hash + Objects.hashCode(this.walks);
        hash = 59 * hash + Objects.hashCode(this.sacfly);
        hash = 59 * hash + Objects.hashCode(this.sacbunt);
        hash = 59 * hash + Objects.hashCode(this.stolenbase);
        hash = 59 * hash + Objects.hashCode(this.battingaverage);
        hash = 59 * hash + Objects.hashCode(this.sluggingpercentage);
        hash = 59 * hash + Objects.hashCode(this.onbasepercentage);
        hash = 59 * hash + Objects.hashCode(this.battingposition);
        hash = 59 * hash + Objects.hashCode(this.team);
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
        final Player other = (Player) obj;
        if (this.playerid != other.playerid) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.atbats, other.atbats)) {
            return false;
        }
        if (!Objects.equals(this.hits, other.hits)) {
            return false;
        }
        if (!Objects.equals(this.runs, other.runs)) {
            return false;
        }
        if (!Objects.equals(this.singles, other.singles)) {
            return false;
        }
        if (!Objects.equals(this.doubles, other.doubles)) {
            return false;
        }
        if (!Objects.equals(this.triples, other.triples)) {
            return false;
        }
        if (!Objects.equals(this.homeruns, other.homeruns)) {
            return false;
        }
        if (!Objects.equals(this.walks, other.walks)) {
            return false;
        }
        if (!Objects.equals(this.sacfly, other.sacfly)) {
            return false;
        }
        if (!Objects.equals(this.sacbunt, other.sacbunt)) {
            return false;
        }
        if (!Objects.equals(this.stolenbase, other.stolenbase)) {
            return false;
        }
        if (!Objects.equals(this.battingaverage, other.battingaverage)) {
            return false;
        }
        if (!Objects.equals(this.sluggingpercentage, other.sluggingpercentage)) {
            return false;
        }
        if (!Objects.equals(this.onbasepercentage, other.onbasepercentage)) {
            return false;
        }
        if (!Objects.equals(this.battingposition, other.battingposition)) {
            return false;
        }
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        return true;
    }  

} 