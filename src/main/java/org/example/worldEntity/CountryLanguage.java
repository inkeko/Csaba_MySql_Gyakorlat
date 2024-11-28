package org.example.worldEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "countrylanguage")
@IdClass(CountryLanguageId.class) // Összetett kulcs
public class CountryLanguage {

    @Id
    @Column(name = "CountryCode", length = 3, nullable = false)
    private String countryCode;

    @Id
    @Column(name = "Language", length = 30, nullable = false)
    private String language;

    @Column(name = "IsOfficial", nullable = false)
    private String isOfficial;

    @Column(name = "Percentage", nullable = false, precision = 4, scale = 1)
    private Double percentage;

    // Getters and Setters
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}