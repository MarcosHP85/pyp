<template>
  <el-col class="side-bar">
    <el-menu mode="vertical"
      :default-active="active"
      @select="menuSelect">
      <div class="logo">

      </div>
      <el-menu-item index="Home">
        <i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp; Inicio</el-menu-item>
      <el-menu-item index="Login">
        <i class="fa fa-sign-in fa-fw" aria-hidden="true"></i>&nbsp; Acceder</el-menu-item>
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
  @import "~sass"
  .side-bar
    top: 0
    width: 100%
    text-align: left
  .el-menu
    min-height: 100vh
  .is-active
    border-right: 3px solid $el_color_blue
  .logo
    height: 96px
    background: $el_color_blue
</style>
