package org.example.menu;

import org.example.services.TableService;

import java.util.Scanner;

public class MainMenu extends BaseMenu {

    private final TableService tableService;

    public MainMenu(Scanner scanner, TableService tableService) {
        super(scanner);
        this.tableService = tableService;

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
