<template>
  <div class="indexPage">
    <a-input-search
      v-model:value="searchParams.searchText"
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

onMounted(() => {
  loadData();
});

const loadData = () => {
  myAxios.post("/search/all", searchParams.value).then((res) => {
    postList.value = res.postList;
    pictureList.value = res.pictureList;
    userList.value = res.userList;
  });
};


watchEffect(() => {
  searchParams.value = {
    ...initSearchParams,
    searchText: route.query.searchText
  };
});


const onSearch = (value: string) => {
  router.push({
    query: {
      ...searchParams.value,
      searchText: value
    }
  });
  loadData();
};
const onTableChange = (key: string) => {
  router.push({
    path: `/${key}`,
    query: searchParams.value
  });
};


</script>
