package com.example.emtlab.service.impl;

import com.example.emtlab.model.Country;
import com.example.emtlab.model.dto.CountryDto;
import com.example.emtlab.model.exceptions.CountryNotFound;
import com.example.emtlab.repository.CountryRepository;
import com.example.emtlab.service.CountryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> findByName(String name) {
        return this.countryRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Country> save(String name, String continent) {
        this.countryRepository.deleteByName(name);
        Country country = new Country(name,continent);
        this.countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        this.countryRepository.deleteByName(countryDto.getName());
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        this.countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    @Transactional
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = this.countryRepository.findById(id).orElseThrow(()-> new CountryNotFound(id));
        country.setContinent(continent);
        country.setName(name);

        this.countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = this.countryRepository.findById(id).orElseThrow(()-> new CountryNotFound(id));

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        this.countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
