<template>
  <div>
    <v-container fill-height align-center justify-center class="myContainer">
      <v-card
        height="600"
        width="800"
        justify="center"
        style="border: blueviolet"
      >
        <canvas
          id="canvas"
          width="800"
          height="600"
          v-bind:style="{ cursor: selectedCursor }"
        >
          This text is displayed if your browser does not support HTML5 Canvas.
        </canvas>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import Circle from "../models/Circle";
import Rectangle from "../models/Rectangle";
import Triangle from "../models/Triangle";
import Ellipse from "../models/Ellipse";
import Line from "../models/Line";
import Square from "../models/Square";
import Service from "../service/PaintService";
import Point from "../models/Point";
import UndoHandler from "../service/UndoHandler";
export default {
  name: "Canvas",
  data() {
    return {
      colorChosen: "",
      //color chosen from colorpicker
      fill: false,
      //check if fill button is clicked
      toolbarFlag: -1,
      //to save the flag coming from toolbar to choose the shape to be drawn
      shapes: [],
      // Array to carry the selection handles (drag boxes) for each shape selection
      selectionHandles: [],
      // Hold canvas information
      canvas: null,
      ctx: null,
      WIDTH: 0,
      HEIGHT: 0,
      // Interval at which the canvas is updated and redrawn again in milliseconds
      INTERVAL: 20,
      // Flags for current selection mode (drag the shape or resizing it)
      isDrag: false,
      isResizeDrag: false,
      // The number of selection handle which the mouse is over it
      expectResize: -1,
      // Mouse Coordinates
      mouse_x: 0,
      mouse_y: 0,
      // Redraw flag
      // when set to true, the canvas will redraw everything
      // invalidate() just sets this to false right now
      // we want to call invalidate() whenever we make a change
      canvasValid: false,
      selectedShape: null,
      // The following are the attributes of the selection border and corners
      selectionColor: "#CC0000",
      selectionWidth: 2,
      selectionBoxColor: "darkred", // New for selection boxes
      selectionBoxSize: 6,
      // Fake Canvas and CanvasContext
      ghostcanvas: null,
      gctx: null,
      // since we can drag from anywhere in a node
      // instead of just its x/y corner, we need to save
      // the offset of the mouse when we start dragging.
      offset_x: 0,
      offset_y: 0,
      // Padding and border style widths for mouse offsets to maintain our calculations on click
      stylePaddingLeft: 0,
      stylePaddingTop: 0,
      styleBorderLeft: 0,
      styleBorderTop: 0,
      selectedCursor: "auto"
    };
  },
  created() {
    Service.createCanvas();
  },
  mounted() {
    // Canvas initialization
    this.canvas = document.getElementById("canvas");
    this.HEIGHT = this.canvas.height;
    this.WIDTH = this.canvas.width;
    this.ctx = this.canvas.getContext("2d");
    this.ghostcanvas = document.createElement("canvas");
    this.ghostcanvas.height = this.HEIGHT;
    this.ghostcanvas.width = this.WIDTH;
    this.gctx = this.ghostcanvas.getContext("2d");
    //fixes a problem where double clicking causes text to get selected on the canvas
    this.canvas.onselectstart = function() {
      return false;
    };
    // fixes mouse co-ordinate problems when there's a border or padding
    // see getMouse for more detail
    if (document.defaultView && document.defaultView.getComputedStyle) {
      this.stylePaddingLeft =
        parseInt(
          document.defaultView.getComputedStyle(this.canvas, null)[
            "paddingLeft"
          ],
          10
        ) || 0;
      this.stylePaddingTop =
        parseInt(
          document.defaultView.getComputedStyle(this.canvas, null)[
            "paddingTop"
          ],
          10
        ) || 0;
      this.styleBorderLeft =
        parseInt(
          document.defaultView.getComputedStyle(this.canvas, null)[
            "borderLeftWidth"
          ],
          10
        ) || 0;
      this.styleBorderTop =
        parseInt(
          document.defaultView.getComputedStyle(this.canvas, null)[
            "borderTopWidth"
          ],
          10
        ) || 0;
    }
    // make mainDraw() fire every INTERVAL milliseconds
    setInterval(this.mainDraw, this.INTERVAL);
    // set our events. Up and down are for dragging,
    this.canvas.onmousedown = this.mouseDown;
    this.canvas.onmouseup = this.mouseUp;
    this.canvas.ondblclick = this.addShape;
    this.canvas.onmousemove = this.mouseMove;
    // set up the selection handle boxes
    for (var i = 0; i < 8; i++) {
      var rect = new Rectangle();
      this.selectionHandles.push(rect);
    }
    this.$root.$on("flag", flag => {
      this.toolbarFlag = flag;
    });
    this.$root.$on("undoFlag", undoFlag => {
      this.undoRedo(undoFlag);
    });
    this.$root.$on("isFill", isFill => {
      this.fill = isFill;
    });
    this.$root.$on("color", color => {
      this.colorChosen = color;
    });
    this.$root.$on("copy", () => {
      this.copy();
    });
    this.$root.$on("delete", () => {
      this.delete();
    });
    this.$root.$on("loaded", data => {
      this.loadShapes(data);
    });
    this.$root.$on("new", () => {
      Service.createCanvas();
      this.cleardata();
    });
  },
  methods: {
    cleardata() {
      this.isDrag = false;
      this.isResizeDrag = false;
      this.expectResize = -1;
      this.shapes = [];
      this.selectedShape = null;
      this.invalidate();
    },
    loadShapes(loadedData) {
      this.cleardata();
      for (var i = 0; i < loadedData.length; i++) {
        const shape = loadedData[i];
        this.shapes.push(this.deserializeShape(shape, shape.name));
      }
    },
    deserializeShape(shape, name) {
      var point = shape.point;
      switch (name) {
        case "circle": {
          var circle = new Circle();
          circle.fill = shape.color;
          circle.r = shape.radius;
          circle.x = point[0];
          circle.y = point[1];
          circle.id = shape.id;
          return circle;
        }
        case "rectangle": {
          var rectangle = new Rectangle();
          rectangle.fill = shape.color;
          rectangle.w = shape.width;
          rectangle.h = shape.height;
          point = shape.point;
          rectangle.x = point[0];
          rectangle.y = point[1];
          rectangle.id = shape.id;
          return rectangle;
        }
        case "line": {
          var line = new Line();
          line.fill = shape.color;
          p1 = shape.point;
          p2 = shape.end;
          var start = new Point(p1[0], p1[1]);
          var end = new Point(p2[0], p2[1]);
          line.p1 = start;
          line.p2 = end;
          line.id = shape.id;
          return line;
        }

        case "ellipse": {
          var ellipse = new Ellipse();
          ellipse.fill = shape.color;
          ellipse.radius_X = shape.radiusX;
          ellipse.radius_Y = shape.radiusY;
          point = shape.point;
          ellipse.x = point[0];
          ellipse.y = point[1];
          ellipse.id = shape.id;
          return ellipse;
        }

        case "triangle": {
          var triangle = new Triangle();
          triangle.fill = shape.color;
          var points = shape.points;
          var p1 = new Point(points[0], points[1]);
          var p2 = new Point(points[2], points[3]);
          var p3 = new Point(points[4], points[5]);
          triangle.p1 = p1;
          triangle.p2 = p2;
          triangle.p3 = p3;
          triangle.id = shape.id;
          return triangle;
        }

        case "square": {
          var square = new Square();
          square.fill = shape.color;
          square.width = shape.length;
          point = shape.point;
          square.x = point[0];
          square.y = point[1];
          square.id = shape.id;
          return square;
        }
      }
    },
    addRect(x, y, w, h, fill) {
      var rect = new Rectangle();
      rect.x = x;
      rect.y = y;
      rect.w = w;
      rect.h = h;
      rect.fill = fill;
      this.shapes.push(rect);
      this.invalidate();
      return rect;
    },
    addCircle(x, y, r, fill) {
      var cir = new Circle();
      cir.x = x;
      cir.y = y;
      cir.r = r;
      cir.fill = fill;
      this.shapes.push(cir);
      this.invalidate();
      return cir;
    },
    addEllipse(x, y, rx, ry, fill) {
      var ell = new Ellipse();
      ell.x = x;
      ell.y = y;
      ell.radius_x = rx;
      ell.radius_y = ry;
      ell.fill = fill;
      this.shapes.push(ell);
      this.invalidate();
      return ell;
    },
    addTriangle(p1, p2, p3, fill) {
      var triangle = new Triangle(p1, p2, p3, fill);
      this.shapes.push(triangle);
      this.invalidate();
      return triangle;
    },
    addLine(p1, p2, fill) {
      var line = new Line(p1, p2, fill);
      this.shapes.push(line);
      this.invalidate();
      return line;
    },
    addSquare(x, y, length, fill) {
      var square = new Square(x, y, length, fill);
      this.shapes.push(square);
      this.invalidate();
      return square;
    },
    //wipes the canvas context
    clear(c) {
      c.clearRect(0, 0, this.WIDTH, this.HEIGHT);
    },
    // Main draw loop.
    // While draw is called as often as the INTERVAL variable demands,
    // It only ever does something if the canvas gets invalidated by our code
    mainDraw() {
      if (this.canvasValid === false) {
        this.clear(this.ctx);
        // draw all boxes
        var l = this.shapes.length;
        for (var i = 0; i < l; i++) {
          this.shapes[i].draw(this.ctx, this);
        }
        this.canvasValid = true;
      }
    },
    // Happens when the mouse is moving inside the canvas
    mouseMove(e) {
      if (this.isDrag) {
        this.getMouse(e);
        this.selectedShape.x = this.mouse_x - this.offset_x;
        this.selectedShape.y = this.mouse_y - this.offset_y;
        // something is changing position so we better invalidate the canvas!
        this.invalidate();
      } else if (this.isResizeDrag) {
        // 0  1  2
        // 3     4
        // 5  6  7
        this.selectedShape.resize(this, this.expectResize);
        this.invalidate();
      }
      this.getMouse(e);
      // if there's a selection see if we grabbed one of the selection handles
      if (this.selectedShape !== null && !this.isResizeDrag) {
        for (var i = 0; i < 8; i++) {
          // 0  1  2
          // 3     4
          // 5  6  7
          var cur = this.selectionHandles[i];
          // we dont need to use the ghost context because
          // selection handles will always be rectangles
          if (
            this.mouse_x >= cur.x &&
            this.mouse_x <= cur.x + this.selectionBoxSize &&
            this.mouse_y >= cur.y &&
            this.mouse_y <= cur.y + this.selectionBoxSize
          ) {
            // we found one!
            this.expectResize = i;
            this.invalidate();
            switch (i) {
              case 0:
                this.selectedCursor = "n-resize";
                break;
              case 1:
                this.selectedCursor = "e-resize";
                break;
              case 2:
                this.selectedCursor = "s-resize";
                break;
              case 3:
                this.selectedCursor = "w-resize";
                break;
              case 4:
                this.selectedCursor = "nw-resize";
                break;
              case 5:
                this.selectedCursor = "ne-resize";
                break;
              case 6:
                this.selectedCursor = "se-resize";
                break;
              case 7:
                this.selectedCursor = "sw-resize";
                break;
            }
            return;
          }
        }
        // not over a selection box, return to normal
        this.isResizeDrag = false;
        this.expectResize = -1;
        this.selectedCursor = "auto";
      }
    },
    // Happens when the mouse is clicked in the canvas
    mouseDown(e) {
      this.getMouse(e);
      //we are over a selection box
      if (this.expectResize !== -1) {
        this.isResizeDrag = true;
        return;
      }
      this.clear(this.gctx);
      var l = this.shapes.length;
      for (var i = l - 1; i >= 0; i--) {
        // draw shape onto ghost context
        this.shapes[i].draw(this.gctx, "black");
        // get image data at the mouse x,y pixel
        var imageData = this.gctx.getImageData(
          this.mouse_x,
          this.mouse_y,
          1,
          1
        );
        // if the mouse pixel exists, select and break
        if (imageData.data[3] > 0) {
          this.selectedShape = this.shapes[i];
          this.offset_x = this.mouse_x - this.selectedShape.x;
          this.offset_y = this.mouse_y - this.selectedShape.y;
          this.selectedShape.x = this.mouse_x - this.offset_x;
          this.selectedShape.y = this.mouse_y - this.offset_y;
          this.isDrag = true;
          if (this.fill && this.selectedShape != null) {
            this.selectedShape.fill = this.colorChosen;
          }
          this.invalidate();
          this.clear(this.gctx);
          return;
        }
      }
      // havent returned means we have selected nothing
      this.selectedShape = null;
      // clear the ghost canvas for next time
      this.clear(this.gctx);
      // invalidate because we might need the selection border to disappear
      this.invalidate();
    },
    mouseUp() {
      if (this.isDrag || this.isResizeDrag) {
        Service.editShape(this.selectedShape);
      }
      this.isDrag = false;
      this.isResizeDrag = false;
      this.expectResize = -1;
    },
    addShape(e) {
      this.getMouse(e);
      var addedShape;

      switch (this.toolbarFlag) {
        case 1:
          addedShape = this.addLine(
            new Point(this.mouse_x - 30, this.mouse_y),
            new Point(this.mouse_x + 30, this.mouse_y),
            this.colorChosen
          );
          break;
        case 2:
          addedShape = this.addRect(
            this.mouse_x - 30,
            this.mouse_y - 20,
            60,
            40,
            this.colorChosen
          );
          break;
        case 3:
          addedShape = this.addCircle(
            this.mouse_x,
            this.mouse_y,
            40,
            this.colorChosen
          );
          break;
        case 4:
          addedShape = this.addSquare(
            this.mouse_x - 20,
            this.mouse_y - 20,
            40,
            this.colorChosen
          );
          break;
        case 5:
          addedShape = this.addTriangle(
            new Point(this.mouse_x - 30, this.mouse_y + 30),
            new Point(this.mouse_x + 30, this.mouse_y + 30),
            new Point(this.mouse_x, this.mouse_y - 50),
            this.colorChosen
          );
          break;
        case 6:
          addedShape = this.addEllipse(
            this.mouse_x,
            this.mouse_y,
            60,
            40,
            this.colorChosen
          );
          break;
      }
      if (this.toolbarFlag !== -1) {
        Service.addShape(addedShape).then(Response => {
          addedShape.id = Number(Response.data);
        });
      }
    },
    invalidate() {
      this.canvasValid = false;
    },
    // Sets mouse_x,mouse_y to the mouse position relative to the canvas
    // unfortunately this can be tricky, we have to worry about padding and borders
    getMouse(e) {
      var element = this.canvas,
        offsetX = 0,
        offsetY = 0;
      if (element.offsetParent) {
        do {
          offsetX += element.offsetLeft;
          offsetY += element.offsetTop;
        } while ((element = element.offsetParent));
      }
      // Add padding and border style widths to offset
      offsetX += this.stylePaddingLeft;
      offsetY += this.stylePaddingTop;
      offsetX += this.styleBorderLeft;
      offsetY += this.styleBorderTop;
      this.mouse_x = e.pageX - offsetX;
      this.mouse_y = e.pageY - offsetY;
    },
    undoRedo(isUndo) {
      Service.undoRedo(isUndo)
        .then(Response => {
          switch (Response.data["type"]) {
            case "DELETE":
              UndoHandler.undoByDelete(Response.data["shape"]["id"], this);
              break;
            case "EDIT":
              UndoHandler.undoByEditing(Response.data["shape"], this);
              break;
            case "ADD":
              UndoHandler.undoByAdding(
                Response.data["shape"]["type"],
                Response.data["shape"],
                this
              );
              break;
          }
          this.invalidate();
        })
        .catch(() => {
          console.log("No more");
        });
    },
    copy() {
      if (this.selectedShape != null) {
        var copiedShape = this.selectedShape.clone();
        Service.addShape(copiedShape).then(Response => {
          copiedShape.id = Number(Response.data);
          this.shapes.push(copiedShape);
          this.invalidate();
        });
      }
    },
    delete() {
      if (this.selectedShape != null) {
        let l = this.shapes.length;
        for (var i = l - 1; i >= 0; i--) {
          if (this.shapes[i] === this.selectedShape) {
            this.shapes.splice(i, i + 1);
            break;
          }
        }
        Service.deleteShape(this.selectedShape.id).then(() => {
          this.selectedShape = null;
          this.invalidate();
        });
      }
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #42b983;
}
.myContainer {
  margin-top: -20px;
}
</style>
