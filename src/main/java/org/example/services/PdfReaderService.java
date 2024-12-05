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

            String[] lines = text.split("\n");
            for (String line : lines) {
                if (line.contains(":")) {
                    String[] parts = line.split(":");
                    String populationString = parts[1].trim();
                    totalPopulation += Integer.parseInt(populationString);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return totalPopulation;
    }
}
