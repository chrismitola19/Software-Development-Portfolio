/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.batterup.BatterUp.controllers;

import com.cm.batterup.BatterUp.entities.Coach;
import com.cm.batterup.BatterUp.entities.Player;
import com.cm.batterup.BatterUp.entities.Team;
import com.cm.batterup.BatterUp.repositories.CoachRepository;
import com.cm.batterup.BatterUp.repositories.PlayerRepository;
import com.cm.batterup.BatterUp.repositories.TeamRepository;
import com.cm.batterup.BatterUp.repositories.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author chris
 */
@Controller
public class BattingOrderController {

    @Autowired
    TeamRepository teams;

    @Autowired
    PlayerRepository players;

    @Autowired
    CoachRepository coaches;

    @Autowired
    UserRepository user;

    Set<ConstraintViolation<Team>> violations = new HashSet<>();
    Set<ConstraintViolation<Player>> pviolations = new HashSet<>();
    Set<ConstraintViolation<Coach>> cviolations = new HashSet<>();
    Set<String> stringError = new HashSet<>();

    @GetMapping("/home")
    public String displayHomePage(Model model) {
        stringError.clear();
        violations.clear();
        cviolations.clear();
        pviolations.clear();
        model.addAttribute("teams", teams.findAll());
        return "/home";
    }

    @GetMapping("/addTeam")
    public String displayAddTeamPage(Model model) {

        Team team = new Team();
        model.addAttribute("teams", team);
        model.addAttribute("errors", violations);

        return "/addTeam";
    }

    @PostMapping("/addTeam")
    public String addTeams(Team team) {

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(team);

        if (violations.isEmpty()) {
            teams.save(team);
            return "redirect:/home";
        }

        return "redirect:/addTeam";
    }

    @GetMapping("/playerList")
    public String displayPlayerListPage(Model model, Integer id) {
        stringError.clear();
        pviolations.clear();
        Team team = teams.findById(id).orElse(null);
        List<Player> player = players.findByTeam(team);
        List<Coach> coach = coaches.findByTeam(team);

        for (int i = 0; i < player.size(); i++) {

            players.findByBattingAverage();
            players.findByOnBasePercentage();
            players.findBySluggingPercentage();
            players.findByBattingPosition();

        }

        for (int i = 0; i < player.size(); i++) {

            players.findById(player.get(i).getPlayerid());

        }

        model.addAttribute("coaches", coach);
        model.addAttribute("players", player);
        model.addAttribute("teams", team);

        return "/playerList";
    }

    @GetMapping("/addPlayer")
    public String displayAddPlayerPage(Model model) {

        List<Team> teamList = teams.findAll();

        model.addAttribute("stringError", stringError);
        model.addAttribute("errs", pviolations);
        model.addAttribute("teams", teamList);

        return "/addPlayer";
    }

    @PostMapping("addPlayer")
    public String addPlayer(Player player, Team team) {

        stringError.clear();
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        pviolations = validate.validate(player);

        if (player.getAtbats() == null) {
            return "redirect:/addPlayer";
        }

        if (player.getHits() == null) {
            return "redirect:/addPlayer";
        }

        if (player.getSingles() == null) {
            return "redirect:/addPlayer";
        }

        if (player.getDoubles() == null) {
            return "redirect:/addPlayer";
        }

        if (player.getTriples() == null) {
            return "redirect:/addPlayer";
        }

        if (player.getHomeruns() == null) {
            return "redirect:/addPlayer";
        }

        if (player.getHits() > player.getAtbats()) {
            stringError.add("Hits cannot be greater than At Bats.");
            return "redirect:/addPlayer";
        }

        if (player.getHits() < player.getSingles() + player.getDoubles() + player.getTriples() + player.getHomeruns()) {
            stringError.add("Singles + Doubles + Triples + Home Runs cannot be greater than Hits.");
            return "redirect:/addPlayer";
        }

        if (player.getHits() > player.getSingles() + player.getDoubles() + player.getTriples() + player.getHomeruns()) {
            stringError.add("Singles + Doubles + Triples + Home Runs cannot be less than Hits.");
            return "redirect:/addPlayer";
        }

        if (pviolations.isEmpty()) {

            players.save(player);
            players.findByBattingAverage();
            players.findByOnBasePercentage();
            players.findBySluggingPercentage();
            players.findByBattingPosition();

            return "redirect:/playerList?id=" + team.getTeamid();
        }

        return "redirect:/addPlayer";
    }

    @GetMapping("/deleteTeam")
    public String deleteTeam(Model model, Integer id) {

        Team team = teams.findById(id).orElse(null);
        List<Player> player = players.findByTeam(team);
        model.addAttribute(players.findByTeam(team));
        model.addAttribute("team", team);
        model.addAttribute("player", player.size());

        return "/deleteTeam";
    }

    @GetMapping("/deleteAll")
    public String deleteAllTeam(Integer id, Team team) {
        List<Player> player = players.findByTeam(team);
        for (int i = 0; i < player.size(); i++) {
            players.deleteById(player.get(i).getPlayerid());
        }
        teams.deleteById(id);
        return "redirect:/home";

    }

    @GetMapping("/editTeam")
    public String editTeam(Model model, Integer id) {

        Team team = teams.findById(id).orElse(null);
        model.addAttribute("team", team);
        model.addAttribute("errors", violations);

        return "/editTeam";
    }

    @GetMapping("/edTeam")
    public String edTeam(Model model, Team team, Integer id) {

        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(team);

        if (violations.isEmpty()) {
            teams.save(team);

            return "redirect:/home";
        }

        return "redirect:/editTeam?id=" + team.getTeamid();
    }

    @GetMapping("deletePlayer")
    public String deletePlayer(Integer id, Integer teamid) {

        players.findById(id).orElse(null);
        players.deleteById(id);

        return "redirect:/playerList?id=" + teamid;
    }

    @GetMapping("displayEditPlayer")
    public String displayEditPlayer(Model model, Integer id) {

        Player player = players.findById(id).orElse(null);
        model.addAttribute("teams", teams.findAll());

        model.addAttribute("player", player);

        model.addAttribute("editerrs", pviolations);

        model.addAttribute("stringError", stringError);

        return "editPlayer";

    }

    @GetMapping("editPlayer")
    public String editPlayer(@ModelAttribute Player player, Team team) {

         stringError.clear();
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        pviolations = validate.validate(player);

        if (player.getAtbats() == null) {
            return "redirect:/displayEditPlayer?id=" + player.getPlayerid();
        }

        if (player.getHits() == null) {
            return "redirect:/displayEditPlayer?id=" + player.getPlayerid();
        }

        if (player.getSingles() == null) {
            return "redirect:/displayEditPlayer?id=" + player.getPlayerid();
        }

        if (player.getDoubles() == null) {
            return "redirect:/displayEditPlayer?id=" + player.getPlayerid();
        }

        if (player.getTriples() == null) {
            return "redirect:/displayEditPlayer?id=" + player.getPlayerid();
        }

        if (player.getHomeruns() == null) {
            return "redirect:/displayEditPlayer?id=" + player.getPlayerid();
        }

        if (player.getHits() > player.getAtbats()) {
            stringError.add("Hits cannot be greater than At Bats.");
            return "redirect:/displayEditPlayer?id=" + player.getPlayerid();
        }

        if (player.getHits() < player.getSingles() + player.getDoubles() + player.getTriples() + player.getHomeruns()) {
            stringError.add("Singles + Doubles + Triples + Home Runs cannot be greater than Hits.");
            return "redirect:/displayEditPlayer?id=" + player.getPlayerid();
        }

        if (player.getHits() > player.getSingles() + player.getDoubles() + player.getTriples() + player.getHomeruns()) {
            stringError.add("Singles + Doubles + Triples + Home Runs cannot be less than Hits.");
            return "redirect:/displayEditPlayer?id=" + player.getPlayerid();
        }

        if (pviolations.isEmpty()) {
            players.save(player);
            players.findByBattingAverage();
            players.findByOnBasePercentage();
            players.findBySluggingPercentage();
            players.findByBattingPosition();
            return "redirect:/playerList?id=" + team.getTeamid();
        }

        return "redirect:/displayEditPlayer?id=" + player.getPlayerid();
    }

    @GetMapping("/addCoach")
    public String displayAddCoachPage(Model model) {

        List<Team> teamList = teams.findAll();

        model.addAttribute("cerrs", cviolations);
        model.addAttribute("teams", teamList);

        return "/addCoach";
    }

    @PostMapping("addCoach")
    public String addCoach(Coach coach, Team team) {

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        cviolations = validate.validate(coach);

        if (cviolations.isEmpty()) {

            coaches.save(coach);
            return "redirect:/playerList?id=" + team.getTeamid();
        }

        return "redirect:/addCoach";
    }

    @GetMapping("deleteCoach")
    public String deleteCoach(int id, Integer teamid) {
        coaches.findById(id).orElse(null);
        coaches.deleteById(id);

        return "redirect:/playerList?id=" + teamid;
    }

    @GetMapping("/battingOrder")
    public String displayBattingOrderPage(Model model, Integer id, Pageable pageable) {

        Team team = teams.findById(id).orElse(null);

        List<Player> player = players.findPlayerByDesc(id);

        model.addAttribute("players", player);
        model.addAttribute("teams", team);

        return "/battingOrder";
    }

}