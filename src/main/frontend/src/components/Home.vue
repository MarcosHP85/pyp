<template>
  <div class="home-view">
    <h2>Home</h2>
    <vue-editor ng-model="value"></vue-editor>
    <comentario-editor></comentario-editor>
  </div>
</template>

<script>
import comentarioEditor from '@/components/comentarioEditor'

export default {
  components: { comentarioEditor },

  data () {
    return {
      value: ''
    }
  }
}
</script>

<style lang="sass">
</style>
