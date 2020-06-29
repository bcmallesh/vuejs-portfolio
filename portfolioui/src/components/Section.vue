<template>
  <div class="row innerbox1 innr-2">
    <div>
      <div class="col-md-6" :class="section.sectionLayout =='left' ? 'float-left' : 'float-right'">
        <h5>{{section.heading}}</h5>
        <div class="row" v-html="section.description"></div>
      </div>
      <div class="col-md-6" :class="section.sectionLayout =='left' ?  'float-right' : 'float-left'">
        <img width="100%" :src="section.imagePath" class="img-responsive" />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: ["section"]
};
</script>