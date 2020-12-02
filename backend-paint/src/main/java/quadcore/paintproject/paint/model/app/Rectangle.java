package quadcore.paintproject.paint.model.app;


import com.fasterxml.jackson.annotation.JsonGetter;

public class Rectangle extends Shape {

    private int height;
    private int width;


    protected Rectangle() {
        //super("rectangle");
        height = 1;
        width = 1;
    }

    public void setLocation(int x, int y) {
        super.setPoint(x, y);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @JsonGetter("height")
    private int getHeight() {
        return height;
    }

    @JsonGetter("width")
    private int getWidth() {
        return width;
    }
}
