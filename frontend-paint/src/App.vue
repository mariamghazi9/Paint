<template id="app">
  <v-app>
    <v-main   style="background-color: #EEE7DF">
      <v-container>
        <Toolbar id="myToolbar"/>
        <Canvas id="myCanvas" />
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Canvas from "./components/Canvas";
import Toolbar from "./components/Toolbar";

export default {
  name: "App",

  components: {
    Toolbar,
    Canvas
  },

  data: () => ({

  })
};
</script>

<style>
#myCanvas {
}
  #myToolbar{
  }
</style>
