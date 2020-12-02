<template>
  <div>
    <v-container class="myContainer">
      <v-row justify="center">
        <v-col cols="12" sm="6" md="3">
          <v-text-field
            label="Canvas Name"
            placeholder="Untitled"
            outlined
            class="myContainer"
            @change="setName"
            v-model="nameField"
          ></v-text-field>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-toolbar dense floating src="../assets/colors.jpg">
        <v-spacer />
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="newCanvas">
              <v-icon>mdi-file-outline</v-icon>
            </v-btn>
          </template>
          <span>New Canvas</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="$emit('load')">
              <v-icon>mdi-file-upload</v-icon>
            </v-btn>
          </template>
          <span>Upload Canvas</span>
        </v-tooltip>

        <v-menu offset-y>
          <template #activator="{ on: onMenu }">
            <v-tooltip bottom>
              <template #activator="{ on: onTooltip }">
                <v-btn icon v-on="{ ...onMenu, ...onTooltip }">
                  <v-icon>mdi-content-save</v-icon>
                </v-btn>
              </template>

              <span>Save Canvas</span>
            </v-tooltip>
          </template>

          <v-list>
            <v-list-item @click="saveJSON">JSON</v-list-item>
            <v-list-item @click="saveXML">XML</v-list-item>
          </v-list>
        </v-menu>

        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="del">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </template>
          <span>Delete</span>
        </v-tooltip>

        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="copy">
              <v-icon>mdi-content-copy</v-icon>
            </v-btn>
          </template>
          <span>Copy</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="undo(true)" color="primary">
              <v-icon>mdi-undo</v-icon>
            </v-btn>
          </template>
          <span>Undo</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="undo(false)" color="primary">
              <v-icon>mdi-redo</v-icon>
            </v-btn>
          </template>
          <span>Redo</span>
        </v-tooltip>
        <v-btn-toggle v-model="value" color="dark" dense group>
          <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="setFlag(6)">
              <v-icon>mdi-ellipse-outline</v-icon>
            </v-btn>
          </template>
          <span>Ellipse</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="setFlag(5)">
              <v-icon>mdi-triangle-outline</v-icon>
            </v-btn>
          </template>
          <span>Triangle</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="setFlag(4)">
              <v-icon>mdi-square-outline</v-icon>
            </v-btn>
          </template>
          <span>Square</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="setFlag(3)">
              <v-icon>mdi-checkbox-blank-circle-outline</v-icon>
            </v-btn>
          </template>
          <span>Circle</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }" >
            <v-btn icon v-on="on" @click="setFlag(2)">
              <v-icon>mdi-rectangle-outline</v-icon>
            </v-btn>
          </template>
          <span>Rectangle</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="setFlag(1)">
              <v-img src="../assets/horizontal-line.png" />
            </v-btn>
          </template>
          <span>Line</span>
        </v-tooltip>
            </v-btn-toggle>
        <v-btn-toggle  color="dark" dense group>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }">
              <v-btn icon v-on="on" @click="fill()">
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
            v-bind="getColor()"
            hide-inputs
            show-swatches
          />
        </v-menu>
      </v-toolbar>
    </v-container>
  </div>
</template>

<script>
import PaintService from "../service/PaintService";

export default {
  name: "Toolbar",
  data() {
    return {
      color: "blue",
      value: "",
      fileName: "",
      file: "",
      flag: "",
      nameField: "Untitled",
      undoFlag:"",
      isFill: false
    };
  },

  methods: {
    saveJSON() {
      PaintService.save("json");
    },
    saveXML() {
      PaintService.save("xml");
    },
    setName() {
      if (this.nameField === "") this.nameField = "Untitled";
      PaintService.setCanvasName(this.nameField);
    },

    setFlag(f) {
      this.flag = f;
      this.$root.$emit("flag", this.flag);
    },
    undo(u) {
      this.undoFlag = u;
      this.$root.$emit("undoFlag", this.undoFlag);
    },
    fill() {
      this.isFill = !this.isFill;
      this.$root.$emit("isFill", this.isFill);
    },
    getColor() {
      this.$root.$emit("color", this.color);
    },
    copy() {
      this.$root.$emit("copy");
    },
    del() {
      this.$root.$emit("delete");
    },
    newCanvas() {
      this.$root.$emit("new");
      this.nameField = "Untitled";
    },
    setCanvasName() {
      this.nameField = name;
      this.$root.$emit("setCanvasName");
    }
  },
  mounted() {
    this.$root.$on("nameChanged", newName => {
      this.nameField = newName;
    });
  }
};
</script>

<style>
.myContainer {
  margin-bottom: -60px;
  margin-top: -20px;
}
</style>
