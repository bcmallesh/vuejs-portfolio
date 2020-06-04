<template>
<section>
<Menu/>
    <div id="delete-project">
        <h1>Delete Project {{ project.name }}</h1>

        <p><router-link v-if="isAuthenticated" class="btn btn-primary" to="/projects">Back to Projects</router-link>

        <notification v-bind:notifications="notifications"></notification>

        <form v-on:submit.prevent="deleteProject">
            <p><button class="btn btn-danger">Delete Project</button></p>
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
         name: "delete-project",
  components: {
    Menu,
    'notification' : Notification
  },
        data(){
            return{
                project:{},
                notifications:[],
            isAuthenticated: AuthenticationService.isUserLoggedIn()

            }
        },

        created: function(){
            this.getProject();
        },

       methods: {

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
           

           
deleteProject: function()
            {
      ProjectDataService.delete(this.project.id)
        .then(response => {
         this.notifications.push({
                        type: 'success',
                        message: 'Project deleted successfully'
                    });
        }, (response) => {
                    this.notifications.push({
                        type: 'error',
                        message: 'Project not deleted'
                    });
                })
        .catch(e => {
          console.log(e);
        });
    },

            
        },

        
    }
</script>

