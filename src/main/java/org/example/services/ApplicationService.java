package org.example.services;


import org.example.exceptions.DatabaseConnectionException;
import org.example.menu.CitySearchMenu;
import org.example.menu.MainMenu;
import org.example.utils.DatabaseUtils;
import org.example.utils.InputValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.sql.Connection;
import java.util.Scanner;


@Service
public class ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);
    @Autowired
    private DatabaseUtils databaseUtils; // Segédosztály az adatbázis-kezeléshez

    @Autowired
    private CitySearchMenu citySearchMenu;

    @Autowired
    private InputValidator inputValidator;

    @Autowired
    private ApplicationContext applicationContext;

    public void start() {

        try (Scanner scanner = new Scanner(System.in);) {
            // Felhasználói hitelesítés
            System.out.print("Kérem, adja meg a felhasználónevet: ");
            String username = scanner.nextLine();

            System.out.print("Kérem, adja meg a jelszót: ");
            String password = scanner.nextLine();

            // Kapcsolódás az adatbázishoz
            inputValidator.validatePassword(password);
            connectToDatabase(username, password);

            // Főmenü megjelenítése
            MainMenu mainMenu = applicationContext.getBean(MainMenu.class);
            mainMenu.displayMenu();

        } catch (DatabaseConnectionException e) {
            System.err.println("Nem sikerült csatlakozni az adatbázishoz. Kérjük, ellenőrizze a hitelesítési adatokat.");
        } catch (Exception e) {
            System.err.println("Ismeretlen hiba történt: " + e.getMessage());
            e.printStackTrace(); // Fejlesztői környezetben rögzíthetjük a részleteket
        } finally {

        }
    }

    private void connectToDatabase(String username, String password) {
        try {
            Connection connection = databaseUtils.connect(username, password);
            System.out.println("Sikeresen kapcsolódott az adatbázishoz!");
        } catch (DatabaseConnectionException e) {
            throw new DatabaseConnectionException("Hiba történt az adatbázis csatlakozása során.", e);
        }
    }
}
