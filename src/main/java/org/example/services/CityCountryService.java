package org.example.services;

import org.example.repository.CityCountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CityCountryService {

    private final CityCountryRepository cityCountryRepository;

    @Autowired
    public CityCountryService(CityCountryRepository cityCountryRepository) {
        this.cityCountryRepository = cityCountryRepository;
    }

    public List<Map<String, Object>> getCitiesByRegionAndCountry(String region, String countryCode) {
        return cityCountryRepository.findCitiesByRegionAndCountry(region, countryCode);
    }
}

