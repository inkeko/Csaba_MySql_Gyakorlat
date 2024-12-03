package org.example.services;

import java.util.List;
import java.util.Scanner;

public interface TableService {
    List<String> getTableNames();

    void executeCustomQuery(Scanner scanner);
}
