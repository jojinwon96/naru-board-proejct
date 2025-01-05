<template>
  <q-page>
    <q-page-container class="q-px-md" style="padding-top: 0;">
      <q-toolbar-title class="q-px-md" style="padding: 16px;">
        게시물 목록
      </q-toolbar-title>

      <q-scroll-area :visible="visible" style="height: 690px">
        <BoardListItem v-for="(board, index) in paginatedBoards" :key="index" :board="board" />
      </q-scroll-area>

      <q-pagination class="justify-center q-py-md" v-model="currentPage" :max="totalPages" :max-pages="5"
        direction-links gutter="20px" />
    </q-page-container>
  </q-page>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import BoardListItem from 'src/components/board/BoardListItem.vue';
import { getBoardList } from 'src/api';

const currentPage = ref(1);
const pageSize = ref(5);
const totalPages = ref(1);
const boardList = ref([]);
const paginatedBoards = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return boardList.value.slice(start, end);
});

// 게시글 전체 조회 처리 함수
const getBoardListResponse = () => {
  getBoardList().then(responseBody => {
    if (!responseBody) return;

    const { code, boardListItemList } = responseBody;
    if (code === "SU") {
      boardList.value = boardListItemList;
      totalPages.value = Math.ceil(boardList.value.length / pageSize.value);
    }
  }).catch(error => {
    console.error('게시판 목록 가져오기 실패', error);
  });
}

onMounted(() => {
  getBoardListResponse();
});

</script>

<style scoped></style>
