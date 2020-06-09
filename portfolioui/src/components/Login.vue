<template>
  <section>
    <Menu/>
    <h1>Login</h1>
    <div class="container">
      <div v-if="hasLoginFailed" class="alert alert-warning">Invalid Credentials</div>

      <div v-if="showSuccessMessage" class="alert">Login Sucessful</div>
      
 <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" v-model="username" name="username" class="form-control" required />
                </div>
                <div class="form-group">
                    <label htmlFor="password">Password</label>
                    <input type="password" v-model="password" name="password" class="form-control" required />
                </div>



      <button class="btn btn-success" v-on:click="loginClicked">Login</button>
    </div>
  </section>
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
          this.$router.push("/projectsinfo");
        })
        .catch(() => {
          this.hasLoginFailed = true;
          this.showSuccessMessage = false;
        });

      // AuthenticationService.executeBasicAuthenticationService(
      //   this.username,
      //   this.password
      // )
      //   .then(() => {
      //     AuthenticationService.registerSuccessfulLogin(
      //       this.username,
      //       this.password
      //     );
      //     this.showSuccessMessage = true;
      //     this.$router.push("/courses");
      //   })
      //   .catch(() => {
      //     this.hasLoginFailed = true;
      //     this.showSuccessMessage = false;
      //   });
    }
  }
};
</script>
<style scoped>
 button, input {
    overflow: visible;
}
  input {
    
    text-rendering: auto;
    color: -internal-light-dark-color(black, white);
    letter-spacing: normal;
    word-spacing: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    text-align: start;
    -webkit-appearance: textfield;
    background-color: -internal-light-dark-color(rgb(255, 255, 255), rgb(59, 59, 59));
    -webkit-rtl-ordering: logical;
    cursor: text;
    margin: 0em;
    font: 400 13.3333px Arial;
    padding: 1px 2px;
    border-width: 2px;
    border-style: inset;
    border-color: -internal-light-dark-color(rgb(118, 118, 118), rgb(195, 195, 195));
    border-image: initial;
}
 .container {
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}
  .logo {
    display: block;
    position: relative;
    width: 70%;
    margin: 0 auto;
    border-radius: 40px;
  }
  .title {
    text-align: center;
    color: white;
    -webkit-text-stroke-width: 1px;
    -webkit-text-stroke-color: black;
    font-size: 4em;
    font-family: Georgia, serif;
  }
  .error {
    color: red;
  }
  .log {
    color: blue;
  }
  .form-control {
    display: block;
    width: 100%;
    padding: .375rem .75rem;
    font-size: 1rem;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
   
   
    
</style>