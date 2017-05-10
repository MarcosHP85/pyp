<template>
  <i class="fa fa-fw"
    :class="[ ordenAsc
      ? 'fa-arrow-down'
      : 'fa-arrow-up' ]">
  </i>
</template>

<script>
export default {
  name: 'icono-orden-asc-desc',
  props: ['ordenAsc']
}
</script>

<style lang="sass">
  @import "~@/styles/font-awesome/font-awesome"
</style>
