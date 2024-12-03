package org.example.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class BaseMenu {
    private final List<MenuItem> menuItems = new ArrayList<>();
    private final Scanner scanner;
    private boolean exit = false; // Exit flag

    public BaseMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    protected void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public void setExit(boolean exit) {
        this.exit = exit; // Állítsuk be az exit flag-et
    }

    public boolean isExit() {
        return exit; // Ellenőrizzük az exit állapotot
    }

    public void displayMenu() {
        exit = false; // Alapértelmezés szerint ne lépjünk ki
        while (!exit) { // A ciklus addig fut, amíg az exit false
            System.out.println("\nMenü:");
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println((i + 1) + ". " + menuItems.get(i).getLabel());
            }
            System.out.println("0. Kilépés");

            System.out.print("Kérem, válasszon egy opciót: ");
            int choice = getChoice();

            if (choice == 0) {
                System.out.println("Kilépés...");
                setExit(true); // Állítsuk be a kilépést
                break;
            }

            if (choice > 0 && choice <= menuItems.size()) {
                menuItems.get(choice - 1).execute();
            } else {
                System.out.println("Érvénytelen opció, próbálja újra.");
            }
        }
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

