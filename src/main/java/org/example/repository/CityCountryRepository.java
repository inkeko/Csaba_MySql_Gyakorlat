package org.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CityCountryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CityCountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findCitiesByRegionAndCountry(String Continent, String countryCode) {
        String sql = """
            SELECT c.ID, c.Name AS CityName, c.District, c.Population, 
                   co.Name AS CountryName, co.Continent
            FROM city c
            JOIN country co ON c.CountryCode = co.Code
            WHERE co.Continent = ? AND co.Code = ?;
        """;
        return jdbcTemplate.queryForList(sql, Continent, countryCode);
    }

    public List<Map<String, Object>> findCountriesByRegionAndMinPopulation(String region, int minPopulation) {
        String sql = """
            SELECT Name, Population
            FROM country
            WHERE Continent = ? AND Population >= ?;
        """;

        return jdbcTemplate.queryForList(sql, region, minPopulation);
    }
}
