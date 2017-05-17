<template>
  <div id="app">
    <top-bar></top-bar>
    <el-row>
      <el-col :span="4">
          <side-bar></side-bar>
      </el-col>
      <el-col :span="20" class="app-main">
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
  @import "~sass"

  #app
    @extend %base-font

  .app-main
    height: calc(100vh - #{$top_bar_height})
    height: -moz-calc(100vh - #{$top_bar_height})
    height: -webkit-calc(100vh - #{$top_bar_height})
</style>
