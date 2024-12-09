import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import IndexPage from "@/views/IndexPage.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home",
    redirect: '/post',
  },
  {
    path: "/:category",
    component: IndexPage,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
