<template>
  <div id="app">
    <form-wizard>
      <template v-slot="{budget}">
        <!-- Step 1 -->
        <tab name="Step 1" info="Description:" :selected="true" :budget="budget">
          <div id="form-step-1">
            <div class="field">
              <div class="field">
                <label class="label">Description</label>
                <div class="control">
                  <textarea
                    class="textarea"
                    name="description"
                    v-model="budget.description"
                    placeholder="Describe your project.."
                    v-validate="'required'"
                    data-vv-scope="step1"
                  ></textarea>
                  <p
                    class="help is-danger"
                    v-show="errors.has('step1.description')"
                  >{{ errors.first('step1.description') }}</p>
                </div>
              </div>

              <label class="label">Estimation Date</label>
              <div class="control">
                <input
                  class="input"
                  name="estimatedDate"
                  type="date"
                  placeholder="Estimation Date.."
                  v-model="budget.estimatedDate"
                  data-vv-scope="step1"
                  v-validate="'required'"
                />
                <p
                  class="help is-danger"
                  v-show="errors.has('step1.estimatedDate')"
                >{{ errors.first('step1.estimatedDate') }}</p>
              </div>
            </div>
          </div>
        </tab>

        <!-- Step 2 -->
        <tab name="Step 2" info="Basic Info about you" data-vv-scope="step2">
          <div id="form-step-2">
            <div class="field">
              <label class="label">Category</label>
              <div class="control">
                <div class="select" name="category">
                  <select
                    v-model="budget.category"
                    v-validate="'required'"
                    name="category"
                    @change="onChange($event)"
                    data-vv-scope="step2"
                  >
                    <option value="select">Select</option>
                    <option
                      v-for="(item, i) in categories"
                      v-bind:key="i"
                      :value="item.id"
                    >{{item.description}}</option>
                  </select>
                </div>
              </div>
              <p
                class="help is-danger"
                v-show="errors.has('step2.category')"
              >{{ errors.first('step2.category') }}</p>
            </div>

            <div class="field">
              <label class="label">Sub Category</label>
              <div class="control">
                <div class="select" name="subCategory">
                  <select
                    v-model="budget.subCategory"
                    v-validate="'required'"
                    name="subCategory"
                    data-vv-scope="step2"
                    :disabled="hasCategory"
                  >
                    <option value="select">Select</option>
                    <option
                      v-for="(item, i) in subCategories"
                      v-bind:key="i"
                      :value="item.id"
                    >{{item.name}}</option>
                  </select>
                </div>
              </div>
              <p
                class="help is-danger"
                v-show="errors.has('step2.subCategory')"
              >{{ errors.first('step2.subCategory') }}</p>
            </div>

            <div class="field">
              <label class="label">Price Preference</label>
              <div class="control">
                <div class="select" name="pricePreference">
                  <select
                    v-model="budget.pricePreference"
                    v-validate="'required'"
                    name="pricePreference"
                    data-vv-scope="step2"
                  >
                    <option value>Select</option>
                    <option value="cheapeast">Lo más barato</option>
                    <option value="balanced">Relación calidad precio</option>
                    <option value="expensive">Mejor calidad</option>
                  </select>
                </div>
              </div>
              <p
                class="help is-danger"
                v-show="errors.has('step2.pricePreference')"
              >{{ errors.first('step2.pricePreference') }}</p>
            </div>

          </div>
        </tab>

        <!-- Step 3 -->
        <tab name="Step 3" info="Finishing Up">
          <div id="form-step-3">
            <label class="label">Name</label>
            <div class="control">
              <input
                class="input"
                name="fullname"
                type="text"
                placeholder="Text input"
                v-model="budget.name"
                data-vv-scope="step3"
                v-validate="'required'"
              />
              <p
                class="help is-danger"
                v-show="errors.has('step3.fullname')"
              >{{ errors.first('step3.fullname') }}</p>
            </div>

            <div class="field">
              <label class="label">Email</label>
              <div class="control">
                <input
                  class="input"
                  name="email"
                  type="email"
                  v-model="budget.email"
                  placeholder="Email input"
                  data-vv-scope="step3"
                  v-validate="'required|email'"
                />
                <p
                  class="help is-danger"
                  v-show="errors.has('step3.email')"
                >{{ errors.first('step3.email') }}</p>
              </div>
            </div>

            <div class="field">
              <label class="label">Phone</label>
              <div class="control">
                <input
                  class="input"
                  name="phone"
                  type="tel"
                  v-model="budget.phone"
                  placeholder="Phone input"
                  data-vv-scope="step3"
                  v-validate="'required'"
                />
                <p
                  class="help is-danger"
                  v-show="errors.has('step3.phone')"
                >{{ errors.first('step3.phone') }}</p>
              </div>
            </div>

          </div>
        </tab>
      </template>
    </form-wizard>
  </div>
</template>

<script>
import Vue from "vue";
import VeeValidate from "vee-validate";
import FormWizard from "./components/FormWizard.vue";
import Tab from "./components/Tab.vue";
import "bulma/css/bulma.css";
import "bulma-calendar/dist/css/bulma-calendar.min.css";
import axios from "axios";

Vue.use(VeeValidate);

export default {
  name: "app",
  components: {
    FormWizard,
    Tab
  },

  data() {
    return {
      categories: [],
      hasCategory: true,
      subCategories: []
    };
  },

  created() {
    axios
      .get("http://localhost:3000/category/list")
      .then(response => (this.categories = response.data));
  },

  methods: {
    onChange(e) {
      axios
        .get("http://localhost:3000/category/list/" + e.target.value)
        .then(response => (this.subCategories = response.data));
      this.hasCategory = false;
    }
  }
};
</script>

<style lang="scss">
@import "./../node_modules/bulma/bulma.sass";
@import "./../node_modules/bulma-steps-component/bulma-steps.sass";
@import "./../node_modules/bulma-calendar/dist/css/bulma-calendar.min.css";

body {
  padding: 40px;
}

[v-cloak] {
  display: none;
}
</style>
