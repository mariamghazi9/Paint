package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.awt.*;

public class Triangle extends Shape {

    private final Point[] points;


    protected Triangle() {
        super("triangle");
        this.points = new Point[]{new Point(2, 1), new Point(1, 2)};
    }

    public void setLocation(Point[] points) {
        super.setPoint(points[0].x, points[0].y);
        this.points[0] = points[1];
        this.points[1] = points[2];
    }

    @JsonGetter("points")
    private int[] getPointsAsArray() {
        return new int[]{super.getPoint().x,super.getPoint().y, points[0].x, points[0].y, points[1].x, points[1].y};
    }

    @JsonSetter("points")
    private void setPointsAsArray(int[] arr) {
        super.setPoint(arr[0], arr[1]);
        points[0].setLocation(arr[2], arr[3]);
        points[1].setLocation(arr[4], arr[5]);
    }

}
