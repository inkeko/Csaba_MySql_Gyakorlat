package org.example.menu;

import org.example.services.CityCountryService;
import org.example.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CitySearchMenu extends BaseMenu {

    private final CityService cityService;
private final CityCountryService cityCountryService;
    @Autowired
    public CitySearchMenu(Scanner scanner, CityService cityService, CityCountryService cityCountryService) {
        super(scanner);
        this.cityService = cityService;
        this.cityCountryService = cityCountryService;

        addMenuItem(new MenuItem("Városok keresése országkód alapján") {
            @Override
            public void execute() {
                System.out.print("Adja meg az országkódot: ");
                String countryCode = scanner.nextLine();
                List<Map<String, Object>> cities = cityService.searchByCountryCode(countryCode);
                if (cities.isEmpty()) {
                    System.out.println("Nincs találat.");
                } else {
                    printTable(cities);
                }
            }
        });

        addMenuItem(new MenuItem("Városok keresése minimális népesség alapján") {
            @Override
            public void execute() {
                System.out.print("Adja meg a minimális népességet: ");
                int minPopulation = scanner.nextInt();
                scanner.nextLine(); // newline elfogyasztása
                List<Map<String, Object>> cities = cityService.searchByMinPopulation(minPopulation);
                if (cities.isEmpty()) {
                    System.out.println("Nincs találat.");
                } else {
                    printTable(cities);
                }
            }
        });

        addMenuItem(new MenuItem("Városok listázása régió és ország alapján") {
            @Override
            public void execute() {
                System.out.print("Adja meg a régió nevét: ");
                String Continent = scanner.nextLine();

                System.out.print("Adja meg az országkódot: ");
                String countryCode = scanner.nextLine();
                // Alakítsd az országkódot csupa nagybetűssé
                countryCode = countryCode.toUpperCase();

                List<Map<String, Object>> results = cityCountryService.getCitiesByRegionAndCountry(Continent, countryCode);
                if (results.isEmpty()) {
                    System.out.println("Nincs találat.");
                } else {
                    printTable(results);
                }
            }
        });

        addMenuItem(new MenuItem("Vissza a főmenübe") {
            @Override
            public void execute() {
                setExit(true); // Kilépés az almenüből
            }
        });
    }

    // Segédmetódus a táblázatszerű kimenethez
    private void printTable(List<Map<String, Object>> data) {
        if (data.isEmpty()) {
            System.out.println("Nincs megjeleníthető adat.");
            return;
        }

        // Mezőnevek kinyerése
        Map<String, Object> firstRow = data.get(0);
        List<String> headers = new ArrayList<>(firstRow.keySet());

        // Maximális szélességek meghatározása
        Map<String, Integer> columnWidths = new HashMap<>();
        for (String header : headers) {
            int maxWidth = header.length(); // Kezdjük az oszlopnév hosszával
            for (Map<String, Object> row : data) {
                Object value = row.get(header);
                if (value != null) {
                    maxWidth = Math.max(maxWidth, value.toString().length());
                }
            }
            columnWidths.put(header, maxWidth);
        }

        // Formázott oszlopnév kiírása
        StringBuilder headerBuilder = new StringBuilder();
        for (String header : headers) {
            int width = columnWidths.get(header);
            headerBuilder.append(String.format("%-" + width + "s | ", header));
        }
        System.out.println(headerBuilder.toString());
        System.out.println("-".repeat(headerBuilder.length())); // Elválasztó vonal

        // Adatok kiírása soronként
        for (Map<String, Object> row : data) {
            StringBuilder rowBuilder = new StringBuilder();
            for (String header : headers) {
                int width = columnWidths.get(header);
                Object value = row.get(header);
                rowBuilder.append(String.format("%-" + width + "s | ", value != null ? value.toString() : ""));
            }
            System.out.println(rowBuilder.toString());
        }
    }
}