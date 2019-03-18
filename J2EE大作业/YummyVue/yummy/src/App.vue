<template>
  <div id="app">
    <router-view></router-view>
    <!--<router-view name="restaurantLogin"></router-view>-->
    <!--<router-view name="mybutton"></router-view>-->
  </div>
</template>

<script>

  import Login from "./components/Login";

  export default {
    name: 'App',
    components: {
      'Login': Login
    },
    data() {
      return {}
    }
  }
</script>
<style>
  #app {
    text-align: center;
    position: absolute;
    width: 100%;
    height: 100%;
    margin-top: 0;
  }
</style>
