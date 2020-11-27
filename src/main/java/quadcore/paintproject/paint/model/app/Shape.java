package quadcore.paintproject.paint.model.app;

import java.awt.*;

public abstract class Shape implements Cloneable {

    private Color strokeColor;
    private int strokeWeight;
    private String type;
    private int id;

    protected Shape(String type, int id) {
        this.strokeColor = new Color(0, 0, 0);
        this.strokeWeight = 1;
        this.type = type;
        this.id = id;
    }

    public Shape copy() {
        Shape shape = null;
        try {
            shape = (Shape) this.clone();
            shape.setId(ShapeFactory.createID());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return shape;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int r, int g, int b) {
        this.strokeColor = new Color(r, g, b);
    }

    public int getStrokeWeight() {
        return strokeWeight;
    }

    public void setStrokeWeight(int strokeWeight) {
        this.strokeWeight = strokeWeight;
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected Shape copyWithSameID() {
        Shape shape = null;
        try {
            shape = (Shape) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return shape;
    }
}
