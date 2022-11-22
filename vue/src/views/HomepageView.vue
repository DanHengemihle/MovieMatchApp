<template>
  <div class="home">
    <NavBar/>
    <div id="homeContainer">
      <h1>this is the homepage</h1>
      <MoviesRow/>
      <!-- your taste -->
      <!-- <MoviesRow/> -->
      <!-- watchlist -->
      <!-- <MoviesRow/> -->

    </div>

  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from '../components/NavBar.vue';
import MoviesRow from '../components/MoviesRow.vue';
import moviesService from '../services/MoviesService.js';

export default {
  name: 'HomepageView',
  components: {
    NavBar,
    MoviesRow,
  },
  data() {
    return {
      newMovies: [],
      errorMessage: "",
    }
  },
  methods: {
    // retrieve new movies needs to be inside of moviesRow component.
    // need way to make each row call different methods. pass them into rows?
    // do it in rows? i dunno

    retrieveNewMovies() {
      moviesService.getNewMovies.then((response) => {
      this.retrieveNewMovies = response.data;
      this.$store.commit("SET_NEW_MOVIES", response.data);
    })
    .catch((error) => {
      if (error.response && error.response.status === 404) {
        alert("Error retrieving new movies");
      }
    });
    }
  },
  created() {

  }
}
</script>

<style>
#homeContainer {
  margin-left: 17%;
  margin-right: 17%;
}



</style>
