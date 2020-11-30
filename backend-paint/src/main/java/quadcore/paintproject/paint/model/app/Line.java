package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.awt.*;

public class Line extends Shape {

    private final Point end;


    protected Line() {
        super("line");
        this.end = new Point(1, 1);
    }


    public void setStart(int x, int y) {
        super.setPoint(x, y);
    }

    public void setEnd(int x, int y) {
        end.setLocation(x, y);
    }

    @JsonGetter("end")
    private int[] getEndAsArr() {
        return new int[]{end.x, end.y};
    }

    @JsonSetter("end")
    private void setEndFromArr(int[] arr) {
        this.end.setLocation(arr[0], arr[1]);
    }
}
