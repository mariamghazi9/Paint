package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Ellipse extends Shape {

    private float radiusX;
    private float radiusY;

    protected Ellipse() {
        super("ellipse");
        this.radiusX = 1;
        this.radiusY = 1;
    }

    public void setLocation(int x, int y) {
        super.setPoint(x, y);
    }

    public void setRadiusX(float radiusX) {
        this.radiusX = radiusX;
    }

    public void setRadiusY(float radiusY) {
        this.radiusY = radiusY;
    }

    @JsonGetter("radiusX")
    private float getRadiusX() {
        return radiusX;
    }

    @JsonGetter("radiusY")
    private float getRadiusY() {
        return radiusY;
    }

}
