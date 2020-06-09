

  

<template>
<section>
<Menu/>
   <div id="all-projects">
        <h1>All Projects</h1>


        <p>        <router-link v-if="isAuthenticated" class="btn btn-primary" to="/createproject">Create Project</router-link>
</p>
        <div class="form-group">
            <input type="text" name="search" v-model="projectSearch" placeholder="Search projects" class="form-control" v-on:keyup="searchProjects">
        </div>
        

        
   
    <!--<div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by name"
          v-model="name"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchName"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    -->

     <table >
            <thead>
            <tr>
                <td>ID</td>
                <td>Project Name</td>
                
                <td>Actions</td>
            </tr>
            </thead>

            <tbody>
                <tr v-for="(project, index) in projects" :key="index">
                    <td>{{ project.id }}</td>
                    <td>{{ project.name }}</td>
                    <td>{{ project.industry }}</td>

                    <td>
                        <router-link :to="{name: 'EditProject', params: { id: project.id }}" class="btn btn-primary">Edit</router-link>
                        <router-link :to="{name: 'DeleteProject', params: { id: project.id }}" class="btn btn-danger">Delete</router-link>
                    </td>
                </tr>
            </tbody>
        </table>
    <!--<div class="col-md-6">
      <h4>Project List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(project, index) in projects"
          :key="index"
          @click="setActiveProject(project, index)"
        >
          {{ project.name }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllProjects">
        Remove All
      </button>
    </div>
-->

<!--
    <div class="col-md-6">
      <div v-if="currentProject">
        <h4>Project</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentProject.name }}
        </div>
        

        <a class="badge badge-warning"
          :href="'/project/' + currentProject.id"
        >
          Edit
        </a>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Project...</p>
      </div>
    </div>

    -->
  </div>
  </section>
</template>

<script>
import Menu from "./Menu";
import ProjectDataService from "../service/ProjectDataService";
import AuthenticationService from "../service/AuthenticationService";

export default {
  name: "projects-list",
  components: {
    Menu
  },
  data() {
    return {
      projects: [],
      currentProject: null,
      currentIndex: -1,
      name: "",
      originalProjects: [],
      projectSearch: '',
      isAuthenticated: AuthenticationService.isUserLoggedIn()
    };
  },
  methods: {
    retrieveProjects() {
      ProjectDataService.getAll()
        .then(response => {
          this.projects = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveProjects();
      this.currentProject = null;
      this.currentIndex = -1;
    },

    setActiveProject(project, index) {
      this.currentProject = project;
      this.currentIndex = index;
    },

    removeAllProjects() {
      ProjectDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchName() {
      ProjectDataService.findByName(this.name)
        .then(response => {
          this.projects = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchProjects()
            {
                if(this.projectSearch == '')
                {
                    this.projects = this.originalProjects;
                    return;
                }

                var searchedProjects = [];
                for(var i = 0; i <  this.projects.length; i++)
                {
                    var productName = null!=this.projects[i]['name']?this.projects[i]['name'].toLowerCase():'';
                    if(productName.indexOf(this.projectSearch.toLowerCase()) >= 0)
                    {
                        searchedProjects.push(this.projects[i]);
                    }
                }

                this.projects = searchedProjects;
            }
  },
  mounted() {
    this.retrieveProjects();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>