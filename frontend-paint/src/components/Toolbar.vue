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
      <v-toolbar dense floating src="../assets/colors.jpg" width="800">
        <v-spacer />

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
            <v-btn icon v-on="on">
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
          <v-tooltip bottom>
            <template v-slot:activator="{ on }">
              <v-btn icon v-on="on" @click="fill()">
                <v-icon>mdi-format-color-fill</v-icon>
              </v-btn>
            </template>
            <span>Fill</span>
          </v-tooltip>
        
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
      color: "white",
      value: "",
      fileName: "",
      file: "",
      flag: "",
      nameField: "untitled",
      undoFlag:"",
      isFill:""
    };
  },

  methods: {
    pickFile() {
      this.$refs.file.click();
    },
    onFilePicked(e) {
      const files = e.target.files;
      if (files[0] !== undefined) {
        this.fileName = files[0].name;
        if (this.fileName.lastIndexOf(".") <= 0) {
          return;
        }
        const fr = new FileReader();
        fr.readAsDataURL(files[0]);
        fr.addEventListener("load", () => {
          this.file = files[0]; // this is a file that can be sent to server...
          this.loadCanvas(this.file);
        });
      } else {
        this.fileName = "";
        this.file = "";
      }
    },
    loadCanvas(file) {
      PaintService.load(file);
    },
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
    fill(){
      this.isFill=true;
      this.$root.$emit('isFill',this.isFill);
    },
    getColor(){
      this.$root.$emit('color',this.color);
    },
    save() {
      PaintService.save("xml");
    },
    copy() {
      this.$root.$emit('copy');
    }
  }
};
</script>

<style>
.myContainer {
  margin-bottom: -60px;
  margin-top: -20px;
}
</style>
