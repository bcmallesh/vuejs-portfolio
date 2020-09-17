<template>
  <div class="background_dig">
    <header id="header">
      <div class="container">
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
        </nav>
        <div class="padding1x"></div>
        <div class="content container">
          <h1>Reinvent Mind</h1>
          <h2>The Complete Design System</h2>
          <div class="btn">
            <router-link to="/" activeClass="active">
              <a>Our Process<i class="fa fa-arrow-right" aria-hidden="true"></i></a>
            </router-link>
          </div>
        </div>
      </div>
    </header>
    <div class="container">
      <section class="work main-box" id="portfolio">
        <div class="row">
          <div class="col-md-10 text-center mr-auto">
            <h2>OUR WORK</h2>
          </div>
          <div class="col-md-2 center filter">
            <div id="app">
              <button type="button" class="btn btn-link circle-icon" @click="showModal">
                <div class="filtericon"></div>
              </button>
              <modal v-show="isModalVisible" @close="closeModal"></modal>
            </div>
          </div>
        </div>
        <div class="portfolios row">
          <appDesign v-for="design in designs" v-bind:key="design.id" :design="design"></appDesign>
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
import Design from "./Design.vue";
import Modal from "./Modal.vue";

export default {
  components: {
    appDesign: Design,
    modal: Modal
  },
  computed: {
    designs() {
      return this.$store.getters.designs;
    },
  },
  data() {
    return {
      isModalVisible: false
    };
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    }
  }
};
</script>

