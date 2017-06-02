<template>
  <div
    class="app-page">
    <div class="page-header">
      <el-row>
        <el-col
          :span="24">
          <span>Tareas abiertas
            <span
              @click="filtrosVisible = !filtrosVisible">
              &nbsp; Filtros
              <i class="fa fa-fw"
                :class="{
                  'fa-caret-down': !filtrosVisible,
                  'fa-caret-up': filtrosVisible }">
              </i>
            </span>
          </span>
        </el-col>
      </el-row>
      <transition name="el-zoom-in-top">
        <el-row
          id="buscar-ot-filtro"
          v-show="filtrosVisible">
          <el-col
            :span="24">
            <p class="font-small-title">Filtrar por &nbsp;</p>
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
      </transition>
    </div>
    <el-row
      id="buscar-ot"
      :class="{ 'alto-filtro-visible': filtrosVisible }">
      <el-col
        v-loading="cargando"
        style="height: 100%"
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
      filtrosVisible: false,
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
        .sort()
    },
    optionComponentes () {
      return _.uniq(
        _.map(this.listaOtsActivas, 'componente'))
        .sort()
    },
    optionOrgMant () {
      return _.uniq(
        _.map(this.listaOtsActivas, 'orgCode'))
        .sort()
    },
    optionTipo () {
      return _.uniq(
        _.map(this.listaOtsActivas, 'tipoTrabajo'))
        .sort()
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

  .app-page
    box-sizing: border-box
    height: 100%
    // padding-right: 12px
  .page-header
    padding: 24px
    font-size: 28px
    box-sizing: border-box
    min-height: $page_header_height
    font-weight: $regular-weight
    border-bottom: $el_border
    // & > div > div
    //   @include vertical-align-middle
    & > div > div > span
      vertical-align: text-bottom
    & > div > div > span > span
      color: $font-link-color
      cursor: pointer
      font-size: .6em
  #buscar-ot-filtro
    height: 38px
    margin-top: 24px
    & > div
      // @include vertical-align-middle
      & > p
        display: inline-block
        padding: 0
        font-weight: $regular-weight
      & > button
        float: right

  #buscar-ot
    height: calc(100% - #{$page_header_height})
    height: -moz-calc(100% - #{$page_header_height})
    height: -webkit-calc(100% - #{$page_header_height})

  #ot-preview
    // padding: 16px
    height: 100%
    overflow-x: auto

  .alto-filtro-visible
    height: calc(100% - #{$page_header_height} - 62px)!important
    height: -moz-calc(100% - #{$page_header_height} - 62px)!important
    height: -webkit-calc(100% - #{$page_header_height} - 62px)!important

  .el-select__tags
    line-height: inherit
</style>
