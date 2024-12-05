package org.example.services;


import org.example.menu.CitySearchMenu;
import org.example.menu.MainMenu;
import org.example.utils.DatabaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Scanner;

@Service
public class ApplicationService {

    @Autowired
    private DatabaseUtils databaseUtils; // Segédosztály az adatbázis-kezeléshez

    @Autowired
    CitySearchMenu citySearchMenu;

    @Autowired
    private ApplicationContext applicationContext;


    public void start() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Kérem, adja meg a felhasználónevet: ");
            String username = scanner.nextLine();

            System.out.print("Kérem, adja meg a jelszót: ");
            String password = scanner.nextLine();

            // Kapcsolódás az adatbázishoz
            Connection connection = databaseUtils.connect(username, password);
            System.out.println("Sikeresen kapcsolódott az adatbázishoz!");

            // Főmenü megjelenítése
            MainMenu mainMenu = applicationContext.getBean(MainMenu.class);
            mainMenu.displayMenu();

        } catch (Exception e) {
            System.err.println("Hiba történt: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
