package org.example.repository;

import java.util.List;

public interface TableRepository {
    List<String> getTableNames();

    List<String> executeQuery(String sqlQuery);
}
