<template>

<section>
<Menu/>
    <div id="create-project">
        <h1>Create Project Info</h1>

        <p><router-link v-if="isAuthenticated" class="btn btn-primary" to="/projectsinfo">Back to Projects Info</router-link>

        <notification v-bind:notifications="notifications"></notification>

        <form v-on:submit.prevent="addProjectInfo" enctype="multipart/form-data">

            
             <div class="form-group">
                <label name="project_description">File Upload</label>
           
          <picture-input
  ref="pictureInput"
  @change="onSelect"
  @remove="onRemoved"
  :width="200"
  :removable="true"
  removeButtonClass="ui red button"
  :height="200"
  multiple
  accept="image/jpeg, image/png, image/gif"
  buttonClass="ui button primary"
  :customStrings="{
  upload: '<h1>Upload it!</h1>',
  drag: 'Drag and drop your image here'}">

</picture-input>

            </div>
 
             <div class="form-group">
                <label name="project_description">Image Description</label>
                <input type="text" class="form-control" v-model="projectinfo.description" id="project_description" required>
            </div>


              <div class="form-group">
                <label name="project_name">Project Name</label>
                
            <select  v-model="projectinfo.project" id="projectNameList" class="form-control" required>
        <option v-for="(project, index) in projects" :key="index" v-bind:value="project.id">
            {{project.name}}
        </option>
    </select>  
            </div>

              <div class="form-group">
                <label name="project_industry">Industry</label>
                
            <select  v-model="projectinfo.industry" id="industryList" class="form-control" required>
        <option v-for="(industry, index) in industries" :key="index" v-bind:value="industry.key">
            {{industry.value}}
        </option>
    </select>  
            </div>

             <div class="form-group">
                <label name="project_type">Type</label>
                
            <select  v-model="projectinfo.type" id="typeList" class="form-control" required>
        <option v-for="(type, index) in types" :key="index" v-bind:value="type.key">
            {{type.value}}
        </option>
    </select>  
            </div>

 <div class="form-group">
                <label name="project_work">Work Type</label>
                
            <select  v-model="projectinfo.work" id="workList" class="form-control" required>
        <option v-for="(work, index) in works" :key="index" v-bind:value="work.key">
            {{work.value}}
        </option>
    </select>  
            </div>
            <div class="form-group">
                <button class="btn btn-primary">Create</button>
            </div>
        </form>
    </div>
          </section>
</template>

<script>
    import Menu from "./Menu";
import ProjectInfoDataService from "../service/ProjectInfoDataService";
import ProjectDataService from "../service/ProjectDataService";

import AuthenticationService from "../service/AuthenticationService";
import Notification from './notifications.vue';
import PictureInput from 'vue-picture-input'
    export default{
         name: "create-project-info",
         
  components: {
    Menu,
    'notification' : Notification,
    PictureInput

  },
        data(){
            return{
                projectinfo:{},
                notifications:[],
                isAuthenticated: AuthenticationService.isUserLoggedIn(),

                industries: this.retrieveLookupData(),
                types: this.retrieveLookupData(),
                works: this.retrieveLookupData(),
                file:"",
                projects: this.retrieveProjects(),
            }
        },

        methods: {
onSelect(){
const file=this.$refs.pictureInput.file;

this.file=file;
},
onRemoved() {
    this.image = '';
  },
retrieveProjects() {
      ProjectDataService.getAll()
        .then(response => {
          this.projects = response.data;
          console.log("projects===>"+this.projects);
        })
        .catch(e => {
          console.log(e);
        });
    },

retrieveLookupData() {
      ProjectInfoDataService.getLookupData()
        .then(response => {
          this.industries = response.data.industryLookupData;
          this.works = response.data.workLookupData;
          this.types = response.data.typeLookupData;

          console.log("industry===>"+this.industries);
        })
        .catch(e => {
          console.log(e);
        });
    },
            addProjectInfo: function()
            {
                let formData = new FormData();

            /*
                Add the form data we need to submit
            */
            formData.append('file', this.file);
            formData.append('projectinfo', this.projectinfo);

                

      ProjectInfoDataService.create(this.file, this.projectinfo)
        .then(response => {
         this.notifications.push({
                        type: 'success',
                        message: 'Project Info created successfully'
                    });
        }, (response) => {
                    this.notifications.push({
                        type: 'error',
                        message: 'Project Info not created'
                    });
                })
        .catch(e => {
          console.log(e);
        });

    },
            
        }

       
    }
</script>
