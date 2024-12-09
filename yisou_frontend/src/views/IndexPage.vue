<template>
  <div class="indexPage">
    <a-input-search
      v-model:value="searchText"
      placeholder="input search text"
      enter-button="Search"
      size="large"
      @search="onSearch"
    />
    <MyDivider />
    <a-tabs v-model:activeKey="activeKey" @change="onTableChange">
      <a-tab-pane key="post" tab="文章">
        <PostList :postList="postList" />
      </a-tab-pane>
      <a-tab-pane key="picture" tab="图片">
        <PictureList :pictureList="pictureList" />
      </a-tab-pane>
      <a-tab-pane key="user" tab="用户">
        <UserList :userList="userList" />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watchEffect } from "vue";
import PictureList from "@/components/PictureList.vue";
import PostList from "@/components/PostList.vue";
import UserList from "@/components/UserList.vue";
import MyDivider from "@/components/MyDivider.vue";
import { useRoute, useRouter } from "vue-router";
import myAxios from "@/plugins/myAxios";

const router = useRouter();
const route = useRoute();
const activeKey = route.params.category;
const postList = ref([]);
const pictureList = ref([]);
const userList = ref([]);
const initSearchParams = {
  searchText: "",
  pageSize: 8,
  current: 1
};

const searchParams = ref(initSearchParams);
const searchText = ref(route.query.searchText || "");

const loadData = () => {
  const type=route.params.category;
  myAxios.post("/search/all", searchParams.value).then((res) => {
    if (type === "post") {
      postList.value = res.postList;
    } else if (type === "user") {
      userList.value = res.userList;
    } else if (type === "picture") {
      pictureList.value = res.pictureList;
    }
  });
};


watchEffect(() => {
  searchParams.value = {
    ...initSearchParams,
    searchText: route.query.searchText,
    type: route.params.category,
  };
  loadData();
});


const onSearch = (value: string) => {
  router.push({
    query: {
      ...searchParams.value,
      searchText: value
    }
  });
};
const onTableChange = (key: string) => {
  router.push({
    path: `/${key}`,
    query: searchParams.value
  });
};


</script>
