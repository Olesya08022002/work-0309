package com.example.hello_spring.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "matches", schema = "public")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "result", nullable = false)
    private String result;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "team_season_1_id", nullable = false)
    private TeamSeasonEntity team1;

    @ManyToOne
    @JoinColumn(name = "team_season_2_id", nullable = false)
    private TeamSeasonEntity team2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TeamSeasonEntity getTeam1() {
        return team1;
    }

    public void setTeam1(TeamSeasonEntity team1) {
        this.team1 = team1;
    }

    public TeamSeasonEntity getTeam2() {
        return team2;
    }

    public void setTeam2(TeamSeasonEntity team2) {
        this.team2 = team2;
    }
}
