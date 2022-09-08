package com.challenge.project.repositories;

import com.challenge.project.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findFilmByNameAndYear(String name, int year);
}
