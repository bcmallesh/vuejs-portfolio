<template>
  <div className="container">
    <router-view />
  </div>
</template>

<script>
import designs from './source'
import axios from 'axios'
export default {
  name: 'app',
  created(){
    // axios.get("http://localhost:8060/portfolio/projectsinfo")
    // .then(response => {
    //   const designs = []
    //   const designData = response.data
    //   for(const k in designData){
    //     const design = designData[k];
    //     designs.push(design)
    //   }
    // console.log(designs);
    // this.$store.dispatch('init_designs',designs)
    // });    

    console.log(designs);
    this.$store.dispatch('init_designs',designs);
    
    axios.get("http://localhost:8060/portfolio/lookupdata")
    .then(response => {
      const modals = []
      const modalData = response.data
     modals.push(modalData);
    console.log(modals);
    this.$store.dispatch('init_modals',modals)
    });
  }
}
</script>

<style>
@import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css)
</style>
