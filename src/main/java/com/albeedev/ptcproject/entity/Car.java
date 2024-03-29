package com.albeedev.ptcproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carlist")
public class Car {
    @Id
    private int id;
    private String carClass;
    private String name;
    @Column(name = "goldrank")
    private int goldrank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoldrank() {
        return goldrank;
    }

    public void setGoldrank(int goldrank) {
        this.goldrank = goldrank;
    }
}