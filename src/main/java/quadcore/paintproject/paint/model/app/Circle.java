package quadcore.paintproject.paint.model.app;

import java.awt.*;

public class Circle extends ClosedShape {

    private final Point center;
    private float radius;

    protected Circle(String type, int id) {
        super(type, id);
        this.center = new Point(0, 0);
        this.radius = 1;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(int x, int y) {
        this.center.setLocation(x, y);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
