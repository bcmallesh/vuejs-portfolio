<template>
  <header>
    <nav id="navigation" class="navbar navbar-light pt-4">
      <router-link to="/" activeClass="active" tag="a">
        <!-- <a class="navbar-brand" href="index.html"> -->
        <img src="../assets/images/SVG/logo.svg" alt="EVRY" class="evry-logo" />
        <span class="vl"></span>
        <span>
          Digital
          <b>Experience</b>
        </span>
      </router-link>
      <router-link v-if="!isAuthenticated" class="nav-link" to="/login"><span><b>Login</b></span></router-link>
      <router-link v-if="isAuthenticated" class="nav-link" to="/logout"><span><b>Logout</b></span></router-link>
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
  }
};
</script>
