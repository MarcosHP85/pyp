<template>
  <div id="bar-app">
    <div v-if="authenticated">
      <p>{{ user.apellido + ", " + user.nombre }}</p>
      <button @click="logout()">Logout</button>
    </div>
    <button v-else-if="!isLoginPage" @click="login()">Login</button>
  </div>
</template>

<script>
import router from '@/router'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'app-bar',

  data () {
    return {
      authenticated: false,
      isLoginPage: false
    }
  },

  mounted () {
    this.queMostrar(this.$route)
  },

  computed: {
    ...mapGetters('auth', {
      user: 'user'
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
      this.queMostrar(this.$route)

      router.push('/')
    },

    queMostrar (route) {
      this.authenticated = this.user !== null
      this.isLoginPage = route.name === 'Login'
    }
  },

  watch: {
    '$route' (to) {
      this.queMostrar(to)
    }
  }
}
</script>

<style>
</style>
