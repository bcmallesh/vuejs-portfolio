<template>
<section>
<Menu/>
  <div id="projectinfo">
    <h1>Project Creation</h1>
    <form v-on:submit.prevent="addProjectInfo" enctype="multipart/form-data">

      <label for="projectname">Project Name:</label>
      <input type="text" v-model="projectinfo.projectname" id="projectname" required>
      <label for="projectnameformat">Project Name Format:</label>
       <select  v-model="projectinfo.strongformat" id="strongformat"  required>
        <option v-for="(strongformat, index) in strongformats" :key="index" v-bind:value="strongformat.key">
            {{strongformat.value}}
        </option>
    </select>  
          <label for="projectnamefontsize">Project Name Font Size:</label>

          <input type="number" v-model="projectinfo.projectnamefontsize" id="projectnamefontsize" required>

      <br>
      <label for="projectdescription">Project Description:</label>
      <textarea  type="text" v-model="projectinfo.projectdescription" id="projectdescription" required></textarea >
   
    <label for="projectdescriptionformattype">Project Description Format:</label>
       <select  v-model="projectinfo.work" id="projectdescriptionformattype"  required>
        <option v-for="(work, index) in works" :key="index" v-bind:value="work.key">
            {{work.value}}
        </option>
    </select>

    
               
           

              
                <label name="project_industry">Industry</label>
                
            <select  v-model="projectinfo.industry" id="industryList"  required>
        <option v-for="(industry, index) in industries" :key="index" v-bind:value="industry.key">
            {{industry.value}}
        </option>
    </select>  
            

                <label name="project_type">Type</label>
                
            <select  v-model="projectinfo.type" id="typeList"  required>
        <option v-for="(type, index) in types" :key="index" v-bind:value="type.key">
            {{type.value}}
        </option>
    </select>  
    <br>
    <label name="project_description">Project Upload</label>
           

    <div  @dragleave="fileDragOut" @dragover="fileDragIn" @drop="handleFileDrop"  >
      <br>
      <div class="file-wrapper">
        <input type="file" name="file" multiple="True"  @change="handleProjectFileInput($event)" > Click to insert Project Files.
      </div>
      <ul>
        <li v-for="(file, index) in projectfiles">
          {{ file.name }} ({{ file.size }} b)
          <button @click="removeProjectFile(index)" title="Remove">X</button>
        </li>
      </ul>
    </div>
 
  
 
     <br><br>
      <button @click="addProjectSection">Add Project Section</button>
      <br>
      <div class="section"
      v-for="(section, counter) in projectinfo.sections"
      v-bind:key="counter">
        <span @click="deleteProjectSection(counter)">x</span>
        <label for="sectionname">{{counter+1}}. Section Name:</label>
        <input type="text" v-model.lazy="section.sectionname" required>
        <label for="sectiontext">Section Text:</label>
        <textarea type="textarea" v-model.lazy="section.sectiontext" required> </textarea>
         <br>


    <div  @dragleave="fileDragOut" @dragover="fileDragIn" @drop="handleFileDrop"  >
      <br>
      <div class="file-wrapper">
        <input type="file" name="file" multiple="True"  @change="handleFileInput($event,counter)" > Click to insert section Files.
      </div>
      <ul>
        <li v-for="(file, index) in section.files">
          {{ file.name }} ({{ file.size }} b)
          <button @click="removeFile(index,counter)" title="Remove">X</button>
        </li>
      </ul>
    </div>
 

      </div>
                <button class="btn btn-primary">Create</button>
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
    return {
        delimiters: ['${', '}'],
        sectionfiles: {sectionindex:"", sectionindex:""},
         files: [],
         projectfiles:[],
       
        color: '#444444',
          projectinfo:{
    sections:[
       {
      sectionname: '',
      sectiontext:'',
      secionid:'',
       files: [],
       }
     ]
          },
                notifications:[],
                isAuthenticated: AuthenticationService.isUserLoggedIn(),

                industries: this.retrieveLookupData(),
                types: this.retrieveLookupData(),
                works: this.retrieveLookupData(),
                strongformats: this.retrieveFormatLookupData(),
                industries: this.retrieveLookupData(),
                file:"",
    sectionfile:"",
    }
    
    
  },
  methods : {
   handleFileDrop(e) {
      let droppedFiles = e.dataTransfer.files;
      if(!droppedFiles) return;
      // this tip, convert FileList to array, credit: https://www.smashingmagazine.com/2018/01/drag-drop-file-uploader-vanilla-js/
      ([...droppedFiles]).forEach(f => {
    
    this.files.push(f);
      });

      this.color="#444444"
    },
  handleProjectFileInput(e) {
      let files = e.target.files;
      files = e.target.files
            if(!files) return;
      // this tip, convert FileList to array, credit: https://www.smashingmagazine.com/2018/01/drag-drop-file-uploader-vanilla-js/
      ([...files]).forEach(f => {
    
    this.projectfiles.push(f);
      });

            //this.projectinfo.sections[sectionIndex].files=e.target.files;

    },
    removeProjectFile(fileKey){
      this.projectfiles.splice(fileKey, 1)
    },
    

    handleFileInput(e,sectionIndex) {
      let files = e.target.files;
      files = e.target.files
            if(!files) return;
      // this tip, convert FileList to array, credit: https://www.smashingmagazine.com/2018/01/drag-drop-file-uploader-vanilla-js/
      ([...files]).forEach(f => {
    
    this.files.push(f);
      });

            this.projectinfo.sections[sectionIndex].files=e.target.files;

    },
    removeFile(fileKey,sectionIndex){
      let files =this.projectinfo.sections[sectionIndex].files;
      files.splice(fileKey, 1)
      this.projectinfo.sections[sectionIndex].files=files;
    },

    fileDragIn(){
      // alert("oof")
      // alert("color")
      this.color="white"
    },
    fileDragOut(){
      this.color="#444444"
    },
  retrieveLookupData() {
      ProjectInfoDataService.getLookupData()
        .then(response => {
          this.industries = response.data.industryLookupData;
          this.works = response.data.workLookupData;
          this.types = response.data.typeLookupData;

         // console.log("industry===>"+this.industries);
        })
        .catch(e => {
          console.log(e);
        });
    },
    retrieveFormatLookupData() {
      ProjectInfoDataService.getFormatLookupData()
        .then(response => {
          this.strongformats = response.data.strongformat;
          //this.works = response.data.workLookupData;
          //this.types = response.data.typeLookupData;

          //console.log("industry===>"+this.industries);
        })
        .catch(e => {
          console.log(e);
        });
    },
    addProjectSection(){
      this.projectinfo.sections.push({
        sectionname:'',
        sectiontext: '',
        sectionimage:''

      })
    },
    deleteProjectSection(counter){
      this.projectinfo.sections.splice(counter,1);
    },

addProjectInfo: function()
            {
                let formData = new FormData();

            /*
                Add the form data we need to submit
            */
            formData.append('files', this.files);
            formData.append('projectinfo', this.projectinfo);

                

      ProjectInfoDataService.create(this.files,this.projectinfo)
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
#projectinfo {
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
</style>