package com.albeedev.ptcproject.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "clubrep")
public class ClubRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "club")
    private Club club;
    @Column(name = "total_rep")
    private int totalRep;
    @Column(name = "lb_position")
    private int lbPosition;

    @Column(name = "date", columnDefinition = "date")
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public int getTotalRep() {
        return totalRep;
    }

    public void setTotalRep(int totalRep) {
        this.totalRep = totalRep;
    }

    public int getLbPosition() {
        return lbPosition;
    }

    public void setLbPosition(int lbPosition) {
        this.lbPosition = lbPosition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}