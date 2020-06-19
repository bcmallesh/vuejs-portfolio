<template>
    <div class="col-md-6">
      <img class="imgq" :src="section_summary_image.summary_image" alt="UI" />
    </div>
</template>
<script>
export default {
  props: ["section_summary_image"]
};
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