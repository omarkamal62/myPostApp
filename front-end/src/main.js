import Vue from 'vue'
import App from './App.vue'

import axios from 'axios'
import router from './plugins/router'
import store from './plugins/store'
import vuetify from './plugins/vuetify'


Vue.prototype.$http = axios

router.store = store
store.$http = axios

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
