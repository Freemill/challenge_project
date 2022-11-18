<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
</script>

<template>
  <el-header>
    <el-menu mode="horizontal">
      <RouterLink to="/"><el-menu-item index="1" >Spark Story</el-menu-item></RouterLink>
      <RouterLink to="/write"><el-menu-item index="2">Sparking Story</el-menu-item></RouterLink>
    </el-menu>
  </el-header>

  <RouterView />
</template>

<style>
body{
  margin: 0;
}
a {
  text-decoration: none;
}
</style>
