package com.example.hello_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_spring.entity.TeamSeasonEntity;
import com.example.hello_spring.repository.Team_SeasonRepo;

import java.util.List;
import java.util.Optional;

@Service
public class Team_SeasonService {

    @Autowired
    private Team_SeasonRepo teamSeasonRepository;

    public TeamSeasonEntity saveTeamSeason(TeamSeasonEntity teamSeason) {
        return teamSeasonRepository.save(teamSeason);
    }

    public Optional<TeamSeasonEntity> findTeamSeasonById(Long id) {
        return teamSeasonRepository.findById(id);
    }

    public List<TeamSeasonEntity> findAllTeamSeasons() {
        return teamSeasonRepository.findAll();
    }

    public void deleteTeamSeasonById(Long id) {
        teamSeasonRepository.deleteById(id);
    }
    
}
