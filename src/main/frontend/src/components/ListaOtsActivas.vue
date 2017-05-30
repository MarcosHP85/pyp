<template>
  <div
    class="app-page">
    <el-row
      id="buscar-ot-filtro">
      <el-col
        :span="24">
        <p class="font-title">Filtrar por &nbsp;</p>
        <el-select
          v-model="filtroComponentes"
          multiple
          filterable
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
          placeholder="prioridad">
          <el-option
            v-for="item in optionPrioridad"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
        <el-select
          v-model="filtroOrgMant"
          multiple
          filterable
          placeholder="mantenimiento">
          <el-option
            v-for="item in optionOrgMant"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
        <el-select
          v-model="filtroTipo"
          multiple
          filterable
          placeholder="trabajo">
          <el-option
            v-for="item in optionTipo"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
        <el-button
          type="text"
          @click="reset">
          Borrar
        </el-button>
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
      filtroComponentes: [],
      filtroPrioridad: [],
      filtroOrgMant: [],
      filtroTipo: []
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
      return _.uniq(
        _.map(this.listaOtsActivas, 'prioridad'))
    },
    optionComponentes () {
      return _.uniq(
        _.map(this.listaOtsActivas, 'componente'))
    },
    optionOrgMant () {
      return _.uniq(
        _.map(this.listaOtsActivas, 'orgCode'))
    },
    optionTipo () {
      return _.uniq(
        _.map(this.listaOtsActivas, 'tipoTrabajo'))
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
    mostrarOt (ot) {
      this.otSeleccionada = ot
    },
    aplicarFiltros () {
      let tmp = this.listaOtsActivas

      let componentes = (this.filtroComponentes.length > 0)
        ? this.filtroComponentes
        : this.optionComponentes
      tmp = _.filter(tmp, (ot) => {
        return componentes.indexOf(ot.componente) !== -1
      })

      let prioridades = (this.filtroPrioridad.length > 0)
        ? this.filtroPrioridad
        : this.optionPrioridad
      tmp = _.filter(tmp, (ot) => {
        return prioridades.indexOf(ot.prioridad) !== -1
      })

      let orgMant = (this.filtroOrgMant.length > 0)
        ? this.filtroOrgMant
        : this.optionOrgMant
      tmp = _.filter(tmp, (ot) => {
        return orgMant.indexOf(ot.orgCode) !== -1
      })

      let tipo = (this.filtroTipo.length > 0)
        ? this.filtroTipo
        : this.optionTipo
      tmp = _.filter(tmp, (ot) => {
        return tipo.indexOf(ot.tipoTrabajo) !== -1
      })

      this.listaOts = tmp
    },
    reset () {
      this.filtroComponentes = []
      this.filtroPrioridad = []
      this.filtroOrgMant = []
      this.filtroTipo = []
    }
  },

  watch: {
    listaOtsActivas: function (val) {
      this.listaOts = val
      this.reset()
      this.cargando = false
    },
    filtroComponentes: function (val) {
      this.aplicarFiltros()
    },
    filtroPrioridad: function (val) {
      this.aplicarFiltros()
    },
    filtroOrgMant: function (val) {
      this.aplicarFiltros()
    },
    filtroTipo: function (val) {
      this.aplicarFiltros()
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
    padding-right: 12px
  #buscar-ot-filtro
    border-bottom: $el_border
    height: $buscar_ot_filtro_height
    & > div
      padding: 8px 40px 8px 24px
      @include vertical-align-middle
      & > p
        display: inline-block
        padding: 0
      & > button
        float: right

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
