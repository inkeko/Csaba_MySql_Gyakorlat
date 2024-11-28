package org.example.repository;

import org.example.worldEntity.CountryLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, Integer> {
}
