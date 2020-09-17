<template>
  <div class="box1 shadow" v-if="section_tags.message" >
    <p class="black">{{section_tags.message}}</p>
  </div>
</template>
<script>
export default {
  props: ["section_tags"]
};
</script>
