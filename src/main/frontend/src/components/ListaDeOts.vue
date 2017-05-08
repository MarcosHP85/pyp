<template>
  <div>
    <el-button @click="ordenar">ordenar</el-button>
    <el-table
      :data="ots"
      highlight-current-row
      @current-change="handleCurrentChange"
      id="lista-de-ots"
      :show-header="false">
      <el-table-column
        label="Tarea"
        prop="numOt">
        <template scope="scope">
          <p>
            <i class="fa fa-bookmark fa-fw"></i>
            {{ scope.row.orgCode }}-{{ scope.row.numOt }}
          </p>
          <p>
            {{ scope.row.componente }} - {{ scope.row.directiva }}
          </p>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'lista-de-ots',
  props: ['ots'],

  data () {
    return {
      otSelecionada: null
    }
  },

  methods: {
    handleCurrentChange (ot) {
      this.otSelecionada = ot
      this.$emit('ot-selecionada', ot)
    },
    ordenar () {
      this.ots.sort((a, b) => { return a.orgCode > b.orgCode })
    }
  }
}
</script>

<style lang="sass">
  @import "~@/styles/main"
  @import "~@/styles/font-awesome/font-awesome"

  #lista-de-ots
    text-align: left
    border: 0
    ::before
      height: 0
    ::after
      width: 0
</style>
