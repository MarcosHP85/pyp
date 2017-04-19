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

<style>
</style>
