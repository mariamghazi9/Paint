class Square{
    constructor(x,y,length,fill){
        this.x=x;
        this.y=y;
        this.length=length;
        this.fill=fill;
        this.type = "square";
        this.id = -1;
    }

    draw(context,currentApp)
    {
      if (context === currentApp.gctx) {
          context.fillStyle = "black";
      } else {
          context.fillStyle = this.fill;
      }
      context.fillRect (this.x,this.y,this.length, this.length)
          
        
      if (currentApp.selectedShape === this) {
        context.strokeStyle = currentApp.selectionColor;
        context.lineWidth = currentApp.selectionWidth;
        context.strokeRect(this.x, this.y, this.length, this.length);
        
        // draw the boxes
        var half = currentApp.selectionBoxSize / 2;
    
        
    
        currentApp.selectionHandles[0].x = this.x + this.length / 2 - half;
        currentApp.selectionHandles[0].y = this.y - half;
    
        currentApp.selectionHandles[1].x = this.x + this.length - half;
        currentApp.selectionHandles[1].y = this.y + this.length / 2 - half;
    
        currentApp.selectionHandles[2].x = this.x + this.length / 2 - half;
        currentApp.selectionHandles[2].y = this.y + this.length - half;
    
        currentApp.selectionHandles[3].x = this.x - half;
        currentApp.selectionHandles[3].y = this.y + this.length / 2 - half;
    
          
    
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
    } // end draw
    resize(currentApp, expectResize) {
      var oldx = this.x;
      var oldy = this.y;
      switch (expectResize) {
        case 0:
          this.y=currentApp.mouse_y;
          this.length +=oldy-currentApp.mouse_y
          break;
        case 1:
          this.length = currentApp.mouse_x - oldx;
          break;
        case 2:
          this.length = currentApp.mouse_y - oldy;
          break;
        case 3:
          this.x = currentApp.mouse_x;
          this.length += oldx - currentApp.mouse_x;
          break;
      }
    }
    setAttributes(shape) {
      this.x = shape["point"][0];
      this.y = shape["point"][1];
      this.length = shape["length"];
      this.fill = shape["color"];
      this.id = shape["id"];
    }
    clone() {
      return new Square(this.x + 30, this.y + 30, this.length, this.fill);
    }
}
export default Square;
