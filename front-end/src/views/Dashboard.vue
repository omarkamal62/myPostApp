<template>
    <div>
          <h1 class="header-title">Dashboard</h1>
          <app-post-grid :posts="posts"></app-post-grid>
          <p v-if="posts.length === 0" class="header-title" style="color: #C62828">There are no Posts yet.</p>
    </div> 
</template>

<script>
import { mapGetters } from 'vuex'
import { mapActions } from 'vuex'

import PostGrid from '../components/PostGrid'

export default {
    created(){
      this.getPublicPosts();
    },
    components:{
        appPostGrid: PostGrid
    },
    computed: {
        posts(){
            const p = this.$store.getters.publicPosts 
            console.log(p)
            return this.$store.getters.publicPosts
        }
    },
    methods: {
        ...mapActions({
        getPublicPosts: 'getPublicPosts',
      })
    }
}
</script>

<style>

</style>