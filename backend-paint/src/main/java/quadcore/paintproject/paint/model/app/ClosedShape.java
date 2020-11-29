package quadcore.paintproject.paint.model.app;


public abstract class ClosedShape extends Shape {

    private String fillColor;

    protected ClosedShape(String type) {
        super(type);
        this.fillColor = "#FFFFFF";
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String color) {
        this.fillColor = color;
    }

}
