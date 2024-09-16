import { createRouter, createWebHistory } from 'vue-router'
import MusicMainComponent from '../components/MusicMainComponent.vue'
import LikedSongs from '../components/LikedSongs.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: MusicMainComponent
  },
  {
    path: '/likedSongs',
    name: 'LikedSongs',
    component: LikedSongs
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router