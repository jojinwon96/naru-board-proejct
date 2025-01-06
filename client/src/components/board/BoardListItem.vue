<template>
  <q-card class="my-card cursor-pointer" @click="goDetailPage(board.id)">
    <q-card-section>
      <q-item-label class="text-h7">
        {{ board.title }}
      </q-item-label>
      <q-item-label caption>
        {{ board.createDate }}
      </q-item-label>
    </q-card-section>
    <q-card-actions class="q-px-md">
      <q-btn color="info" label="수정" @click.stop="goEditPage(board.id)" />
    </q-card-actions>
  </q-card>
</template>

<script setup>
import { defineProps, readonly } from 'vue'
import { useRouter } from 'vue-router'

const props = defineProps({
  board: Object,
})
const board = readonly(props.board)

const router = useRouter()

// 게시물 상세보기 이동 함수
const goDetailPage = (pageId) => {
  router.push({ path: `/detail/${pageId}` })
}

// 게시물 수정 이동 함수
const goEditPage = (pageId) => {
  router.push({ path: `/write/${pageId}` })
}
</script>

<style scoped>
.my-card {
  margin-bottom: 10px;
}
</style>
