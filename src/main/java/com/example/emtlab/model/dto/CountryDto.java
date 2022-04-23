package com.example.emtlab.model.dto;

import lombok.Data;

@Data
public class CountryDto {

    private String continent;
    private String name;

    public CountryDto(String continent, String name) {
        this.continent = continent;
        this.name = name;
    }

    public CountryDto() {
    }
}
