<template>
  <i v-if="code === '2' || code === 'B'"
    class="fa fa-arrow-up fa-fw" style="color: rgb(255,26,26)">
  </i>
  <i v-else-if="code === '3'"
    class="fa fa-arrow-up fa-fw" style="color: rgb(234,125,36)">
  </i>
  <i v-else-if="code === '4' || code === 'C'"
    class="fa fa-arrow-down fa-fw" style="color: rgb(35,177,49)">
  </i>
  <i v-else-if="code === '5' || code === 'D'"
    class="fa fa-minus-circle fa-fw" style="color: rgb(25,123,175)">
  </i>
  <i v-else-if="code === '1' || code === 'A'"
    class="fa fa-exclamation-triangle fa-fw" style="color: rgb(255,26,26)">
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
