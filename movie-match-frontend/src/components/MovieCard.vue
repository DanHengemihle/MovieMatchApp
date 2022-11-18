<template>
    <div id="movieCard">
    <!-- h1 with title of whatever the row is for here (passed in when called) -->
    <!-- v for for movie cards here -->
    <div id="movieImg"> movie poster </div>
    <h3 id="movieTitleText">Movie Title</h3>
    <h4 id="tasteMatchText">A 90% TasteMatch says this movie is COOL</h4>
    <Button type="button" id="watchlistButton">Add to watchlist</Button>
    <!-- maybe not make trailer link a button -->
    <Button type="button" id="viewTrailerButton">View Trailer</Button>
    </div>
</template>

<script>
export default {
  components: { },
  name: "movie-card",
  props: ["movie"],
  data()  {
    return  {
    }
  }
    
}
</script>

<style>
#movieCard {
    background-color: green;
    height: 375px;
    width: 175px;
}

#movieImg {
    background-color: red;
    height: 200px;
}
#movieTitleText {
    position: relative;
    bottom: 15px;
}

#tasteMatchText {
    position: relative;
    bottom: 30px;
}

#watchlistButton {
    position: relative;
    bottom: 40px;
}

#viewTrailerButton {
    position: relative;
    bottom: 30px;
}

</style>