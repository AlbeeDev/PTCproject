package com.albeedev.ptcproject.service;

import com.albeedev.ptcproject.entity.Club;
import com.albeedev.ptcproject.entity.ClubRep;
import com.albeedev.ptcproject.entity.Player;
import com.albeedev.ptcproject.entity.PlayerRep;
import com.albeedev.ptcproject.repository.ClubRepRepository;
import com.albeedev.ptcproject.repository.ClubRepository;
import com.albeedev.ptcproject.repository.PlayerRepRepository;
import com.albeedev.ptcproject.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataService {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    PlayerRepRepository playerRepRepository;
    @Autowired
    ClubRepRepository clubRepRepository;

    public Player getPlayerByDiscordId(String discordId){
        return playerRepository.getPlayerByDiscordId(discordId);
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player getPlayerById(int id) {
        return playerRepository.findById((long) id).orElse(null);
    }

    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    public Club getClubById(int id) {
        return clubRepository.findById((long) id).orElse(null);
    }

    public PlayerRep savePlayerRep(PlayerRep playerRep) {
        return playerRepRepository.save(playerRep);
    }

    public ClubRep saveClubRep(ClubRep clubRep) {
        return clubRepRepository.save(clubRep);
    }

}
