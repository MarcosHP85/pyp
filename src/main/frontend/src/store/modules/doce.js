import * as types from '@/store/mutation-types'
import axios from 'axios'

if (process.env.NODE_ENV !== 'production') {
  axios.defaults.baseURL = 'http://localhost:8080'
}

const state = {
  otsDoce: []
}

const getters = {
  listaOtsDoce: state => state.otsDoce
}

const actions = {
  buscarOtsDoce ({ commit }, sots) {
    axios.get('/api/doce/search', {
      params: {
        ots: sots
      }
    }).then(response => {
      commit(types.DOCE_LISTA_OTS, response.data)
    })
  }
}

const mutations = {
  [types.DOCE_LISTA_OTS] (state, ots) {
    state.otsDoce = ots
  }
}

export default {
  namespaced: true,

  state,
  getters,
  actions,
  mutations
}
