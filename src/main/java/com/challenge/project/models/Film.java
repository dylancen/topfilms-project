package com.challenge.project.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Film {
    @Id
    @SequenceGenerator(
            name = "film_sequence",
            sequenceName = "film_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private int year;

    @OneToMany
    private Set<Rating> rating;

    public Film(){
        this.name = "";
        this.year = 0;
    }

    public Film(Long id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Film(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Rating> getRating() {
        return rating;
    }

    public void setRating(Set<Rating> rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name + ", " + year;
    }
}
