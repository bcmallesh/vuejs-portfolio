<template>

<section>
<Menu/>
    <div id="create-project">
        <h1>Create Project</h1>

        <p><router-link v-if="isAuthenticated" class="btn btn-primary" to="/projects">Back to Projects</router-link>

        <notification v-bind:notifications="notifications"></notification>

        <form v-on:submit.prevent="addProject">
            

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
                <button class="btn btn-primary">Create</button>
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
         name: "create-project",
         
  components: {
    Menu,
    'notification' : Notification,

  },
        data(){
            return{
                project:{},
                notifications:[],
                isAuthenticated: AuthenticationService.isUserLoggedIn(),
                industries: this.retrieveIndustries(),
            }
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
            addProject: function()
            {
                var name = this.project.name;
                if(null==name||''==name||undefined==name)
                {
                    this.notifications.push({
                        type: 'danger',
                        message: 'project name required'
                    });
                    return false;
                } else {
                    this.project.name = this.project.name;
                }

      ProjectDataService.create(this.project)
        .then(response => {
         this.notifications.push({
                        type: 'success',
                        message: 'Project created successfully'
                    });
        }, (response) => {
                    this.notifications.push({
                        type: 'error',
                        message: 'Project not created'
                    });
                })
        .catch(e => {
          console.log(e);
        });

    },
            
        }

       
    }
</script>
