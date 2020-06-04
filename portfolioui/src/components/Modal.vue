<template>
  <div tabindex="-1" role="dialog" class="filter-modal show-panel" id="filterModal">
    <button type="button" class="close" @click="close" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>

    <div class="filter-modal-body" id="filter-options">
      <div class="row">
        <div class="col-md-6">
          <h3 class="mb-3">Work:</h3>
          <div class="form-group" v-for="work in modals[0].workLookupData">
            <input
              class="m-r-3"
              type="checkbox"
              :id="work.id"
              :value="work.key"
              v-model="selectedWorks"
            />
            <label :for="work.key">{{work.value}}</label>
          </div>
        </div>

        <div class="col-md-6">
          <h3 class="mb-3">Type:</h3>
          <div class="form-group" v-for="type in modals[0].typeLookupData">
            <input
              class="m-r-3"
              type="checkbox"
              :id="type.id"
              :value="type.key"
              v-model="selectedTypes"
            />
            <label :for="type.key">{{type.value}}</label>
          </div>
        </div>

        <div class="col-md-6">
          <h3 class="mb-3">Industry:</h3>
          <div class="form-group" v-for="industry in modals[0].industryLookupData">
            <input
              class="m-r-3"
              type="checkbox"
              :id="industry.id"
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
        <span class="glyphicon glyphicon-ok"></span>
        Apply
      </button>

      <button id="filterClearBtn" type="button" @click="clear" class="btn btn-light text-right">
        &nbsp;
        <span aria-hidden="true">&times;</span> Clear
      </button>
    </div>
  </div>
</template>


<script>
export default {
  computed: {
    modals() {
      return this.$store.getters.modals;
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
      this.selectedWorks.forEach(item => {
        selectedCheckbox.push(item);
      });

      this.selectedTypes.forEach(item => {
        selectedCheckbox.push(item);
      });

      this.selectedIndustries.forEach(item => {
        selectedCheckbox.push(item);
      });

      this.$options.parent.$children.forEach(event => {
        if (selectedCheckbox.length) {
          if (element != event.$el) {
            for (var i = 0; i < selectedCheckbox.length; i++) {
              if (event.$el.classList.contains(selectedCheckbox[i])) {
                event.$el.style.display = "";
                break;
              } else {
                event.$el.style.display = "none";
              }
            }
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
