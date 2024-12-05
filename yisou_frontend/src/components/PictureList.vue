<template>
  <a-list item-layout="horizontal" :grid="{ gutter: 16, column: 4 }" :data-source="props.pictureList">
    <template #renderItem="{ item }">
      <a-card hoverable style="width: 240px">
        <template #cover>
          <img alt="example" :src="item.url" />
        </template>
      </a-card>
    </template>
  </a-list>
</template>

<script setup lang="ts">
import logo from "@/assets/logo.png";
import { withDefaults, defineProps } from "vue";

interface Props {
  pictureList: any[];
}

const props = withDefaults(defineProps<Props>(), {
  pictureList: () => []
});
</script>
<style scoped></style>
