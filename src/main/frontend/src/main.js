// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import Vueditor from 'vueditor'
import 'vueditor/dist/css/vueditor.min.css'
import App from './App'
import router from './router'
import store from './store'

Vue.config.productionTip = false
Vue.use(ElementUI)

let config = {
  toolbar: [
    'bold', 'italic', 'underline', 'strikeThrough', 'foreColor', 'backColor', 'divider',
    'justifyLeft', 'justifyCenter', 'justifyRight', '|', 'insertOrderedList', 'insertUnorderedList', '|', 'switchView'
  ],
  fontName: [
    {val: 'arial black'}, {val: 'times new roman'}, {val: 'Courier New'}
  ],
  fontSize: [
    '12px', '14px', '16px', '18px', '20px', '24px', '28px', '32px', '36px'
  ],
  lang: 'en',
  mode: 'default',
  iframePath: '',
  fileuploadUrl: '',
  id: '',
  classList: []
}
Vue.use(Vueditor, config)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
