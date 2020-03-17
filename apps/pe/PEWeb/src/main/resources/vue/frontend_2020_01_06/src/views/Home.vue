<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <KfwApp msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import KfwApp from '@/components/KfwApp.vue'

export default {
  name: 'home',
  components: {
    KfwApp
  }
}
</script>
