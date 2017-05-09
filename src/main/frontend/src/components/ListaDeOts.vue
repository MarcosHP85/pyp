<template>
  <div>
    <div id="orden-lista-de-ots">
      <el-dropdown trigger="click" @command="commandOrdenar" size="mini">
        <span>
          Ordenar por {{ labelOrdenerPor }}
          <i v-show="ordenarPor !== ''"
            class="fa fa-fw"
            :class="[ ordenAsc
              ? 'fa-arrow-down'
              : 'fa-arrow-up' ]">
          </i>|<i class="fa fa-caret-down fa-fw"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item v-for="item in listaOrdenarPor"
            :command="item.key" :key="item.key">
            <p>
              {{ item.label }}
              <i v-show="item.key === ordenarPor"
                class="fa fa-fw"
                :class="[ ordenAsc
                  ? 'fa-arrow-up'
                  : 'fa-arrow-down' ]">
              </i>
            </p>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-table
      v-loading="ots.length === 0"
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
            <span class="fa-stack fa-lg">
              <i class="fa fa-file-o fa-stack-1x"></i>
              <i class="fa fa-exclamation-circle fa-stack-1x" style="font-size: 12px"></i>
            </span>
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
      otSelecionada: null,
      listaOrdenarPor: [{
        label: 'Ot',
        key: 'numOt'
      }, {
        label: 'Prioridad',
        key: 'prioridad'
      }, {
        label: 'Componente',
        key: 'componente'
      }],
      ordenarPor: '',
      ordenAsc: true
    }
  },

  computed: {
    labelOrdenerPor: function () {
      return this.ordenarPor === '' ? ''
        : this.listaOrdenarPor.find(x => x.key === this.ordenarPor).label
    }
  },

  methods: {
    handleCurrentChange (ot) {
      this.otSelecionada = ot
      this.$emit('ot-selecionada', ot)
    },
    commandOrdenar (key) {
      if (this.ordenarPor === key) {
        this.ots.reverse()
        this.ordenAsc = !this.ordenAsc
      } else {
        this.ordenAsc = true
        this.ordenarPor = key
        this.ots.sort((a, b) => { return a[key] > b[key] })
      }
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
    .cell
      padding-top: 12px
      padding-bottom: 12px

  #orden-lista-de-ots
    text-align: left
    padding: 18px
</style>
