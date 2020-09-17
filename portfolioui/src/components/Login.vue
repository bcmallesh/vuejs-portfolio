<template>
  <div class="background_dig">
    <header id="header">
      <div class="container">
        <Menu></Menu>
      </div>
    </header>
    <div class="container">
      <section class="main-box">
        <div v-if="hasLoginFailed" class="alert alert-danger text-center">Invalid Credentials</div>
        <div v-if="showSuccessMessage" class="alert">Login Sucessful</div>
        <div class="container pt-3">
          <div class="row justify-content-sm-center">
            <div class="col-sm-6 col-md-4">
              <div class="card border-col text-center">
                <div class="card-header colr">Sign In</div>
                <div class="card-body">
                    <input
                      type="text"
                      class="form-control mb-25"
                      placeholder="Username"
                      v-model="username"
                      name="username"
                      required
                      autofocus
                    />
                    <input
                      type="password"
                      class="form-control mb-25"
                      placeholder="Password"
                      v-model="password"
                      name="password"
                      required
                    />
                    <button
                      class="btn btn btn-primary btn-block mb-1 colr"
                      v-on:click="loginClicked"
                      type="submit"
                    >Login</button>
                </div>
              </div>
            </div>
          </div>
        </div>
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
import AuthenticationService from "../service/AuthenticationService";

export default {
  name: "Login",
  components: {
    Menu
  },
  data() {
    return {
      username: "",
      password: "",
      hasLoginFailed: false,
      showSuccessMessage: false
    };
  },
  methods: {
    loginClicked() {
      AuthenticationService.executeJwtAuthenticationService(
        this.username,
        this.password
      )
        .then(response => {
          AuthenticationService.registerSuccessfulLoginForJwt(
            this.username,
            response.data.token
          );
          this.$router.push("/projects");
        })
        .catch(() => {
          this.hasLoginFailed = true;
          this.showSuccessMessage = false;
        });

    }
  }
};
</script>