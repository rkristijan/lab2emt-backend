package com.example.emtlab.service.impl;

import com.example.emtlab.model.Author;
import com.example.emtlab.model.Country;
import com.example.emtlab.model.dto.AuthorDto;
import com.example.emtlab.model.exceptions.AuthorNotFound;
import com.example.emtlab.model.exceptions.CountryNotFound;
import com.example.emtlab.repository.AuthorRepository;
import com.example.emtlab.repository.CountryRepository;
import com.example.emtlab.service.AuthorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return this.authorRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Author> save(String name, String surname, Long country) {
        Country country1 = this.countryRepository.findById(country).orElseThrow(() -> new CountryNotFound(country));

        this.authorRepository.deleteByName(name);
        Author author = new Author(name,surname,country1);
        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country1 = this.countryRepository.findById(authorDto.getCountry()).orElseThrow(() -> new CountryNotFound(authorDto.getCountry()));

        this.authorRepository.deleteByName(authorDto.getName());
        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country1);
        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    @Transactional
    public Optional<Author> edit(Long id, String name, String surname, Long country) {
        Country country1 = this.countryRepository.findById(country).orElseThrow(() -> new CountryNotFound(country));

        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFound(id));

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country1);

        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Country country1 = this.countryRepository.findById(authorDto.getCountry()).orElseThrow(() -> new CountryNotFound(authorDto.getCountry()));

        Author author = this.authorRepository.findById(id).orElseThrow(()-> new AuthorNotFound(id));

        author.setName(authorDto.getName());
        author.setCountry(country1);
        author.setSurname(author.getSurname());

        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
