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
        <!-- <el-input
          ref="textarea"
          type="textarea"
          :rows="5"
          v-model="textarea">
        </el-input> -->
        <textarea
          ref="textarea"
          class="el-textarea__inner"
          :value="textarea">
        </textarea>
      </el-col>
      <el-col :span="12">
        <p v-html="textareaMarked"></p>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div
          id="ceTextarea"
          ref="ceTextarea"
          class="el-textarea__inner ce-textarea"
          contenteditable="true"
          v-html="texto">
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import marked from 'marked'
import toMarkdown from 'to-markdown'

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
      textarea: ''
    }
  },

  computed: {
    texto () {
      return marked('No **necesitas** ser experto')
    },
    textareaMarked () {
      return marked(this.textarea)
    }
  },

  methods: {
    addBold () {
      console.log('start ' + this.$refs.textarea.selectionStart)
      console.log('end ' + this.$refs.textarea.selectionEnd)
    },
    textEdit (val) {
      document.execCommand(val, false)
    },
    textoMarkdown () {
      console.log(toMarkdown(this.$refs.ceTextarea.innerHTML,
        { gfm: true }))
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
