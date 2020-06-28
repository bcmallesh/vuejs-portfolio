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
            <!-- <a href="digital.html">Our Process </a> -->
            <router-link to="/digital" activeClass="active">
              <a>Our Process<i class="fa fa-arrow-right" aria-hidden="true"></i></a>
            </router-link>
          </div>
          <!-- <div class="btn ">
                        <a href="digital.html">Our Process 
                            <svg xmlns="http://www.w3.org/2000/svg"  width="22px" height="12px" viewBox="0 0 45.14 32.06"><defs><style></style></defs><title>arrow</title><g id="Layer_2" data-name="Layer 2"><g id="Layer_2-2" data-name="Layer 2"><path class="cls-1" d="M30.41,31.58a1.58,1.58,0,0,1-2.27,0,1.63,1.63,0,0,1,0-2.28L39.67,17.65H1.62A1.61,1.61,0,0,1,0,16a1.63,1.63,0,0,1,1.62-1.63H39.67L28.14,2.78a1.65,1.65,0,0,1,0-2.3,1.58,1.58,0,0,1,2.27,0L44.67,14.89a1.63,1.63,0,0,1,0,2.28Z"/></g></g></svg></a>
          </div>-->
        </div>
      </div>
    </header>
    <div class="container">
      <section class="work main-box" id="portfolio">
        <div class="row" style="position: relative;">
          <div class="col-md-10 text-center mr-auto">
            <h2>OUR WORK</h2>
          </div>
          <div class="col-md-2 center filter" style="margin-top: 24px;">
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

