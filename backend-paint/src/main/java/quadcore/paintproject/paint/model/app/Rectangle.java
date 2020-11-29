package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;

public class Rectangle extends ClosedShape {

    private final Point bottomRight;


    protected Rectangle() {
        super("rectangle");
        this.bottomRight = new Point(1, 1);
    }

    public void setLocation(int x1, int y1, int x2, int y2) {
        super.setPoint(Math.min(x1, x2), Math.min(y1, y2));
        this.bottomRight.setLocation(Math.max(x1, x2), Math.max(y1, y2));
    }

    @JsonIgnore
    public Point getTopLeft() {
        return super.getPoint();
    }

    public Point getBottomRight() {
        return bottomRight;
    }

}
