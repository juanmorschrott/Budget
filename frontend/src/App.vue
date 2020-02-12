<template>
  <div id="app">
    <multi-step-form></multi-step-form>
  </div>
</template>

<script>
import "bulma/css/bulma.css";
import "bulma-calendar/dist/css/bulma-calendar.min.css";
import MultiStepForm from "./components/MultiStepForm.vue";

export default {
  name: 'App',
  components: {
    MultiStepForm,
  }
}
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