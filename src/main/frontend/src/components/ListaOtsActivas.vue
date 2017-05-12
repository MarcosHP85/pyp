<template>
  <div
    class="app-page">
    <el-row
      id="buscar-ot-filtro">
      <el-col
        :span="24">
        <h2>Filtro</h2>
      </el-col>
    </el-row>
    <el-row
      id="buscar-ot">
      <el-col
        id="buscar-ot-lista"
        :span="6">
        <lista-de-ots
          :ots="listaOtsActivas"
          :cargando="cargando"
          @ot-selecionada="mostrarOt">
        </lista-de-ots>
      </el-col>
      <el-col
        :span="18">
        <ot-preview
          :ot="otSeleccionada">
        </ot-preview>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import listaDeOts from '@/components/ListaDeOts'
import otPreview from '@/components/OtPreview'

export default {
  name: 'lista-ots-activas',
  components: { otPreview, listaDeOts },
  props: ['ots'],

  data () {
    return {
      otSeleccionada: null,
      cargando: false
    }
  },
  mounted () {
    this.cargarLista()
  },

  computed: {
    ...mapGetters('ifs', [
      'listaOtsActivas'
    ])
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
    }
  },

  watch: {
    listaOtsActivas: function (val) {
      this.cargando = false
    },
    '$route' (to, from) {
      this.cargarLista()
    }
  }
}
</script>

<style lang="sass">
  @import "~@/styles/main"

  $buscar_ot_filtro_height: 64px

  .app-page
    box-sizing: border-box
    height: calc(100% - #{$top_bar_height})
    height: -moz-calc(100% - #{$top_bar_height})
    height: -webkit-calc(100% - #{$top_bar_height})
  #buscar-ot-filtro
    border-bottom: $el_border
    height: $buscar_ot_filtro_height
  #buscar-ot
    height: calc(100% - #{$buscar_ot_filtro_height})
    height: -moz-calc(100% - #{$buscar_ot_filtro_height})
    height: -webkit-calc(100% - #{$buscar_ot_filtro_height})
  #buscar-ot-lista
    padding: 12px
    border-right: $el_border
    height: 100%
    overflow-x: auto
</style>
