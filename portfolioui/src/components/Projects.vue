<template>
  <section>
    <Menu/>
    <h3>All Projects</h3>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Project Name</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="project in projects" v-bind:key="project.id">
            <td>{{project.id}}</td>
            <td>{{project.projectName}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>
<script>
import Menu from "./Menu";
import ProjectDataService from "../service/ProjectDataService";

export default {
  name: "Projects",
  components: {
    Menu
  },
  data() {
    return {
      projects: [],
      message: null
    };
  },
  methods: {
    refreshProjects() {
      ProjectDataService.retrieveAllProject() 
        .then(response => {
          this.projects = response.data;
        });
    }
  },
  created() {
    this.refreshProjects();
  }
};
</script>
