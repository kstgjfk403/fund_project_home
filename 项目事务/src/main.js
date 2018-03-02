
import Vue from 'vue';
import App from './App';
import router from './router';
import store from './vuex/store';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from "axios";
import './styles/iconfont.css';
Vue.config.productionTip = false;
Vue.use(ElementUI)

import htmlToPdf from '@/api/htmlToPdf'  
Vue.use(htmlToPdf);
//axios.defaults.baseURL='http://192.168.106.79:8080';
axios.defaults.baseURL='http://localhost:8080';
// axios.defaults.baseURL = 'http://192.168.106.135:8080';
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  store
})
