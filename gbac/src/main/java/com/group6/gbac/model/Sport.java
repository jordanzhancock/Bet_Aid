package com.group6.gbac.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;

@Entity
public class Sport {
    @Id
    String id;
    @JsonProperty("sport_title")
    String sportTitle;
    @JsonProperty("sport_key")
    String sportKey;
    @JsonProperty("commence_time")
    Date commenceTime;
    @JsonProperty("home_team")
    String homeTeam;
    @JsonProperty("away_team")
    String awayTeam;
    Boolean completed = false;

//    List<Score> scores = null;
//
//
//
//    List<Bookmaker> bookmakers;

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getSportTitle() {
        return sportTitle;
    }

    public void setSportTitle(String sportTitle) {
        this.sportTitle = sportTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//    public List<Bookmaker> getBookmakers() {
//        return bookmakers;
//    }
//
//    public void setBookmakers(List<Bookmaker> bookmakers) {
//        this.bookmakers = bookmakers;
//    }

    public String getSportKey() {
        return sportKey;
    }

    public void setSportKey(String sportKey) {
        this.sportKey = sportKey;
    }

    public Date getCommenceTime() {
        return commenceTime;
    }

    public void setCommenceTime(Date commenceTime) {
        this.commenceTime = commenceTime;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

//    public List<Score> getScores() {
//        return scores;
//    }
//
//    public void setScores(List<Score> scores) {
//        this.scores = scores;
//    }
}
