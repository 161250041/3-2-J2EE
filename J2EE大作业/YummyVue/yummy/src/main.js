// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router';
import App from './App'
import Routers from './router'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import axios from 'axios'
import qs from 'qs'


// import VueAxios from 'vue-axios'
// import AV from 'leancloud-storage'


Vue.use(VueRouter);
Vue.use(iView);
Vue.prototype.$axios = axios    //全局注册，使用方法为:this.$axios
Vue.prototype.qs = qs           //全局注册，使用方法为:this.qs

// The routing configuration
const RouterConfig = {
  routes: Routers
};
const router = new VueRouter(RouterConfig);

// axios.defaults.headers = {
//   "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
// }

new Vue({
  el: '#app',
  router: router,
  render: h => h(App)
});
// const app = new Vue({
//   router
// }).$mount('#app')
// /* eslint-disable no-new */
// new Vue({
//   el: '#app',
//   router,
//   components: {App},
//   template: '<App/>'
// });


