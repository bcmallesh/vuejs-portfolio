<template>
  <header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
      <div>
        <a href="http://www.evry.com" class="navbar-brand">TietoEvry</a>
      </div>
      <ul class="navbar-nav">
        <li>
          <router-link v-if="isAuthenticated" class="nav-link" to="/projects">Projects</router-link>
        </li>
        
      </ul>
      <ul class="navbar-nav">
        <li>
          <router-link v-if="isAuthenticated" class="nav-link" to="/projectsinfo">Projects Info</router-link>
        </li>
        
      </ul>
      <ul class="navbar-nav navbar-collapse justify-content-end">
        <li>
          <router-link v-if="!isAuthenticated" class="nav-link" to="/login">Login</router-link>
        </li>
        <li>
          <router-link v-if="isAuthenticated" class="nav-link" to="/logout" >Logout</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>
<script>
import AuthenticationService from "../service/AuthenticationService";

export default {
  name: "Menu",
  data() {
    return {
      isAuthenticated: AuthenticationService.isUserLoggedIn()
    };
  },
}
</script>
