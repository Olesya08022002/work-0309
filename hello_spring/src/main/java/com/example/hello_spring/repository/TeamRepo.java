package com.example.hello_spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hello_spring.entity.TeamEntity;

@Repository
public interface TeamRepo extends JpaRepository<TeamEntity, Long> {
    
}