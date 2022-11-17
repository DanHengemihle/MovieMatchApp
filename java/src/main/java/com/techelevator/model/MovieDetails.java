package com.techelevator.model;

public class MovieDetails {

    private String backdropPath;
    private String genres;
    private String id;
    private String overview;
    private String posterPath;
    private String releaseDate;
    private String runtime;
    private String title;
    private String trailerUrl;

    public MovieDetails() {
    }

    public MovieDetails(String backdropPath, String genres, String id, String overview, String posterPath, String releaseDate,
                        String runtime, String title, String trailerUrl) {
        this.backdropPath = backdropPath;
        this.genres = genres;
        this.id = id;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.title = title;
        this.trailerUrl = trailerUrl;
    }


    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "backdropPath='" + backdropPath + '\'' +
                ", genres='" + genres + '\'' +
                ", id='" + id + '\'' +
                ", overview='" + overview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", runtime='" + runtime + '\'' +
                ", title='" + title + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                '}';
    }
}
