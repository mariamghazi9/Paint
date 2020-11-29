<template>
  <div>
    <v-container>
    
      <v-toolbar dense floating src="../assets/colors.jpg">

        <v-spacer></v-spacer>

        <v-btn icon>
          <v-icon>mdi-file-outline</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-file-upload</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-content-save</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-delete</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-content-copy</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-undo</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-redo</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-ellipse-outline</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-triangle-outline</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-square-outline</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-checkbox-blank-circle-outline</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-rectangle-outline</v-icon>
        </v-btn>

        <v-btn icon>
          <v-img src="../assets/horizontal-line.png" />
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-format-color-fill</v-icon>
        </v-btn>

        <v-menu offset-y>
          <template v-slot:activator="{ on }">
            <v-btn :color="color" v-on="on">
              Color
            </v-btn>
          </template>
          <v-color-picker
            value="#7417BE"
            v-model="color"
            hide-inputs
            show-swatches
          ></v-color-picker>
        </v-menu>
      </v-toolbar>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "Toolbar",
  data() {
    return {
      color: "white",
    };
  },
};
</script>

<style></style>
