import axios from '../../../axios-auth'
import router from '../../router'

const state = {
    publicPosts: [],
    myPosts: []
}

const mutations = {
    'GET_PUBLIC_POSTS'(state, posts){
        state.publicPosts = posts
    },
    'SAVE_POST'(state, post){    
        state.publicPosts.push(post);
        router.push('/myPosts')
    },
    'GET_MY_POSTS'(state, posts){
        state.myPosts = posts
    }
}

const actions = {
    getPublicPosts({ commit }){
        axios.get(
            '/api/publicposts')
            .then(res => res.data)
            .then(data => {
                commit('GET_PUBLIC_POSTS', data.posts)
            })
            .catch(error => console.log(error))
    },
    savePost({ commit, getters }, post){
        axios.post(
            '/api/savepost?access_token=' + getters.access_token, JSON.stringify(post),
            { headers: { 'Content-Type': 'application/json' } })
            .then(res => res.data)
            .then(data => {
                commit('SAVE_POST', data.post)
            })
            
    },
    getMyPosts({ commit, getters }){
        axios.get(
            '/api/myposts?access_token=' + getters.access_token)
        .then(res => res.data)
        .then(data => {
            commit('GET_MY_POSTS', data.posts)
        });
    },
    search({ commit }, payload){
        if(payload.searchText === ''){
            commit('GET_PUBLIC_POSTS', new Array())
            return
        }
        axios.get(
            '/api/posts/' + payload.searchText
        ).then(res => res.data)
        .then(data => {
            commit('GET_PUBLIC_POSTS', data.posts)
        })
    }
}

const getters = {
    publicPosts: (state) => {
        return state.publicPosts
    },
    myPosts: (state) => {
        return state.myPosts
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}