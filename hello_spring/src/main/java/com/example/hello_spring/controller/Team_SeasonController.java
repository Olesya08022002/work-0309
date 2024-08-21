package com.example.hello_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hello_spring.entity.TeamSeasonEntity;
import com.example.hello_spring.services.Team_SeasonService;

import java.util.List;

@RestController
@RequestMapping("/team-seasons")
public class Team_SeasonController {

    @Autowired
    private Team_SeasonService teamSeasonService;

    @PostMapping
    public TeamSeasonEntity createTeamSeason(@RequestBody TeamSeasonEntity teamSeason) {
        return teamSeasonService.saveTeamSeason(teamSeason);
    }

    @GetMapping("/{id}")
    public TeamSeasonEntity getTeamSeasonById(@PathVariable Long id) {
        return teamSeasonService.findTeamSeasonById(id).orElse(null);
    }

    @GetMapping
    public List<TeamSeasonEntity> getAllTeamSeasons() {
        return teamSeasonService.findAllTeamSeasons();
    }

    @DeleteMapping("/{id}")
    public void deleteTeamSeasonById(@PathVariable Long id) {
        teamSeasonService.deleteTeamSeasonById(id);
    }

}
