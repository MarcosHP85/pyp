<template>
  <el-col class="side-bar">
    <el-menu mode="vertical"
      :default-active="active"
      @select="menuSelect">
      <el-menu-item index="Home">
        <i class="fa fa-home fa-lg fa-fw" aria-hidden="true"></i>&nbsp; Inicio</el-menu-item>
      <el-menu-item index="Login">
        <i class="fa fa-sign-in fa-lg fa-fw" aria-hidden="true"></i>&nbsp; Acceder</el-menu-item>
    </el-menu>
  </el-col>
</template>

<script>
import router from '@/router'

export default {
  name: 'side-bar',
  data () {
    return {
      active: 'Home'
    }
  },

  methods: {
    menuSelect (key, keyPath) {
      router.push({
        name: key,
        params: { redirectName: router.currentRoute.name }
      })
    }
  },

  watch: {
    '$route' (to) {
      this.active = to.name
    }
  }
}
</script>

<style lang="sass">
  @import "~sass"
  .side-bar
    top: 0
    width: 100%
    text-align: left
  .el-menu
    min-height: calc(100vh - #{$top_bar_height})
    min-height: -moz-calc(100vh - #{$top_bar_height})
    min-height: -webkit-calc(100vh - #{$top_bar_height})
  .el-menu-item
    height: 48px
    line-height: 48px
  .is-active
    border-right: 3px solid $el_color_blue
</style>
