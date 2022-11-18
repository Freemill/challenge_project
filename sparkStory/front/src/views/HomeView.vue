<script setup lang="ts">
import axios from "axios";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const posts = ref([]);

axios.get("/api/posts?page=1&size=5").then((response) => {
  response.data.forEach((r: any) => {
    posts.value.push(r);
  });
});
</script>

<template>

  <img src='../assets/sparking story.png'>
  <ul>
    <li v-for= "post in posts" :key="post.id" style="line-height:0%;">
      <span>
        <div>
          <router-link :to="{ name: 'read', params: { postId : post.id } }">
            {{
              post.title.substr(0, 15)
            }}
          </router-link>
        </div>
      </span>
      <el-divider border-style="dashed" />



    </li>
  </ul>
</template>

<style scoped>
li{
  margin-botton: 1rem;
}
li:last-child{
  margin-bottom: 0;
}
 ul{
   list-style:none;
 }


img{
  width: 410px;
  height: 320px;
  padding-left: 34px;
}
</style>
