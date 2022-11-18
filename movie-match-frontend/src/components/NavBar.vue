

<template>
    <div id="navBar">
    <div id="spacer"/>
    <router-link v-bind:to= "{ name: 'HomepageView' }">Home </router-link>
    <form id="searchBar" action="" class="search-bar"
    >
    <input type="text" placeholder="Search movies" />
    <img src="../assets/magnifying-glass.png"/>
    </form>
    <router-link v-bind:to="{name: 'SignInView'}"> Sign in</router-link>


    <div id="spacer"/>
    </div>
</template>

<script>
export default {
  components: { },
  name: "nav-bar",
  data()  {
    return  {
    }
  }
    
}
</script>

<style>
#navBar {
  display: flex;
  height: 50px;
  justify-content: space-between;
  align-items: center;
  background-color: grey;
}

#searchBar {
  position: relative;
  width: 240px;
}

#searchBar > input {
  border: solid 1px;
  border-radius: 20px;
  padding: 2px;
  padding-left: 10px;
  display: block;
  width: 100%;
  box-sizing: border-box; 
}

#searchBar > img {
  position: absolute;
  top: 2px;
  right: 5px;
  width: 20px;
}


</style>