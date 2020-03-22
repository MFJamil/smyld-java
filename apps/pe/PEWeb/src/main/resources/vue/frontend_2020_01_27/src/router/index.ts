import Vue from 'vue'
import VueRouter from 'vue-router'
import KfwApp from '../module/ratingAnzeige/KfwApp.vue';
import RatingHistory from '../module/ratingAnzeige/panels/RatingHistory.vue';
import KfwLogin from '../components/panels/KfwLogin.vue';

Vue.use(VueRouter)

export const routes = [
  {
    path: '/',
    name: 'mainApp',
    component: KfwApp,
    default: true,
    children:[
      
      {
        path: '/history',
        name: 'history',
        component: RatingHistory,
      }   
    ],
  },
  {
    path: '/login',
    name: 'login',
    component: KfwLogin,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
