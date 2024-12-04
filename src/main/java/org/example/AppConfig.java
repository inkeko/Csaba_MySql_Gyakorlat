package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in); // Standard inputra épülő Scanner bean
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}