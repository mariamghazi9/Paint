import Point from "./Point";
class Triangle {
  constructor(p1, p2, p3, fill) {
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
    this.fill = fill;
    this.x = 0;
    this.y = 0;
    this.type = "triangle";
    this.id = -1;
  }
  draw(context, currentApp) {
    if (context === currentApp.gctx) {
      context.fillStyle = "black";
    } else {
      context.fillStyle = this.fill;
    }

    // var maxWidth=currentApp.WIDTH
    // var maxHeight=currentApp.HEIGHT
    //if(!(this.p1.isInRange(maxWidth,maxHeight)&&this.p2.isInRange(maxWidth,maxHeight)&& this.p3.isInRange(maxWidth,maxHeight))) return;
    context.beginPath();
    context.moveTo(this.p1.x + this.x, this.p1.y + this.y);
    context.lineTo(this.p2.x + this.x, this.p2.y + this.y);
    context.lineTo(this.p3.x + this.x, this.p3.y + this.y);
    context.closePath();
    context.fill();

    if (currentApp.selectedShape === this)
      this.drawSelected(context, currentApp);
  }

  drawSelected(context, currentApp) {
    context.strokeStyle = currentApp.selectionColor;
    context.lineWidth = currentApp.selectionWidth;
    context.beginPath();
    context.moveTo(this.p1.x + this.x, this.p1.y + this.y);
    context.lineTo(this.p2.x + this.x, this.p2.y + this.y);
    context.lineTo(this.p3.x + this.x, this.p3.y + this.y);
    context.closePath();
    context.stroke();

    /*
          0

     1          2
     
     */

    currentApp.selectionHandles[0].x = this.p1.x + this.x - 3;
    currentApp.selectionHandles[0].y = this.p1.y + this.y - 3;

    currentApp.selectionHandles[1].x = this.p2.x + this.x - 3;
    currentApp.selectionHandles[1].y = this.p2.y + this.y - 3;

    currentApp.selectionHandles[2].x = this.p3.x + this.x - 3;
    currentApp.selectionHandles[2].y = this.p3.y + this.y - 3;

    /*for (let index = 0; index < 3; index++) {
        currentApp.selectionHandles[index + 3].x = -1;
        currentApp.selectionHandles[index + 3].y = -1;
        
    }*/
    for (var i = 0; i < 3; i++) {
      var cur = currentApp.selectionHandles[i];
      context.fillStyle = "#CC0000";
      context.fillRect(
        cur.x,
        cur.y,
        currentApp.selectionBoxSize,
        currentApp.selectionBoxSize
      );
    }
  }
  /*to do check bounds of app*/
  resize(currentApp, expectResize) {
    //var oldy = currentApp.selectionHandles[expectResize].y;
    //var oldx = currentApp.selectionHandles[expectResize].x;
    switch (expectResize) {
      case 0:{
        this.p1.x = currentApp.mouse_x - this.x
        this.p1.y = currentApp.mouse_y - this.y
        break;
      }
      case 1:
        this.p2.x = currentApp.mouse_x - this.x
        this.p2.y = currentApp.mouse_y - this.y
        break;
      case 2:
        this.p3.x = currentApp.mouse_x - this.x
        this.p3.y = currentApp.mouse_y - this.y
        break;
    }
  }
  setAttributes(shape) {
    this.p1.x=shape["points"][0];
    this.p1.y=shape["points"][1];
    this.p2.x=shape["points"][2];
    this.p2.y=shape["points"][3];
    this.p3.x=shape["points"][4];
    this.p3.y=shape["points"][5];
    this.fill=shape["color"];
    this.x=0;
    this.y=0;
    this.id = shape["id"];
  }
  clone() {
    let tri = new Triangle(new Point(this.p1.x, this.p1.y), new Point(this.p2.x, this.p2.y), new Point(this.p3.x, this.p3.y), this.fill);
    tri.x = this.x + 30;
    tri.y = this.y + 30;
    return tri;
  }
}

export default Triangle;
