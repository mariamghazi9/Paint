package quadcore.paintproject.paint.model.app;

import java.awt.*;

public class Triangle extends ClosedShape {

    private Point[] points;


    protected Triangle() {
        super("triangle");
        this.points = new Point[]{new Point(2, 1), new Point(1, 2)};
    }

    public void setLocation(Point[] points) {
        super.setPoint(points[0].x, points[0].y);
        this.points[0] = points[1];
        this.points[1] = points[2];
    }

    public Point[] getPoints() {
        Point[] arr = new Point[3];
        arr[0] = super.getPoint();
        arr[1] = points[0];
        arr[2] = points[1];
        return arr;
    }

}
