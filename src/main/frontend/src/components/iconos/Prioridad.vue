<template>
  <i v-if="code === '2'"
    class="fa fa-arrow-up fa-fw" style="color: rgb(189,20,5)">
  </i>
  <i v-else-if="code === '3'"
    class="fa fa-arrow-up fa-fw" style="color: rgb(247,146,50)">
  </i>
  <i v-else-if="code === '4'"
    class="fa fa-arrow-down fa-fw" style="color: rgb(30,110,0)">
  </i>
  <i v-else-if="code === '5'"
    class="fa fa-minus-circle fa-fw" style="color: rgb(25,123,175)">
  </i>
  <i v-else-if="code === '1'"
    class="fa fa-exclamation-triangle fa-fw" style="color: rgb(199,43,28)">
  </i>
</template>

<script>
export default {
  name: 'icono-prioridad',
  props: ['code']
}
</script>

<style lang="sass">
</style>
