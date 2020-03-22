import Vue from 'vue'
import VueRouter from 'vue-router'
import KfwApp from '../components/KfwApp.vue'
import KfwLogin from '../components/KfwLogin.vue';
Vue.use(VueRouter)

export const routes = [
${routes}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
