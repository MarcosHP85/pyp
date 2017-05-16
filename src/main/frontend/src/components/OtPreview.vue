<<<<<<< HEAD
<template>
  <div style="padding: 16px" v-if="ot !== null && ot !== undefined">
    <el-row>
      <el-col :span="24">
        <div id="ot-view-cabecera">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item>Semana 1720</el-breadcrumb-item>
            <el-breadcrumb-item>{{ ot.numOt }}</el-breadcrumb-item>
          </el-breadcrumb>
          <div class="ot-view-titulo">
            {{ ot.directiva }}
          </div>
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
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="16">
        <div id="ot-view-main">
          <div id="ot-view-detalles">
            <el-row>
              <el-col :span="4">Tipo:</el-col>
              <el-col :span="8">{{ ot.tipoTrabajo }}</el-col>
              <el-col :span="4">Estado:</el-col>
              <el-col :span="8">{{ ot.estado }}</el-col>
            </el-row>
            <el-row>
              <el-col :span="4">Prioridad:</el-col>
              <el-col :span="8">{{ ot.prioridad }}</el-col>
              <el-col :span="4">Org:</el-col>
              <el-col :span="8">{{ ot.orgCode }}</el-col>
            </el-row>
          </div>
          <div id="ot-view-descripcion">
            {{ ot.tarea }}
            {{ ot.error }}
          </div>
          <div id="ot-view-relacionadas">

          </div>
          <div id="ot-view-adjuntos">

          </div>
          <div id="ot-view-actividades">
            {{ ot.comentarioPla }}
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div id="ot-view-side">
          <div id="ot-view-personas">

          </div>
          <div id="ot-view-fechas">
            <el-row>
              <el-col :span="8">Creacion</el-col>
              <el-col :span="16">{{ fechaRegistro }}</el-col>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'ot-preview',

  props: ['ot'],

  computed: {
    fechaRegistro () {
      let d = new Date(this.ot.fechaRegistro)
      return d.getDay() + '/' + (1 + d.getMonth()) + '/' + d.getFullYear() + ' ' + d.getHours() + ':' + d.getMinutes()
    }
  }
}
</script>

<style lang="sass">
  %ot-view-before
    white-space: pre
    font-size: 14px
    font-weight: 700

  #ot-view-cabecera
    padding-bottom: 18px
  #ot-view-detalles::before
    content: 'Detalles\A'
    @extend %ot-view-before
  #ot-view-descripcion::before
    content: 'Descripcion\A'
    @extend %ot-view-before
  #ot-view-actividades::before
    content: 'Actividades\A'
    @extend %ot-view-before
  #ot-view-fechas::before
    content: 'Fechas\A'
    @extend %ot-view-before
  #ot-view-personas::before
    content: 'Personas\A'
    @extend %ot-view-before
</style>
=======
<template>
  <div style="padding: 16px" v-if="ot !== null && ot !== undefined">
    <el-row>
      <el-col :span="24">
        <div id="ot-view-cabecera">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item>Semana 1720</el-breadcrumb-item>
            <el-breadcrumb-item>{{ ot.numOt }}</el-breadcrumb-item>
          </el-breadcrumb>
          <div class="ot-view-titulo">
            {{ ot.componente }} - {{ ot.directiva }}
          </div>
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
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="16">
        <div id="ot-view-main">
          <div id="ot-view-detalles">
            <el-row>
              <el-col :span="4">Tipo:</el-col>
              <el-col :span="8">
                <icono-tipo-trabajo :code="ot.tipoTrabajo"></icono-tipo-trabajo>{{ ot.tipoTrabajo }}
              </el-col>
              <el-col :span="4">Estado:</el-col>
              <el-col :span="8">{{ ot.estado }}</el-col>
            </el-row>
            <el-row>
              <el-col :span="4">Prioridad:</el-col>
              <el-col :span="8">
                <icono-prioridad :code="ot.prioridad"></icono-prioridad>{{ ot.prioridad }}
              </el-col>
              <el-col :span="4">Org:</el-col>
              <el-col :span="8">{{ ot.orgCode }}</el-col>
            </el-row>
          </div>
          <div id="ot-view-descripcion" class="font-body">
            {{ ot.tarea }}
            {{ ot.error }}
          </div>
          <div id="ot-view-relacionadas">

          </div>
          <div id="ot-view-adjuntos">

          </div>
          <div id="ot-view-actividades">
            {{ ot.comentarioPla }}
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div id="ot-view-side">
          <div id="ot-view-personas">

          </div>
          <div id="ot-view-fechas">
            <el-row>
              <el-col :span="8">Creacion</el-col>
              <el-col :span="16">{{ fechaRegistro }}</el-col>
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
  name: 'ot-preview',
  props: ['ot'],
  components: { iconoPrioridad, iconoTipoTrabajo },

  computed: {
    fechaRegistro () {
      let d = new Date(this.ot.fechaRegistro)
      return d.getDay() + '/' + (1 + d.getMonth()) + '/' + d.getFullYear() + ' ' + d.getHours() + ':' + d.getMinutes()
    }
  }
}
</script>

<style lang="sass">
  %ot-view-before
    white-space: pre
    font-size: 14px
    font-weight: 700

  #ot-view-cabecera
    padding-bottom: 18px
  #ot-view-detalles::before
    content: 'Detalles\A'
    @extend %ot-view-before
  #ot-view-descripcion::before
    content: 'Descripcion\A'
    @extend %ot-view-before
  #ot-view-actividades::before
    content: 'Actividades\A'
    @extend %ot-view-before
  #ot-view-fechas::before
    content: 'Fechas\A'
    @extend %ot-view-before
  #ot-view-personas::before
    content: 'Personas\A'
    @extend %ot-view-before
</style>
>>>>>>> branch 'master' of https://github.com/MarcosHP85/pyp.git
