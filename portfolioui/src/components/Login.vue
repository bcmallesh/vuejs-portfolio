<template>
  <section>
    <Menu/>
    <h1>Login</h1>
    <div class="container">
      <div v-if="hasLoginFailed" class="alert alert-warning">Invalid Credentials</div>
      <div v-if="showSuccessMessage" class="alert">Login Sucessful</div>User Name:
      <input type="text" name="username" v-model="username">
      Password:
      <input type="password" name="password" v-model="password">
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
          this.$router.push("/projects");
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
  * {
    margin: 0;
    padding: 0;
    -moz-box-sizing: border-box;
         box-sizing: border-box;
  }
  body, html { height:100%; width:100% }
  body {
    background: #F5F5F5;
    font-family: sans-serif;
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
  input[type='text'],
    input[type='password'] {
      width: 100%;
      margin-bottom: 0.5em;
      border: 1;
      border-radius: 7px;
      height: 2.5em;
      font-size: 1.2em;
      color: hsl(0, 0%, 40%);
      padding-left: 0.5em;
    }
  input[type='password'] {
      margin-bottom: 0.1em;
    }
   
   
    .ribbon {
      position: absolute;
      right: -5px; top: -5px;
      z-index: 1;
      overflow: hidden;
      width: 75px; height: 75px;
      text-align: right;
    }
    .ribbon span {
      font-size: 10px;
      font-weight: bold;
      color: #FFF;
      text-transform: uppercase;
      text-align: center;
      line-height: 20px;
      transform: rotate(45deg);
      -webkit-transform: rotate(45deg);
      width: 100px;
      display: block;
      background: #79A70A;
      background: linear-gradient(#C9C9C9 0%, #6B6B6B 100%);
      box-shadow: 0 3px 10px -5px rgba(0, 0, 0, 1);
      position: absolute;
      top: 19px; right: -21px;
    }
    .ribbon span::before {
      content: "";
      position: absolute; left: 0px; top: 100%;
      z-index: -1;
      border-left: 3px solid #6B6B6B;
      border-right: 3px solid transparent;
      border-bottom: 3px solid transparent;
      border-top: 3px solid #6B6B6B;
    }
    .ribbon span::after {
      content: "";
      position: absolute; right: 0px; top: 100%;
      z-index: -1;
      border-left: 3px solid transparent;
      border-right: 3px solid #6B6B6B;
      border-bottom: 3px solid transparent;
      border-top: 3px solid #6B6B6B;
    }
</style>