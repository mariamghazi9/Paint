<template>
  <v-app>
    <v-main>
      <Canvas/>
    </v-main>
  </v-app>
</template>

<script>
import Canvas from "./components/Canvas";
export default {
  name: "App",

  components: {
    Canvas
  },

  data: () => ({
    //
  })
};
</script>
