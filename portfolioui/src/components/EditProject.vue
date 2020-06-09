<template>
<section>
<Menu/>
    <div id="update-project">
        <h1>Update Project</h1>

        <p><router-link v-if="isAuthenticated" class="btn btn-primary" to="/projects">Back to Projects</router-link>

        <notification v-bind:notifications="notifications"></notification>

        <form v-on:submit.prevent="editProject">
            <div class="form-group">
                <label name="project_id">ID</label>
                <input type="number" class="form-control" disabled v-model="project.id" id="project_id">
            </div>

            <div class="form-group">
                <label name="project_name">Name</label>
                <input type="text" class="form-control" v-model="project.name" id="project_name" required>
            </div>
 <div class="form-group">
                <label name="project_industry">Industry</label>
                
            <select  v-model="project.industry" id="industryList" class="form-control" required>
        <option v-for="(industry, index) in industries" :key="index" v-bind:value="industry.key">
            {{industry.value}}
        </option>
    </select>  
            </div>
           

            <div class="form-group">
                <button class="btn btn-primary">Update</button>
            </div>
        </form>
    </div>
    </section>
</template>

<script>
import Menu from "./Menu";
import ProjectDataService from "../service/ProjectDataService";
import AuthenticationService from "../service/AuthenticationService";
import Notification from './notifications.vue';

    export default{
         name: "edit-project",
  components: {
    Menu,
    'notification' : Notification
  },
        data(){
            return{
                project:{},
                notifications:[],
                isAuthenticated: AuthenticationService.isUserLoggedIn(),
                
               industries: this.retrieveIndustries()

            }
        },

        created: function(){
            this.getProject();
        },

        methods: {
            retrieveIndustries() {
      ProjectDataService.getLookupData()
        .then(response => {
          this.industries = response.data.industryLookupData;
          console.log("industry===>"+this.industries);
        })
        .catch(e => {
          console.log(e);
        });
    },

getProject: function()
            {
      ProjectDataService.get(this.$route.params.id)
        .then(response => {
          this.project = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
           

           
editProject: function()
            {
      ProjectDataService.update(this.project.id, this.project)
        .then(response => {
         this.notifications.push({
                        type: 'success',
                        message: 'Project updated successfully'
                    });
        }, (response) => {
                    this.notifications.push({
                        type: 'error',
                        message: 'Project not updated'
                    });
                })
        .catch(e => {
          console.log(e);
        });
    },

            
        },

        
    }
</script>
