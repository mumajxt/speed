import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import less from "less";
import qs from "qs";

import store from "./vuex/store";
Vue.prototype.$qs = qs;

import axios from "axios";
axios.defaults.withCredentials = true; //携带cookie
Vue.prototype.$axios = axios;
import "./assets/font/iconfont.css";

import animated from "animate.css"; // npm install animate.css --save安装，在引入
Vue.use(animated);

Vue.use(less);
Vue.config.productionTip = false;

//工具类
import myUtil from "./assets/js/util";
Vue.use(myUtil);

import Drawer from "@/components/notice/Drawer";
Vue.component("Drawer", Drawer);

import Toast from "@/message/toast.js";
Vue.use(Toast);

Date.prototype.format = function(format) {
  var o = {
    "M+": this.getMonth() + 1, //month
    "d+": this.getDate(), //day
    "h+": this.getHours(), //hour
    "m+": this.getMinutes(), //minute
    "s+": this.getSeconds(), //second
    "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
    S: this.getMilliseconds(), //millisecond
  };
  if (/(y+)/.test(format))
    format = format.replace(
      RegExp.$1,
      (this.getFullYear() + "").substr(4 - RegExp.$1.length)
    );
  for (var k in o)
    if (new RegExp("(" + k + ")").test(format))
      format = format.replace(
        RegExp.$1,
        RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)
      );
  return format;
};

router.beforeEach((to, from, next) => {
  if (/admin/.test(to.path)) {
    if (to.path == "/admin-login") {
      console.log("登录");
      next();
      return;
    }
    //console.log("需要验证");
    // 查看请求是否需要认证
    if (store.state.admin) {
      // 有权限,继续往下跳转
      console.log("已登录");
      next();
      return;
    } else {
      //console.log("验证token");
      axios({
        method: "get",
        url: "/api/admin/verify",
      })
        .then((response) => {
          let info = response.data;
          console.log(info);
          if (info.flag) {
            let admin = info.data;
            store.commit("setAdmin", admin);
            console.log("验证成功");
            next();
          } else {
            next("/admin-login");
          }
        })
        .catch((error) => {
          console.error(error);
          next("/admin-login");
        });
      return;
    }
  } else {
    // 不需要认证的全部直接放行
    //console.log("无需验证");
    next();
    return;
  }
});

//无限加载
import infiniteScroll from "vue-infinite-scroll";
Vue.use(infiniteScroll);
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
