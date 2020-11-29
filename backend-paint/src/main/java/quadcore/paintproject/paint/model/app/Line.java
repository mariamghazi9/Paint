package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;

public class Line extends Shape {

    private final Point end;


    protected Line(String type) {
        super(type);
        this.end = new Point(1, 1);
    }

    @JsonIgnore
    public Point getStart() {
        return super.getPoint();
    }

    public void setStart(int x, int y) {
        super.setPoint(x, y);
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(int x, int y) {
        this.end.setLocation(x, y);
    }
}
