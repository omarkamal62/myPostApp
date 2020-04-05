<template>
    <div>
        <h1 class="header-title">Register Page</h1>
        <v-card
            shaped
            class="pa-md-12 mt-4 ml-6 mr-6"
            >
            <form>
                <v-alert class="mx-8 mb-6" type="error" color="deep-purple" v-if="error !== null">
                    {{ error }}
                </v-alert>
                <v-text-field
                v-model="username"
                :counter="15"
                label="Username"
                required
                ></v-text-field>
                <v-text-field
                type="password"
                v-model="password"
                label="Password"
                required
                ></v-text-field>
                <v-text-field
                type="password"
                v-model="confirmpassword"
                label="Password Confirmation"
                required
                ></v-text-field>

            <v-btn class="mr-4 purple darken-3 white--text" @click="save">Register</v-btn>
            </form>
        </v-card>
    </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    data(){
        return {
            username: '',
            password: '',
            confirmpassword: ''
        }
    },
    methods: {
        ...mapActions({
            submit: 'saveUser'
        }),
        save(){
            const user = {
                username: this.username,
                password: this.password,
                passwordConfirmation: this.confirmpassword
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