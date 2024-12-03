package org.example.services;

import org.example.exceptions.EmptyQueryException;
import org.example.exceptions.InvalidQueryException;
import org.example.exceptions.NoResultsException;
import org.springframework.stereotype.Service;
import org.example.repository.TableRepository;

import java.util.List;
import java.util.Scanner;

@Service
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public List<String> getTableNames() {
        return tableRepository.getTableNames();
    }

    @Override
    public void executeCustomQuery(Scanner scanner) {
        System.out.print("Írjon be egy SQL lekérdezést: ");
        String sqlQuery = scanner.nextLine();

        try {
            List<String> results = tableRepository.executeQuery(sqlQuery);
            results.forEach(System.out::println);
        } catch (EmptyQueryException e) {
            System.out.println(e.getMessage()); // "A lekérdezés nem lehet üres."
        } catch (InvalidQueryException e) {
            System.out.println(e.getMessage()); // "Helytelen SQL parancs: ..."
        } catch (NoResultsException e) {
            System.out.println(e.getMessage()); // "Nincs eredmény a megadott lekérdezéshez."
        } catch (Exception e) {
            System.out.println("Ismeretlen hiba történt: " + e.getMessage());
        }
    }
}