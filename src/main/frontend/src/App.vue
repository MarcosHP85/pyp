<template>
  <div id="app">
    <app-bar></app-bar>
    <img src="./assets/logo.png">
    <router-view></router-view>
  </div>
</template>

<script>
import * as types from '@/store/mutation-types'
import { mapMutations } from 'vuex'
import appBar from '@/components/AppBar'

export default {
  name: 'app',
  components: { appBar },
  mounted () {
    if (localStorage.getItem('token') !== null) {
      this.loadUser(JSON.parse(localStorage.getItem('user')))
    }
  },
  methods: {
    ...mapMutations('auth', {
      loadUser: types.AUTH_USER
    })
  }
}
</script>

<style lang="sass">
  #app
    font-family: 'Avenir', Helvetica, Arial, sans-serif
    -webkit-font-smoothing: antialiased
    -moz-osx-font-smoothing: grayscale
    text-align: center
    color: #2c3e50
    margin-top: 60px
</style>
