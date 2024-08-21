package com.example.hello_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_spring.entity.TeamEntity;
import com.example.hello_spring.repository.TeamRepo;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepo teamRepository;

    public TeamEntity saveTeam(TeamEntity team) {
        return teamRepository.save(team);
    }

    public Optional<TeamEntity> findTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public List<TeamEntity> findAllTeams() {
        return teamRepository.findAll();
    }

    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }

}
