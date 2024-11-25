package org.example.services;



import org.example.utils.DatabaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Scanner;

@Service
public class ApplicationService {

    @Autowired
    private DatabaseUtils databaseUtils;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        try {
            // Felhasználói bemenetek bekérése
            System.out.print("Kérem, adja meg a felhasználónevet: ");
            String username = scanner.nextLine();

            System.out.print("Kérem, adja meg a jelszót: ");
            String password = scanner.nextLine();
            // Kapcsolódás az adatbázishoz
            Connection conn = databaseUtils.connect(username, password);
            System.out.println("Sikeresen kapcsolódott az adatbázishoz!");


            // További logika (pl. lekérdezések futtatása)


        } catch (Exception e) {
            System.err.println("Hiba történt: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}