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
import CourseDataService from "../service/CourseDataService";

export default {
  name: "Courses",
  components: {
    Menu
  },
  data() {
    return {
      courses: [],
      message: null,
      INSTRUCTOR: "in28minutes"
    };
  },
  methods: {
    refreshCourses() {
      CourseDataService.retrieveAllCourses(this.INSTRUCTOR) //HARDCODED
        .then(response => {
          this.courses = response.data;
        });
    }
  },
  created() {
    this.refreshCourses();
  }
};
</script>
