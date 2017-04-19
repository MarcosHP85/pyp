<template>
  <div>
    <div v-if="autenticando">
      <h2>Validando usuario...</h2>
    </div>
    <div v-else>
      <h2>Login page</h2>
      <input v-model="username" placeholder="Username" />
      <input v-model="password" placeholder="password" type="password"/>
      <button @click="loginUser">login</button>
    </div>
  </div>
</template>

<script>
// import router from '../router'
import { mapActions, mapGetters } from 'vuex'

export default {
  data () {
    return {
      username: '',
      password: '',
      autenticando: false
    }
  },

  props: {
    redirectName: String
  },

  computed: {
    ...mapGetters('auth', {
      user: 'user'
    })
  },

  methods: {
    ...mapActions('auth', {
      login: 'login'
    }),

    loginUser () {
      this.autenticando = true

      this.login({
        credentials: {
          username: this.username,
          password: this.password
        },
        redirectName: this.redirectName
      })

      this.username = ''
      this.password = ''
    }
  }
}
</script>

<style>
</style>
