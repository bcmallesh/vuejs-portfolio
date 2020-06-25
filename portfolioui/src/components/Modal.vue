<template>
  <div tabindex="-1" role="dialog" class="filter-modal show-panel" id="filterModal">
    <button type="button" class="close" @click="close" aria-label="Close">
      <i class="fa fa-times" aria-hidden="true"></i>
    </button>

    <div class="filter-modal-body" id="filter-options">
      <div class="row">
        <div class="col-md-6">
          <h3 class="mb-3">Work:</h3>
          <div class="form-group" v-for="work in modals.workLookupData">
            <input
              class="m-r-3"
              type="checkbox"
              :id="work.key"
              :value="work.key"
              v-model="selectedWorks"
            />
            <label :for="work.key">{{work.value}}</label>
          </div>
        </div>

        <div class="col-md-6">
          <h3 class="mb-3">Type:</h3>
          <div class="form-group" v-for="type in modals.typeLookupData">
            <input
              class="m-r-3"
              type="checkbox"
              :id="type.key"
              :value="type.key"
              v-model="selectedTypes"
            />
            <label :for="type.key">{{type.value}}</label>
          </div>
        </div>

        <div class="col-md-6">
          <h3 class="mb-3">Industry:</h3>
          <div class="form-group" v-for="industry in modals.industryLookupData">
            <input
              class="m-r-3"
              type="checkbox"
              :id="industry.key"
              :value="industry.key"
              v-model="selectedIndustries"
            />
            <label :for="industry.key">{{industry.value}}</label>
          </div>
        </div>
      </div>
    </div>
    <div class="filter-modal-footer">
      <button
        id="filterApplyBtn"
        type="button"
        @click="SendRequest"
        class="btn btn-primary text-right"
        style="margin-left: 20px;"
      >
        &nbsp;
        <i class="fas fa-check m-10"></i>
        Apply
      </button>

      <button id="filterClearBtn" type="button" @click="clear" class="btn btn-light text-right">
        &nbsp;
        <i class="fas fa-times m-10"></i> Clear
      </button>
    </div>
  </div>
</template>


<script>
export default {
  computed: {
    modals() {
      return this.$store.getters.modals[0] ? this.$store.getters.modals[0] : [];
    }
  },
  methods: {
    close() {
      this.$emit("close");
    },
    clear() {
      this.selectedWorks = [];
      this.selectedTypes = [];
      this.selectedIndustries = [];
    },
    SendRequest() {
      this.$emit("close");
      var element = this.$el;
      var selectedCheckbox = [];
      var filterImages = [];
      this.selectedWorks.forEach(item => {
        selectedCheckbox.push(item);
      });

      this.selectedTypes.forEach(item => {
        selectedCheckbox.push(item);
      });

      this.selectedIndustries.forEach(item => {
        selectedCheckbox.push(item);
      });

      this.$options.parent.$children.filter(function(el) {
        if (el.$el.classList.contains("portfolio")) {
          filterImages.push(el);
          console.log("element---->" + el);
        }
      });

      filterImages.forEach(event => {
        var valuePresent = false;
        if (selectedCheckbox.length > 0) {
          var classList = event.$el.classList.value.split(" ");

          if (
            (this.selectedIndustries.length &&
              this.selectedTypes.length &&
              this.selectedWorks.length) > 0
          ) {
            classList.filter(element => {
              if (selectedCheckbox.includes(element)) {
                event.$el.style.display = "";
              } else {
                event.$el.style.display = "none";
              }
            });
          } else if (
            (this.selectedIndustries.length ||
              this.selectedTypes.length ||
              this.selectedWorks.length) > 0
          ) {
            selectedCheckbox.filter(element => {
              if (classList.includes(element)) {
                event.$el.style.display = "";
                valuePresent = true;
              } else {
                if (!valuePresent) {
                  event.$el.style.display = "none";
                }
              }
            });
          }
        } else {
          event.$el.style.display = "";
        }
      });
    }
  },
  data() {
    return {
      selectedIndustries: [],
      selectedTypes: [],
      selectedWorks: []
    };
  }
};
</script>
<style>
.m-10 {
  margin-right: 10px;
}
</style>