package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Value("${movieDatabase.api.key}")
    private String apiKey;
    @Value("${movieDatabase.api.url}")
    private String apiUrl;

    @Value("${youtube.base.url}")
    private String youtubeBaseUrl;




    public List<Movie> getTitleSearchResults(String searchString){
     //   String url = "https://api.themoviedb.org/3/discover/movie?api_key=1860d7aac96c2d5d65b5d6760a855c9b&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate";
           String url = apiUrl + apiKey + "&query=" + searchString;
//    HttpHeaders headers = new HttpHeaders();
//    headers.add("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZTRhYjRmOGVkNGYzZGYxYzg0NTJlYjVhYzc3MjNiZSIsInN1YiI6IjYzNmFiYmEwNGMx"
//            + "YmIwMDA3ZDI5MDI1ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.LZGGVCRZhPaW5wH2FOvb27A62eV0j1nIjegQkUNsIlU");
        // class for querying external APIs
        RestTemplate restTemplate = new RestTemplate();
        // because we have to "pick" through the nodes to get our info
        // we need to do some setup, including an httpentity object
        HttpEntity<String> httpEntity = new HttpEntity<>("");
        // Object mapper will let us walk through the nodes in the response
        ObjectMapper objectMapper = new ObjectMapper();
        // json node object also needed to walk through the response
        JsonNode jsonNode;
        // make the call to the api using restTemplate.exchange
        // sends back a response entity object of type String
    ResponseEntity<String> response =
            restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        // create an empty list of gifs
        List<Movie> movieList = new ArrayList<>();

        try {  // needed for the objectMapper.readTree method
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("results");
            for (int i = 0; i < root.size(); i++){
                //root is the json node starting at 'data'
                //path(i) says which object in the array we are going to
                // path("id") says which key are we grabbing data from
                String imgUrl = root.path(i).path("poster_path").asText();
                String overview = root.path(i).path("overview").asText();
                String releaseDate = root.path(i).path("release_date").asText();
                String movieId = root.path(i).path("id").asText();
                String title = root.path(i).path("title").asText();
                String backdropImg = root.path(i).path("backdrop_path").asText();
                List<String> genreIds = new ArrayList<>();
                for(JsonNode genre : root.path(i).path("genre_ids")){
                    genreIds.add(genre.asText());
                }
                // in order to get back just the gif, we have to format this
                // margaret did some investigation to figure this out
                String movieUrl = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;

                Movie movie = new Movie(imgUrl, overview, releaseDate, genreIds, movieId, title, backdropImg);
                movieList.add(movie);
            }

        } catch (JsonProcessingException e) {
            System.out.println(response);
            System.out.println(url);
            e.getMessage();
            e.printStackTrace();
            e.getOriginalMessage();
        }
        return movieList;
    }

    public MovieDetails getMovieDetails(String id) {

        String url = "http://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey + "&append_to_response=videos";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = new HttpEntity<>("");
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        JsonNode jsonNode;

        try {  // needed for the objectMapper.readTree method
            jsonNode = objectMapper.readTree(response.getBody());
            String backdropPath = jsonNode.get("backdrop_path").asText();
            String movieId = jsonNode.get("id").asText();
            String overview = jsonNode.get("overview").asText();
            String posterPath = jsonNode.get("poster_path").asText();
            String releaseDate = jsonNode.get("release_date").asText();
            String runtime = jsonNode.get("runtime").asText();
            String title = jsonNode.get("title").asText();
            List<String> genres = jsonNode.get("genres").findValuesAsText("name");
            List<String> trailerUrlKeys = jsonNode.get("videos").findValuesAsText("key");
            List<String> trailerUrls = new ArrayList<>();
            for(String trailerUrl : trailerUrlKeys){
                trailerUrls.add(String.format(youtubeBaseUrl + trailerUrl));
            }
            String movieUrl = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey;

               return new MovieDetails(backdropPath, genres, movieId, overview, posterPath, releaseDate, runtime, title, trailerUrls);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }




}




