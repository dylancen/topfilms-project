package com.challenge.project.services;

import com.challenge.project.models.Rating;
import com.challenge.project.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Cacheable(value="topFilms")
    public List<Rating> getTopRatingsForDate(LocalDate date){
        return ratingRepository.findTop10RatingByDateOrderByPosition(date);
    }

    public List<LocalDate> getSavedDates(){
        return ratingRepository.selectAllDates();
    }
}
