<template>
  <div class="background_dig">
    <header id="header">
      <div class="container">
        <Menu></Menu>
        <div class="padding1x"></div>
      </div>
    </header>
    <div class="container">
      <section class="main-box">
        <div v-if="hasLoginFailed" class="alert alert-warning">Invalid Credentials</div>
        <div v-if="showSuccessMessage" class="alert">Login Sucessful</div>
        <div class="container pt-3">
          <div class="row justify-content-sm-center">
            <div class="col-sm-6 col-md-4">
              <div class="card border-info text-center">
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
                      class="btn btn-lg btn-primary btn-block mb-1 colr"
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
    <footer class="foot p-t-r-20">
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
          this.$router.push("/createproject");
        })
        .catch(() => {
          this.hasLoginFailed = true;
          this.showSuccessMessage = false;
        });

    }
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


.background_dig {
  background: url(../assets/images/img.jpg);
  width: 100%;
  position: relative;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-size: cover;
}
.main-box {
  background: white;
  border-radius: 20px;
  padding-right: 35px;
  padding-bottom: 42px;
  padding-left: 35px;
  -webkit-transition: 300ms all cubic-bezier(0.4, 0, 0.2, 1);
  transition: 300ms all cubic-bezier(0.4, 0, 0.2, 1);
  margin-top: 50px;
  margin-bottom: 20px;
  -webkit-box-shadow: 0 8px 60px 0 rgba(103, 151, 255, 0.11),
    0 12px 90px 0 rgba(103, 151, 255, 0.11);
  box-shadow: 0 8px 60px 0 rgba(103, 151, 255, 0.11),
    0 12px 90px 0 rgba(103, 151, 255, 0.11);
}

.padding1x {
  padding-top: 5px;
}

.border-info {
  border-color: rgb(113, 42, 184) !important;
}

.p-t-r-20 {
  padding: 20px 0px 20px 0px;
}

.colr {
  background-color: rgb(113, 42, 184);
  color: white;
}

.mb-25 {
  margin-bottom: 25px;
}

.vl {
  border-left: #d9a4f5 solid 1px;
  padding-right: 1px;
  margin-right: 12px;
  margin-left: 12px;
}
</style>