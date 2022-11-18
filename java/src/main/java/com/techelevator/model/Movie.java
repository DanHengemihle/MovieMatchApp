package com.techelevator.model;

import java.util.Arrays;
import java.util.List;

public class Movie {

    private String imgUrl;
    private String overview;
    private String releaseDate;
    private List<String> genreIds;
    private String movieId;
    private String title;
    private String backdropImg;

    public Movie(String imgUrl, String overview, String releaseDate, List<String> genreIds, String movieId, String title, String backdropImg) {
        this.imgUrl = imgUrl;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.movieId = movieId;
        this.title = title;
        this.backdropImg = backdropImg;
    }

    public Movie() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<String> genreIds) {
        this.genreIds = genreIds;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropImg() {
        return backdropImg;
    }

    public void setBackdropImg(String backdropImg) {
        this.backdropImg = backdropImg;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imgUrl='" + imgUrl + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", genreIds=" + genreIds +
                ", movieId='" + movieId + '\'' +
                ", title='" + title + '\'' +
                ", backdropImg='" + backdropImg + '\'' +
                '}';
    }
}
