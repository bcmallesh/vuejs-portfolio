<template>
  <div class="inner2">
    <header id="header">
      <div class="container">
        <Menu />
        <div class="padding1x"></div>
      </div>
    </header>
    <div id="projectcreation">
      <h1 class="col-white">Projects List</h1>
      <p>
        <router-link
          v-if="isAuthenticated"
          class="btn btn-primary"
          to="/CreateProject"
        >Create Project</router-link>
      </p>
      <div class="form-group">
        <input
          type="text"
          name="search"
          v-model="projectInfoSearch"
          placeholder="Search project"
          class="form-control"
          v-on:keyup="searchProjects"
        />
      </div>

      <table class="table table-striped">
        <thead>
          <tr>
            <td>Id</td>
            <td>Project Name</td>
            <td>Description</td>
            <td>Image</td>
            <td>Actions</td>
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
                width="24px"
                height="24px"
              />
            </td>

            <td>
              <router-link
                :to="{name: 'EditProject', params: { id: projectinfo.id }}"
                class="btn btn-primary"
              >Edit</router-link>
                <!-- <router-link :to="{name: 'DeleteProject', params: { id: projectinfo.id }}" class="btn btn-danger">Delete</router-link> -->
                <button type="button" calss="btn btn-danger" @click="deleteThisProject(projectinfo.id)" >Delete Project</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
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
     deleteThisProject(projectId){
     if(confirm("Are you sre you want to delete this project")){
      ProjectDataService.delete(projectId)
        .then(response => {
         alert('project deleted successfully')
        location.reload();
        }, (response) => {
                     alert('Unable to delete the project')
                })
        .catch(e => {
          console.log(e);
        });
     }
    },
    retrieveProjectsInfo() {
      ProjectInfoDataService.getAll()
        .then((response) => {
          this.projectsinfo = response.data;
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
      if (this.projectInfoSearch == "") {
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

<style scoped>
body,
html {
  height: 100%;
}
main {
  margin-top: 30px;
  height: 100%;
}

#projectcreation {
  margin: 20px auto;
  max-width: 1000px;
}

.inner2 {
  background: white;
  background: -webkit-gradient(
    linear,
    left bottom,
    left top,
    color-stop(84%, white),
    color-stop(84%, #7028b7)
  );
  background: linear-gradient(0deg, white 84%, #7028b7 84%);
  height: 25%;
  width: 100%;
}

.main-box {
  background: white;
  border-radius: 20px;
  padding-right: 35px;
  padding-bottom: 42px;
  padding-left: 35px;
  -webkit-transition: 300ms all cubic-bezier(0.4, 0, 0.2, 1);
  transition: 300ms all cubic-bezier(0.4, 0, 0.2, 1);
  margin-top: 24px;
  -webkit-box-shadow: 0 8px 60px 0 rgba(103, 151, 255, 0.11),
    0 12px 90px 0 rgba(103, 151, 255, 0.11);
  box-shadow: 0 8px 60px 0 rgba(103, 151, 255, 0.11),
    0 12px 90px 0 rgba(103, 151, 255, 0.11);
}

.m-t-25 {
  margin-top: 25px;
}

.p-10 {
  padding: 10px;
  margin-bottom: 10px;
}

.m-6 {
  margin-top: -6px;
}

.seclass {
  border: 1px solid lightgray;
  display: inline-flex;
  border-radius: 10px;
}

.m-r-10 {
  margin-right: 10px;
}

.m-r-5 {
  margin-right: 5px;
}

.d-i-10 {
  display: inline;
  margin-right: 10px;
}

.w-50 {
  width: 50%;
}

.d-flex {
  display: flex;
}

div#picture-input {
  display: flex;
}

.col-white{
  color: white!important;
}

.colr {
  background-color: rgb(113, 42, 184)!important;
  border-color:  rgb(113, 42, 184)!important ;
  color: white;
}

.clr-link{
    color: rgb(113, 42, 184)!important;
}

.colr:hover {
  background-color: #8654b8!important;
}
</style>