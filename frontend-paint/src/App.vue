<template id="app">
  <v-app>
    <v-main   style="background-color: #EEE7DF">
      <v-container>
        <LoadModal id ="modal" v-if= "showModal" @close= "showModal = false"/>
        <Toolbar id="myToolbar" @load = "showModal = true"/>
        <Canvas id="myCanvas" />
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Canvas from "./components/Canvas";
import Toolbar from "./components/Toolbar";
import LoadModal from "./components/LoadModal";
export default {
  name: "App",

  components: {
    Toolbar,
    Canvas,
    LoadModal
  },

  data() {
    return {
      showModal: false
    }
  }
};
</script>

<style>
#myCanvas {
}
  #myToolbar{
  }
</style>
