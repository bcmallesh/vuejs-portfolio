<template>
  <div
    class="portfolio col-lg-3 col-md-6"
    @click="openDescreptionPageFunction"
    v-bind:class="[design.industrys.join(' ') , design.works.join(' ') , design.types.join(' ') ]"
  >
    <figure class="portfolio-image">
      <img :src="design.imagePath" alt="CxCalibration" class="img-responsive" />
      <figcaption class="caption">
        <div class="caption-content">
          <h3 class="portfolio-item-title text-center sub-title">{{design.name}}</h3>
          <p>{{design.description | design.id}}</p>
        </div>
      </figcaption>
    </figure>
  </div>
</template>
<script>
import description from "../sectionsSource";
export default {
  props: ["design"],
  methods: {
    openDescreptionPageFunction() {
      this.$router.push({
        path: "/description",
        query: { preview: "false", id: this.design.id }
      });
    },
    convertListTocommaSeperated(){
      return this.design.works[1]
    }
  }
};
</script>
