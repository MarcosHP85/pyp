<template>
  <div>
    <div id="ot-lista-orden">
      <el-dropdown
        trigger="click"
        @command="commandOrdenar"
        size="mini">
        <span>
          Ordenar por {{ labelOrdenerPor }}
          <icono-orden-asc-desc
            :ordenAsc="ordenAsc">
          </icono-orden-asc-desc>
          |<i class="fa fa-caret-down fa-fw"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item
            v-for="item in listaOrdenarPor"
            :command="item.value"
            :key="item.value">
            <p>
              {{ item.label }}
              <icono-orden-asc-desc
                v-show="item.value === ordenarPor"
                :ordenAsc="!ordenAsc">
              </icono-orden-asc-desc>
            </p>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <ul class="ot-lista">
      <li v-for="ot in ots"
        class="ot-lista-item"
        :class="{ 'ot-lista-item-selecciondo': ot === otSelecionada }"
        @click="nuevaOtSeleccionada(ot)">
        <p>
          <icono-tipo-trabajo
            :code="ot.tipoTrabajo">
          </icono-tipo-trabajo>
          <icono-prioridad
            :code="ot.prioridad">
          </icono-prioridad>
          {{ ot.componente }} - {{ ot.numOt }}
        </p>
        <p>
          {{ ot.orgCode }} - {{ ot.directiva }}
        </p>
      </li>
    </ul>
  </div>
</template>

<script>
import iconoOrdenAscDesc from '@/components/iconos/OrdenAscDesc'
import iconoTipoTrabajo from '@/components/iconos/TipoTrabajo'
import iconoPrioridad from '@/components/iconos/Prioridad'

export default {
  name: 'lista-de-ots',
  props: ['ots'],
  components: { iconoOrdenAscDesc, iconoTipoTrabajo, iconoPrioridad },

  data () {
    return {
      otSelecionada: null,
      listaOrdenarPor: [{
        label: 'Ot',
        value: 'numOt'
      }, {
        label: 'Prioridad',
        value: 'prioridad'
      }, {
        label: 'Componente',
        value: 'componente'
      }],
      ordenarPor: 'prioridad',
      ordenAsc: true
    }
  },

  computed: {
    labelOrdenerPor: function () {
      return this.listaOrdenarPor
        .find(x => x.value === this.ordenarPor)
        .label
    }
  },

  methods: {
    nuevaOtSeleccionada (ot) {
      this.otSelecionada = ot
      this.$emit('ot-selecionada', ot)
    },
    commandOrdenar (value) {
      this.ordenarPor === value
        ? this.invertirOrden()
        : this.ordenarAscPor(value)
    },
    ordenarAscPor (value) {
      this.ordenAsc = true
      this.ordenarPor = value
      this.ots.sort((a, b) => { return a[value] > b[value] })
    },
    invertirOrden () {
      this.ots.reverse()
      this.ordenAsc = !this.ordenAsc
    }
  }
}
</script>

<style lang="sass">
  @import "~@/styles/main"
  @import "~@/styles/font-awesome/font-awesome"

  .ot-lista
    text-align: left
    .ot-lista-item-selecciondo
      background-color: rgb(235,242,259)
    .ot-lista-item
      color: #1F2D3D
      font-size: 14px
      border-bottom: $el_border
      transition: background-color .25s ease
      padding-top: 12px
      padding-bottom: 12px
      padding-left: 18px
      padding-right: 18px
      &:hover
        background-color: rgb(238,241,246)
      p
        line-height: 24px

  #ot-lista-orden
    text-align: left
    padding: 18px
</style>
