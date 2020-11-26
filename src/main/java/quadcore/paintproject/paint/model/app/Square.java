package quadcore.paintproject.paint.model.app;

import java.awt.*;

public class Square extends ClosedShape{

    private final Point topRight;
    private float length;


    protected Square(String type, int id) {
        super(type, id);
        this.topRight = new Point(0, 0);
        this.length = 1;
    }

    public Point getTopRight() {
        return topRight;
    }

    public void setTopRight(int x, int y) {
        this.topRight.setLocation(x, y);
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }
}
