<template>
    <v-row class="mx-3 my-6">
        <template v-for="post in posts">
            <v-col cols="3" :key="post.id">
                <app-post :title="post.title" :content="post.content" :user="post.user"></app-post>
            </v-col>
            </template>
    </v-row>
</template>

<script>

import Post from '../components/Post'

export default {
    props: ['posts'],
    components: {
        appPost: Post
    }
}
</script>