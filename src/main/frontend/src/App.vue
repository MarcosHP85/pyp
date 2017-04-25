<template>
  <div id="app">
    <side-bar></side-bar>
    <app-bar></app-bar>

    <router-view></router-view>
  </div>
</template>

<script>
import * as types from '@/store/mutation-types'
import { mapMutations } from 'vuex'
import appBar from '@/components/AppBar'
import sideBar from '@/components/SideBar'

export default {
  name: 'app',
  components: { appBar, sideBar },
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
    //margin-top: 60px
</style>
