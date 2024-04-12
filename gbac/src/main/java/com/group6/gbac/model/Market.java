package com.group6.gbac.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Market {
    @Id
    @Column(name="keyId")
    String key;

//    List<Outcome> outcomes;
    @JsonProperty("last_update")
    Date lastUpdate;

    String bookMakerKeyId;


    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

//    public List<Outcome> getOutcomes() {
//        return outcomes;
//    }
//
//    public void setOutcomes(List<Outcome> outcomes) {
//        this.outcomes = outcomes;
//    }

    public String getBookMakerKeyId() {
        return bookMakerKeyId;
    }

    public void setBookMakerKeyId(String bookMakerKeyId) {
        this.bookMakerKeyId = bookMakerKeyId;
    }
}
