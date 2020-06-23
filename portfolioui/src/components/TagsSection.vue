<template>
  <div class="box1">
    <p class="black">{{section_tags.message}}</p>
  </div>
</template>
<script>
export default {
  props: ["section_tags"]
};
</script>

<style scoped>
.boxsm {
  display: -webkit-inline-box;
  display: -ms-inline-flexbox;
  display: inline-flex;
  margin-left: 16px;
}

.boxsm .box1 {
  border: 1px solid #ece9e9;
  padding: 10px;
  padding-left: 20px;
  padding-right: 20px;
  border-radius: 30px;
  margin-right: 20px;
}

.boxsm .black {
  color: black !important;
  margin-bottom: 0px !important;
}
</style>