package com.example.emtlab.repository;

import com.example.emtlab.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, String> {

    @Query("select u from LibraryUser u")
    List<LibraryUser> listAll();
    Optional<LibraryUser> findByUsernameAndPassword(String username, String password);
    Optional<LibraryUser> findByUsername(String username);

}
