package com.example.hello_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hello_spring.entity.PlayerEntity;
import com.example.hello_spring.services.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public PlayerEntity createPlayer(@RequestBody PlayerEntity player) {
        return playerService.savePlayer(player);
    }

    @GetMapping("/{id}")
    public PlayerEntity getPlayerById(@PathVariable Long id) {
        return playerService.findPlayerById(id).orElse(null);
    }

    @GetMapping
    public List<PlayerEntity> getAllPlayers() {
        return playerService.findAllPlayers();
    }

    @DeleteMapping("/{id}")
    public void deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
    }

}