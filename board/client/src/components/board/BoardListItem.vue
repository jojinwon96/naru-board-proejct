<template>
  <q-card class="my-card cursor-pointer" @click="goDetailPage(board.id)">
    <q-card-section>
      <div class="flex q-gutter-md">
        <div>
          <input type="checkbox" v-model="board.selected" @click.stop />
        </div>
        <div class="column">
          <q-item-label class="text-h7">
            {{ board.title }}
          </q-item-label>
          <q-item-label caption>
            {{ board.createDate }}
          </q-item-label>
        </div>
      </div>
    </q-card-section>
  </q-card>
</template>

<script setup>
import { defineProps } from 'vue'
import { useRouter } from 'vue-router'

const props = defineProps({
  board: Object,
})
const board = props.board

const router = useRouter()

// 게시물 상세보기 이동 함수
const goDetailPage = (pageId) => {
  router.push({ path: `/detail/${pageId}` })
}
</script>

<style scoped>
.my-card {
  margin-bottom: 10px;
}
</style>
