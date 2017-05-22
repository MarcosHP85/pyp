<template>
  <div class="comentario-editor">
    <div class="ce-toolbar">
      <el-button-group>
        <el-button @click="textEdit('bold')">bold</el-button>
        <el-button @click="textEdit('italic')">italic</el-button>
        <el-button @click="textEdit('strikeThrough')">tachar</el-button>
      </el-button-group>
    </div>
    <div
      ref="ceTextarea"
      class="el-textarea__inner ce-textarea"
      contenteditable="true"
      v-html="texto">
    </div>
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

  computed: {
    texto () {
      return marked('No **necesitas** ser experto para crear interfaces web interactivas, Vue.js te ayuda a escribir código de forma fácil, entendible y sostenible. \n\n`que se yo`\n\n1. list\n1. jfb\n\n> quote acbdsh\nkndsiom\n\n+ tip\n+ tip\n\n---\n\nt1 | t2 | t3\n--- | --- | ---\nf1 | f2 | f3\n')
    }
  },

  methods: {
    textEdit (val) {
      document.execCommand(val, false)
      console.log(toMarkdown(this.$refs.ceTextarea.innerHTML, { gfm: true }))
    }
  }
}
</script>

<style lang="sass">
  @import "~sass"
  .comentario-editor
    b, strong
      font-weight: $bold-weight!important

    i
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
