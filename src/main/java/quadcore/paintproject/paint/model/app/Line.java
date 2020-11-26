package quadcore.paintproject.paint.model.app;

import java.awt.*;

public class Line extends Shape {

    private final Point start;
    private final Point end;


    protected Line(String type, int id) {
        super(type, id);
        this.start = new Point(0, 0);
        this.end = new Point(1, 1);
    }

    public Point getStart() {
        return start;
    }

    public void setStart(int x, int y) {
        this.start.setLocation(x, y);
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(int x, int y) {
        this.end.setLocation(x, y);
    }
}
