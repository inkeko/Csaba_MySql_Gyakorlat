package org.example.menu;

import java.util.Scanner;

public class MainMenu extends BaseMenu {

    public MainMenu(Scanner scanner) {
        super(scanner);

        addMenuItem(new MenuItem("Táblázatok listázása") {
            @Override
            public void execute() {
                System.out.println("Táblázatok listázása opció kiválasztva.");
                // itt kell meghivni a metodust ami lekérdezi és kinyomtatja az eredményt
            }
        });

        addMenuItem(new MenuItem("Lekérdezés futtatása") {
            @Override
            public void execute() {
                System.out.println("Lekérdezés futtatása opció kiválasztva.");
                // Hívhatunk egy metódust, ami végrehajt egy SQL lekérdezést.
            }
        });

        addMenuItem(new MenuItem("Egyéni opció") {
            @Override
            public void execute() {
                System.out.println("Egyéni opció kiválasztva.");
                // Ide kerülhet más, testreszabott logika.
            }
        });
    }
}
