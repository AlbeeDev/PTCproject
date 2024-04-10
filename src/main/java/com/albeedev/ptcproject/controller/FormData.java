package com.albeedev.ptcproject.controller;

public class FormData {
    private String carName;
    private int stars;
    private int carRank;

    public FormData() {
    }

    // Getters and setters
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getCarRank() {
        return carRank;
    }

    public void setCarRank(int carRank) {
        this.carRank = carRank;
    }
}
