<template>
  <div class="inner2">
    <header id="header">
      <div class="container">
        <nav class="navbar navbar-light pt-4">
          <router-link to="/" activeClass="active" tag="a">
          <!-- <a class="navbar-brand" href="index.html"> -->
            <img src="../assets/images/SVG/logo.svg" alt="EVRY" class="evry-logo" />
            <span class="vl"></span>
            <span>
              Digital
              <b>Experience</b>
            </span>
          </router-link>
        </nav>
        <div class="padding2x"></div>
        <div class="content col-md-6">
          <h1>
            {{description.project_name}}
          </h1>
        </div>
      </div>
    </header>
    <div class="container">
      <section class="work main-box" id="boxinner">
        <div class="row innerbox1 mb-5">
          <appSectionSummary :section_summary="description.section_summary"></appSectionSummary>
          <appSectionSummaryImage :section_summary_image="description.section_summary"></appSectionSummaryImage>
          <div class="boxsm">
            <app-tags-section
              v-for="tag in description.tags"
              v-bind:key="tag.message"
              :section_tags="tag"
            ></app-tags-section>
          </div>
        </div>
        <app-section
          v-for="section in description.sections"
          v-bind:key="section.id"
          :section="section"
        ></app-section>
      </section>
    </div>
    <footer class="foot">
      <div class="container">
        <div class="copyright text-center">
          <p>© 2020 EVRY India</p>
        </div>
      </div>
    </footer>
  </div>
</template>
<script>
import Section from "./Section.vue";
import TagsSection from "./TagsSection.vue";
import SectionSummary from "./SectionSummary";
import SectionSummaryImage from "./sectionSummaryImage";
import description from "../sectionsSource";
import axios from 'axios'
export default {
  data() {
    return {
      id: this.$route.query.id,
      preview: this.$route.query.preview,
      description,
      descriptionTo : this.$route.query.description,
    };

  },
  created() {
    console.log("created hook for description");
    this.previewData = JSON.parse(localStorage.getItem('previewData'));
    if (this.preview == "true" && this.previewData ) {
      console.log('previewData:',this.previewData);
      this.description = this.previewData;

    } else {
      // this will have a axios call
    //this.description = description
    axios.get(`http://localhost:8060/portfolio/projects/${this.id}`)
    .then(response => {
     console.log("axios call");
     console.log("this.description: ",this.description);
    this.description = response.data;

    }); 
    }
  },
  components: {
    appSection: Section,
    appTagsSection: TagsSection,
    appSectionSummary: SectionSummary,
    appSectionSummaryImage: SectionSummaryImage
  }
};
</script>
