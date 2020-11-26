package quadcore.paintproject.paint.model.app;

import java.awt.*;

public abstract class ClosedShape extends Shape {

    private Color fillColor;

    protected ClosedShape(String type, int id) {
        super(type, id);
        this.fillColor = new Color(255, 255, 255);
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(int r, int g, int b) {
        this.fillColor = new Color(r, g, b);
    }
}
