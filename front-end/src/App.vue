<template>
  <v-app id="app">
    <v-app-bar app clipped-left color="deep-purple">
      <v-app-bar-nav-icon @click="drawer = !drawer" />
      <span class="title" style="margin-right: 100px; color: #FFF"><span class="font-weight-light">my</span>Posts</span>
      <v-spacer />
      <v-btn v-if="isAuthenticated" icon large white class="white--text" @click="onLogout"><v-icon>mdi-logout</v-icon></v-btn>
      <v-btn v-else class="deep-purple lighten-4 white--text"><router-link style="color: #8E24AA; text-decoration: none" :to="{path: '/signin'}">SignIn / SignUp</router-link></v-btn>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" app clipped color="deep-purple lighten-4">
      <v-list dense class="deep-purple lighten-4">
        <template v-for="(item, i) in items">
          <v-divider v-if="item.divider && (isAuthenticated || !item.auth)" :key="i" dark class="my-4"/>
          <v-list-item v-else-if="isAuthenticated || !item.auth" :key="i" link @click="navigateTo(item)">
            <v-list-item-action>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title class="white--text">
                {{ item.text }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </template>
      </v-list>
    </v-navigation-drawer>

    <v-content>
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
import { mapActions } from 'vuex'

import { deleteCookie, getCookie } from './plugins/script.js'

export default {
    props: {
      source: String,
    },
    data: () => ({
      drawer: null,
      items: [
        { icon: 'mdi-home', text: 'Dashboard', path: '/', auth: false },
        { divider: true, auth: false},
        { icon: 'mdi-post', text: 'Posts', path: '/posts', auth: true },
        { divider: true, auth: true},
        { icon: 'mdi-magnify', text: 'Search', path: '/search', auth: true},
        { divider: true, auth: true }
      ],
    }),
    methods: {
      ...mapActions(['destroyToken', 'getUsername']),
      navigateTo(item) {
        window.location.href =  item.path
      },
      onLogout(){
        this.destroyToken()
      }
    },
    computed: {
      ...mapGetters({
        isAuthenticated: 'isAuthenticated'
      })
    }
  }
</script>

<style>
.header-title{
    font-family: 'Arizonia', cursive;
    color: #BA68C8;
    margin-top: 10px;
    text-align: center;
    font-size: 62px;
}
</style>
