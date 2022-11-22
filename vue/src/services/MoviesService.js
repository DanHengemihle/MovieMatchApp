import axios from 'axios';

export default {

  getNewMovies() {
    return axios.get('/newMovies');
  },

}