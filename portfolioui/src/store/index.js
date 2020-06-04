import Vue from 'vue'
import Vuex from 'vuex'
import designs from './modules/designs'
import modals from "./modules/modals"
Vue.use(Vuex)

export default new Vuex.Store({
    state: {
    },
    mutations: {
    },
    actions: {
    },
    modules: {
        designs,
        modals
    }
  })
  