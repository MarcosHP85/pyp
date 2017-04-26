<template>
  <div id="login-view">
    <h2>Login page</h2>

    <el-col :span="8" :offset="8">
      <el-card>
        <el-form :model="credentials" class="login-form" label-width="90px">
          <el-form-item label="Usuario">
            <el-input
              v-model="credentials.username"
              placeholder="usuario@na-sa.com.ar" />
          </el-form-item>
          <el-form-item label="Contraseña">
            <el-input
              v-model="credentials.password"
              placeholder="contraseña"
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
      </el-card>
    </el-col>

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

<style lang="sass">
  #login-view
    margin-top: 20px
  h2
    margin-bottom: 20px !important
  .login-form
    margin-top: 16px
    & > div:last-child
      margin-bottom: 0
    & > div > div > button
      float: right
</style>
