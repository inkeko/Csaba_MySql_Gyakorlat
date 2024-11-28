package org.example.repository;

import org.example.repository.TableRepository;
import org.springframework.stereotype.Repository;
import org.example.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
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

        if (connection == null) {
            System.err.println("Hiba: Kapcsolat nincs inicializálva1!");
            return tableNames;
        }
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


}

