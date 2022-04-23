package com.example.emtlab.model.dto;

import com.example.emtlab.model.enums.Category;
import lombok.Data;

@Data
public class BookDto {

    private String name;
    private Long author;
    private Category category;
    private Integer availableCopies;

    public BookDto(String name, Long author, Category category, Integer availableCopies) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.availableCopies = availableCopies;
    }

    public BookDto() {
    }
}
