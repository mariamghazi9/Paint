package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;

public class Rectangle extends ClosedShape {

    private int height;
    private int width;


    protected Rectangle() {
        super("rectangle");
        height = 1;
        width = 1;
    }

    public void setLocation(int x, int y, int h, int w) {
        super.setPoint(x, y);
        this.height = h;
        this.width = w;
    }

    @JsonIgnore
    public Point getTopLeft() {
        return super.getPoint();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
