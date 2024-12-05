package org.example.menu;

import org.example.services.CityCountryService;
import org.example.services.PdfReaderService;
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

    @Autowired
    public MainMenu(Scanner scanner, CitySearchMenu citySearchMenu,CityCountryService cityCountryService,PdfReaderService pdfReaderService) {
        super(scanner);
        this.citySearchMenu = citySearchMenu;
        this.cityCountryService = cityCountryService;
        this.pdfReaderService = pdfReaderService;

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

        addMenuItem(new MenuItem("Régiók lakosságának mentése és összegzése") {
            @Override
            public void execute() {
                System.out.print("Adja meg a régió nevét (pl. Europe): ");
                String region = scanner.nextLine();

                System.out.print("Adja meg a minimális lakosságot: ");
                int minPopulation = scanner.nextInt();
                scanner.nextLine(); // newline elfogyasztása

                String fileName = "region_population.pdf";

                // Lekérdezés
                List<Map<String, Object>> data = cityCountryService.getCountriesByRegionAndMinPopulation(region, minPopulation);

                // PDF mentés
                cityCountryService.saveCountriesToPdf(fileName, data);

                // PDF visszaolvasás és összegzés
                int totalPopulation = pdfReaderService.calculateTotalPopulation(fileName);

                System.out.println("Az összesített lakosság: " + totalPopulation + " fő");
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
