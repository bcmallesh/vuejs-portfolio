const state = {
    modals: []
  }
  

const mutations = {
    'SET_MODALS' (state, modals) {
      state.modals = modals
    }
}

const actions = {
    init_modals:({commit},modals) => {
        console.log('modals:'+modals);
        commit('SET_MODALS',modals);
    }
}


const getters = {
    modals: state => {
        return state.modals
    }
}


export default {
    state,
    getters,
    actions,
    mutations
}