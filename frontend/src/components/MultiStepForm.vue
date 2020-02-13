<template>
  <div>
    <ul class="steps has-content-centered">
      <li class="steps-segment" :class="{ 'is-active': step === 1}">
        <span class="steps-marker"></span>
        <div class="steps-content">
          <p class="is-size-4" v-if="step === 1">Step: {{step}}</p>
        </div>
      </li>
      <li class="steps-segment" :class="{ 'is-active': step === 2}">
        <span class="steps-marker"></span>
        <div class="steps-content">
          <p class="is-size-4" v-if="step === 2">Step: {{step}}</p>
        </div>
      </li>
      <li class="steps-segment" :class="{ 'is-active': step === 3}">
        <span class="steps-marker"></span>
        <div class="steps-content">
          <p class="is-size-4" v-if="step === 3">Step: {{step}}</p>
        </div>
      </li>
    </ul>

    <!-- Step 1 -->
    <div id="form-step-1" v-if="step === 1">
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

    <!-- Step 2 -->
    <div id="form-step-2" v-if="step === 2">
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

    <!-- Step 3 -->
    <div id="form-step-3" v-if="step === 3">
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

    <br />

    <!-- Buttons -->
    <div class="field is-grouped">
      <div class="control">
        <button @click="previousTab()" class="button is-primary" :disabled="step <= 1">Previous</button>
      </div>
      <div class="control">
        <button @click="nextTab()" class="button is-link" :disabled="step < 1 || step >= 3">Next</button>
      </div>
      <div class="control">
        <button @click="submit()" class="button is-success" :disabled="step !== 3">Submit</button>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal" :class="{ 'is-active': showModal }">
      <div class="modal-background"></div>
      <div class="modal-card">
        <header class="modal-card-head">
          <p class="modal-card-title">Info:</p>
        </header>
        <section class="modal-card-body">
          <p>{{modalBody}}</p>
        </section>
        <footer class="modal-card-foot">
          <button @click="closeModal()" class="button">Close</button>
        </footer>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import VeeValidate from "vee-validate";
import axios from "axios";
import BudgetService from "../services/BudgetService.js";

Vue.use(VeeValidate);

export default {
  name: "MultiStepForm",

  data() {
    return {
      step: 1,
      categories: [],
      hasCategory: true,
      subCategories: [],
      budget: {
        description: "",
        estimatedDate: "",
        category: "select",
        subCategory: "select",
        pricePreference: "",
        name: "",
        email: "",
        phone: ""
      },
      showModal: false,
      modalBody: ""
    };
  },

  created() {
    axios
      .get("http://localhost:8082/category/list")
      .then(response => (this.categories = response.data))
      .catch(error => {
        this.showModal(error);
      });
  },

  methods: {
    previousTab() {
      this.step--;
    },

    nextTab() {
      this.$root.$validator
        .validate("step" + (this.step) + ".*")
        .then(valid => {
          if (valid) {
            this.step++;
          }
        });
    },

    onChange(e) {
      axios
        .get("http://localhost:8082/category/list/" + e.target.value)
        .then(response => (this.subCategories = response.data))
        .then(() => (this.hasCategory = false));
    },

    submit() {
      this.$root.$validator
        .validate("step" + this.step + ".*")
        .then(valid => {
          if (valid) {
            BudgetService.createBudget(this.budget)
              .then(response => {
                console.log(response);
                if (response.data != null) {
                  this.openModal("Congrats! Your Budget has been created: \n" + JSON.stringify(response.data));
                }
              })
              .catch(error => {
                console.log(error);
                this.openModal(error);
              });
          }
        });
    },

    openModal(text) {
      this.modalBody = text;
      this.showModal = true;
    },

    closeModal() {
      this.showModal = false;
    }
  }
};
</script>

<style lang="scss">
</style>
