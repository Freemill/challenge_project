<script setup lang="ts">
import {ref} from "vue";

import axios from "axios";
import {useRouter} from "vue-router";

const title = ref("")
const content = ref("")
const router = useRouter()

const write = function(){
  axios.post("/api/posts",  {
    title : title.value,
    content : content.value
  })
  .then(() => {
    router.replace({name: "home"});
  })
}
</script>

<template>
  <div>
    <el-input v-model="title" placeholder="제목을 입력해주세요" style=" margin-left: 10px; width:455px;"/>
  </div>

  <div class = "mt-2">
    <el-input v-model="content" type="textarea" rows="15" style="margin-left: 10px; width:455px;"/>
  </div>

  <div class="mt-2">
    <el-button type="primary" @click="write()" style="margin-left: 10px;">Sparking!</el-button>
  </div>
</template>

<style>

</style>