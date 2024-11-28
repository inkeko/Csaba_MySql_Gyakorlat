


import org.example.Main;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest(classes = Main.class) // Adjunk meg egyértelmű konfigurációs osztályt
@Disabled  // igy most nincs tesztelés
 // @ActiveProfiles("test") // ha tesztelni szeretnél
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Kapcsolat sikeresen létrejött az adatbázissal!");
        }
    }
}

