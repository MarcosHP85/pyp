<template>
  <div id="app">
    <app-bar></app-bar>
    <img src="./assets/logo.png">
    <router-view></router-view>
  </div>
</template>

<script>
import appBar from '@/components/AppBar'

export default {
  name: 'app',
  components: { appBar },
  mounted: () => console.log(localStorage.getItem('token'))
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
