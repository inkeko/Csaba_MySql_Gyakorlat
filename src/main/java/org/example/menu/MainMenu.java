package org.example.menu;

import org.example.services.CityCountryService;
import org.example.services.PdfReaderService;
import org.example.services.SqlExecutionService;
import org.example.services.TableService;
import org.example.utils.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


@Component
public class MainMenu extends BaseMenu {

    private final CitySearchMenu citySearchMenu;
    private final CityCountryService cityCountryService;
    private final PdfReaderService pdfReaderService;
    private final TableService tableService;
    private final SqlExecutionService sqlExecutionService;

    @Autowired
    public MainMenu(Scanner scanner, CitySearchMenu citySearchMenu, CityCountryService cityCountryService, PdfReaderService pdfReaderService, TableService tableService, SqlExecutionService sqlExecutionService) {
        super(scanner);
        this.citySearchMenu = citySearchMenu;
        this.cityCountryService = cityCountryService;
        this.pdfReaderService = pdfReaderService;
        this.tableService = tableService;
        this.sqlExecutionService = sqlExecutionService;

        addMenuItem(new MenuItem("Listázza az összes táblanevet") {
            @Override
            public void execute() {
                System.out.println("Táblanevek listázása...");
                try {
                    List<String> tableNames = tableService.getTableNames();
                    if (tableNames.isEmpty()) {
                        System.out.println("Nincsenek táblák az adatbázisban.");
                    } else {
                        System.out.println("Adatbázis táblái:");
                        tableNames.forEach(System.out::println);
                    }
                } catch (Exception e) {
                    ExceptionHandler.handleException(e, "Hiba történt a táblanevek listázása során.");
                }
            }
        });

        addMenuItem(new MenuItem("SQL parancs futtatása") {
            @Override
            public void execute() {
                System.out.println("SQL parancs futtatása...");
                try {
                    System.out.print("Adja meg az SQL parancsot (csak SELECT engedélyezett): ");
                    String sql = scanner.nextLine();

                    if (!sql.trim().toUpperCase().startsWith("SELECT")) {
                        System.out.println("Csak SELECT típusú lekérdezések engedélyezettek.");
                        return;
                    }

                    List<Map<String, Object>> results = sqlExecutionService.executeQuery(sql);
                    if (results.isEmpty()) {
                        System.out.println("Nincs találat.");
                    } else {
                        results.forEach(row -> System.out.println(row));
                    }
                } catch (Exception e) {
                    ExceptionHandler.handleException(e, "Hiba történt az SQL parancs futtatása során.");
                }
            }
        });

        addMenuItem(new MenuItem("Városok keresése") {
            @Override
            public void execute() {
                citySearchMenu.displayMenu(); // Almenü elindítása
            }
        });

        addMenuItem(new MenuItem("Régiók lakosságának mentése és összegzése") {
            @Override
            public void execute() {
                try {
                    System.out.print("Adja meg a régió nevét (pl. Europe): ");
                    String region = scanner.nextLine();

                    System.out.print("Adja meg a minimális lakosságot: ");
                    int minPopulation = scanner.nextInt();
                    scanner.nextLine();

                    List<Map<String, Object>> data = cityCountryService.getCountriesByRegionAndMinPopulation(region, minPopulation);
                    cityCountryService.saveCountriesToPdf("region_population.pdf", data);

                    int totalPopulation = pdfReaderService.calculateTotalPopulation("region_population.pdf");
                    System.out.println("Összesített lakosság: " + totalPopulation + " fő");
                } catch (Exception e) {
                    ExceptionHandler.handleException(e, "Hiba történt a régiók feldolgozása során.");
                }
            }
        });


        addMenuItem(new MenuItem("Kilépés") {
            @Override
            public void execute() {
                System.out.println("Kilépés...");
                setExit(true); // Kilépés a programból
            }
        });
    }
}
