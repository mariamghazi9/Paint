package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;

public class Square extends ClosedShape {

    private float length;


    protected Square() {
        super("square");
        this.length = 1;
    }

    public void setLocation(int x, int y, float length) {
        super.setPoint(x, y);
        this.length = length;
    }

    @JsonIgnore
    public Point getTopRight() {
        return super.getPoint();
    }

    public void setTopRight(int x, int y) {
        super.setPoint(x, y);
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }
}
