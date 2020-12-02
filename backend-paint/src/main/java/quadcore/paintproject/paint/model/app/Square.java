package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Square extends Shape {

    private float length;


    protected Square() {
        //super("square");
        this.length = 1;
    }

    public void setLocation(int x, int y) {
        super.setPoint(x, y);
    }

    public void setLength(float length) {
        this.length = length;
    }

    @JsonGetter("length")
    private float getLength() {
        return length;
    }
}
