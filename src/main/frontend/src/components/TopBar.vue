<template>
  <header id="bar-app">
    <el-input
      class="bar-app-buscar"
      icon="search"
      size="small"
      v-model="input"
      :on-icon-click="buscar">
    </el-input>
    <div v-if="isLogin">
      <p>{{ user.apellido + ", " + user.nombre }}</p>
      <el-button
        size="small"
        type="text"
        @click="logout"
        class="button-raised">
        SALIR
      </el-button>
    </div>
  </header>
</template>

<script>
import router from '@/router'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'top-bar',

  data () {
    return {
      input: ''
    }
  },

  computed: {
    ...mapGetters('auth', [
      'user',
      'isLogin'
    ])
  },

  methods: {
    ...mapActions('auth', {
      userLogout: 'logout'
    }),

    buscar () {
      router.push({ name: 'OtsActivas',
        query: { ots: this.input.replace(/;/g, ',') }
      })
    },

    logout () {
      this.userLogout()
      router.push({ name: 'Home' })
    }
  }
}
</script>

<style lang="sass">
  @import "~sass"

  #bar-app
    box-sizing: border-box
    background: $el_color_white
    top: 0
    width: 100%
    height: $top_bar_height
    border-bottom: $el_border
    // box-shadow: $el_shadow
    // -webkit-transform-style: preserve-3d
    // -moz-transform-style: preserve-3d
    // transform-style: preserve-3d
    & > div > p
      display: inline-block
    & > *
      float: right
      margin-right: 40px
      @include vertical-align-middle

  .bar-app-buscar
    width: 200px
</style>
