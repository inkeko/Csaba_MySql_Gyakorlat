package org.example.repository;

import org.example.worldEntity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("SELECT c FROM City c WHERE c.countryCode = :countryCode")
    List<City> findByCountryCode(@Param("countryCode") String countryCode);

    @Query("SELECT c FROM City c WHERE c.population >= :minPopulation")
    List<City> findByMinPopulation(@Param("minPopulation") int minPopulation);

    @Query("SELECT c FROM City c WHERE c.name LIKE %:name%")
    List<City> findByName(@Param("name") String name);
}
