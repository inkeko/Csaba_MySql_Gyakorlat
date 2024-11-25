package org.example.utils;


import org.example.exceptions.DatabaseConnectionException;
import org.example.exceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseUtils {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    /**
     * Kapcsolódás az adatbázishoz.
     *
     * @param username a felhasználónév
     * @param password a jelszó
     * @return Connection objektum
     * @throws DatabaseConnectionException adatbázis-kapcsolódási hiba esetén
     */
    public Connection connect(String username, String password) throws DatabaseConnectionException {
        try {
            validateCredentials(username, password); // Itt lehet, hogy többféle kivétel is dobódik
            DataSource dataSource = createDataSource(username, password);
            return dataSource.getConnection();
        } catch (SQLException | DatabaseConnectionException | InvalidPasswordException e) {
            // Itt mindent egy DatabaseConnectionException típusúba csomagolunk
            throw new DatabaseConnectionException("Sikertelen adatbázis-kapcsolódás: " + e.getMessage(), e);
        }
    }


    /**
     * Felhasználónév és jelszó érvényesítése.
     *
     * @param username a felhasználónév
     * @param password a jelszó
     * @throws DatabaseConnectionException ha a felhasználónév üres
     * @throws InvalidPasswordException ha a jelszó nem felel meg a szabályoknak
     */
    private void validateCredentials(String username, String password) throws DatabaseConnectionException, InvalidPasswordException {
        if (username == null || username.isBlank()) {
            throw new DatabaseConnectionException("A felhasználónév nem lehet üres!");
        }
        if (password == null || password.isBlank()) {
            throw new DatabaseConnectionException("A jelszó nem lehet üres!");
        }
        validatePassword(password);
    }

    /**
     * Adatbázis kapcsolat létrehozása.
     *
     * @param username a felhasználónév
     * @param password a jelszó
     * @return DataSource objektum
     */
    private DataSource createDataSource(String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * Jelszó validálása: csak számokat tartalmazhat.
     *
     * @param password a jelszó
     * @throws InvalidPasswordException ha a jelszó nem csak számokat tartalmaz
     */
    private void validatePassword(String password) throws InvalidPasswordException {
        if (!password.matches("\\d+")) {
            throw new InvalidPasswordException("A jelszó csak számokat tartalmazhat!");
        }
    }
}
