class Triangle {
  constructor(p1, p2, p3, fill) {
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
    this.fill = fill;
    this.x = 0;
    this.y = 0;
    this.type = "triangle";
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
    var oldy = currentApp.selectionHandles[expectResize].y;
    var oldx = currentApp.selectionHandles[expectResize].x;
    switch (expectResize) {
      case 0:
        diff = oldx - currentApp.mouse_x;
        if (diff < 0) this.p1.x += Math.abs(diff);
        else this.p1.x -= diff;
        break;
      case 1:
        diff = oldx - currentApp.mouse_x;
        if (diff < 0) this.p2.x += Math.abs(diff);
        else this.p2.x -= diff;
        break;
      case 2:
        var diff = oldy - currentApp.mouse_y;
        if (diff < 0) this.p3.y += Math.abs(diff);
        else this.p3.y -= diff;
        break;
    }
  }
}
export default Triangle;
