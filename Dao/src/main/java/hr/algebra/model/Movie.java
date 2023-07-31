/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author matej.galic
 */
public class Movie {
    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    private int id;
    private String title;
    private LocalDateTime publishedDate;
    private String description;
    private String picturePath;
    private Person director;
    private List<Person> actors;
    private int duration;
    private int year;

    public Movie(String title, LocalDateTime publishedDate, String description, String picturePath, Person director, List<Person> actors, int duration, int year) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.picturePath = picturePath;
        this.director = director;
        this.actors = actors;
        this.duration = duration;
        this.year = year;
    }

    public Movie(int id, String title, LocalDateTime publishedDate, String description, String picturePath, Person director, List<Person> actors, int duration, int year) {
        this(title, publishedDate, description, picturePath, director, actors, duration, year);
        this.id = id;      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return id + " - " + title + " - " + publishedDate + " - " + description;
    }

}