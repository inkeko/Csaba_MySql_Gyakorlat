package org.example.services;

import org.example.repository.CityCountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CityCountryService {

    private final CityCountryRepository cityCountryRepository;
    private final PdfWriterService pdfWriterService;

    @Autowired
    public CityCountryService(CityCountryRepository cityCountryRepository, PdfWriterService pdfWriterService) {
        this.cityCountryRepository = cityCountryRepository;
        this.pdfWriterService = pdfWriterService;
    }

    public List<Map<String, Object>> getCitiesByRegionAndCountry(String region, String countryCode) {
        return cityCountryRepository.findCitiesByRegionAndCountry(region, countryCode);
    }

    public List<Map<String, Object>> getCountriesByRegionAndMinPopulation(String region, int minPopulation) {
        List<Map<String, Object>> result = cityCountryRepository.findCountriesByRegionAndMinPopulation(region, minPopulation);
        System.out.println("Lekérdezés eredménye: " + result); // Ellenőrizd, hogy vannak-e adatok
        return result;
       // return cityCountryRepository.findCountriesByRegionAndMinPopulation(region, minPopulation);
    }

    public void saveCountriesToPdf(String fileName, List<Map<String, Object>> data) {
        pdfWriterService.saveToPdf(fileName, data);
    }

}

