<template>
  <div class="inner2">
    <header id="header">
      <div class="container">
        <Menu></Menu>
        <div class="padding1x"></div>
      </div>
    </header>
    <div id="projectcreation">
      <h1 class="col-white">New Project</h1>
      <notification v-bind:notifications="notifications"></notification>
      <section class="main-box">
        <form v-on:submit.prevent="addProjectInfo" enctype="multipart/form-data">
          <div>
            <div>
              <div class="row">
                <div class="col-md-6">
                  <div class="m-t-25">
                    <label name="project_Category">Category</label>
                    <select
                      class="form-control"
                      v-model="project.category"
                      id="categoryList"
                      required
                    >
                      <option
                        v-for="(category, index) in categorys"
                        :key="index"
                        v-bind:value="category.key"
                      >{{category.value}}</option>
                    </select>
                  </div>
                  <div class="m-t-25">
                    <label name="project_Category">Project Name</label>
                    <input
                      class="form-control"
                      type="text"
                      v-model="project.projectName"
                      id="projectName"
                      required
                    />
                  </div>
                  <div class="m-t-25">
                    <label for="projectshortsummary">Short Summary:</label>
                    <textarea
                      class="form-control"
                      type="text"
                      v-model="project.shortSummary"
                      id="projectshortSummary"
                      required
                    ></textarea>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="m-t-25">
                    <label name="projectThumbnailPictureInput">Thumbnail Image:</label>
                    <picture-input
                      ref="pictureInput1"
                      @change="projectThumbnailPictureInputOnSelect"
                      @remove="projectThumbnailPictureInputOnRemoved"
                      :width="185"
                      :removable="true"
                      removeButtonClass="ui red button"
                      :height="127"
                      accept="image/jpeg, image/png, image/gif"
                      buttonClass="ui button primary"
                      :customStrings="{
                        upload: '<h1>Upload Thumbnail Image!</h1>',
                        drag: 'Drag and drop Thumbnail Image here'}"
                    ></picture-input>
                  </div>
                  <div>
                    <label name="projectHeroPictureInput">Hero Image:</label>
                    <picture-input
                      ref="pictureInput2"
                      @change="projectHeroPictureInputOnSelect"
                      @remove="projectHeroPictureInputOnRemoved"
                      :width="185"
                      :removable="true"
                      removeButtonClass="ui red button"
                      :height="127"
                      accept="image/jpeg, image/png, image/gif"
                      buttonClass="ui button primary"
                      :customStrings="{
                        upload: '<h1>Upload Hero Image!</h1>',
                        drag: 'Drag and drop Hero Image here'}"
                    ></picture-input>
                  </div>
                </div>
              </div>
              <div class="m-t-25">
                <label for="scopeofwork">Scope of work:</label>
                <div v-for="scopeOfWork in scopeOfWorks" v-bind:key="scopeOfWork.key">
                  <input
                    type="checkbox"
                    v-model="project.scopeofworks"
                    :id="scopeOfWork.key"
                    :value="scopeOfWork.value"
                  />
                  {{scopeOfWork.value}}
                </div>
              </div>
            </div>
            <hr />

            <div>
              <div>
                <span>
                  <strong>Sections:</strong>
                </span>
                <button
                  type="button"
                  @click="addProjectSection"
                  class="btn btn-link m-6 clr-link"
                >+Add Project Section</button>
              </div>

              <div
                class="card p-10"
                v-for="(section, counter) in project.sections"
                v-bind:key="counter"
              >
                <div class="seclass p-10">
                  <span class="m-r-10">Section {{counter+1}}: {{section.sectionTitle}}</span>
                  <button
                    type="button"
                    class="btn btn-primary btn-sm colr ml-auto"
                    v-on:click="deleteProjectSection(counter)"
                    aria-label="Close"
                  >
                    <i class="fa fa-times" aria-hidden="true"></i>
                  </button>
                </div>

                <div class="m-t-25">
                  <label class="m-r-10">Section layout:</label>
                  <div class="d-i-10">
                    <input type="radio" class="m-r-5" value="left" v-model="section.sectionLayout" />
                    <img src="../assets/images/left.jpg" height="50px" width="50px" />
                  </div>
                  <div class="d-i-10">
                    <input type="radio" class="m-r-5" value="right" v-model="section.sectionLayout" />
                    <img src="../assets/images/right.jpg" height="50px" width="50px" />
                  </div>
                </div>

                <div class="m-t-25">
                  <label for="title">Section Title</label>
                  <input
                    type="text"
                    id="title"
                    class="col-md-6 m-r-10 form-control"
                    v-model.lazy="section.sectionTitle"
                    required
                  />
                </div>

                <div class="m-t-25">
                  <label>Section Content:</label>
                  <div class="w-50">
                    <ckeditor
                      :editor="editor"
                      v-model="section.sectionContent"
                      tag-name="textarea"
                      :config="editorConfig"
                    ></ckeditor>
                  </div>
                </div>

                <div class="m-t-25">
                  <label name="pictureInput3">Section Image:</label>
                  <picture-input
                    ref="pictureInputSection"
                    @change="projectPictureInputOnSelect(counter)"
                    @remove="projectPictureInputOnRemoved(counter)"
                    :width="185"
                    :removable="true"
                    removeButtonClass="ui red button"
                    :height="127"
                    accept="image/jpeg, image/png, image/gif"
                    buttonClass="ui button primary"
                    :customStrings="{
  upload: '<h1>Upload Section Image!</h1>',
  drag: 'Drag and drop Section Image here'}"
                  ></picture-input>
                </div>
              </div>
            </div>
            <hr />
            <div>
              <label for="scopeofwork">Attributes</label>
              <div class="row">
                <div class="col-md-4">
                  <label for="work">
                    <b>Work:</b>
                  </label>
                  <div v-for="work in works" v-bind:key="work.key">
                    <input type="checkbox" v-model="project.works" :id="work.key" :value="work.key" />
                    {{work.value}}
                  </div>
                </div>
                <div class="col-md-4">
                  <label for="type">
                    <b>Type:</b>
                  </label>
                  <div v-for="type in types" v-bind:key="type.key">
                    <input type="checkbox" v-model="project.types" :id="type.key" :value="type.key" />
                    {{type.value}}
                  </div>
                </div>
                <div class="col-md-4">
                  <label for="type">
                    <b>Industry:</b>
                  </label>
                  <div v-for="industry in industries" v-bind:key="industry.key">
                    <input
                      type="checkbox"
                      v-model="project.industrys"
                      :id="industry.key"
                      :value="industry.key"
                    />
                    {{industry.value}}
                  </div>
                </div>
              </div>
            </div>
            <hr />
            <div class="row float-right">
              <button class="btn btn-primary m-r-5 colr">CANCEL</button>
              <button class="btn btn-primary m-r-5 colr" @click="previewProjectInfo">PREVIEW</button>
              <button class="btn btn-primary colr">SAVE</button>
            </div>
          </div>
        </form>
      </section>
    </div>
  </div>
</template>

<script>
import Menu from "./Menu";
import ProjectInfoDataService from "../service/ProjectInfoDataService";
import ProjectDataService from "../service/ProjectDataService";

import AuthenticationService from "../service/AuthenticationService";
import Notification from "./notifications.vue";
import PictureInput from "vue-picture-input";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";

export default {
  name: "projectcreation",

  components: {
    Menu,
    notification: Notification,

    PictureInput
  },
  data() {
    return {
      projectThumbnailPictureInputFile: "",
      projectHeroPictureInputFile: "",
      sectionFiles: [],
      // fileMultiple: [],
      editor: ClassicEditor,

      editorConfig: {
        toolbar: ["bold", "italic", "bulletedList", "numberedList"]
      },

      //sectionfiles:[],
      project: {
        scopeofworks: [],
        sections: [{}],
        works: [],
        types: [],
        industrys: []
      },
      notifications: [],
      isAuthenticated: AuthenticationService.isUserLoggedIn(),
      industries: this.retrieveLookupData(),
      types: this.retrieveLookupData(),
      categorys: this.retrieveLookupData(),
      works: this.retrieveLookupData(),
      industries: this.retrieveLookupData(),
      scopeOfWorks: this.retrieveLookupData()
    };
  },

  methods: {
    projectPictureInputOnSelect(counter) {
      this.projectSectionPictureInputFile = this.$refs.pictureInputSection[
        counter
      ].file;

      this.sectionFiles.splice(counter, 0, this.projectSectionPictureInputFile);
      //this.sectionfiles.push({sectionIndex: projectSectionPictureInputFile});
      //  this.sectionfiles.push(sectionIndex, this.projectSectionPictureInputFile);
    },
    projectPictureInputOnRemoved(counter) {
      // this.sectionfiles.splice(sectionIndex, 1)
    },
    projectThumbnailPictureInputOnSelect() {
      const projectThumbnailPictureInputFile = this.$refs.pictureInput1.file;

      this.projectThumbnailPictureInputFile = projectThumbnailPictureInputFile;
    },
    projectThumbnailPictureInputOnRemoved() {
      this.projectThumbnailPictureInputFile = "";
    },
    projectHeroPictureInputOnSelect() {
      const projectHeroPictureInputFile = this.$refs.pictureInput2.file;

      this.projectHeroPictureInputFile = projectHeroPictureInputFile;
    },
    projectHeroPictureInputOnRemoved() {
      this.projectHeroPictureInputFile = "";
    },

    retrieveLookupData() {
      ProjectInfoDataService.getLookupData()
        .then(response => {
          this.industries = response.data.industryLookupData;
          this.categorys = response.data.categoryLookupData;
          this.works = response.data.workLookupData;
          this.types = response.data.typeLookupData;
          this.scopeOfWorks = response.data.scopeOfWorkLookupData;
        })
        .catch(e => {
          console.log(e);
        });
    },

    addProjectSection() {
      this.project.sections.push({
        sectionLayout: "",
        sectionTitle: "",
        sectionContent: ""
        //sectionimage:''
      });
    },
    deleteProjectSection(counter) {
      this.project.sections.splice(counter, 1);
    },

    addProjectInfo: function() {
      ProjectInfoDataService.create(
        this.projectThumbnailPictureInputFile,
        this.projectHeroPictureInputFile,
        this.sectionFiles,
        this.project
      )
        .then(
          response => {
            this.notifications.push({
              type: "success",
              message: "Project  created successfully"
            });
          },
          response => {
            this.notifications.push({
              type: "error",
              message: "Project  not created"
            });
          }
        )
        .catch(e => {
          console.log(e);
        });
    },
 previewProjectInfo: function() {
      ProjectInfoDataService.preview(
        this.projectThumbnailPictureInputFile,
        this.projectHeroPictureInputFile,
        this.sectionFiles,
        this.project
      )
        .then(
          response => {
            console.log(response.data);
            this.$router.push({
        path: "/description",
        query: { preview: "true", description: response.data }
      });
          }
          
        )
        .catch(e => {
          console.log(e);
        });
    }





  }
};
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body,
html {
  height: 100%;
}
main {
  margin-top: 30px;
  height: 100%;
}

#projectcreation {
  margin: 20px auto;
  max-width: 1000px;
}

.inner2 {
  background: white;
  background: -webkit-gradient(
    linear,
    left bottom,
    left top,
    color-stop(84%, white),
    color-stop(84%, #7028b7)
  );
  background: linear-gradient(0deg, white 84%, #7028b7 84%);
  height: 25%;
  width: 100%;
}

.main-box {
  background: white;
  border-radius: 20px;
  padding-right: 35px;
  padding-bottom: 42px;
  padding-left: 35px;
  -webkit-transition: 300ms all cubic-bezier(0.4, 0, 0.2, 1);
  transition: 300ms all cubic-bezier(0.4, 0, 0.2, 1);
  margin-top: 24px;
  -webkit-box-shadow: 0 8px 60px 0 rgba(103, 151, 255, 0.11),
    0 12px 90px 0 rgba(103, 151, 255, 0.11);
  box-shadow: 0 8px 60px 0 rgba(103, 151, 255, 0.11),
    0 12px 90px 0 rgba(103, 151, 255, 0.11);
}

.m-t-25 {
  margin-top: 25px;
}

.p-10 {
  padding: 10px;
  margin-bottom: 10px;
}

.m-6 {
  margin-top: -6px;
}

.seclass {
  border: 1px solid lightgray;
  display: inline-flex;
  border-radius: 10px;
}

.m-r-10 {
  margin-right: 10px;
}

.m-r-5 {
  margin-right: 5px;
}

.d-i-10 {
  display: inline;
  margin-right: 10px;
}

.w-50 {
  width: 50%;
}

.d-flex {
  display: flex;
}

div#picture-input {
  display: flex;
}

.col-white{
  color: white!important;
}

.colr {
  background-color: rgb(113, 42, 184)!important;
  border-color:  rgb(113, 42, 184)!important ;
  color: white;
}

.clr-link{
    color: rgb(113, 42, 184)!important;
}

.colr:hover {
  background-color: #8654b8!important;
}
</style>
