import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueRouter from 'vue-router'
import router from './router'
import axios from 'axios'
import store from './store'
import  './axios'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import './permission'
Vue.prototype.$axios=axios


Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(mavonEditor)
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router:router,
  store
}).$mount('#app')
