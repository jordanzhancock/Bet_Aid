package com.group6.gbac.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Outcome {
    @Id
    String name;
    int price;

    String marketName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }
}
