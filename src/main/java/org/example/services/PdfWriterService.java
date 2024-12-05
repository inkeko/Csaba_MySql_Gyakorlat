package org.example.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PdfWriterService {

    public void saveToPdf(String fileName, List<Map<String, Object>> data) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 750);

                contentStream.showText("Országok Lakossága:");
                contentStream.newLineAtOffset(0, -20);

                for (Map<String, Object> row : data) {
                    String line = row.get("Name") + ": " + row.get("Population");
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -15);
                }

                contentStream.endText();
            }

            document.save(fileName);
            System.out.println("PDF sikeresen mentve: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
