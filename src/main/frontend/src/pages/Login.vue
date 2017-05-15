<template>
  <div id="login-view">
    <el-col :span="11">
      <el-card>
        <div slot="header" style="font-size:11pt;">
          <i class="fa fa-info-circle fa-3x fa-pull-left" aria-hidden="true"></i>
          Utilice su usuario y contraseña de Windows.<br />
          El mismo de su correo electrónico usuario@na-sa.com.ar
        </div>
        <el-form :model="credentials" class="login-form" label-width="120px">
          <el-form-item label="Usuario">
            <el-input
              v-model="credentials.username"
              :disabled="disabledInput"
              placeholder="usuario" />
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
    margin: 24px

  .el-form
    width: 420px
    margin-top: 18px
    margin-bottom: 24px

  .el-form-item:last-child
    margin-bottom: 0
</style>
