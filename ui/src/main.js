import Vue from 'vue'
import App from './App.vue'
import Datetime from 'vue-datetime'
import 'vue-datetime/dist/vue-datetime.css'
import VueRessource from 'vue-resource'


Vue.config.productionTip = false
Vue.use(VueRessource)
Vue.use(Datetime)

new Vue({
  render: h => h(App)
}).$mount('#app')
