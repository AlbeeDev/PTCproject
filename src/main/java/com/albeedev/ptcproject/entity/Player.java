package com.albeedev.ptcproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "discord_id")
    private String discordId;

    @Column(name = "gameloft_id")
    private String gameloftId;
    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "currentclub")
    private Club currentClub;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getGameloftId() {
        return gameloftId;
    }

    public void setGameloftId(String gameloftId) {
        this.gameloftId = gameloftId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Club getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(Club currentClub) {
        this.currentClub = currentClub;
    }
}
