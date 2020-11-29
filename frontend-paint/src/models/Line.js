class Line{
    constructor(p1,p2,fill)
    {
        this.p1=p1;
        this.p2=p2;
        this.fill=fill;
        this.x=0;
        this.y=0;
        
    }

     draw(context,currentApp)
     {
        if (context === currentApp.gctx) {
            context.fillStyle = "black";
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
     
        
        
    currentApp.selectionHandles[0].x = this.p1.x+this.x-3;
    currentApp.selectionHandles[0].y = this.p1.y+this.y-3;
    currentApp.selectionHandles[1].x = this.p2.x+this.x-3;
    currentApp.selectionHandles[1].y = this.p2.y+this.y-3;
    for (var i = 0; i < 2; i++) {
        var cur = currentApp.selectionHandles[i];
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
         case 0:
             {
                this.p1.x=currentApp.mouse_x;
                this.p1.y=currentApp.mouse_y;  
                break;
             }
        case 1:
            {
                this.p2.x=currentApp.mouse_x;
                this.p2.y=currentApp.mouse_y;  
             }

      }
    }

}
export default Line;
