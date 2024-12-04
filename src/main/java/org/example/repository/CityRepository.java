package org.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CityRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findByCountryCode(String countryCode) {
        String sql = "SELECT * FROM city WHERE UPPER(CountryCode) = UPPER(?)";
        return jdbcTemplate.queryForList(sql, countryCode);
    }

    public List<Map<String, Object>> findByMinPopulation(int minPopulation) {
        String sql = "SELECT * FROM city WHERE population >= ?";
        return jdbcTemplate.queryForList(sql, minPopulation);
    }

    public List<Map<String, Object>> findByName(String name) {
        String sql = "SELECT * FROM city WHERE name LIKE ?";
        return jdbcTemplate.queryForList(sql, "%" + name + "%");
    }
}
