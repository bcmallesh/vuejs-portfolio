<template>

<section>
<Menu/>
      <div id="projectcreation">
             <h1>Project Creation</h1>
             <notification v-bind:notifications="notifications"></notification>
    <form v-on:submit.prevent="addProjectInfo" enctype="multipart/form-data">
<div class="project">
          <label name="project_Category">Category</label>
                
            <select  v-model="project.category" id="categoryList"  required>
        <option v-for="(category, index) in categorys" :key="index" v-bind:value="category.key">
            {{category.value}}
        </option>
    </select>  
    <br>
    <label name="project_Category">Project Name</label>
        
              <input type="text" v-model="project.projectName" id="projectName" required>
<div>
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
  drag: 'Drag and drop Thumbnail Image here'}">

</picture-input>

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
  drag: 'Drag and drop Hero Image here'}">

</picture-input>

            </div>
 <label for="projectshortsummary">Short Summary:</label>
      <textarea  type="text" v-model="project.shortSummary" id="projectshortSummary" required></textarea >
   
 <label for="scopeofwork">Scope of work:</label>
<div  v-for="scopeOfWork in scopeOfWorks" v-bind:key="scopeOfWork.key">
             
            <input type="checkbox"  v-model="project.scopeofworks" :id="scopeOfWork.key" :value="scopeOfWork.key">
    {{scopeOfWork.value}}
    
        </div>





     <br><br>
      <button @click="addProjectSection"><strong>Sections </strong> +Add Project Section</button>
      
      <br>
      <div class="section" 
      v-for="(section, counter) in project.sections"
      v-bind:key="counter">


        <label for="sectionname">Section {{counter+1}}:{{section.sectionTitle}}
        <button v-on:click=deleteProjectSection(counter)>x</button></label>

        <label for="sectiontext">Section layout:</label>
    <input type="radio" value="left" 
    v-model="section.sectionLayout">
    <img src="../assets/images/left.jpg" height="50px" width="50px"/>
    
    <input type="radio" value="right" v-model="section.sectionLayout">
    <img src="../assets/images/right.jpg" height="50px" width="50px"/>

 <label for="sectiontext">Section Title:</label>
        <input type="text" v-model.lazy="section.sectionTitle" required>

        <label for="sectiontext">Section Content:</label>
         <ckeditor :editor="editor" v-model="section.sectionContent" tag-name="textarea" :config="editorConfig"></ckeditor>
         <br>

    <div>
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
  drag: 'Drag and drop Section Image here'}">

</picture-input>

            </div>
   

   
      </div>

 
<label for="scopeofwork">Attributes</label>

<label for="work">Work:</label>
<div  v-for="work in works" v-bind:key="work.key">
             
            <input type="checkbox"  v-model="project.works" :id="work.key" :value="work.key">
    {{work.value}}
    
        </div>

        <label for="type">Type:</label>
<div  v-for="type in types" v-bind:key="type.key">
             
            <input type="checkbox"  v-model="project.types" :id="type.key" :value="type.key">
    {{type.value}}
    
        </div>

        <label for="type">Industry:</label>
<div  v-for="industry in industries" v-bind:key="industry.key">
             
            <input type="checkbox"  v-model="project.industrys" :id="industry.key" :value="industry.key">
    {{industry.value}}
    
        </div>

                <button class="btn btn-primary">CANCEL</button>
                 <button class="btn btn-primary">PREVIEW</button>
 <button class="btn btn-primary">SAVE</button>
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
import PictureInput from 'vue-picture-input';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'


    export default{
        name: 'projectcreation',
         
 components: {
    Menu,
    'notification' : Notification,
   
    PictureInput

  },
       data(){
    return {
      projectThumbnailPictureInputFile:"",
       projectHeroPictureInputFile:"",
       sectionFiles:[],
     // fileMultiple: [],
         editor: ClassicEditor,
      
      editorConfig: {
        toolbar: ['bold', 'italic', 'bulletedList', 'numberedList']
      },
        
     //sectionfiles:[],
          project:{
              scopeofworks:[],
              sections:[
              {
     
            }
            ],
            works:[],
            types:[],
            industrys:[]
            
          
          },
                notifications:[],
                isAuthenticated: AuthenticationService.isUserLoggedIn(),
                industries: this.retrieveLookupData(),
                types: this.retrieveLookupData(),
                categorys: this.retrieveLookupData(),
                works: this.retrieveLookupData(),
                industries: this.retrieveLookupData(),
                scopeOfWorks: this.retrieveLookupData(),

             
    
    }
    
    
  },

     methods : {
       projectPictureInputOnSelect(counter){


this.projectSectionPictureInputFile=this.$refs.pictureInputSection[counter].file;

this.sectionFiles.splice(counter, 0, this.projectSectionPictureInputFile );
//this.sectionfiles.push({sectionIndex: projectSectionPictureInputFile}); 
  //  this.sectionfiles.push(sectionIndex, this.projectSectionPictureInputFile); 
},
projectPictureInputOnRemoved(counter) {
   // this.sectionfiles.splice(sectionIndex, 1)
  },
 projectThumbnailPictureInputOnSelect(){
const projectThumbnailPictureInputFile=this.$refs.pictureInput1.file;

this.projectThumbnailPictureInputFile=projectThumbnailPictureInputFile;
},
projectThumbnailPictureInputOnRemoved() {
    this.projectThumbnailPictureInputFile = '';
  },
   projectHeroPictureInputOnSelect(){
const projectHeroPictureInputFile=this.$refs.pictureInput2.file;

this.projectHeroPictureInputFile=projectHeroPictureInputFile;
},
projectHeroPictureInputOnRemoved() {
    this.projectHeroPictureInputFile = '';
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
   
    addProjectSection(){
      this.project.sections.push({
        sectionLayout:'',
        sectionTitle: '',
        sectionContent:''
        //sectionimage:''

      })
    },
    deleteProjectSection(counter){
      this.project.sections.splice(counter,1);
    },

addProjectInfo: function()
            {
              
                      

                

      ProjectInfoDataService.create(this.projectThumbnailPictureInputFile,this.projectHeroPictureInputFile,this.sectionFiles,this.project)
        .then(response => {
         this.notifications.push({
                        type: 'success',
                        message: 'Project  created successfully'
                    });
        }, (response) => {
                    this.notifications.push({
                        type: 'error',
                        message: 'Project  not created'
                    });
                })
        .catch(e => {
          console.log(e);
        });

    },

    
  }
       
    }
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body,html{
  height: 100%;
}
main {
  margin-top: 30px;
  height: 100%;
}

#app {
  border: 1px solid yellow;
  height: 100vh;
}
.file-wrapper {
  text-align: center;
  width: 300px;
  height: 5em;
  vertical-align: middle;
  display: table-cell;
  position: relative;
  overflow: hidden;
  background: gray; /* and other things to make it pretty */
}

textarea {
    overflow: auto;
    resize: both;
    width: 100%;
}
.file-wrapper input {
    position: absolute;
    top: 0;
    right: 0; /* not left, because only the right part of the input seems to
                 be clickable in some browser I can't remember */
    cursor: pointer;
    opacity: 0.0;
    filter: alpha(opacity=0); /* and all the other old opacity stuff you
                                 want to support */
    font-size: 300px; /* wtf, but apparently the most reliable way to make
                         a large part of the input clickable in most browsers */
    height: 200px;
}
#projectcreation {
  margin: 20px auto;
  max-width: 700px;
}
label{
  display: block;
  margin: 20px 0 10px;
}
input {
  font-size: 100%;
  border: 1px double rgb(102, 97, 96) ;
  border-radius: 4px;
}
select {
  font-size:100%;
  border: 1px double rgb(102, 97, 96) ;
  border-radius: 4px;
}
option {
  font-size:100%;
  border: 1px double rgb(102, 97, 96) ;
  border-radius: 4px;
}
optios {
  font-size: 30px;
  border: 1px double rgb(102, 97, 96) ;
  border-radius: 4px;
}
button {
  font-size: 16px;
 background: rgb(64, 179, 140);
  padding: 0.4rem 1.3rem;
  text-align: center;
  border: none;
  cursor: pointer;
  border-radius: 4px;
 margin: 10px;
}
span{
  width: 30px;
  float: right;
  cursor: pointer;
}
span:hover{
  color: brown;
}
.section{
  border: 1.5px solid;
  padding:5px;
  margin-bottom: 10px;
}

.project{
  border: 2px solid;
  padding:5px;
  margin-bottom: 10px;
}
</style>
