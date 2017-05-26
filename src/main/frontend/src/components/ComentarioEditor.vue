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
      setTimeout(_ => {
        let tmp = event.target.value
        if (tmp.indexOf('\t') !== -1) {
          tmp = tmp.replace(/\t/g, ' | ')
          tmp = tmp.replace('\n', '\n---|---\n')
          this.vmdEditor.value = tmp
          console.log(this.vmdEditor.value)
        }
      }, 100)
      this.__updateInput()
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
        this.__setSelection(selected.start - cLength, selected.end + cLength)
        this.__replaceSelection(chunk)
        cursor = selected.start - cLength
      } else {
        this.__replaceSelection(chars + chunk + chars)
        cursor = selected.start + cLength
      }

      // 设置选择内容
      this.__setSelection(cursor, cursor + chunk.length)
      this.__updateInput()
    },
    __getSelection () {
      let e = this.vmdEditor
      return (
        ('selectionStart' in e && function () {
          let l = e.selectionEnd - e.selectionStart
          return {start: e.selectionStart, end: e.selectionEnd, length: l, text: e.value.substr(e.selectionStart, l)}
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

        /* 如果浏览器不支持 */
        function () {
          return null
        }
      )()
    },
    __replaceSelection (text) {
      let e = this.vmdEditor
      return (
        ('selectionStart' in e && function () {
          e.value = e.value.substr(0, e.selectionStart) + text + e.value.substr(e.selectionEnd, e.value.length)
          // Set cursor to the last replacement end
          e.selectionStart = e.value.length
          return null
        }) ||

        /* 如果浏览器不支持 */
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
</style>
