package com.example.tvbox;

public class Movie {
    private String title;
    private String posterUrl;
    private String year;
    private String genre;
    private String director;
    private String actors;
    private String plot;

    public Movie(String title, String posterUrl, String year, String genre, String director, String actors, String plot) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
