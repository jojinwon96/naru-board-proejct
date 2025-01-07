<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { usePost } from '@/apis/post'
import type { Post } from '@/types/post'

const { getPost, fetchPost } = usePost()
const post = ref<Post | null>(null)

// 데이터를 가져오는 함수
const getPostHandler = async () => {
  await fetchPost()
  post.value = getPost()
}

onMounted(() => {
  getPostHandler()
})
</script>

<template>
  <div class="wrap">
    <div class="flex-col">
      <div class="flex">
        <h3>id:</h3>
        <p>{{ post?.id }}</p>
      </div>
      <div class="flex">
        <h3>title:</h3>
        <p>{{ post?.title }}</p>
      </div>
      <div class="flex">
        <h3>body:</h3>
        <p>{{ post?.body }}</p>
      </div>
      <div class="flex">
        <h3>userId:</h3>
        <p>{{ post?.userId }}</p>
      </div>
    </div>
  </div>
</template>

<style>
.wrap {
  margin: 0 auto;
  width: 1000px;
}
.flex {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.flex > p {
  font-size: 16px;
  font-weight: bold;
}

.flex-col {
  display: flex;
  flex-direction: column;
}
</style>
