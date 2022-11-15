package com.techelevator.controller;

import com.techelevator.model.Movie;
import com.techelevator.service.MovieService;
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
    public List<Movie> searchMovies(@RequestParam String query){
        return movieService.getSearchResults(query);
    }



}
