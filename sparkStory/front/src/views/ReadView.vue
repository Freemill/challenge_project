<script setup lang = "ts">
import { defineProps, onMounted, ref } from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const props = defineProps({
  postId: {
    type: [Number, String],
    require: true,
  },
});

const post = ref({
  id: 0,
  title: "",
  content: "",
})

const router = useRouter();

const moveToEdit = () => {
  router.push({name : "edit"});
}

onMounted(() => {
  axios.get('/api/posts/' + props.postId).then((response) => {
    console.log(response);
    post.value = response.data;
  });
});
</script>

<template>

  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span style="margin-right: 180px"><h2>{{ post.title }}</h2></span>
        <el-button type="danger" @click="moveToEdit()">Sparking edit</el-button>
      </div>
    </template>
    <div v-for="o in 1" :key="o" class="text item"><h4>{{ post.content }}</h4> </div>
  </el-card>


</template>

<style>
.card-header {
  display: flex;
  justify-content: normal;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 480px;
}
</style>