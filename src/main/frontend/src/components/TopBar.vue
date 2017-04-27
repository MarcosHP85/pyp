<template>
  <header id="bar-app">
    <el-input
      class="bar-app-buscar"
      icon="search"
      size="small"
      v-model="input"
      :on-icon-click="buscar">
    </el-input>
    <div v-if="authenticated">
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
    ...mapGetters('auth', {
      user: 'user',
      authenticated: 'authenticated'
    })
  },

  methods: {
    ...mapActions('auth', {
      userLogout: 'logout'
    }),
    ...mapActions({
      buscarOtActiva: 'buscarOtActiva'
    }),

    buscar () {
      this.buscarOtActiva(this.input)
    },

    logout () {
      this.userLogout()

      router.push({ name: 'Home' })
    }
  }
}
</script>

<style lang="sass">
  @import "~@/styles/main"

  #bar-app
    background: $el_color_white
    top: 0
    width: 100%
    height: 36px
    border-bottom: $el_border
    box-shadow: $el_shadow
    & + div
      padding-top: 20px
    & > div > p
      display: inline-block
    & > *
      float: right
      margin-right: 40px

  .bar-app-buscar
    width: 200px
</style>
