package com.movieMatch.Movie.Match.controller;

import com.movieMatch.Movie.Match.model.Movie;
import com.movieMatch.Movie.Match.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movie")
    public List<Movie> returnAllMovies(@RequestParam String query){
        return movieService.getSearchResults(query);
    }



}
