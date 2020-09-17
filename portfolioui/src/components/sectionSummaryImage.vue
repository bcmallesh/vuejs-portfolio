<template>
    <div class="col-md-6">
      <img class="imgq rounded" :src="section_summary_image.summary_hero_image" alt="UI" />
    </div>
</template>
<script>
export default {
  props: ["section_summary_image"]
};
</script>
