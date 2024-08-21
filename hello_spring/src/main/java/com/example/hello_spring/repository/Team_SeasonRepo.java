package com.example.hello_spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hello_spring.entity.TeamSeasonEntity;

@Repository
public interface Team_SeasonRepo extends JpaRepository<TeamSeasonEntity, Long> {

}
