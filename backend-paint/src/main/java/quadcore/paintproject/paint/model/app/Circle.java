package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonGetter;


public class Circle extends Shape {

    private float radius;

    protected Circle() {
        //super("circle");
        this.radius = 1;
    }

    public void setLocation(int x, int y) {
        super.setPoint(x, y);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @JsonGetter("radius")
    private float getRadius() {
        return radius;
    }
}
