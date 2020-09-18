<template>
  <div class="background_dig">
    <header id="header">
      <div class="container">
        <Menu />
        <div class="padding1x"></div>
      </div>
    </header>
    <div class="container">
      <section class="main-box">
        <h3 class="p-2">Projects List</h3>
        <div class="row form-group">
          <div class="col-md-8">
            <input
              type="text"
              name="search"
              v-model="projectInfoSearch"
              placeholder="Search project"
              class="form-control"
              v-on:keyup="searchProjects"
            />
          </div>
          <div class="col-md-4">
            <router-link
              v-if="isAuthenticated"
              class="btn btn-primary text-right"
              to="/CreateProject"
            >Create Project</router-link>
          </div>
        </div>

        <table class="table table-hover table-responsive">
          <thead class="thead-light">
            <tr>
              <th>Id</th>
              <th>Project Name</th>
              <th>Description</th>
              <th>Image</th>
              <th colspan="2">Actions</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="(projectinfo, index) in projectsinfo" :key="index">
              <td>{{ projectinfo.id }}</td>
              <td>{{ projectinfo.name }}</td>

              <td>{{ projectinfo.description }}</td>

              <td>
                <img
                  :src="projectinfo.imagePath"
                  alt="CxCalibration"
                  class="img-responsive"
                  width="40px"
                  height="40px"
                />
              </td>

              <td>
                <router-link
                  :to="{name: 'EditProject', params: { id: projectinfo.id }}"
                  class="btn btn-link mr-2"
                ><i class="far fa-edit"></i></router-link></td>
                <!-- <router-link :to="{name: 'DeleteProject', params: { id: projectinfo.id }}" class="btn btn-danger">Delete</router-link> -->
                <td>
                <button
                  type="button"
                  class="btn btn-link red-danger" 
                  @click="deleteThisProject(projectinfo.id)"
                ><i class="fa fa-trash"></i></button>
              </td>
            </tr>
          </tbody>
        </table>
      </section>
    </div>
     <footer class="foot">
      <div class="container">
        <div class="copyright text-center">
          <p>© 2020 EVRY India</p>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>
import Menu from "./Menu";
import ProjectInfoDataService from "../service/ProjectInfoDataService";
import AuthenticationService from "../service/AuthenticationService";
import ProjectDataService from "../service/ProjectDataService";
export default {
  name: "projectsinfo-list",
  components: {
    Menu,
  },
  data() {
    return {
      projectsinfo: [],
      currentProjectInfo: null,
      currentIndex: -1,
      name: "",
      originalProjectsInfo: [],
      projectInfoSearch: "",
      isAuthenticated: AuthenticationService.isUserLoggedIn(),
    };
  },
  methods: {
    deleteThisProject(projectId) {
      if (confirm("Are you sre you want to delete this project")) {
        ProjectDataService.delete(projectId)
          .then(
            (response) => {
              alert("project deleted successfully");
              location.reload();
            },
            (response) => {
              alert("Unable to delete the project");
            }
          )
          .catch((e) => {
            console.log(e);
          });
      }
    },
    retrieveProjectsInfo() {
      ProjectInfoDataService.getAll()
        .then((response) => {
          this.projectsinfo = response.data;
          this.originalProjectsInfo = response.data;
          console.log(response.data);
        })
        .catch((e) => {
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
        .then((response) => {
          console.log(response.data);
          this.refreshList();
        })
        .catch((e) => {
          console.log(e);
        });
    },

    searchName() {
      ProjectInfoDataService.findByName(this.name)
        .then((response) => {
          this.projectsinfo = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    searchProjects() {
      
      if (this.projectInfoSearch == '') {
        this.projectsinfo = this.originalProjectsInfo;
        return;
      }

      var searchedProjects = [];
      for (var i = 0; i < this.projectsinfo.length; i++) {
        var productName =
          null != this.projectsinfo[i]["name"]
            ? this.projectsinfo[i]["name"].toLowerCase()
            : "";
        if (productName.indexOf(this.projectInfoSearch.toLowerCase()) >= 0) {
          searchedProjects.push(this.projectsinfo[i]);
        }
      }

      this.projectsinfo = searchedProjects;
    },
  },
  mounted() {
    this.retrieveProjectsInfo();
  },
};
</script>
