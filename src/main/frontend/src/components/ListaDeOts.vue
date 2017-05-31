<template>
  <div class="submenu-vertical">
    <div id="buscar-ot-lista">
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
        <li v-for="ot in listaOts"
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
    <div id="ot-lista-total">
      <p><i class="fa fa-refresh fa-fw"></i>Total {{ listaOts.length }}</p>
    </div>
  </div>
</template>

<script>
import _ from 'lodash'
import iconoOrdenAscDesc from '@/components/iconos/OrdenAscDesc'
import iconoTipoTrabajo from '@/components/iconos/TipoTrabajo'

export default {
  name: 'lista-de-ots',
  props: ['ots'],
  components: { iconoOrdenAscDesc, iconoTipoTrabajo },

  data () {
    return {
      listaOts: [],
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
        let indexSeleccion = this.otSelecionada.indexOf(ot)
        if (indexSeleccion === -1) {
          this.otSelecionada.push(ot)
        } else {
          let pre = this.otSelecionada.slice(0, indexSeleccion)
          let post = this.otSelecionada.slice(indexSeleccion + 1, this.otSelecionada.length)
          this.otSelecionada = pre.concat(post)
        }
      } else if (event.shiftKey) {
        let i = this.listaOts.indexOf(
          this.otSelecionada[this.otSelecionada.length - 1])
        let j = this.listaOts.indexOf(ot)

        let temp = i < j
          ? this.listaOts.slice(i, ++j)
          : this.listaOts.slice(j, ++i)

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
      // this.listaOts.sort((a, b) => { return a[value] > b[value] })
      let otro = (value === 'numOt') ? 'componente' : 'numOt'
      this.listaOts = _.sortBy(this.listaOts, [value, otro])
    },
    invertirOrden () {
      this.listaOts.reverse()
      this.ordenAsc = !this.ordenAsc
    }
  },

  watch: {
    ots: function (val) {
      this.listaOts = val
    }
  }
}
</script>

<style lang="sass">
  @import "~sass"
  $ot-lista-total-height: 40px

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

  #buscar-ot-lista
    padding: 16px
    box-sizing: border-box
    height: calc(100% - #{$ot-lista-total-height})
    height: -moz-calc(100% - #{$ot-lista-total-height})
    height: -webkit-calc(100% - #{$ot-lista-total-height})
    overflow-x: auto

  .submenu-vertical
    border-right: $el_border
    height: 100%

  #ot-lista-total
    height: $ot-lista-total-height
    padding: 0 16px 0 16px
    & > p
      @extend .font-body
      @include vertical-align-middle
      font-size: 14px

  #ot-lista-orden
    text-align: left
    padding: 0 8px 4px 8px
</style>
