import Vue from "vue"
import Resource from "vue-resource"
import VueRouter from "vue-router"
import "webpack-material-design-icons"
import "material-design-lite/dist/material.min.css"
import "material-design-lite"
import "./css/resetChrome.css"
import login from "components/login.vue"
import index from "components/index.vue"
import work from "components/work.vue"

Vue.use(Resource);
Vue.use(VueRouter);

var App = Vue.extend({});

var router = new VueRouter({
  hashbang: false,
  history: true,
  saveScrollPosition: true
});

router.map({
  "/login": {
    component: login
  },
  "/h5/index": {
    component: index
  },
  "/h5/work/:id": {
    component: work
  },
});

router.beforeEach(function () {
  window.scrollTo(0, 0)
});

router.redirect({
  '*': '/h5/index'
});

router.start(App, "body");
