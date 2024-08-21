package com.example.hello_spring.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hello_spring.entity.MatchEntity;

@Repository
public interface MatchRepo extends JpaRepository<MatchEntity, Long> {

}
