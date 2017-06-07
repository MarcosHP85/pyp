import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'
import Home from '@/components/Home'
import Hello from '@/components/Hello'
import Login from '@/pages/Login'
import OtsActivas from '@/pages/OtsActivas'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      props: route => ({ redirect: route.query.redirect })
    },
    {
      path: '/search',
      name: 'OtsActivas',
      component: OtsActivas,
      props: route => ({ ots: route.query.ots })
    },
    {
      path: '/hello',
      name: 'Hello',
      component: Hello,
      meta: { requiresAuth: true }
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!store.getters['auth/isLogin']) {
      next({
        name: 'Login',
        query: { redirect: to.fullPath }
      })
    }
  }

  next()
})

export default router
