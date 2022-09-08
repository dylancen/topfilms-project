package com.challenge.project.models;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class Rating {



    @Id
    @SequenceGenerator(
            name = "date_sequence",
            sequenceName = "date_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "date_sequence"
    )
    private Long id;
    private LocalDate date;
    private double rating;
    private int position;
    private int votecount;

    @ManyToOne
    private Film film;

    public Rating(){
        this.date = LocalDate.now();
        this.rating = 0;
        this.position = 0;
        this.votecount = 0;
    }

    public Rating(LocalDate date, double rating, int position, int votecount, Film film){
        this.date = date;
        this.rating = rating;
        this.position = position;
        this.votecount = votecount;
        this.film = film;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getRating() {
        return rating;
    }

    public int getPosition() {
        return position;
    }

    public int getVotecount() {
        return votecount;
    }

    public Film getFilm() {
        return film;
    }

    public void setDate(LocalDate ratingDate) {
        this.date = date;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int compareTo(Rating rating){
        return Integer.compare(rating.position, rating.position);
    }
}
