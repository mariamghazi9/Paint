package quadcore.paintproject.paint.model.app;


public abstract class ClosedShape extends Shape {

    private final int[] fillColor;

    protected ClosedShape(String type) {
        super(type);
        this.fillColor = new int[3];
        fillColor[0] = 255;
        fillColor[1] = 255;
        fillColor[2] = 255;
    }

    public int[] getFillColor() {
        return fillColor;
    }

    public void setFillColor(int r, int g, int b) {
        this.fillColor[0] = r;
        this.fillColor[1] = g;
        this.fillColor[2] = b;
    }

}
