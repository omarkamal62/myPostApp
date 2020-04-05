<template>
    <div>
        <h1 class="header-title">Login Page</h1>
        <v-card
            shaped
            class="pa-md-12 mt-4 ml-6 mr-6"
            >
            <form>
                <v-alert class="mx-8 mb-6" 
                type="error"
                color="deep-purple" 
                v-if="error !== null">
                    {{ error }}
                </v-alert>
                <v-text-field
                v-model="username"
                label="Username"
                required
                ></v-text-field>
                <v-text-field
                type="password"
                v-model="password"
                label="Password"
                required
                ></v-text-field>

            <v-btn class="mr-4 purple darken-3 white--text" @click="save">login</v-btn>
            </form>
        </v-card>
        <v-card class="mx-6 my-6 purple darken-3">
            <v-card-text>
                <p class="header-title" style="color: white">If not Registered yet</p>
            </v-card-text>
            <v-card-actions>
                <router-link class="v-btn" style="color: #FFF; margin: auto" :to="{path: '/signup'}">Register</router-link>
            </v-card-actions>
        </v-card>
    </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    data(){
        return {
            username: '',
            password: ''
        }
    },
    methods: {
        ...mapActions({
            submit: 'login'
        }),
        save(){
            const user = {
                username: this.username,
                password: this.password
            }
            this.submit({  
            user
            })
        }
    },
    computed: {
        error(){
            return this.$store.getters.error
        }
    },
    created(){
      this.$store.commit('SET_ERROR', null)
    }
}
</script>