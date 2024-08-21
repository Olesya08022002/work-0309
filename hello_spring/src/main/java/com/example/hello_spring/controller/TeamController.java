package com.example.hello_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hello_spring.entity.TeamEntity;
import com.example.hello_spring.services.TeamService;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public TeamEntity createTeam(@RequestBody TeamEntity team) {
        return teamService.saveTeam(team);
    }

    @GetMapping("/{id}")
    public TeamEntity getTeamById(@PathVariable Long id) {
        return teamService.findTeamById(id).orElse(null);
    }

    @GetMapping
    public List<TeamEntity> getAllTeams() {
        return teamService.findAllTeams();
    }

    @DeleteMapping("/{id}")
    public void deleteTeamById(@PathVariable Long id) {
        teamService.deleteTeamById(id);
    }

}
