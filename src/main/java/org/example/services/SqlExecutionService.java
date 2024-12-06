package org.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SqlExecutionService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SqlExecutionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Általános SQL lekérdezés futtatása.
     *
     * @param sql Az SQL parancs.
     * @return Az eredmény egy lista, ahol minden elem egy sor az eredménytáblából.
     */
    public List<Map<String, Object>> executeQuery(String sql) {
        return jdbcTemplate.queryForList(sql);
    }
}

