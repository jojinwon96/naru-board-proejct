<template>
  <q-layout view="lHh Lpr lFf" style="height: 500px; min-height: 500px; max-height: 500px">
    <q-header>
      <q-toolbar class="bg-blue text-white shadow-2 rounded-borders main-header cursor-pointer">
        <q-icon name="home" size="md" @click="goHome" />
        <q-toolbar-title class="text-bold">Main Header</q-toolbar-title>
      </q-toolbar>
    </q-header>

    <div class="row q-mx-auto q-mt-md q-gutter-sm" style="max-width: 1400px; margin-top: 60px">
      <div class="col-2 column items-center shadow-2 q-pa-md" style="height: 150px">
        <div class="col">
          <q-btn class="text-h7 text-bold" color="primary" style="width: 200px" @click="goWrite">
            글작성
          </q-btn>
        </div>
        <div class="col">One of three rows</div>
      </div>
      <div class="col shadow-2">
        <q-page-container style="max-height: 860px; padding-top: 0px">
          <router-view />
        </q-page-container>
      </div>
    </div>
  </q-layout>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goHome = () => {
  router.replace({ path: '/' })
}

const goWrite = () => {
  router.replace({ path: '/write' })
}
</script>

<style lang="scss">
.main-header {
  padding: 0 20px;
}
</style>
