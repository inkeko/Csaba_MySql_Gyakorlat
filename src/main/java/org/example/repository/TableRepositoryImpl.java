package org.example.repository;

import org.example.exceptions.EmptyQueryException;
import org.example.exceptions.InvalidQueryException;
import org.example.exceptions.NoResultsException;
import org.example.repository.TableRepository;
import org.springframework.stereotype.Repository;
import org.example.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableRepositoryImpl implements TableRepository {

    private final DatabaseUtils databaseUtils;

    public TableRepositoryImpl(DatabaseUtils databaseUtils) {
        this.databaseUtils = databaseUtils;
    }

    @Override
    public List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection connection = databaseUtils.getConnection();
        String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'world'";

        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                tableNames.add(resultSet.getString("TABLE_NAME"));
            }
        } catch (Exception e) {
            System.err.println("Hiba a táblanevek lekérdezése során: " + e.getMessage());
        }

        return tableNames;
    }

    @Override
    public List<String> executeQuery(String query) {
        List<String> results = new ArrayList<>();
        Connection connection = databaseUtils.getConnection();

        if (connection == null) {
            throw new RuntimeException("Hiba: Kapcsolat nincs inicializálva!");
        }

        // Üres lekérdezés kezelése
        if (query == null || query.isBlank()) {
            throw new EmptyQueryException("A lekérdezés nem lehet üres.");
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(resultSet.getString(i)).append("\t");
                }
                results.add(row.toString().trim());
            }

            // Nincs eredmény kezelése
            if (results.isEmpty()) {
                throw new NoResultsException("Nincs eredmény a megadott lekérdezéshez.");
            }

        } catch (SQLException e) {
            // Hibás SQL kezelése
            throw new InvalidQueryException("Helytelen SQL parancs: " + e.getMessage());
        }

        return results;
    }



}

