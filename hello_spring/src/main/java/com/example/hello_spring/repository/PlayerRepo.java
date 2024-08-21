package com.example.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hello_spring.entity.PlayerEntity;

@Repository
public interface PlayerRepo extends JpaRepository<PlayerEntity, Long> {

} 
