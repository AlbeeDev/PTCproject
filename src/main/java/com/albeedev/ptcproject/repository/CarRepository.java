package com.albeedev.ptcproject.repository;

import com.albeedev.ptcproject.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "select name from carList",nativeQuery = true)
    List<String> getAllCarNames();

    @Query(value = "select count(*) from carList", nativeQuery = true)
    int getTotalCars();

    @Query(value = "select id from carlist where name= :carname",nativeQuery = true)
    int getIdFromCarName(@Param("carname") String carname);
}