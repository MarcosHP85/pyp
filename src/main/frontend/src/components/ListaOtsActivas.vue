<template>
  <div
    class="app-page">
    <el-row
      id="buscar-ot-filtro">
      <el-col
        :span="24">
        <h2>Filtro</h2>
        <el-select
          v-model="filtroComponentes"
          multiple
          filterable
          allow-create
          placeholder="componente">
          <el-option
            v-for="item in optionComponentes"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
        <el-select
          v-model="filtroPrioridad"
          multiple
          filterable
          allow-create
          placeholder="prioridad">
          <el-option
            v-for="item in optionPrioridad"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-col>
    </el-row>
    <el-row
      id="buscar-ot">
      <el-col
        id="buscar-ot-lista"
        v-loading="cargando"
        :span="6">
        <lista-de-ots
          :ots="listaOts"
          @ot-selecionada="mostrarOt">
        </lista-de-ots>
      </el-col>
      <el-col
        id="ot-preview"
        :span="18">
        <ot-preview
          :ot="otSeleccionada">
        </ot-preview>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import _ from 'lodash'
import { mapGetters, mapActions } from 'vuex'
import listaDeOts from '@/components/ListaDeOts'
import otPreview from '@/components/OtPreview'

export default {
  name: 'lista-ots-activas',
  components: { otPreview, listaDeOts },
  props: ['ots'],

  data () {
    return {
      listaOts: [],
      otSeleccionada: null,
      cargando: false,
      // optionComponentes: [],
      filtroComponentes: [],
      // optionPrioridad: [],
      filtroPrioridad: []
    }
  },
  mounted () {
    this.cargarLista()
  },

  computed: {
    ...mapGetters('ifs', [
      'listaOtsActivas'
    ]),
    optionPrioridad () {
      let tmp = []
      _.forEach(
        _.groupBy(this.listaOts, 'prioridad'),
        (value, key) => {
          tmp.push(key)
        }
      )
      return tmp
    },
    optionComponentes () {
      let tmp = []
      _.forEach(
        _.groupBy(this.listaOts, 'componente'),
        (value, key) => {
          tmp.push(key)
        }
      )
      return tmp
    }
  },

  methods: {
    ...mapActions('ifs', [
      'buscarOtsActivas'
    ]),
    cargarLista () {
      this.cargando = true
      this.buscarOtsActivas(this.ots)
    },
    // cargarFiltros () {
    //   _.forEach(
    //     _.groupBy(this.listaOts, 'componente'),
    //     (value, key) => {
    //       this.optionComponentes.push(key)
    //     }
    //   )
      // _.forEach(
      //   _.groupBy(this.listaOts, 'prioridad'),
      //   (value, key) => {
      //     this.optionPrioridad.push(key)
      //   }
      // )
    // },
    mostrarOt (ot) {
      this.otSeleccionada = ot
    }
  },

  watch: {
    listaOtsActivas: function (val) {
      this.listaOts = val
      // this.cargarFiltros()
      this.cargando = false
    },
    filtroComponentes: function (val) {
      this.listaOts = (this.filtroComponentes.length > 0)
        ? _.filter(this.listaOtsActivas, (ot) => {
          return this.filtroComponentes.indexOf(ot.componente) !== -1
        })
        : this.listaOts
    },
    filtroPrioridad: function (val) {
      this.listaOts = (this.filtroPrioridad.length > 0)
        ? _.filter(this.listaOtsActivas, (ot) => {
          return this.filtroPrioridad.indexOf(ot.prioridad) !== -1
        })
        : this.listaOts
    },
    '$route' (to, from) {
      this.cargarLista()
    }
  }
}
</script>

<style lang="sass">
  @import "~sass"

  $buscar_ot_filtro_height: 64px

  .app-page
    box-sizing: border-box
    height: 100%
  #buscar-ot-filtro
    border-bottom: $el_border
    height: $buscar_ot_filtro_height
  #buscar-ot
    height: calc(100% - #{$buscar_ot_filtro_height})
    height: -moz-calc(100% - #{$buscar_ot_filtro_height})
    height: -webkit-calc(100% - #{$buscar_ot_filtro_height})
  #buscar-ot-lista
    padding: 16px
    border-right: $el_border
    height: 100%
    overflow-x: auto
  #ot-preview
    // padding: 16px
    height: 100%
    overflow-x: auto
</style>
