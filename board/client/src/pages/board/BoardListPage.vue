<template>
  <q-page style="min-height: 0px">
    <q-page-container class="q-pa-md" style="padding-top: 0px">
      <q-toolbar-title class="q-pa-md text-bold">목록</q-toolbar-title>

      <div class="flex justify-between q-py-sm" style="height: 60px">
        <div class="self-center q-ml-md">
          <input class="" type="checkbox" v-model="checkAllComputed" name="checkAll" />
          <label class="q-ml-sm" for="checkAll">전체선택</label>
        </div>
        <q-btn
          v-show="isAnyBoardSelected"
          size="md"
          color="red"
          label="선택 삭제"
          @click="patchBoardStatus"
        />
      </div>

      <BoardListItem
        v-for="board in boardList"
        :key="board.id"
        :board="board"
        @update:selected="updateBoardSelected(board.id, $event)"
      />

      <q-pagination
        class="justify-center q-py-md"
        v-model="currentPage"
        :max="totalPages"
        :max-pages="5"
        boundary-links
        gutter="20px"
      />
    </q-page-container>
  </q-page>
</template>

<script setup>
import { ref, onMounted, watch, computed } from 'vue'
import BoardListItem from 'src/components/board/BoardListItem.vue'
import { patchDisableBoardStatus, postBoardPaginationList } from 'src/api'

const currentPage = ref(1)
const pageSize = ref(5)
const totalPages = ref(1)
const boardList = ref([])

const boardPaginationRequest = ref({
  page: currentPage.value,
  size: pageSize.value,
})

// 게시글 전체 페이징 조회 처리 함수
const getBoardPaginationListResponse = async (requestBody) => {
  await postBoardPaginationList(requestBody)
    .then((responseBody) => {
      if (!responseBody) return
      const { code, total, boardListItemList } = responseBody
      if (code === 'SU') {
        totalPages.value = Math.ceil(total / pageSize.value)
        boardList.value = boardListItemList.map((board) => {
          return {
            ...board,
            selected: false,
          }
        })
        console.log('전체 선택 : ', checkAllComputed.value)
      }
    })
    .catch((error) => {
      console.error('게시판 페이징 목록 가져오기 실패', error)
    })
}

// currentPage가 변경될 때마다 페이징 조회
watch(currentPage, () => {
  boardPaginationRequest.value.page = currentPage.value
  getBoardPaginationListResponse(boardPaginationRequest.value)
})

/** 체크 박스  */
watch(
  boardList,
  (newBoardList) => {
    const allSelected = newBoardList.every((board) => board.selected)
    checkAllComputed.value = allSelected
  },
  { deep: true },
)

// 게시글 비활성화 처리 함수
const patchBoardStatus = async () => {
  if (confirm('정말 삭제하시겠습니까?')) {
    const idList = boardList.value.filter((data) => data.selected).map((data) => data.id)
    const requestBody = {
      idList: idList,
    }
    try {
      const responseBody = await patchDisableBoardStatus(requestBody)
      if (!responseBody) return

      const { code, resultRow } = responseBody
      if (code === 'SU') {
        console.log(resultRow)
        alert('삭제가 완료되었습니다')

        await getBoardPaginationListResponse(boardPaginationRequest.value)
        console.log(boardList.value) // 최신 boardList 확인
      }
    } catch (error) {
      console.error('삭제 처리 실패', error)
    }
  }
}

// 체크박스 중 하나라도 선택된 항목이 있는지 확인하는 computed
const isAnyBoardSelected = computed(() => {
  return boardList.value.some((board) => board.selected)
})

// 체크박스 전체선택 computed
const checkAllComputed = computed({
  get: () => boardList.value.every((board) => board.selected),
  set: (value) => {
    boardList.value.forEach((board) => {
      board.selected = value
    })
  },
})

// 개별 board.selected 값을 업데이트하는 함수
const updateBoardSelected = (id, selected) => {
  const board = boardList.value.find((board) => board.id === id)
  if (board) {
    board.selected = selected
  }
}

onMounted(() => {
  getBoardPaginationListResponse(boardPaginationRequest.value)
})

// 게시글 전체 조회 처리 함수
// const getBoardListResponse = () => {
//   getBoardList()
//     .then((responseBody) => {
//       if (!responseBody) return

//       const { code, boardListItemList } = responseBody
//       if (code === 'SU') {
//         boardList.value = boardListItemList
//         totalPages.value = Math.ceil(boardList.value.length / pageSize.value)

//         console.log('totalPages : ', totalPages.value)
//       }
//     })
//     .catch((error) => {
//       console.error('게시판 목록 가져오기 실패', error)
//     })
// }

// // 페이지네이션 로직을 위한 computed
// const paginatedBoards = computed(() => {
//   const start = (currentPage.value - 1) * pageSize.value
//   const end = start + pageSize.value
//   return boardList.value.slice(start, end)
// })\
</script>

<style scoped></style>
