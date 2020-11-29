class Square{
    constructor(x,y,width,fill){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=this.width;
        this.fill=fill;
        this.type = "square";
    }

    draw(context,currentApp)
    {
        if (context === currentApp.gctx) {
            context.fillStyle = "black";
          } else {
            context.fillStyle = this.fill;
          }
            context.fillRect (this.x,this.y,this.width, this.height)
            
          
          if (currentApp.selectedShape === this) {
            context.strokeStyle = currentApp.selectionColor;
            context.lineWidth = currentApp.selectionWidth;
            context.strokeRect(this.x, this.y, this.width, this.height);
            
           
      
            // draw the boxes
      
            var half = currentApp.selectionBoxSize / 2;
      
          
      
            currentApp.selectionHandles[0].x = this.x + this.width / 2 - half;
            currentApp.selectionHandles[0].y = this.y - half;
      
            currentApp.selectionHandles[1].x = this.x + this.width - half;
            currentApp.selectionHandles[1].y = this.y + this.height / 2 - half;
      
            currentApp.selectionHandles[2].x = this.x + this.width / 2 - half;
            currentApp.selectionHandles[2].y = this.y + this.height - half;
      
            currentApp.selectionHandles[3].x = this.x - half;
            currentApp.selectionHandles[3].y = this.y + this.height / 2 - half;
      
           
      
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
                this.width+=oldy-currentApp.mouse_y
               
                this.height=this.width
                break;
              case 1:
                this.width = currentApp.mouse_x - oldx;
                this.height=this.width
                break;
              case 2:
                this.width = currentApp.mouse_y - oldy;
                this.height=this.width
                break;
              case 3:
                this.x = currentApp.mouse_x;
                this.width += oldx - currentApp.mouse_x;
                this.height=this.width
                break;
        
            }
          }
     
  
    
}
export default Square;
