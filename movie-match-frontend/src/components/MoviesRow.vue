<template>
    <div id="moviesRow">
    <!-- h1 with title of whatever the row is for here (passed in when called) -->
    <!-- test data  -->
    <h2>New Movies</h2>
    <!-- v for for movie cards here -->
    <!-- more test data  -->
    <div id="moviesList">
        <MovieCard/>
        <MovieCard/>
        <MovieCard/>
        <MovieCard/>
        <MovieCard/>
        <MovieCard/>
    </div>
    </div>
</template>

<script>
import MovieCard from '../components/MovieCard.vue';

export default {
  components: {
    MovieCard,
   },
  name: "movies-row",
  data()  {
    return  {
    }
  }
    
}
</script>

<style>
h2 {
    padding: 20px;
    text-align: left;
}
#moviesRow {
    background-color: blue;
    height: 51vh;
}
#moviesList {
    display: flex;
    justify-content: space-around;
}


</style>