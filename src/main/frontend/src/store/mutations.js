import * as types from '@/store/mutation-types'

export const mutations = {
  [types.ADD_OT] (state, ot) {
    state.ots.push(ot)
  }
}
