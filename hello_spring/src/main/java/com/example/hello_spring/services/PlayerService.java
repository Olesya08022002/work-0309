package com.example.hello_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_spring.entity.PlayerEntity;
import com.example.hello_spring.repository.PlayerRepo;

import java.util.List;
import java.util.Optional;


@Service
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepository;

    public PlayerEntity savePlayer(PlayerEntity player) {
        return playerRepository.save(player);
    }

    public Optional<PlayerEntity> findPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public List<PlayerEntity> findAllPlayers() {
        return playerRepository.findAll();
    }

    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }

}