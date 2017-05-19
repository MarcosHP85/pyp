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
            v-show="ordenarPor !== ''"
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
        :class="{ 'ot-lista-item-selecciondo': otSelecionada.indexOf(ot) !== -1 }"
        @click="nuevaOtSeleccionada($event,ot)">
        <p>
          <icono-tipo-trabajo
            :code="ot.tipoTrabajo">
          </icono-tipo-trabajo>
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

export default {
  name: 'lista-de-ots',
  props: ['ots'],
  components: { iconoOrdenAscDesc, iconoTipoTrabajo },

  data () {
    return {
      otSelecionada: [],
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
      ordenarPor: '',
      ordenAsc: true
    }
  },

  computed: {
    labelOrdenerPor: function () {
      return this.ordenarPor === '' ? ''
        : this.listaOrdenarPor
          .find(x => x.value === this.ordenarPor)
          .label
    }
  },

  methods: {
    nuevaOtSeleccionada (event, ot) {
      if (event.ctrlKey || event.metaKey) {
        this.otSelecionada.push(ot)
      } else if (event.shiftKey) {
        let i = this.ots.indexOf(
          this.otSelecionada[this.otSelecionada.length - 1])
        let j = this.ots.indexOf(ot)

        let temp = i < j
          ? this.ots.slice(i, ++j)
          : this.ots.slice(j, ++i)

        this.otSelecionada = this.otSelecionada.concat(temp)
      } else {
        this.otSelecionada = [ot]
        this.$emit('ot-selecionada', ot)
      }
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
  @import "~sass"

  .ot-lista
    text-align: left
    .ot-lista-item-selecciondo
      background-color: rgb(235,242,259)
    .ot-lista-item
      color: $font-link-color
      border-bottom: $el_border
      transition: background-color .25s ease
      padding: 8px
      cursor: pointer
      &:hover
        background-color: rgb(238,241,246)
      p
        line-height: 24px
        -webkit-user-select: none
        -moz-user-select: none
        -ms-user-select: none
        user-select: none

  #ot-lista-orden
    text-align: left
    padding: 0 8px 4px 8px
</style>
