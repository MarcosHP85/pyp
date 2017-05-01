<template>
  <div id="login-view">
    <h2>Login page</h2>

    <el-col :span="8" :offset="8">
      <el-card>
        <div slot="header" style="font-size:14px;">
          <i class="fa fa-exclamation-circle fa-lg" aria-hidden="true"></i>
          Utilice su usuario y contraseña de Windows. El mismo de su correo electrónico usuario@na-sa.com.ar
        </div>
        <el-form :model="credentials" class="login-form" label-width="90px">
          <el-form-item label="Usuario">
            <el-input
              v-model="credentials.username"
              :disabled="disabledInput"
              placeholder="usuario@na-sa.com.ar" />
          </el-form-item>
          <el-form-item label="Contraseña">
            <el-input
              v-model="credentials.password"
              :disabled="disabledInput"
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
      <el-alert v-if="authError"
        title="Usuario y/o contraseña incorrecta"
        type="error"
        :closable="false">
      </el-alert>
    </el-col>

  </div>
</template>

<script>
import router from '@/router'
import { mapActions, mapGetters } from 'vuex'

export default {
  data () {
    return {
      autenticando: false,
      disabledInput: false,
      credentials: {
        username: '',
        password: ''
      }
    }
  },

  props: ['redirect'],

  computed: {
    ...mapGetters('auth', [
      'user',
      'authError',
      'isLogin'
    ])
  },

  methods: {
    ...mapActions('auth', [
      'login'
    ]),

    loginUser () {
      this.autenticando = true
      this.login(this.credentials)

      this.disabledInput = true
    }
  },

  watch: {
    isLogin: function () {
      this.redirect === undefined
        ? router.go(-1)
        : router.push(this.redirect)
    },

    authError: function (isError) {
      if (isError) {
        this.credentials.password = ''
        this.disabledInput = false
        this.autenticando = false
      }
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
