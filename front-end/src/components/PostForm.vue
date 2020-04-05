<template>
    <v-card
            shaped
            class="pa-md-12 mt-4 ml-6 mr-6"
            >
            <v-card-text>
                <p class="header-title">
                    Post
                </p>
            </v-card-text>
            <v-form
                ref="form"
                class="form-panel"
            > 
                <v-alert class="mx-8 mb-6" 
                type="error"
                color="deep-purple" 
                v-if="error !== ''">
                    {{ error }}
                </v-alert>
                
                <v-text-field
                label="Title"
                v-model="title"
                required
                ></v-text-field>
                <v-textarea
                    outlined
                    name="input-7-4"
                    label="content"
                    v-model="content"
                    ></v-textarea>
                    <v-checkbox
                    v-model="exclusive"
                    label="public"
                    required
                    ></v-checkbox>
                <v-btn
                class="mx-auto"
                color="deep-purple white--text"
                @click="save"
                >
                Post
                </v-btn>
            </v-form>
        </v-card>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    data(){
        return {
            title: '',
            content: '',
            exclusive: false,
            error: ''
        }
    },
    methods: {
        ...mapActions({
            submit: 'savePost'
        }),
        save(){
        if(this.title === '' || this.content === ''){
            this.error = "Title or content can't be empty"
        }else{
            const post = {
                title: this.title,
                content: this.content,
                exclusive: this.exclusive
            }
            this.submit({  
                post
            })
        }
      }
    }    
}
</script>

<style>


</style>