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
    @Query("SELECT new com.albeedev.ptcproject.dto.GarageItemDTO(p.username, g.rank, cL.name, cL.carClass, cL.goldrank) " +
            "FROM Garage g " +
            "JOIN g.player p " +
            "JOIN g.car cL " +
            "WHERE p.username = :username")
    List<GarageItemDTO> getAllGarageItemsForPlayer(@Param("username") String username);
}
