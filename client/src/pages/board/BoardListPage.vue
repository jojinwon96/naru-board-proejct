<template>
  <q-page style="min-height: 0px">
    <q-page-container class="q-pa-md" style="padding-top: 0px">
      <q-toolbar-title class="q-pa-md text-bold">목록</q-toolbar-title>

      <BoardListItem v-for="board in paginatedBoards" :key="board.id" :board="board" />

      <q-pagination class="justify-center q-py-md" v-model="currentPage" :max="totalPages" :max-pages="5" boundary-links
        gutter="20px" />
    </q-page-container>
  </q-page>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import BoardListItem from 'src/components/board/BoardListItem.vue'
import { getBoardList, postBoardPaginationList } from 'src/api'

const currentPage = ref(2)
const pageSize = ref(5)
const totalPages = ref(1)
const boardList = ref([])

const boardPaginationRequest = ref({
  page: currentPage.value,
  size: pageSize.value
})

// 게시글 전체 페이징 조회 처리 함수
const getBoardPaginationListResponse = (requestBody) => {
  postBoardPaginationList(requestBody)
    .then((responseBody) => {
      if (!responseBody) return
      console.log(responseBody);
      const { code, total, boardListItemList } = responseBody
      if (code === 'SU') {
        console.log('total : ', total);
        console.log('list : ', boardListItemList);
      }
    })
}

// 게시글 전체 조회 처리 함수
const getBoardListResponse = () => {
  getBoardList()
    .then((responseBody) => {
      if (!responseBody) return

      const { code, boardListItemList } = responseBody
      if (code === 'SU') {
        boardList.value = boardListItemList
        totalPages.value = Math.ceil(boardList.value.length / pageSize.value)

        console.log('totalPages : ', totalPages.value)
      }
    })
    .catch((error) => {
      console.error('게시판 목록 가져오기 실패', error)
    })
}

// 페이지네이션 로직을 위한 computed
const paginatedBoards = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  const end = start + pageSize.value
  return boardList.value.slice(start, end)
})

onMounted(() => {
  getBoardListResponse()
  getBoardPaginationListResponse(boardPaginationRequest.value);
})
</script>

<style scoped></style>
