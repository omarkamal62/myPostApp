<template>
    <div>
        <h1 class="header-title">Search</h1>
        <v-text-field
            v-model="searchText"
            label="search"
            outlined
            shaped
            class="mx-8 mt-6"
          ></v-text-field>
          <v-row>
              <v-btn rounded color="deep-purple" class="align-self-center" @click="search" style="margin: auto" dark>Search</v-btn>
          </v-row>
          <v-alert class="mx-8 mt-5" type="info" color="deep-purple" v-if="error || (posts.length === 0 && searchPressed)">
            <template v-if="posts.length === 0 && searchPressed">
                No posts were found.
            </template>
            <template v-if="error">
                You entered invalid data
            </template>
          </v-alert>
          <app-post-grid :posts="posts"></app-post-grid>
    </div>
</template>

<script>
import PostGrid from '../components/PostGrid'
import { mapActions } from 'vuex'

export default {
    data(){
        return {
            searchText: '',
            error: false,
            errorMessage: '',
            searchPressed: false
        }
    },
    components: {
        appPostGrid: PostGrid
    },
    methods: {
        ...mapActions({
            searchPosts: 'search'
        }),
        search(){
            if((this.searchText !== null && this.searchText.trim() === '') || this.searchText == ''){
                this.error = true
                this.searchPressed = false
                this.searchText = ''
            }else{
                this.error = false
                this.searchPressed = true
            }
            this.searchPosts({
                searchText: this.searchText
            })
        }
    },
    computed: {
        posts(){
            return this.$store.getters.publicPosts
        }
    }
}
</script>