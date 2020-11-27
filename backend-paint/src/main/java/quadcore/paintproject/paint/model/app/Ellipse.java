package quadcore.paintproject.paint.model.app;

import java.awt.*;

public class Ellipse extends ClosedShape {

    private float radiusX;
    private float radiusY;

    protected Ellipse() {
        super("ellipse");
        this.radiusX = 1;
        this.radiusY = 1;
    }

    public void setLocation(int x, int y, float radiusX, float radiusY) {
        super.setPoint(x, y);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    public Point getCenter() {
        return super.getPoint();
    }

    public void setCenter(int x, int y) {
        super.setPoint(x, y);
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
