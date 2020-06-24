<template>

<section>
<Menu/>
      <div id="app">
        <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
        <hr>
        <div>{{editorData}}</div>
    </div>
          </section>
</template>

<script>
    import Menu from "./Menu";
import ProjectInfoDataService from "../service/ProjectInfoDataService";
import ProjectDataService from "../service/ProjectDataService";
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'

import AuthenticationService from "../service/AuthenticationService";
import Notification from './notifications.vue';
import PictureInput from 'vue-picture-input'
    export default{
        name: 'app',
         
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
                editor: ClassicEditor,
      editorData: '<p>Content of the editor.</p>',
      editorConfig: {
        toolbar: ['bold', 'italic', 'bulletedList', 'numberedList']
      }
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
