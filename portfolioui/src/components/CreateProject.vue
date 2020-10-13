<template>
  <div class="inner2">
    <header id="header">
      <div class="container">
        <Menu></Menu>
      </div>
    </header>
    <div id="projectcreation">
      <h1 class="col-white">New Project</h1>
      <notification v-bind:notifications="notifications"></notification>
      <section class="main-box mt-4">
        <form v-on:submit.prevent="addProjectInfo" enctype="multipart/form-data">
          <div class="row">
            <div class="col-md-6">
              <div class="m-t-25">
                <label name="project_Category">Category</label>
                <select class="form-control" v-model="project.category" id="categoryList" required>
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
              <div class="m-t-25 mb-3">
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
              <div class="form-group-checkbox">
                <input
                  type="checkbox"
                  v-model="project.scopeofworks"
                  :id="scopeOfWork.key"
                  :value="scopeOfWork.value"
                />
                <label :for="scopeOfWork.key" >{{scopeOfWork.value}}</label>
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
                class="btn btn-link m-6"
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
                  <div class="form-group-checkbox">
                    <input type="checkbox" v-model="project.works" :id="work.key" :value="work.key" />
                    <label :for="work.key">{{work.value}}</label>
                  </div>
                </div>
              </div>
              <div class="col-md-4">
                <label for="type">
                  <b>Type:</b>
                </label>
                <div v-for="type in types" v-bind:key="type.key">
                  <div class="form-group-checkbox">
                    <input type="checkbox" v-model="project.types" :id="type.key" :value="type.key" />
                    <label :for="type.key" >{{type.value}}</label>
                  </div>
                </div>
              </div>
              <div class="col-md-4">
                <label for="type">
                  <b>Industry:</b>
                </label>
                <div v-for="industry in industries" v-bind:key="industry.key">
                  <div class="form-group-checkbox">
                    <input
                      type="checkbox"
                      v-model="project.industrys"
                      :id="industry.key"
                      :value="industry.key"
                    />
                    <label :for="industry.key" >{{industry.value}}</label>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <hr />
          <div class="row float-right">
            <button class="btn btn-primary m-r-5 colr">CANCEL</button>
            <!-- <button class="btn btn-primary m-r-5 colr" @click="previewProjectInfo">PREVIEW</button> -->
             <router-link  target="_blank"  :to="{path:'/description',query:{preview: true}} " @click.native="previewProjectInfo">PREVIEW</router-link>
            <button class="btn btn-primary colr">SAVE</button>
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
    PictureInput,
  },
  data() {
    return {
      previewData:{},
      projectThumbnailPictureInputFile: "",
      projectHeroPictureInputFile: "",
      sectionFiles: [],
      // fileMultiple: [],
      editor: ClassicEditor,
      formData : new FormData(),
      editorConfig: {
        toolbar: ["bold", "italic", "bulletedList", "numberedList"],
      },

      //sectionfiles:[],
      project: {
        scopeofworks: [],
        sections: [{}],
        works: [],
        types: [],
        industrys: [],
      },
      notifications: [],
      isAuthenticated: AuthenticationService.isUserLoggedIn(),
      industries: this.retrieveLookupData(),
      types: this.retrieveLookupData(),
      categorys: this.retrieveLookupData(),
      works: this.retrieveLookupData(),
      industries: this.retrieveLookupData(),
      scopeOfWorks: this.retrieveLookupData(),
    };
  },

  methods: {
    projectPictureInputOnSelect(counter) {
      var selectedFile = this.$refs.pictureInputSection[counter].file;
      this.sectionFiles[counter]=selectedFile;
      this.project.sections[counter].sectionimageName=selectedFile.name;
      this.project.sections[counter].sectionImageIndex=counter;
      this.project.sections[counter].sectionImagePath=selectedFile.path;
     // this.project.sections[counter].multipartfile=selectedFile;
    },
    projectPictureInputOnRemoved(counter) {
       this.sectionFiles[counter]=null;
       this.project.sections[counter].sectionimageName=null;
       this.project.sections[counter].sectionImageIndex=null;
       this.project.sections[counter].sectionImagePath=null;
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
        .then((response) => {
          this.industries = response.data.industryLookupData;
          this.categorys = response.data.categoryLookupData;
          this.works = response.data.workLookupData;
          this.types = response.data.typeLookupData;
          this.scopeOfWorks = response.data.scopeOfWorkLookupData;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    addProjectSection() {
      this.project.sections.push({
        sectionLayout: "",
        sectionTitle: "",
        sectionContent: "",
        sectionimageName:null,
        sectionImageIndex:null,
        sectionImagePath:null
        //sectionimage:''
      });
    },
    deleteProjectSection(counter) {
      this.project.sections.splice(counter, 1);
      this.sectionFiles.splice(counter, 1);
    },

    addProjectInfo: function () {
      ProjectInfoDataService.create(
        this.projectThumbnailPictureInputFile,
        this.projectHeroPictureInputFile,
        this.sectionFiles,
        this.project
      )
        .then(
          (response) => {
            this.notifications.push({
              type: "success",
              message: "Project  created successfully",
            });
            alert("Project created successfully");
            this.$router.push("/projects");
          },
          (response) => {
            this.notifications.push({
              type: "error",
              message: "Project  not created",
            });
          }
        )
        .catch((e) => {
          console.log(e);
        });
    },
    previewProjectInfo: function () {
      ProjectInfoDataService.preview(
        this.projectThumbnailPictureInputFile,
        this.projectHeroPictureInputFile,
        this.sectionFiles,
        this.project
      )
        .then((response) => {
          this.previewData = response.data; 
          console.log(' this.previewData : '+JSON.stringify( this.previewData)); 
          localStorage.setItem('previewData',JSON.stringify(this.previewData))
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

