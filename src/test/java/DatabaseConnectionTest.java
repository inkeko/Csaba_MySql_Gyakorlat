


import org.example.Main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = Main.class) // Adjunk meg egyértelmű konfigurációs osztályt
//@Disabled  // igy most nincs tesztelés
  @ActiveProfiles("test") // ha tesztelni szeretnél
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "A kapcsolat nem lehet null!");
            System.out.println("Kapcsolat sikeresen létrejött az adatbázissal!");
        } catch (SQLException e) {
            fail("Nem sikerült kapcsolatot létesíteni az adatbázissal: " + e.getMessage());
        }
    }

    @Test
    public void testQuery() {
        try {
            String sql = "SELECT COUNT(*) FROM world"; // Cseréld a megfelelő táblanévre
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
            assertNotNull(count, "A lekérdezés nem adott vissza eredményt!");
            System.out.println("Teszt táblában található sorok száma: " + count);
        } catch (Exception e) {
            fail("Hiba történt a lekérdezés végrehajtása során: " + e.getMessage());
        }
    }
}

