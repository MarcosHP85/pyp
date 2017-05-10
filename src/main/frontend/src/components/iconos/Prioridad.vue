<template>
  <i v-if="code === '2'"
    class="fa fa-arrow-up fa-fw" style="color: rgba(189,20,5,1)">
  </i>
  <i v-else-if="code === '3'"
    class="fa fa-arrow-up fa-fw" style="color: rgba(250,117,15,1)">
  </i>
  <i v-else-if="code === '4'"
    class="fa fa-arrow-down fa-fw" style="color: rgba(30,110,00,1)">
  </i>
  <i v-else-if="code === '5'"
    class="fa fa-minus-circle fa-fw" style="color: rgba(25,123,175,1)">
  </i>
  <i v-else-if="code === '1'"
    class="fa fa-exclamation-triangle fa-fw" style="color: rgba(199,43,28,1)">
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
