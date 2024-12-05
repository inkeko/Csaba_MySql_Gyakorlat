package org.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CountryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findCountriesByMinPopulation(int minPopulation) {
        String sql = "SELECT Name, Population FROM country WHERE Population >= ?";
        return jdbcTemplate.queryForList(sql, minPopulation);
    }
}

