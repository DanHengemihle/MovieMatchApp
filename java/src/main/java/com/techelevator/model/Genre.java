package com.techelevator.model;

public class Genre {

    private String genreId;
    private String genre;

    public Genre() {
    }

    public Genre(String genreId, String genre) {
        this.genreId = genreId;
        this.genre = genre;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId='" + genreId + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
