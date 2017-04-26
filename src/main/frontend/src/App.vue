<template>
  <div id="app">
    <el-row>
      <el-col :span="4">
          <side-bar></side-bar>
      </el-col>
      <el-col :span="20">
          <top-bar></top-bar>
          <router-view></router-view>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as types from '@/store/mutation-types'
import { mapMutations } from 'vuex'
import topBar from '@/components/TopBar'
import sideBar from '@/components/SideBar'

export default {
  name: 'app',
  components: { topBar, sideBar },
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
</style>
