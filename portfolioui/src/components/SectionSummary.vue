<template>
    <div class="row">
          <div class="col-md-6">
                            <h5>{{section_summary.summary_heading}}</h5>
                        </div>
                        <div class="col-md-6">
                            <img class="imgq" :src="section_summary.summary_image" alt="UI">
                        </div>
    </div>
</template>
<script>
export default {
    props:['section_summary']
}
</script>

<style scoped>
.imgq {
  position: absolute;
  z-index: 2;
  width: 90%;
  left: 7%;
  top: -118%;
}
</style>