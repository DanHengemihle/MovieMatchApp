<template>
  <div class="home">
    <NavBar/>
    <div id="homeContainer">
      <h1>this is the homepage</h1>
      <MoviesRow/>
      <!-- your taste -->
      <MoviesRow/>
      <!-- watchlist -->
      <MoviesRow/>

    </div>

  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from '../components/NavBar.vue';
import MoviesRow from '../components/MoviesRow.vue';

export default {
  name: 'HomepageView',
  components: {
    NavBar,
    MoviesRow,
  }
}
</script>

<style>
#homeContainer {
  margin-left: 17%;
  margin-right: 17%;
}



</style>
