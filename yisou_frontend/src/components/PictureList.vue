<template>
  <a-list item-layout="horizontal" :data-source="props.postList">
    <template #renderItem="{ item }">
      <a-card hoverable style="width: 240px">
        <template #cover>
          <img alt="example" :src="logo" />
        </template>
        <a-card-meta :title="item.title">
          <template #description>{{ item.content }}</template>
        </a-card-meta>
      </a-card>
    </template>
  </a-list>
</template>

<script setup lang="ts">
import logo from "@/assets/logo.png";
import { withDefaults, defineProps } from "vue";

interface Props {
  postList: any[];
}

const props = withDefaults(defineProps<Props>(), {
  postList: () => [],
});
</script>
<style scoped></style>
