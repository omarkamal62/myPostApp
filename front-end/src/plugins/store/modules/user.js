import axios from '../../../axios-auth'
import router from '../../router'

import { getCookie, deleteCookie } from '../../script.js'

const state = {
    access_token: getCookie('access_token') || null,
    error: null
}

const mutations = {
    'SET_ACCESS_TOKEN'(state, auth){
        state.access_token = auth.token
        router.push('/')
      },
    'DESTROY_TOKEN'(state){
        deleteCookie('access_token')
        state.access_token = null
        if(router.currentRoute !== '/' || router.currentRoute !== '')
            router.push('/')
    },
    'SET_ERROR'(state, msg){
        state.error = msg
    }
}

const actions = {
    
    saveUser({ commit }, user){
        axios.post(
            '/api/register', JSON.stringify(user),
            { headers: { 'Content-Type': 'application/json' } })
            .then(res => router.push('/signin'))
            .catch(rej => {
                 commit('SET_ERROR', rej.response.data)
            })
            
    },
    login({ commit }, user){
        let params = new URLSearchParams();
        params.append("grant_type", "password")
        params.append("username", user.user.username)
        params.append("password", user.user.password)
        axios({
            method: 'post',
            url: 'oauth/token',
            auth: {username: 'first-client', password: 'noonewilleverguess'},
            headers: {'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'},
            data: params
        }).then(res => {
            console.log(res.data)
            document.cookie = 'access_token=' + res.data.access_token + "; Path=/;"
            commit('SET_ACCESS_TOKEN', {token: res.data.access_token})
        }).catch(error => {
            commit('SET_ERROR', error.response.data.error_description)
        })
    },
    destroyToken({commit, getters}){
        axios.get('/logouts?access_token=' + getters.access_token)
        .then(commit('DESTROY_TOKEN'))
        .catch(rej => console.log(rej))
    },
    getUsername({ commit, getters }){
        axios.get('/getUsername?access_token=' + getters.access_token)
        .then(res => console.log(res))
        .catch(err => commit('DESTROY_TOKEN'))
    }
}

const getters = {
    access_token: (state) =>{
        return state.access_token;
    },
    isAuthenticated: (state) => {
        return state.access_token !== null || ''
    },
    error: (State) => {
        return state.error
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}