<template>
  <div className="container">
    <router-view />
  </div>
</template>

<script>
import designs from './source'
export default {
  name: 'app',
  created(){
    console.log(designs);
    this.$store.dispatch('init_designs',designs)
  }
}
</script>

<style>
@import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css)
</style>
