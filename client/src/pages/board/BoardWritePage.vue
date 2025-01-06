<template>
  <q-page style="min-height: 0px">
    <q-page-container class="q-pa-md q-gutter-sm" style="padding-top: 0">
      <q-toolbar-title v-if="mode === 'create'" class="q-pa-md"> 글작성 </q-toolbar-title>
      <q-toolbar-title v-else class="q-pa-md"> 글수정 </q-toolbar-title>

      <q-input outlined v-model="form.title" label="제목" :dense="dense" />

      <q-editor
        class="q-mb-md"
        v-model="form.content"
        label="내용"
        :definitions="{
          bold: { label: 'Bold', icon: null, tip: 'My bold tooltip' },
        }"
      />

      <q-btn
        v-if="mode === 'create'"
        class="float-right q-mb-md"
        color="primary"
        label="등록"
        @click="onSubmit"
      />

      <q-btn v-else class="float-right q-mb-md" color="primary" label="수정" @click="onEdit" />
    </q-page-container>
  </q-page>
</template>

<script setup>
import { getBoard, patchBoard, postBoard } from 'src/api'
import { onMounted, onUnmounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
const dense = ref(true)

// 페이지 파라미터
const route = useRoute()
// 페이지 이동
const router = useRouter()
// 페이지 모드
const mode = route.params.id ? 'edit' : 'create'
// 페이지 아이디
const pageId = route.params.id

// 제목, 내용 데이터
const form = ref({
  title: '',
  content: '',
})

// 게시글 등록 처리 함수
const onSubmit = () => {
  postBoard(form.value).then((responseBody) => {
    if (!responseBody) return

    const { code } = responseBody
    if (code === 'SU') {
      router.push({ path: '/' })
    }
  })
}

// 게시글 수정 처리 함수
const onEdit = () => {
  patchBoard(pageId, form.value).then((responseBody) => {
    if (!responseBody) return

    const { code } = responseBody
    if (code === 'SU') {
      router.replace({ path: `/detail/${pageId}` })
    }
  })
}

// 게시글 입력 상태 초기화 함수
const reset = () => {
  form.value.title = ''
  form.value.content = ''
}

// 게시글 상세보기 처리 함수
const getBoardDetail = () => {
  if (mode === 'edit') {
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

onMounted(() => {
  reset()
  getBoardDetail()
})
onUnmounted(() => {
  reset()
  console.log('onUnmounted')
})
</script>
