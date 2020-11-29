package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;

public class Ellipse extends Shape {

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


    @JsonIgnore
    public Point getCenter() {
        return super.getPoint();
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
