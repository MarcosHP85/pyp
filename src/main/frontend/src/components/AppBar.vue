<template>
  <header id="bar-app">
    <div v-if="authenticated">
      <p>{{ user.apellido + ", " + user.nombre }}</p>
      <el-button
        size="small"
        type="text"
        @click="logout()"
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
  name: 'app-bar',

  data () {
    return {
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
  }
}
</script>

<style lang="sass">
  @import "~@/styles/main"
  $app_bar_padding: 15px

  #bar-app
    background: white
    top: 0
    left: 140.5px
    width: 81vw
    overflow: auto
    margin-bottom: 30px
    padding: $app_bar_padding
    box-shadow: 0px 1px 6px #727272
    & > div > p
      display: inline-block
    & > *
      float: right
      margin-right: $app_bar_padding * 2
</style>
