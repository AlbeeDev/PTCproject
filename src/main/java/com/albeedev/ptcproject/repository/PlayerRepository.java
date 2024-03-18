package com.albeedev.ptcproject.repository;

import com.albeedev.ptcproject.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "select * from player where discord_id= :discordId", nativeQuery = true)
    Player getPlayerByDiscordId(@Param("discordId") String discordId);

}
