import * as types from '@/store/mutation-types'
import router from '@/router'
import axios from 'axios'

if (process.env.NODE_ENV !== 'production') {
  axios.defaults.baseURL = 'http://localhost:8080'
}

const state = {
  user: null
}

const getters = {
  user: state => state.user
}

const actions = {
  login ({ commit }, { credentials, redirectName }) {
    // Loguea usuario
    axios.post('/login', credentials)
      // Si es correcto
      .then((response) => {
        localStorage.setItem('token', response.headers.authorization)
        commit(types.AUTH_USER, response.data)
        // Redirecciona a la página previa
        router.push({ name: redirectName })
      })
  },

  logout ({ commit }) {
    localStorage.removeItem('token')
    commit(types.LOGOUT_USER)
  }
}

const mutations = {
  [types.AUTH_USER] (state, user) {
    state.user = user
  },

  [types.LOGOUT_USER] (state, user) {
    state.user = null
  }
}

export default {
  namespaced: true,

  state,
  getters,
  actions,
  mutations
}
