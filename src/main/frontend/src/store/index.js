import Vue from 'vue'
import Vuex from 'vuex'
import { actions } from './actions'
import { getters } from './getters'
import { mutations } from './mutations'
import auth from './modules/auth'
import ifs from './modules/ifs'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    ots: []
  },

  actions,
  getters,
  mutations,
  modules: {
    auth,
    ifs
  }
})
