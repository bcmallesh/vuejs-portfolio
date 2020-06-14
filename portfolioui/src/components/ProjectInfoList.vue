

  

<template>
<section>
<Menu/>
   <div id="all-projects">
        <h1>Project Info List</h1>


        <p>        <router-link v-if="isAuthenticated" class="btn btn-primary" to="/CreateProjectWithSections">Create Project Info</router-link>
</p>
        <div class="form-group">
            <input type="text" name="search" v-model="projectInfoSearch" placeholder="Search project info" class="form-control" v-on:keyup="searchProjects">
        </div>
   

     <table >
            <thead>
            <tr>
                <td>ID</td>
                <td>Project Name</td>
                <td>Project Industry</td>
                 <td>Image Description</td>
                 <td>Image</td>

                <td>Actions</td>
            </tr>
            </thead>

            <tbody>
                <tr v-for="(projectinfo, index) in projectsinfo" :key="index">
                    <td>{{ projectinfo.id }}</td>
                    <td>{{ projectinfo.name }}</td>
                    <td>{{ projectinfo.industry }}</td>
                    <td>{{ projectinfo.description }}</td>

                    
                    <td><img :src="projectinfo.imagePath" alt="CxCalibration" class="img-responsive" width="24px" height="24px"/></td>

                    <td>
                        <router-link :to="{name: 'EditProject', params: { id: projectinfo.id }}" class="btn btn-primary">Edit</router-link>
                        <router-link :to="{name: 'DeleteProject', params: { id: projectinfo.id }}" class="btn btn-danger">Delete</router-link>
                    </td>
                </tr>
            </tbody>
        </table>
   

  </div>
  </section>
</template>

<script>
import Menu from "./Menu";
import ProjectInfoDataService from "../service/ProjectInfoDataService";
import AuthenticationService from "../service/AuthenticationService";

export default {
  name: "projectsinfo-list",
  components: {
    Menu
  },
  data() {
    return {
      projectsinfo: [],
      currentProjectInfo: null,
      currentIndex: -1,
      name: "",
      originalProjectsInfo: [],
      projectInfoSearch: '',
      isAuthenticated: AuthenticationService.isUserLoggedIn()
    };
  },
  methods: {
    retrieveProjectsInfo() {
      ProjectInfoDataService.getAll()
        .then(response => {
          this.projectsinfo = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveProjectsInfo();
      this.currentProjectInfo = null;
      this.currentIndex = -1;
    },

    setActiveProject(project, index) {
      this.currentProjectInfo = project;
      this.currentIndex = index;
    },

    removeAllProjects() {
      ProjectInfoDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchName() {
      ProjectInfoDataService.findByName(this.name)
        .then(response => {
          this.projectsinfo = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchProjects()
            {
                if(this.projectInfoSearch == '')
                {
                    this.projectsinfo = this.originalProjectsInfo;
                    return;
                }

                var searchedProjects = [];
                for(var i = 0; i <  this.projectsinfo.length; i++)
                {
                    var productName = null!=this.projectsinfo[i]['name']?this.projectsinfo[i]['name'].toLowerCase():'';
                    if(productName.indexOf(this.projectInfoSearch.toLowerCase()) >= 0)
                    {
                        searchedProjects.push(this.projectsinfo[i]);
                    }
                }

                this.projectsinfo = searchedProjects;
            }
  },
  mounted() {
    this.retrieveProjectsInfo();
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