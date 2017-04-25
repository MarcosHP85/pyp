<template>
  <div>
    <h2>Login page</h2>
    <el-form :inline="true" :model="credentials">
      <el-form-item>
        <el-input
          v-model="credentials.username"
          placeholder="Username" />
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="credentials.password"
          placeholder="Password"
          type="password"/>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          :loading="autenticando"
          @click="loginUser">
          INICIAR
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// import router from '../router'
import { mapActions, mapGetters } from 'vuex'

export default {
  data () {
    return {
      credentials: {
        username: '',
        password: ''
      },
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
        credentials: this.credentials,
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
