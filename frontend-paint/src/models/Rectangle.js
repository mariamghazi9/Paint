class Rectangle {
  constructor() {
    this.x = 0;
    this.y = 0;
    // default width and height?
    this.w = 1;
    this.h = 1;
    this.fill = "#444444";
    this.type = "rectangle";
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

    context.fillRect(this.x, this.y, this.w, this.h);
    // draw selection
    // this is a stroke along the box and also 8 new selection handles
    if (currentApp.selectedShape === this) {
      context.strokeStyle = currentApp.selectionColor;
      context.lineWidth = currentApp.selectionWidth;
      context.strokeRect(this.x, this.y, this.w, this.h);

      // draw the boxes

      var half = currentApp.selectionBoxSize / 2;

      // 0  1  2
      // 3     4
      // 5  6  7

      // 4  0  5
      // 3     1
      // 7  2  6

      currentApp.selectionHandles[0].x = this.x + this.w / 2 - half;
      currentApp.selectionHandles[0].y = this.y - half;

      currentApp.selectionHandles[1].x = this.x + this.w - half;
      currentApp.selectionHandles[1].y = this.y + this.h / 2 - half;

      currentApp.selectionHandles[2].x = this.x + this.w / 2 - half;
      currentApp.selectionHandles[2].y = this.y + this.h - half;

      currentApp.selectionHandles[3].x = this.x - half;
      currentApp.selectionHandles[3].y = this.y + this.h / 2 - half;

      currentApp.selectionHandles[4].x = this.x - half;
      currentApp.selectionHandles[4].y = this.y - half;

      currentApp.selectionHandles[5].x = this.x + this.w - half;
      currentApp.selectionHandles[5].y = this.y - half;

      currentApp.selectionHandles[6].x = this.x + this.w - half;
      currentApp.selectionHandles[6].y = this.y + this.h - half;

      currentApp.selectionHandles[7].x = this.x - half;
      currentApp.selectionHandles[7].y = this.y + this.h - half;

      context.fillStyle = currentApp.selectionBoxColor;
      // Draw the tiny boxes on the selected shapes
      for (var i = 0; i < 8; i++) {
        var cur = currentApp.selectionHandles[i];
        context.fillRect(
          cur.x,
          cur.y,
          currentApp.selectionBoxSize,
          currentApp.selectionBoxSize
        );
      }
    }
  } // end draw
  resize(currentApp, expectResize) {
    var oldx = this.x;
    var oldy = this.y;
    switch (expectResize) {
      case 0:
        this.y = currentApp.mouse_y;
        this.h += oldy - currentApp.mouse_y;
        break;
      case 1:
        this.w = currentApp.mouse_x - oldx;
        break;
      case 2:
        this.h = currentApp.mouse_y - oldy;
        break;
      case 3:
        this.x = currentApp.mouse_x;
        this.w += oldx - currentApp.mouse_x;
        break;
      case 4:
        this.x = currentApp.mouse_x;
        this.y = currentApp.mouse_y;
        this.w += oldx - currentApp.mouse_x;
        this.h += oldy - currentApp.mouse_y;
        break;
      case 5:
        this.y = currentApp.mouse_y;
        this.w = currentApp.mouse_x - oldx;
        this.h += oldy - currentApp.mouse_y;
        break;
      case 6:
        this.w = currentApp.mouse_x - oldx;
        this.h = currentApp.mouse_y - oldy;
        break;
      case 7:
        this.x = currentApp.mouse_x;
        this.w += oldx - currentApp.mouse_x;
        this.h = currentApp.mouse_y - oldy;
        break;
    }
  }
}

export default Rectangle;
