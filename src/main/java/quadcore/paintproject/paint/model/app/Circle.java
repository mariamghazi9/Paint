package quadcore.paintproject.paint.model.app;

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

    public Point getCenter() {
        return super.getPoint();
    }

    public void setCenter(int x, int y) {
        super.setPoint(x, y);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
