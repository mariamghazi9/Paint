import Point from "./Point";
class Line{
    constructor(p1,p2,fill)
    {
        this.p1=p1;
        this.p2=p2;
        this.fill=fill;
        this.x=0;
        this.y=0;
        this.type = "line";
        this.id = -1;
    }

     draw(context,currentApp)
     {
        if (context === currentApp.gctx) {
            context.strokeStyle = "black";
          } else {
            context.strokeStyle = this.fill;
          }
          context.beginPath()
          context.moveTo(this.p1.x+this.x, this.p1.y+this.y);
          context.lineTo(this.p2.x+this.x , this.p2.y+this.y);
          context.stroke();
       
         
          
          if(currentApp.selectedShape==this)
            this.drawSelected(context,currentApp)
          
     }

   
     drawSelected(context,currentApp)
     { context.beginPath()
       context.strokeStyle = currentApp.selectionColor;
        context.lineWidth = currentApp.selectionWidth;
        context.moveTo(this.p1.x+this.x, this.p1.y+this.y);
        context.lineTo(this.p2.x+this.x , this.p2.y+this.y);
        context.stroke()
     
        
        
    currentApp.selectionHandles[4].x = this.p1.x+this.x-3;
    currentApp.selectionHandles[4].y = this.p1.y+this.y-3;
    currentApp.selectionHandles[5].x = this.p2.x+this.x-3;
    currentApp.selectionHandles[5].y = this.p2.y+this.y-3;
    for (var i = 0; i < 2; i++) {
        var cur = currentApp.selectionHandles[i+4];
        context.beginPath()
        context.fillStyle = "#CC0000";
        context.fillRect(
          cur.x,
          cur.y,
          currentApp.selectionBoxSize,
          currentApp.selectionBoxSize
        );
      }
    }
    resize(currentApp,expectResize)
    {
      switch(expectResize)
      {    
         case 4:
        {  
          this.p1.x = currentApp.mouse_x - this.x
          this.p1.y = currentApp.mouse_y - this.y
          break;
        }
        case 5:
        {
          this.p2.x = currentApp.mouse_x - this.x
          this.p2.y = currentApp.mouse_y - this.y
          break;
        }
      }
    }
    setAttributes(shape) {
      this.p1.x=shape["end"][0];
      this.p1.y=shape["end"][1];  
      this.p2.x=shape["point"][0];
      this.p2.y=shape["point"][1];
        
      this.fill=shape["fill"];
      this.x=0;
      this.y=0;
      this.id = shape["id"];
    }
    clone() {
      let l = new Line(new Point(this.p1.x + 30, this.p1.y + 30), new Point(this.p2.x + 30, this.p2.y + 30), this.fill);
      l.x = this.x;
      l.y = this.y; 
      return l;
    }
}
export default Line;
