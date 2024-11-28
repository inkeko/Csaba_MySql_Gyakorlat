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

    private Connection connection;
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
            validateCredentials(username, password); // Hitelesítési ellenőrzés
            DataSource dataSource = createDataSource(username, password);

            // Inicializáljuk a `connection` mezőt
            connection = dataSource.getConnection();

            return connection;
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
     * @throws InvalidPasswordException    ha a jelszó nem felel meg a szabályoknak
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
        // Regex: Csak számokat, kisbetűket és nagybetűket enged.
        if (!password.matches("[a-zA-Z0-9]+")) {
            throw new InvalidPasswordException("A jelszó csak számokat, kisbetűket és nagybetűket tartalmazhat!");
        }
        // Ellenőrizzük, hogy a jelszó hossza legalább 8 karakter.
        if (password.length() < 8) {
            throw new InvalidPasswordException("A jelszónak legalább 8 karakter hosszúnak kell lennie!");
        }
    }


    public Connection getConnection() {
        return connection;
    }

}
