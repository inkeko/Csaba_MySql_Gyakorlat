package org.example.menu;

import org.example.services.CityService;
import org.example.worldEntity.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;



public class CitySearchMenu extends BaseMenu {

    private final CityService cityService;

    public CitySearchMenu(Scanner scanner, CityService cityService) {
        super(scanner);
        this.cityService = cityService;

        if (cityService == null) {
            System.err.println("CityService is NULL!");
        } else {
            System.out.println("CityService initialized successfully.");
        }


        addMenuItem(new MenuItem("Városok keresése országkód alapján") {
            @Override
            public void execute() {
                System.out.print("Adja meg az országkódot: ");
                String countryCode = scanner.nextLine();
                List<City> cities = cityService.searchByCountryCode(countryCode);
                if (cities.isEmpty()) {
                    System.out.println("Nincs találat.");
                } else {
                    cities.forEach(System.out::println);
                }
            }
        });

        addMenuItem(new MenuItem("Városok keresése minimális népesség alapján") {
            @Override
            public void execute() {
                System.out.print("Adja meg a minimális népességet: ");
                int minPopulation = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                List<City> cities = cityService.searchByMinPopulation(minPopulation);
                if (cities.isEmpty()) {
                    System.out.println("Nincs találat.");
                } else {
                    cities.forEach(System.out::println);
                }
            }
        });

        addMenuItem(new MenuItem("Városok keresése név alapján") {
            @Override
            public void execute() {
                System.out.print("Adja meg a város nevének részletét: ");
                String name = scanner.nextLine();
                List<City> cities = cityService.searchByName(name);
                if (cities.isEmpty()) {
                    System.out.println("Nincs találat.");
                } else {
                    cities.forEach(System.out::println);
                }
            }
        });

        addMenuItem(new MenuItem("Vissza a főmenübe") {
            @Override
            public void execute() {
                System.out.println("Visszatérés a főmenübe...");
                setExit(true);
            }
        });
    }
}

