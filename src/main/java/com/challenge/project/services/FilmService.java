package com.challenge.project.services;

import com.challenge.project.models.Rating;
import com.challenge.project.repositories.RatingRepository;
import com.challenge.project.repositories.FilmRepository;
import com.challenge.project.models.Film;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final RatingRepository ratingRepository;


    @Autowired
    public FilmService(FilmRepository filmRepository, RatingRepository ratingRepository) {
        this.filmRepository = filmRepository;
        this.ratingRepository = ratingRepository;
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void getTopFilmsFromSite() throws IOException {

        Document doc = Jsoup.connect("https://www.kinopoisk.ru/lists/movies/top250/")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36")
                .get();

        Elements main = doc.select("main");
        int position = 1;

        for(Element div : main.select("div[class*=styles_content]")){
            extractAndSaveDivData(position, div);
            position++;
        }
    }

    private void extractAndSaveDivData(int position, Element div){

        String title = div.select("span[class*=styles_mainTitle]").text();

        String[] data = div.select("span[class*=desktop-list-main-info_secondaryText]")
                .text()
                .split(",");
        int year = Integer.parseInt(data[data.length - 2].trim());

        float rating = Float.parseFloat(div.select("span[class*=styles_kinopoiskValuePositive]")
                .text());

        int votes = Integer.parseInt(div.select("span[class*=styles_kinopoiskCount__2]")
                .text()
                .replaceAll(" ", ""));

        Optional<Film> foundFilm = filmRepository.findFilmByNameAndYear(title, year);
        if(!foundFilm.isPresent()){
            Film film = new Film(title, year);
            film = filmRepository.save(film);
            ratingRepository.save(new Rating(LocalDate.now(), rating, position, votes, film));
        }else{
            ratingRepository.save(new Rating(LocalDate.now(), rating, position, votes, foundFilm.get()));
        }
    }
}
