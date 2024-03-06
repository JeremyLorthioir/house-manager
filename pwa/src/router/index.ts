import { createRouter, createWebHistory } from 'vue-router'
import HouseManager from '../views/HouseManagerView.vue'
import Home from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Home
    },
    {
      path: '/house-manager',
      name: 'House Manager',
      component: HouseManager
    },
    {
      path: '/about',
      component: () => import('../views/AboutView.vue')
    }
  ]
})

export default router
