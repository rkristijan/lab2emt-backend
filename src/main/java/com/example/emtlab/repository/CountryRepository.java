package com.example.emtlab.repository;

import com.example.emtlab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    Optional<Country> findByName(String name);
    void deleteByName(String name);
}
