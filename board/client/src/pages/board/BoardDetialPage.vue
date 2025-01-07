<template>
  <q-page style="min-height: 0px">
    <q-page-container class="q-pa-md" style="padding-top: 0px">
      <q-toolbar-title class="q-pa-md"> 글 상세보기 </q-toolbar-title>

      <q-input outlined v-model="form.title" label="제목" :disable="true" />

      <q-editor
        class="q-my-md"
        v-model="form.content"
        label="내용"
        :readonly="true"
        :toolbar="false"
      />

      <q-btn
        class="float-right q-mb-md"
        color="primary"
        label="수정페이지 이동"
        @click="goEditPage(pageId)"
      />
    </q-page-container>
  </q-page>
</template>

<script setup>
import { getBoard } from 'src/api'
import { onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

// 페이지 파라미터
const route = useRoute()
// 페이지 이동
const router = useRouter()

// 페이지 아이디
const pageId = route.params.id

const form = ref({
  title: '테스트 제목',
  content: '테스트 내용',
})

// 게시글 상세보기 처리 함수
const getBoardDetail = () => {
  if (pageId) {
    getBoard(pageId)
      .then((responseBody) => {
        if (!responseBody) return

        const { code, board } = responseBody
        if (code === 'SU') {
          form.value.title = board.title
          form.value.content = board.content
        }
      })
      .catch((error) => {
        console.error('게시판 상세 조회 실패', error)
      })
  }
}

// 게시물 수정 이동 함수
const goEditPage = (pageId) => {
  router.push({ path: `/write/${pageId}` })
}

onMounted(() => {
  console.log('현재 페이지 번호 : ', pageId)
  getBoardDetail()
})
</script>
