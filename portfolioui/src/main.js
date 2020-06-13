import Vue from 'vue'
import App from './App.vue'
import router from './routes';
import store from './store/index'
import CKEditor from '@ckeditor/ckeditor5-vue'
Vue.use(CKEditor)
Vue.config.productionTip = false


new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
