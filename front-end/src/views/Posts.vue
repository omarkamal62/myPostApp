<template>
    <div>
        <div>
            <v-btn class="mx-6 my-6 text deep-purple"><router-link style="color: white; text-decoration: none" :to="{path: 'myposts'}">My Posts</router-link></v-btn>
        </div>
        <app-Post-form></app-Post-form>
    </div>
</template>

<script>
import PostForm from '../components/PostForm'

export default {
    components: {
        appPostForm: PostForm
    }
}
</script>