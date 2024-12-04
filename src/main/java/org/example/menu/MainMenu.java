package org.example.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu extends BaseMenu {

    private final CitySearchMenu citySearchMenu;

    @Autowired
    public MainMenu(Scanner scanner, CitySearchMenu citySearchMenu) {
        super(scanner);
        this.citySearchMenu = citySearchMenu;

        addMenuItem(new MenuItem("Listázza az összes táblanevet") {
            @Override
            public void execute() {
                System.out.println("Táblanevek listázása...");
                // Táblák lekérdezésének logikája
            }
        });

        addMenuItem(new MenuItem("SQL parancs futtatása") {
            @Override
            public void execute() {
                System.out.println("SQL parancs futtatása...");
                // SQL lekérdezés végrehajtása
            }
        });

        addMenuItem(new MenuItem("Városok keresése") {
            @Override
            public void execute() {
                citySearchMenu.displayMenu(); // Almenü elindítása
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
