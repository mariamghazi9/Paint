package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.awt.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Square.class, name = "square"),
        @JsonSubTypes.Type(value = Circle.class, name = "circle"),
        @JsonSubTypes.Type(value = Ellipse.class, name = "ellipse"),
        @JsonSubTypes.Type(value = Line.class, name = "line"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Triangle.class, name = "triangle")
})
public abstract class Shape implements Cloneable {

    private String strokeColor;
    private int strokeWeight;
    private String name;
    private int id;
    private final Point point;


    protected Shape(String name) {
        this.strokeColor = "#000000";
        this.strokeWeight = 1;
        this.name = name.toLowerCase();
        point = new Point(0, 0);
        this.id = App.getInstance().getCanvas().createID();
    }

    public Shape copy() {
        Shape shape = null;
        try {
            shape = (Shape) this.clone();
            shape.id = App.getInstance().getCanvas().createID();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return shape;
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

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String color) {
        this.strokeColor = color;
    }

    public int getStrokeWeight() {
        return strokeWeight;
    }

    public void setStrokeWeight(int strokeWeight) {
        this.strokeWeight = strokeWeight;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @JsonGetter("point")
    protected Point getPoint() {
        return point;
    }

    protected void setPoint(int x, int y) {
        this.point.setLocation(x, y);
    }

    public ClosedShape toClosedShape() {
        return (ClosedShape)this;
    }

    public Line toLine() {
        return (Line)this;
    }
}
