<template>
  <el-row>
    <el-col :span="12">
      <el-table
        :data="listaOtsActivas"
        highlight-current-row
        @current-change="handleCurrentChange"
        style="width: 100%; text-align: left">
        <el-table-column
          prop="numOt"
          label="OT"
          width="100">
        </el-table-column>
        <el-table-column
          prop="componente"
          label="Componente"
          width="140">
        </el-table-column>
        <el-table-column
          prop="tarea"
          label="Tarea">
        </el-table-column>
      </el-table>
    </el-col>
    <el-col :span="12">
      <ot-preview :ot="otSelect"></ot-preview>
    </el-col>
  </el-row>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import otPreview from '@/components/OtPreview'

export default {
  name: 'lista-ots-activas',
  components: { otPreview },
  props: ['ots'],

  data () {
    return {
      otSelect: null
    }
  },
  mounted () {
    this.cargaTabla()
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
    cargaTabla () {
      this.buscarOtsActivas(this.ots)
    },
    handleCurrentChange (val) {
      this.otSelect = val
      console.log(val)
    }
  },

  watch: {
    '$route' (to, from) {
      this.cargaTabla()
    }
  }
}
</script>

<style lang="sass">
</style>
