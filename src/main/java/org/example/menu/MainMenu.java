package org.example.menu;

import org.example.services.CityService;
import org.example.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu extends BaseMenu {


    private final TableService tableService;
    private final CityService cityService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public MainMenu(Scanner scanner, TableService tableService, CityService cityService) {
        super(scanner);
        this.tableService = tableService;
        this.cityService = cityService;

        if (applicationContext == null) {
            System.err.println("HIBA: ApplicationContext is NULL!");
        } else {
            System.out.println("ApplicationContext initialized successfully.");
        }

        addMenuItem(new MenuItem("Listázza az összes táblanevet") {
            @Override
            public void execute() {
                System.out.println("Összes táblanév: ");
                tableService.getTableNames().forEach(System.out::println);
            }
        });

        addMenuItem(new MenuItem("SQL parancs futtatása") {
            @Override
            public void execute() {
                System.out.println("Lekérdezés futtatása opció kiválasztva.");
                tableService.executeCustomQuery(scanner);
            }
        });

        addMenuItem(new MenuItem("Városok keresése") {
            @Override
            public void execute() {
                // Lekérjük a CitySearchMenu példányt a Spring-től
                CitySearchMenu citySearchMenu = applicationContext.getBean(CitySearchMenu.class);
                citySearchMenu.displayMenu(); // Az almenü elindul
            }
        });

        addMenuItem(new MenuItem("Kilépés") {
            @Override
            public void execute() {
                System.out.println("Kilépés a programból.");
                System.exit(0);
            }
        });
    }
}
