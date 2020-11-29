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
<<<<<<< HEAD
      
=======
       
>>>>>>> 99fb05293efc2ec62a41a8d8c7a2b7d886f2fe68
         
          
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
             {  var oldx=this.p1.x
                var oldY=this.p1.y
                var diff = oldx - currentApp.mouse_x;
                if (diff < 0) this.p1.x += Math.abs(diff);
                else this.p1.x -= diff;

                 diff = oldY - currentApp.mouse_y;
                if (diff < 0) this.p1.y += Math.abs(diff);
                else this.p1.y -= diff;
                break;
             }
        case 1:
            {
               oldx=this.p2.x
               oldY=this.p2.y
               diff = oldx - currentApp.mouse_x;
              if (diff < 0) this.p2.x += Math.abs(diff);
              else this.p2.x -= diff;

               diff = oldY - currentApp.mouse_y;
              if (diff < 0) this.p2.y += Math.abs(diff);
              else this.p2.y -= diff;
              break;
             }

      }
    }

}
export default Line;
