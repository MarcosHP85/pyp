<template>
  <div style="padding: 16px" v-if="ot !== null && ot !== undefined">
    <div style="font-size: 18px;text-align: left;padding-bottom: 12px">{{ ot.numOt }}</div>
    <div style="font-size: 18px;text-align: left;padding-bottom: 12px">{{ ot.componente }}</div>
    <div style="font-size: 16px;text-align: left;padding-bottom: 12px">{{ ot.orgCode }}</div>
    <div style="font-size: 16px;text-align: left;padding-bottom: 12px">{{ ot.tipoTrabajo }}</div>
    <div style="font-size: 16px;text-align: left;padding-bottom: 12px">{{ ot.prioridad }}</div>
    <div style="font-size: 14px;text-align: left;padding-bottom: 12px">{{ fechaRegistro }}</div>
    <div style="font-size: 14px;text-align: left;padding-bottom: 12px">{{ ot.directiva }}</div>
    <div style="font-size: 14px;text-align: left;padding-bottom: 12px">{{ ot.tarea }}</div>
    <div style="font-size: 14px;text-align: left;padding-bottom: 12px">{{ ot.error }}</div>
    <div style="font-size: 14px;text-align: left;padding-bottom: 12px">{{ ot.comentarioPla }}</div>
  </div>
</template>

<script>
export default {
  name: 'ot-preview',

  props: ['ot'],

  computed: {
    fechaRegistro () {
      let d = new Date(this.ot.fechaRegistro)
      return d.getDay() + '/' + (1 + d.getMonth()) + '/' + d.getFullYear() + ' ' + d.getHours() + ':' + d.getMinutes()
    }
  }
}
</script>

<style lang="sass">
</style>
