const state = {
    designs: []
  }
  

const mutations = {
    'SET_DESIGNS' (state, designs) {
      state.designs = designs
    }
}

const actions = {
    init_designs:({commit},designs) => {
        console.log('designs:'+designs);
        commit('SET_DESIGNS',designs);
    }
}


const getters = {
    designs: state => {
        return state.designs
    }
}


export default {
    state,
    getters,
    actions,
    mutations
}