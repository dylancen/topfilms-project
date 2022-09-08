package com.challenge.project.controllers;

import com.challenge.project.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "ratings")
public class RatingController {
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public String getDates(Model model){
        model.addAttribute("dates", ratingService.getSavedDates());
        return "dates";
    }

    @PostMapping
    public String showRating(@RequestParam(name = "date") String date, Model model){
        model.addAttribute("ratings", ratingService.getTopRatingsForDate(LocalDate.parse(date)));
        return "filmrating";
    }
}
