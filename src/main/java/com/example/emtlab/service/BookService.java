package com.example.emtlab.service;

import com.example.emtlab.model.Book;
import com.example.emtlab.model.dto.BookDto;
import com.example.emtlab.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> findByName(String name);
    Optional<Book> save(String name, Category category, Integer availableCopies, Long author);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id,String name, Category category, Integer availableCopies,Long author);
    Optional<Book> edit(Long id, BookDto bookDto);
    void deleteById(Long id);
}
