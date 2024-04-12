package com.group6.gbac.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Score {
    @Id
    String name;
    String score;

    String sport_Id;

    String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSport_Id() {
        return sport_Id;
    }

    public void setSport_Id(String sport_Id) {
        this.sport_Id = sport_Id;
    }
}
