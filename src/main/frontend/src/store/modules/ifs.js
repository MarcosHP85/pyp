import * as types from '@/store/mutation-types'
import axios from 'axios'

if (process.env.NODE_ENV !== 'production') {
  axios.defaults.baseURL = 'http://localhost:8080'
}

const state = {
  otsActivasEncontradas: []
}

const getters = {
  listaOtsActivas: state => state.otsActivasEncontradas
}

const actions = {
  buscarOtsActivas ({ commit }, sots) {
    axios.get('/api/ot_activa/search', {
      params: {
        ots: sots
      }
    }).then(response => {
      commit(types.IFS_LISTA_OTS_ACTIVAS, response.data)
    })
  }
}

const mutations = {
  [types.IFS_LISTA_OTS_ACTIVAS] (state, ots) {
    state.otsActivasEncontradas = ots
  }
}

export default {
  namespaced: true,

  state,
  getters,
  actions,
  mutations
}
