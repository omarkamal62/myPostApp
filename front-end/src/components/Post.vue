<template>
    <v-card
    class="mx-auto"
    max-width="344"
    shaped
  >
    <v-card-title>{{ title }}</v-card-title>
    <v-card-subtitle><span style="font-weight: bold">Created by: </span>{{ user.username }}</v-card-subtitle>
    <v-card-text style="white-space: pre-line">{{ content }}</v-card-text>
    </v-card>
</template>

<script>
export default {
    props: ['title', 'content', 'user']
}
</script>