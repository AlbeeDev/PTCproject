package com.albeedev.ptcproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ig_club_id")
    private String igClubId;

    @Column(name = "discord_club_id")
    private String discordClubId;

    @Column(name = "clubname")
    private String clubname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIgClubId() {
        return igClubId;
    }

    public void setIgClubId(String igClubId) {
        this.igClubId = igClubId;
    }

    public String getDiscordClubId() {
        return discordClubId;
    }

    public void setDiscordClubId(String discordClubId) {
        this.discordClubId = discordClubId;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }
}
