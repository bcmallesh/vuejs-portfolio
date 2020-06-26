<template>
  <header>
    <nav id="navigation" class="navbar navbar-ligh">
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

<style scoped>
.navbar {
  padding-top: 20px;
}

.text-center {
  text-align: center;
}

#header {
  color: white;
}

#header a span {
  color: white;
  font-size: 1.4em;
}

#header .padding1x {
  padding-top: 84px;
}

#header .padding2x {
  padding-top: 22px;
}

#header .content h1 {
  font-size: 3em;
  font-family: Regular-bold;
  text-shadow: #898787b3 1px 1px 3px;
  margin-bottom: 0px;
}

#header .content h2 {
  font-size: 2em;
  font-family: Regular-bold;
}

#header .content .btn {
  background: #ffffff3b;
  border-radius: 100px;
  font-size: 1.2em;
  margin-top: 20px;
}

#header .content .btn a {
  text-decoration: none;
  color: white;
  font-family: Regular-bold;
  padding-right: 10px;
  padding-left: 10px;
}

#header .content .btn a svg {
  fill: white;
  margin-left: 11px;
}

#header .content .btn:hover {
  background: white;
}

#header .content .btn:hover a {
  color: var(--main-color);
}

#header .content .btn:hover a svg {
  fill: var(--main-color);
}

#header .evry-logo {
  fill: white;
  height: 42px;
  margin-top: -4px;
}

.vl {
  border-left: #d9a4f5 solid 1px;
  padding-right: 1px;
  margin-right: 12px;
  margin-left: 12px;
}
</style>
