package com.example.hello_spring.model;

import java.util.Date;
import java.util.Arrays;

public class Match {
    private long id;
    private String result;
    private Date date;
    private Team_Season[] teams;

    public Match() {
        teams = new Team_Season[2];
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Team_Season[] getTeams() {
        return teams;
    }

    public void setTeams(Team_Season[] teams) {
        this.teams = teams;
    }
}
