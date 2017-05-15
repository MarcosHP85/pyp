<template>
  <span v-if="code === 'PRE'" class="fa-stack stack-inline fa-lg">
    <i class="fa fa-square fa-stack-1x" style="color: rgb(75,173,232)"></i>
    <i class="fa fa-check fa-stack-1x" style="font-size: .6em;color: white"></i>
  </span>
  <span v-else-if="code === 'COR'" class="fa-stack stack-inline fa-lg">
    <i class="fa fa-square fa-stack-1x" style="color: rgb(229,73,58)"></i>
    <i class="fa fa-circle fa-stack-1x" style="font-size: .5em;color: white"></i>
  </span>
  <span v-else-if="code === 'OTR'" class="fa-stack stack-inline fa-lg">
    <i class="fa fa-square fa-stack-1x" style="color: rgb(255,180,78)"></i>
    <i class="fa fa-asterisk fa-stack-1x" style="font-size: .55em;color: white"></i>
  </span>
  <span v-else-if="code === 'ELE'" class="fa-stack stack-inline fa-lg">
    <i class="fa fa-square fa-stack-1x" style="color: rgb(99,186,60)"></i>
    <i class="fa fa-bookmark fa-stack-1x" style="font-size: .57em;color: white"></i>
  </span>
  <span v-else-if="code === 'TAP'" class="fa-stack stack-inline fa-lg">
    <i class="fa fa-square fa-stack-1x" style="color: rgb(255,180,78)"></i>
    <i class="fa fa-clone fa-stack-1x" style="font-size: .5em;color: white"></i>
  </span>
  <span v-else class="fa-stack stack-inline fa-lg">
    <i class="fa fa-square fa-stack-1x" style="color: rgb(144,78,226)"></i>
    <i class="fa fa-flash fa-stack-1x" style="font-size: .7em;color: white"></i>
  </span>
</template>

<script>
export default {
  name: 'tipo-trabajo',
  props: ['code']
}
</script>

<style lang="sass">
  .stack-inline
    width: 1em!important
    height: 1em!important
    line-height: 1em!important

  .tipo-trabajo-simbol
    font-size: .6em!important
    color: white
</style>
