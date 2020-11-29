<template>
  <div>
    <v-container>
      <v-toolbar dense floating src="../assets/colors.jpg">
        <v-spacer />
        <v-btn-toggle
        v-model="value"
        color="dark"
        dense
        group
      >
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-file-outline</v-icon>
            </v-btn>
          </template>
          <span>New Canvas</span>
        </v-tooltip>

        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-file-upload</v-icon>
            </v-btn>
          </template>
          <span>Upload Canvas</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-content-save</v-icon>
            </v-btn>
          </template>
          <span>Save Canvas</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </template>
          <span>Delete Canvas</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-content-copy</v-icon>
            </v-btn>
          </template>
          <span>Copy</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-undo</v-icon>
            </v-btn>
          </template>
          <span>Undo</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-redo</v-icon>
            </v-btn>
          </template>
          <span>Redo</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-ellipse-outline</v-icon>
            </v-btn>
          </template>
          <span>Ellipse</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-triangle-outline</v-icon>
            </v-btn>
          </template>
          <span>Triangle</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-square-outline</v-icon>
            </v-btn>
          </template>
          <span>Square</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-checkbox-blank-circle-outline</v-icon>
            </v-btn>
          </template>
          <span>Circle</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-rectangle-outline</v-icon>
            </v-btn>
          </template>
          <span>Rectangle</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-img src="../assets/horizontal-line.png" />
            </v-btn>
          </template>
          <span>Line</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-format-color-fill</v-icon>
            </v-btn>
          </template>
          <span>Fill</span>
        </v-tooltip>
        </v-btn-toggle>
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
          />
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
      value:""
    };
  }
};
</script>

<style />
