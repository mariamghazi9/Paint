package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;

public class Circle extends ClosedShape {

    private float radius;

    protected Circle() {
        super("circle");
        this.radius = 1;
    }

    public void setLocation(int x, int y, int radius) {
        super.setPoint(x, y);
        this.radius = radius;
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
