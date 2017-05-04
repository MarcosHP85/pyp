<template>
  <div style="padding: 16px" v-if="ot !== null">
    <div style="font-size: 18px;text-align: left;padding-bottom: 12px">{{ ot.numOt }}</div>
    <div style="font-size: 18px;text-align: left;padding-bottom: 12px">{{ ot.componente }}</div>
    <div style="font-size: 16px;text-align: left;padding-bottom: 12px">{{ ot.orgCode }}</div>
    <div style="font-size: 16px;text-align: left;padding-bottom: 12px">{{ ot.prioridad }}</div>
    <div style="font-size: 14px;text-align: left;padding-bottom: 12px">{{ ot.comentarioPla }}</div>
    <div style="font-size: 14px;text-align: left;padding-bottom: 12px">{{ ot.tarea }}</div>
  </div>
</template>

<script>
export default {
  name: 'ot-preview',

  props: ['ot']
}
</script>

<style lang="sass">
</style>
