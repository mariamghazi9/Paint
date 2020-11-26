package quadcore.paintproject.paint.model.app;

import java.awt.*;

public class Rectangle extends ClosedShape {

    private final Point topLeft;
    private final Point bottomRight;


    protected Rectangle(String type, int id) {
        super(type, id);
        this.topLeft = new Point(0, 0);
        this.bottomRight = new Point(1, 1);
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setVertices(int x1, int y1, int x2, int y2) {
        this.topLeft.setLocation(Math.min(x1, x2), Math.min(y1, y2));
        this.bottomRight.setLocation(Math.max(x1, x2), Math.max(y1, y2));
    }

    public Point getBottomRight() {
        return bottomRight;
    }

}
