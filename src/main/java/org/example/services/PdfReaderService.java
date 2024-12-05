package org.example.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PdfReaderService {

    public int calculateTotalPopulation(String fileName) {
        int totalPopulation = 0;

        try (PDDocument document = PDDocument.load(new File(fileName))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            // Szöveg sorokra bontása
            String[] lines = text.split("\n");
            for (String line : lines) {
                if (line.contains(":")) { // Csak azokat a sorokat dolgozzuk fel, amelyek tartalmaznak ":"
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String populationString = parts[1].trim();
                        if (!populationString.isEmpty()) { // Ha a szám nem üres
                            try {
                                int population = Integer.parseInt(populationString);
                                totalPopulation += population;
                            } catch (NumberFormatException e) {
                                System.err.println("Nem megfelelő formátumú adat: " + populationString);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalPopulation;
    }
}