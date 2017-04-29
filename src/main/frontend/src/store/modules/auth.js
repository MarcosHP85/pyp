import * as types from '@/store/mutation-types'
import axios from 'axios'

if (process.env.NODE_ENV !== 'production') {
  axios.defaults.baseURL = 'http://localhost:8080'
}

const state = {
  user: null,
  error: false
}

const getters = {
  user: state => state.user,
  isLogin: state => state.user !== null,
  authError: state => state.error
}

const actions = {
  login ({ commit }, credentials) {
    // Loguea usuario
    axios.post('/login', credentials)
      // Si es correcto
      .then((response) => {
        localStorage.setItem('token', response.headers.authorization)
        localStorage.setItem('user', JSON.stringify(response.data))

        commit(types.AUTH_USER, response.data)
        commit(types.AUTH_ERROR, false)
      })
      .catch(() => {
        commit(types.AUTH_ERROR, true)
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

  [types.AUTH_ERROR] (state, isError) {
    state.error = isError
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
