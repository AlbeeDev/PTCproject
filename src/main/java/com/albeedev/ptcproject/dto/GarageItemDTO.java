package com.albeedev.ptcproject.dto;

public class GarageItemDTO {
    private String username;
    private int rank;
    private String carName;
    private String carClass;
    private int goldRank;

    public GarageItemDTO(String username, int rank, String carName, String carClass, int goldRank) {
        this.username = username;
        this.rank = rank;
        this.carName = carName;
        this.carClass = carClass;
        this.goldRank = goldRank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public int getGoldRank() {
        return goldRank;
    }

    public void setGoldRank(int goldRank) {
        this.goldRank = goldRank;
    }
}