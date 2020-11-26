package quadcore.paintproject.paint.model.app;

import java.awt.*;

public class Triangle extends ClosedShape{

    private Point[] points;


    protected Triangle(String type, int id) {
        super(type, id);
        this.points = new Point[]{new Point(0, 0), new Point(2, 1), new Point(1, 2)};
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }
}
