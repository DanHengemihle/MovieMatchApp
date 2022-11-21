package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Value("${movieDatabase.api.key}")
    private String apiKey;
    @Value("${movieDatabase.api.url}")
    private String apiUrl;




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
//        String response =
//                restTemplate.getForObject(url, String.class);
//        System.out.println(response);

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

                //GENRE IDS NOT WORKING
                //int height = root.path("images").path("preview").path("height").asInt();



                String movieId = root.path(i).path("id").asText();
                String title = root.path(i).path("title").asText();
                String backdropImg = root.path(i).path("backdrop_path").asText();


                // in order to get back just the gif, we have to format this
                // margaret did some investigation to figure this out
                String movieUrl = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;

                JsonNode genres = root.get("genre_ids");
//                System.out.println(genres);
                ArrayNode arrayNode = (ArrayNode)root.get("genre_ids");
//                for(int j = 0; j < genres.size(); j++) {

                    List<String> genreIds = root.findValuesAsText("genre_ids");

                Movie movie = new Movie(imgUrl, overview, releaseDate, genreIds, movieId, title, backdropImg);
                movieList.add(movie);
         //   }
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
            String json = objectMapper.writeValueAsString(jsonNode);

            String backdropPath = jsonNode.get("backdrop_path").asText();
            String genres = jsonNode.get("genres").asText();
            String movieId = jsonNode.get("id").asText();
            String overview = jsonNode.get("overview").asText();
            String posterPath = jsonNode.get("poster_path").asText();
            String releaseDate = jsonNode.get("release_date").asText();
            String runtime = jsonNode.get("runtime").asText();
            String title = jsonNode.get("title").asText();
            String trailerUrl = jsonNode.get("videos").asText();

                String movieUrl = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;

                return new MovieDetails(backdropPath, genres, movieId, overview, posterPath, releaseDate, runtime, title, trailerUrl);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> getNewMovies() {
        List<Movie> newMoviesList = new ArrayList<>();

        LocalDate searchDate = LocalDate.now().minusMonths(1);

        //call api and return only movies that were released in the last month
        //get current date, subtract one from month, put that in search url

//        String sampleUrl = "https://api.themoviedb.org/3/discover/movie?api_key=1860d7aac96c2d5d65b5d6760a855c9b&sort_by=popularity.desc&include_adult=false&primary_release_date.gte=2022-10-18";
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + apiKey + "&sort_by=popularity.desc&include_adult=false&primary_release_date.gte=" +
                searchDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = new HttpEntity<>("");
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        JsonNode jsonNode;

        try {  // needed for the objectMapper.readTree method
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("results");
            for (int i = 0; i < root.size(); i++){
                String imgUrl = root.path(i).path("poster_path").asText();
                String overview = root.path(i).path("overview").asText();
                String releaseDate = root.path(i).path("release_date").asText();

                //GENRE IDS NOT WORKING
                //int height = root.path("images").path("preview").path("height").asInt();



                String movieId = root.path(i).path("id").asText();
                String title = root.path(i).path("title").asText();
                String backdropImg = root.path(i).path("backdrop_path").asText();

                JsonNode genres = root.get("genre_ids");
//                System.out.println(genres);
                ArrayNode arrayNode = (ArrayNode)root.get("genre_ids");
//                for(int j = 0; j < genres.size(); j++) {

                List<String> genreIds = root.findValuesAsText("genre_ids");

                Movie movie = new Movie(imgUrl, overview, releaseDate, genreIds, movieId, title, backdropImg);
                newMoviesList.add(movie);
                //   }
            }

        } catch (JsonProcessingException e) {
            System.out.println(response);
            System.out.println(url);
            e.getMessage();
            e.printStackTrace();
            e.getOriginalMessage();
        }
        return newMoviesList;

    }


}




