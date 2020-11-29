package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;

public class Circle extends Shape {

    private float radius;

    protected Circle() {
        super("circle");
        this.radius = 1;
    }

    public void setLocation(int x, int y) {
        super.setPoint(x, y);
    }

    @JsonIgnore
    public Point getCenter() {
        return super.getPoint();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
