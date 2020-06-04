<template>
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
                <input type="text" class="form-control" v-model="project.industry" id="project_industry" required>
            </div>
            

            <div class="form-group">
                <button class="btn btn-primary">Create</button>
            </div>
        </form>
    </div>
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
    'notification' : Notification
  },
        data(){
            return{
                project:{},
                notifications:[],
                industry:[],
                isAuthenticated: AuthenticationService.isUserLoggedIn()
            }
        },

        methods: {


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
