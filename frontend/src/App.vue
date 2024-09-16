<script>
import LikedSongs from './components/LikedSongs.vue';
import MusicMainComponent from './components/MusicMainComponent.vue';


export default {
  data() {
    return {
      isLikedPage: true,
    };
  },
}
</script>

<template>
  <div id="app">
    <div class="btn-box" v-if="isLikedPage" @click="isLikedPage = !isLikedPage">
      <router-link to="/likedSongs" >Посмотреть лайкнутые песни</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style scoped>
.btn-box {
	margin-top: 100px;
	margin-left: 50px;
    background: darkgrey;
    width: 250px;
    height: 60px;
    border-radius: 5px;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    -webkit-box-shadow: 0px 5px 10px 2px rgba(34, 60, 80, 0.2);
    -moz-box-shadow: 0px 5px 10px 2px rgba(34, 60, 80, 0.2);
    box-shadow: 0px 5px 10px 2px rgba(34, 60, 80, 0.2);
}
</style>
