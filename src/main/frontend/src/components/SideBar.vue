<template>
  <el-col :span="4" class="side-bar">
    <el-menu mode="vertical" :default-active="active" @select="menuSelect">
      <div class="logo">
        
      </div>
      <el-menu-item index="Home">
        <i class="el-icon-menu"></i>Inicio</el-menu-item>
      <el-menu-item index="Login">
        <i class="el-icon-setting"></i>Acceder</el-menu-item>
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
      console.log(key + ' ' + keyPath)
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
  @import "~@/styles/main"
  .side-bar
    top: 0
    text-align: left
  .el-menu
    min-height: 100vh
  .logo
    height: 15vh
    background: rgb(230,230,230) //$color_secundario_oscuro
</style>
