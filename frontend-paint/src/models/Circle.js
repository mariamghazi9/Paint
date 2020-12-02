class Circle {
  constructor() {
    this.x = 0;
    this.y = 0;
    // default radius?
    this.r = 1;
    this.fill = "#844484";
    this.type = "circle";
    this.id = -1;
  }
  // mainDraw() will call this with the normal canvas
  // myDown will call this with the ghost canvas with 'black'
  draw(context, currentApp) {
    if (context === currentApp.gctx) {
      context.fillStyle = "black"; // always want black for the ghost canvas
    } else {
      context.fillStyle = this.fill;
    }

    // We can skip the drawing of elements that have moved off the screen:
    if (this.x > currentApp.WIDTH || this.y > currentApp.HEIGHT) return;
    if (this.x + this.w < 0 || this.y + this.h < 0) return;
    context.beginPath();
    context.arc(this.x, this.y, this.r, 0, 2 * Math.PI);
    context.fill();
    // draw selection
    // this is a stroke along the box and also 8 new selection handles
    if (currentApp.selectedShape === this) {
      context.strokeStyle = currentApp.selectionColor;
      context.lineWidth = currentApp.selectionWidth;
      context.arc(this.x, this.y, this.r, 0, 2 * Math.PI);
      context.stroke();

      // draw the boxes

      var half = currentApp.selectionBoxSize / 2;

      //    0
      // 3     1
      //    2

      currentApp.selectionHandles[0].x = this.x - half;
      currentApp.selectionHandles[0].y = this.y - this.r - half;

      currentApp.selectionHandles[1].x = this.x + this.r - half;
      currentApp.selectionHandles[1].y = this.y - half;

      currentApp.selectionHandles[2].x = this.x - half;
      currentApp.selectionHandles[2].y = this.y + this.r - half;

      currentApp.selectionHandles[3].x = this.x - this.r - half;
      currentApp.selectionHandles[3].y = this.y - half;

      for (let index = 0; index < 4; index++) {
        currentApp.selectionHandles[index + 4].x = -1;
        currentApp.selectionHandles[index + 4].y = -1;
      }

      context.fillStyle = currentApp.selectionBoxColor;
      // Draw the tiny boxes on the selected shapes
      for (var i = 0; i < 4; i++) {
        var cur = currentApp.selectionHandles[i];
        context.fillRect(
          cur.x,
          cur.y,
          currentApp.selectionBoxSize,
          currentApp.selectionBoxSize
        );
      }
    }
  }
  resize(currentApp, expectResize) {
    var oldy = currentApp.selectionHandles[expectResize].y;
    var oldx = currentApp.selectionHandles[expectResize].x;
    switch (expectResize) {
      case 0:
        if (oldy - currentApp.mouse_y > -this.r) {
          this.r += oldy - currentApp.mouse_y;
        }
        break;
      case 1:
        if (currentApp.mouse_x - oldx > -this.r) {
          this.r += currentApp.mouse_x - oldx;
        }
        break;
      case 2:
        if (currentApp.mouse_y - oldy > -this.r) {
          this.r += currentApp.mouse_y - oldy;
        }
        break;
      case 3:
        if (oldx - currentApp.mouse_x > -this.r) {
          this.r += oldx - currentApp.mouse_x;
        }
        break;
    }
  } // end draw
  setAttributes(shape) {
    this.x = shape["point"][0];
    this.y = shape["point"][1];
    this.r = shape["radius"];
    this.fill = shape["color"];
    this.id = shape["id"];
  }
  clone() {
    let cir = new Circle();
    cir.x = this.x + 30;
    cir.y = this.y + 30;
    cir.r = this.r;
    cir.fill = this.fill;
    return cir;
  }
}

export default Circle;
