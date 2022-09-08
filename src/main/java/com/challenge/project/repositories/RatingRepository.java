package com.challenge.project.repositories;

import com.challenge.project.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findTop10RatingByDateOrderByPosition(LocalDate date);

    @Query("SELECT r.date FROM Rating AS r GROUP BY r.date ORDER BY r.date DESC")
    List<LocalDate> selectAllDates();
}
