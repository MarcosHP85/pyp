<template>
  <div class="comentario-editor">
    <div class="ce-toolbar">
      <el-button-group>
        <el-button @click="addBold">bold</el-button>
        <el-button @click="textEdit('italic')">italic</el-button>
        <el-button @click="textEdit('strikeThrough')">tachar</el-button>
      </el-button-group>
    </div>
    <el-row>
      <el-col :span="12">
        <textarea
          id="ceTextarea"
          ref="textarea"
          class="el-textarea__inner"
          v-model="textRaw">
        </textarea>
      </el-col>
      <el-col :span="12">
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
      textRaw: '**hola** mundo !!!'
    }
  },

  computed: {
    textHtml () {
      return marked(this.textRaw)
    }
  },

  methods: {
    addBold () {
      let sel = this._seleccion()
      this.textRaw = sel.pre + this._textoEntre(sel.in, '**') + sel.pos
      this.$refs.textarea.focus()
    },
    _seleccion () {
      let {start, end} = this._startEndSeleccion()

      return {
        pre: this.textRaw.substring(0, start),
        in: this.textRaw.substring(start, end),
        pos: this.textRaw.substring(end, this.textRaw.lenght)
      }
    },
    _startEndSeleccion () {
      let start = this.$refs.textarea.selectionStart
      let end = this.$refs.textarea.selectionEnd

      if (start === end) {
        while (start > 0 &&
          this.textRaw.charAt(start - 1) !== ' ' &&
          this.textRaw.charAt(start - 1) !== '\n') {
          --start
        }
        while (end < this.textRaw.length &&
          this.textRaw.charAt(end) !== ' ' &&
          this.textRaw.charAt(end) !== '\n') {
          ++end
        }
      }
      return {start, end}
    },
    _textoEntre (texto, symbol) {
      if (texto.substring(0, symbol.length) === symbol &&
        texto.substring(texto.length - symbol.length, texto.length) === symbol) {
        return texto.substring(symbol.length, texto.length - symbol.length)
      } else {
        return symbol + texto + symbol
      }
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
</style>
