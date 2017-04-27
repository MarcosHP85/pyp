export const getters = {
  ots: state => state.ots,

  ot: (state, getters) => id => {
    return state.ots.find(ot => ot.otId === parseInt(id))
  }
}
