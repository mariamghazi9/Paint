package quadcore.paintproject.paint.model.app;

import java.awt.*;

public class Ellipse extends ClosedShape {

    private final Point center;
    private float radiusX;
    private float radiusY;

    protected Ellipse(String type, int id) {
        super(type, id);
        this.center = new Point(0, 0);
        this.radiusX = 1;
        this.radiusY = 1;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(int x, int y) {
        this.center.setLocation(x, y);
    }

    public float getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(float radiusX) {
        this.radiusX = radiusX;
    }

    public float getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(float radiusY) {
        this.radiusY = radiusY;
    }

}
