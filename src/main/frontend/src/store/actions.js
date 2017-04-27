import * as types from '@/store/mutation-types'
// import router from '@/router'
import axios from 'axios'

export const actions = {
  buscarOtActiva ({ commit, getters }, ot) {
    if (getters.ot(ot) !== undefined) {
      console.log('está en vuex')
      return ot
    }
    axios('/json/' + ot)
      .then((response) => {
        console.log('buscando ot: ' + ot)
        commit(types.ADD_OT, response.data)
      })
  }
}
