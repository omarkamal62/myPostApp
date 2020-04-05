<template>
    <div>
          <h1 class="header-title">My Posts</h1>
          <div>
            <v-btn class="mx-6 my-6 text deep-purple"><router-link style="color: white; text-decoration: none" :to="{path: 'posts'}">Add Post</router-link></v-btn>
        </div>
        <app-post-grid :posts="posts"></app-post-grid>
        <p v-if="posts.length === 0" class="header-title" style="color: #C62828">There are no Posts yet.</p>
    </div>
</template>

<script>
import Post from '../components/Post'
import { mapActions } from 'vuex'

import PostGrid from '../components/PostGrid'
 
export default {
    created(){
      this.getMyPosts();
    },
    components:{
        appPostGrid: PostGrid
    },
    computed: {
        posts(){
            return this.$store.getters.myPosts
        }
    },
    methods: {
        ...mapActions({
        getMyPosts: 'getMyPosts',
      })
    }
}
</script>