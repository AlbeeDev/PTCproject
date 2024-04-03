package com.albeedev.ptcproject.service;

import com.albeedev.ptcproject.dto.GarageItemDTO;
import com.albeedev.ptcproject.entity.Club;
import com.albeedev.ptcproject.entity.ClubRep;
import com.albeedev.ptcproject.entity.Player;
import com.albeedev.ptcproject.entity.PlayerRep;
import com.albeedev.ptcproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @Autowired
    GarageRepository garageRepository;
    @Autowired
    CarRepository carRepository;

    public List<GarageItemDTO> getAllGarageItems(String username) {
        return garageRepository.getAllGarageItemsForPlayer(username);
    }

    public List<String> getAllCarNames(){
        return carRepository.getAllCarNames();
    }

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
