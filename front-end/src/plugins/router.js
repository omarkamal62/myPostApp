import Vue from 'vue'
import VueRouter from 'vue-router'

import Posts from '../views/Posts'
import Dashboard from '../views/Dashboard'
import Login from '../views/Login'
import Register from '../views/Register'
import Logout from '../views/Logout'
import MyPosts from '../views/MyPosts'
import Search from '../views/Search'

import store from './store/index'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Dashboard',
    component: Dashboard
  },
  {
    path: '/posts',
    name: 'Posts',
    component: Posts,
    beforeEnter (to, from ,next){
      if(store.getters.access_token){
        next()
      }else{
        next('/signin')
      }
    }
  },
  {
    path: '/signin',
    name: 'Login Page',
    component: Login,
    beforeEnter (to, from ,next){
      if(!store.getters.access_token){
        next()
      }else{
        next('/logoutpage')
      }
    }
  },
  {
    path: '/signup',
    name: 'Register',
    component: Register,
    beforeEnter (to, from ,next){
      if(!store.getters.access_token){
        next()
      }else{
        next('/logoutpage')
      }
    }
  },
  {
    path: '/logoutpage',
    name: 'Logout',
    component: Logout
  },
  {
    path: '/myposts',
    name: 'My Posts',
    component: MyPosts,
    beforeEnter (to, from ,next){
      if(store.getters.access_token){
        next()
      }else{
        next('/signin')
      }
    }
  },
  {
    path: '/search',
    name: 'Search',
    component: Search,
    beforeEnter (to, from ,next){
      if(store.getters.access_token){
        next()
      }else{
        next('/signin')
      }
    }
  }
]

let router = new VueRouter({
  routes,
  mode: 'history'
})


export default router
