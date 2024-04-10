package com.albeedev.ptcproject.repository;

import com.albeedev.ptcproject.dto.GarageItemDTO;
import com.albeedev.ptcproject.entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {
    @Query("SELECT new com.albeedev.ptcproject.dto.GarageItemDTO(p.username, g.carRank, cL.name, cL.carClass, cL.goldrank) " +
            "FROM Garage g " +
            "JOIN g.player p " +
            "JOIN g.car cL " +
            "WHERE p.username = :username")
    List<GarageItemDTO> getAllGarageItemsForPlayer(@Param("username") String username);

    @Query(value = "select count(*) from garage join player p on p.id = garage.player_id where p.username= :username",nativeQuery = true)
    int getTotalPlayerCars(@Param("username") String username);

    @Query(value = "insert into garage(player_id, car_id, stars, car_rank, date) values (:playerid,:carid, :stars, :carrank, curdate());",nativeQuery = true)
    void setGarageItem(@Param("playerid") int playerid, @Param("carid") int carid, @Param("stars") int stars, @Param("carrank") int carrank);
}
