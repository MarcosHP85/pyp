<template>
  <div v-if="ot !== null && ot !== undefined" class="ot-view">
    <el-row>
      <el-col :span="24">
        <div id="ot-view-cabecera" class="ot-view-bloque">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item>Semana 1720</el-breadcrumb-item>
            <el-breadcrumb-item>{{ ot.numOt }}</el-breadcrumb-item>
          </el-breadcrumb>
          <div class="font-main-title">
            {{ ot.componente }} - {{ ot.directiva }}
          </div>
          <div>
            <el-button size="small">
              <i class="fa fa-comment-o fa-fw"></i> Comentar
            </el-button>
            <el-button-group>
              <el-button size="small">Asignar</el-button>
              <el-button size="small">Mas</el-button>
            </el-button-group>
            <el-button-group style="float: right">
              <el-button size="small">
                <i class="fa fa-share-square-o fa-fw"></i> Compartir
              </el-button>
              <el-button size="small">
                <i class="fa fa-download fa-fw"></i>Exportar
              </el-button>
            </el-button-group>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="16">
        <div id="ot-view-main">
          <div id="ot-view-detalles" class="ot-view-bloque">
            <p>Detalles &nbsp;<hr /></p>
            <el-row class="ot-view-detalle-fila">
              <el-col :span="4">Tipo:</el-col>
              <el-col :span="8" class="font-val">
                <icono-tipo-trabajo
                  :code="ot.tipoTrabajo">
                </icono-tipo-trabajo>&nbsp;{{ ot.tipoTrabajo }}
              </el-col>
              <el-col :span="4">Estado:</el-col>
              <el-col :span="8" class="font-val">
                <span>{{ ot.estado }}</span>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="4">Prioridad:</el-col>
              <el-col :span="8" class="font-val">
                <icono-prioridad
                  :code="ot.prioridad">
                </icono-prioridad>&nbsp;{{ ot.prioridad }}
              </el-col>
              <el-col :span="4">Org:</el-col>
              <el-col :span="8" class="font-val">{{ ot.orgCode }}</el-col>
            </el-row>
          </div>
          <div id="ot-view-descripcion" class="ot-view-bloque">
            <p>Descripcion &nbsp;<hr /></p>
            <pre>{{ ot.tarea }}{{ ot.error }}</pre>
          </div>
          <div id="ot-view-relacionadas" class="ot-view-bloque">
            <p>Relacionadas &nbsp;<hr /></p>
          </div>
          <div id="ot-view-adjuntos" class="ot-view-bloque">
            <p>Adjuntos &nbsp;<hr /></p>
          </div>
          <div id="ot-view-actividades" class="ot-view-bloque">
            <p>Actividades &nbsp;<hr /></p>
            <p>
              <pre>{{ ot.comentarioPla }}</pre>
            </p>
            <Vueditor></Vueditor>
            <vue-html5-editor :content="content" :height="500"></vue-html5-editor>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div id="ot-view-side">
          <div id="ot-view-personas" class="ot-view-bloque">
            <p>Personas &nbsp;<hr /></p>
          </div>
          <div id="ot-view-fechas" class="ot-view-bloque">
            <p>Fechas &nbsp;<hr /></p>
            <el-row>
              <el-col :span="8">Creacion</el-col>
              <el-col :span="16" class="font-val">{{ fechaRegistro }}</el-col>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import iconoPrioridad from '@/components/iconos/Prioridad'
import iconoTipoTrabajo from '@/components/iconos/TipoTrabajo'

export default {
  name: 'ot-view',
  props: ['ot'],
  components: { iconoPrioridad, iconoTipoTrabajo },

  data () {
    return {
      myHTML: ''
    }
  },

  computed: {
    fechaRegistro () {
      let d = new Date(this.ot.fechaRegistro)
      return d.getDay() + '/' + (1 + d.getMonth()) + '/' + d.getFullYear() + ' ' + d.getHours() + ':' + d.getMinutes()
    }
  }
}
</script>

<style lang="sass">
  @import "~sass"

  // #ot-view-cabecera
  //   padding-bottom: $vertical-spacing-l

  .ot-view-bloque
    @extend .font-body
    padding: $vertical-spacing-m $vertical-spacing-l
    p:first-child
      @extend .font-small-title
      display: inline-block
      background: white
      padding-bottom: $vertical-spacing-s
    & > hr
      position: relative
      top: -19px
      font-size: 0
      border-bottom: 0
      border-top: $el_border
      z-index: -999
  .ot-view-detalle-fila
    padding-bottom: $vertical-spacing-s
</style>
