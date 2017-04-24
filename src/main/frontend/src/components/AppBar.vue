<template>
  <header id="bar-app">
    <div v-if="authenticated">
      <p>{{ user.apellido + ", " + user.nombre }}</p>
      <button @click="logout()" class="button-raised">SALIR</button>
    </div>
    <button v-else-if="!isLoginPage" @click="login()" class="button-flat">Acceder</button>
  </header>
</template>

<script>
import router from '@/router'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'app-bar',

  data () {
    return {
      isLoginPage: router.currentRoute.name === 'Login'
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

    login () {
      router.push({
        name: 'Login',
        params: { redirectName: router.currentRoute.name }
      })
    },

    logout () {
      this.userLogout()

      router.push({ name: 'Home' })
    }
  },

  watch: {
    '$route' (to) {
      this.isLoginPage = to.name === 'Login'
    }
  }
}
</script>

<style lang="sass">
  @import "~@/styles/main"
  $app_bar_padding: 15px

  #bar-app
    background: $color_principal_oscuro
    position: fixed
    top: 0
    left: 0
    width: 100%
    padding: $app_bar_padding
    box-shadow: 0px 1px 6px #727272
    & > button
      float: right
      margin-right: $app_bar_padding * 2
</style>
