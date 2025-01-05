<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar class="bg-blue text-white shadow-2 rounded-borders main-header">
        <q-toolbar-title>
          게시판
        </q-toolbar-title>
        <!-- <q-space />
        <q-tabs v-model="tab" shrink>
          <q-tab name="login" label="로그인" />
          <q-tab name="write" label="글쓰기" />
          <q-tab name="list" label="목록" />
        </q-tabs> -->
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { onMounted, ref } from 'vue'

const boardList = ref(['board1', 'board2']);

const getBoardList = () => {
  // boardList.value.push....
  boardList.value.forEach((item) => {
    console.log(item);
  })
}

onMounted(() => {
  getBoardList();
})
</script>

<style lang="scss">
.main-header {
  padding: 0 20px;
}
</style>
