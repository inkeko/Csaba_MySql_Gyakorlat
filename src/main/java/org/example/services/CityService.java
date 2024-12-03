package org.example.services;

import org.example.repository.CityRepository;
import org.example.worldEntity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

  @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> searchByCountryCode(String countryCode) {
        return cityRepository.findByCountryCode(countryCode);
    }

    public List<City> searchByMinPopulation(int minPopulation) {
        return cityRepository.findByMinPopulation(minPopulation);
    }

    public List<City> searchByName(String name) {
        return cityRepository.findByName(name);
    }
}

