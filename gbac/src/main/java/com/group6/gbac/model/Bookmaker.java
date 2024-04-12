package com.group6.gbac.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;

@Entity
public class Bookmaker {
    @Id
    @Column(name="keyId")
    String key;
    String title;
    @JsonProperty("last_update")
    Date lastUpdate;

//    List<Market> markets;

    String sportId;

//    public List<Market> getMarkets() {
//        return markets;
//    }
//
//    public void setMarkets(List<Market> markets) {
//        this.markets = markets;
//    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }
}
