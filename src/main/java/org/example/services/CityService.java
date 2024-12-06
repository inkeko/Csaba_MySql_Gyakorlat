package org.example.services;

import org.example.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<Map<String, Object>> searchByCountryCode(String countryCode) {
        return cityRepository.findByCountryCode(countryCode);
    }

    public List<Map<String, Object>> searchByMinPopulation(int minPopulation) {
        return cityRepository.findByMinPopulation(minPopulation);
    }

    public List<Map<String, Object>> searchByName(String name) {
        return cityRepository.findByName(name);
    }
}
