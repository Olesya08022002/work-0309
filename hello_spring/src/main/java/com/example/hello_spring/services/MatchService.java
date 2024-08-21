package com.example.hello_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_spring.entity.MatchEntity;
import com.example.hello_spring.repository.MatchRepo;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepo matchRepository;

    public MatchEntity saveMatch(MatchEntity match) {
        return matchRepository.save(match);
    }

    public Optional<MatchEntity> findMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public List<MatchEntity> findAllMatches() {
        return matchRepository.findAll();
    }

    public void deleteMatchById(Long id) {
        matchRepository.deleteById(id);
    }
    
}