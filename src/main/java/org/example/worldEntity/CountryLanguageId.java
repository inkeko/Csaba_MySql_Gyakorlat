package org.example.worldEntity;

import java.io.Serializable;
import java.util.Objects;

public class CountryLanguageId implements Serializable {

    private String countryCode;
    private String language;

    public CountryLanguageId() {
    }

    public CountryLanguageId(String countryCode, String language) {
        this.countryCode = countryCode;
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryLanguageId that = (CountryLanguageId) o;
        return Objects.equals(countryCode, that.countryCode) && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, language);
    }
}