import Vue from "vue";
import VueRouter from "vue-router";
import Rank from "@/views/Rank.vue";
import Home from "@/views/rank/Home.vue";
import About from "@/views/rank/About.vue";
import Donate from "@/views/rank/Donate.vue";

import Admin from "@/views/Admin.vue";
import Login from "@/views/Login.vue";
import RecordManage from "@/views/admin/manage/RecordManage.vue";
import TrackManage from "@/views/admin/manage/TrackManage.vue";
import PlayerManage from "@/views/admin/manage/PlayerManage.vue";
import CarManage from "@/views/admin/manage/CarManage.vue";
import PetManage from "@/views/admin/manage/PetManage.vue";
import EcuManage from "@/views/admin/manage/EcuManage.vue";
import DonateManage from "@/views/admin/manage/DonateManage.vue";

import NotFound from "@/views/NotFound.vue";

Vue.use(VueRouter);

//获取原型对象上的push函数
const originalPush = VueRouter.prototype.push;
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err);
};

const routes = [
  {
    path: "/",
    redirect: "/rank",
  },
  {
    path: "/404",
    name: "notfound",
    component: NotFound,
  },

  // 普通用户
  {
    path: "/rank",
    name: "rank",
    component: Rank,
    redirect: "/rank/home",

    children: [
      {
        path: "home",
        name: "home",
        component: Home,
      },
      {
        path: "donate",
        name: "Donate",
        component: Donate,
      },
      {
        path: "about",
        name: "About",
        component: About,
      },
    ],
  },

  // 管理员
  {
    path: "/admin-login",
    name: "login",
    component: Login,
  },
  {
    path: "/admin",
    name: "admin",
    component: Admin,
    children: [
      {
        path: "records",
        name: "records",
        component: RecordManage,
      },
      {
        path: "tracks",
        name: "tracks",
        component: TrackManage,
      },
      {
        path: "players",
        name: "players",
        component: PlayerManage,
      },
      {
        path: "cars",
        name: "cars",
        component: CarManage,
      },
      {
        path: "pets",
        name: "pets",
        component: PetManage,
      },
      {
        path: "ecus",
        name: "ecus",
        component: EcuManage,
      },
      {
        path: "donates",
        name: "donates",
        component: DonateManage,
      },
    ],
  },

  {
    path: "*",
    redirect: "/404",
  },
];

const router = new VueRouter({
  routes,
});

export default router;
