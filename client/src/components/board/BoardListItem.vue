<template>
  <q-card class="my-card">
    <q-card-section>
      <q-item-label class="text-h6">
        {{ board.title }}
      </q-item-label>
      <q-item-label caption>
        {{ board.createDate }}
      </q-item-label>
    </q-card-section>
    <q-card-actions>
      <q-btn color="primary" label="보기" @click="viewPost" />
    </q-card-actions>
  </q-card>
</template>

<script setup>
import { defineProps, readonly } from 'vue';

const props = defineProps({
  board: Object
});
const board = readonly(props.board);

/**
 * 게시물 보기 함수
 */

</script>

<style scoped>
.my-card {
  margin-bottom: 10px;
}
</style>
