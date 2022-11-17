package com.techelevator.controller;

import com.techelevator.model.Movie;
import com.techelevator.model.MovieDetails;
import com.techelevator.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movie")
    public List<Movie> searchMovieTitles(@RequestParam String query){
        return movieService.getTitleSearchResults(query);
    }

    @GetMapping("/movie/{id}")
    public MovieDetails getMovieDetails(@PathVariable String id){
        return movieService.getMovieDetails(id);
    }



}
