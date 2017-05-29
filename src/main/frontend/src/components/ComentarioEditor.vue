<template>
  <div class="comentario-editor">
    <div class="ce-toolbar">
      <el-button-group>
        <el-button size="small" @click="addBold" title="Ctrl + B">
          <i class="fa fa-bold" aria-hidden="true"></i></el-button>
        <el-button size="small" @click="addItalic" title="Ctrl + I">
          <i class="fa fa-italic" aria-hidden="true"></i></el-button>
        <el-button size="small" @click="addStrikethrough" title="Ctrl + S">
          <i class="fa fa-strikethrough" aria-hidden="true"></i></el-button>
      </el-button-group>
    </div>
    <el-row>
      <el-col :span="12">
        <textarea class="el-textarea__inner" ref="vmdEditor" rows="5"
          :value="vmdInput"
          @input="textInput"
          @paste="findTable"
          @keydown.ctrl.b.prevent="addBold"
          @keydown.ctrl.i.prevent="addItalic"
          @keydown.ctrl.s.prevent="addStrikethrough">
        </textarea>
      </el-col>
      <el-col :span="12" style="padding: 5px 7px">
        <p v-html="textHtml"></p>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import marked from 'marked'

marked.setOptions({
  renderer: new marked.Renderer(),
  gfm: true,
  tables: true,
  breaks: true,
  pedantic: false,
  sanitize: false,
  smartLists: true,
  smartypants: false
})

export default {
  name: 'comentario-editor',

  data () {
    return {
      vmdInput: 'hola mundo !!!',
      vmdEditor: null
    }
  },

  mounted () {
    this.vmdEditor = this.$refs.vmdEditor
  },

  computed: {
    textHtml () {
      return marked(this.vmdInput)
    }
  },

  methods: {
    textInput (event) {
      this.__updateInput()
    },
    addBold () {
      this.__addEntre('**')
    },
    addItalic () {
      this.__addEntre('_')
    },
    addStrikethrough () {
      this.__addEntre('~~')
    },
    findTable (event) {
      let e = this.vmdEditor
      let start = e.selectionStart
      setTimeout(_ => {
        let end = e.selectionStart
        let value = e.value.substring(start, end)
        if ((value.match(/\t/mg) || []).length > 1 &&
          (value.match(/\n/mg) || []).length > 0) {
          let lineas = value.split('\n')

          value = ''
          let continua = false
          let head = true
          for (var i = 0; i < lineas.length - 1; i++) {
            value += (!continua) ? '|' : ''
            let columnas = lineas[i].split('\t')
            for (var j = 0; j < columnas.length; j++) {
              // si es el fin de la linea y la celda es multilinea
              if (j === columnas.length - 1 &&
                columnas[j][0] === '"') {
                columnas[j] = columnas[j].substring(1, columnas[j].length)
                continua = true
              }
              if (j === 0 &&
                continua &&
                columnas[j][columnas[j].length - 1] === '"') {
                columnas[j] = columnas[j].substring(0, columnas[j].length - 1)
                continua = false
              }
              value += ' ' + columnas[j]
              value += (!continua) ? ' |' : ''
            }
            value += (!continua) ? '\n' : '<br>'
            if (head && !continua) {
              value += '| -\n'
              head = false
            }
          }
          // Agrega una linea en blanco antes de la tabla
          if (start !== 0) {
            if (e.value[start - 1] !== '\n') {
              value = '\n\n' + value
            } else {
              if (e.value[start - 2] !== '\n') {
                value = '\n' + value
              }
            }
          }
          e.value = e.value.substring(0, start) +
            value + e.value.substring(end, e.value.length)
          this.__updateInput()
        }
      }, 100)
    },
    __addEntre (chars) {
      let chunk
      let cursor
      let selected = this.__getSelection()
      let content = this.__getContent()
      let cLength = chars.length

      if (selected.length === 0) {
        // 提供额外的内容
        chunk = 'texto'
      } else {
        chunk = selected.text
      }

      // 替换选择内容并将光标设置到chunk内容前
      if (content.substr(selected.start - cLength, cLength) === chars &&
        content.substr(selected.end, cLength) === chars) {
        // this.__setSelection(selected.start - cLength, selected.end + cLength)
        this.__replaceSelection(selected.start - cLength, selected.end + cLength, chunk)
        cursor = selected.start - cLength
      } else {
        this.__replaceSelection(selected.start, selected.end, chars + chunk + chars)
        cursor = selected.start + cLength
      }

      // 设置选择内容
      this.__setSelection(cursor, cursor + chunk.length)
      this.__updateInput()
    },
    __getSelection () {
      let e = this.vmdEditor
      let i = e.selectionStart
      let j = e.selectionEnd

      if (i === j) {
        for (; i > 0 &&
          e.value[i - 1] !== ' ' &&
          e.value[i - 1] !== '\n'; i--) { }
        for (; j < e.value.length - 1 &&
          e.value[j] !== ' ' &&
          e.value[j] !== '\n'; j++) { }
      }
      return (
        ('selectionStart' in e && function () {
          let l = j - i
          return {start: i, end: j, length: l, text: e.value.substr(i, l)}
        }) ||

        /* 如果浏览器不支持 */
        function () {
          return null
        }
      )()
    },
    __setSelection (start, end) {
      let e = this.vmdEditor
      return (
        ('selectionStart' in e && function () {
          e.selectionStart = start
          e.selectionEnd = end
          return null
        }) ||

        function () {
          return null
        }
      )()
    },
    __replaceSelection (start, end, text) {
      let e = this.vmdEditor
      return (
        ('selectionStart' in e && function () {
          e.value = e.value.substr(0, start) + text + e.value.substr(end, e.value.length)
          // Set cursor to the last replacement end
          // e.selectionStart = e.value.length
          return null
        }) ||

        function () {
          e.value += text
          return null
        }
      )()
    },
    __getContent () {
      return this.vmdEditor.value
    },
    __updateInput () {
      this.vmdInput = this.vmdEditor.value
      this.vmdEditor.focus()
    }
  }
}
</script>

<style lang="sass">
  @import "~sass"
  .comentario-editor
    b, strong
      font-weight: $bold-weight!important

    i, em
      font-style: italic!important

    ol
      margin: 1em 1.5em!important
      &>li
        list-style: decimal

    ul
      margin: 1em 1.5em!important
      &>li
        list-style: disc

    code
      background-color: yellow

    blockquote
      border-left: 4px solid #ccc!important
      margin: 1em .5em!important
      &>p
        padding-left: .5em

    table
      max-width: 100%
      margin: 1em 0
      border: $el_border
      tr
        border: $el_border
      th
        padding: 0 18px
      td
        padding: 0 18px
      thead
        background-color: #eef1f6
        & > tr > th
          text-align: center
        & :first-child
          text-align: left
        & :last-child
          text-align: right
      tbody
        & > tr > td
          text-align: center
        & :first-child
          text-align: left
        & :last-child
          text-align: right
</style>
