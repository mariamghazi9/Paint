package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.awt.*;
import java.io.*;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Square.class, name = "square"),
        @JsonSubTypes.Type(value = Circle.class, name = "circle"),
        @JsonSubTypes.Type(value = Ellipse.class, name = "ellipse"),
        @JsonSubTypes.Type(value = Line.class, name = "line"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Triangle.class, name = "triangle")
})
public abstract class Shape implements Serializable {

    private String color;
    private final String name;
    private int id;
    private final Point point;


    protected Shape(String name) {
        this.color = "#FFFFFF";
        this.name = name.toLowerCase();
        point = new Point(0, 0);
        this.id = App.getInstance().getCanvas().createID();
    }

    public Shape copy() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            Shape shape = (Shape) objInputStream.readObject();
            shape.id = App.getInstance().getCanvas().createID();
            return shape;
        }
        catch (Exception e) {
            throw new RuntimeException("Error Copying");
        }
    }

    protected Shape copyWithSameID() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return (Shape) objInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Error Copying");
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    protected Point getPoint() {
        return point;
    }

    protected void setPoint(int x, int y) {
        this.point.setLocation(x, y);
    }

    @JsonGetter("name")
    private String getName() {
        return name;
    }

    @JsonGetter("point")
    public int[] getPointAsArr() {
        return new int[]{point.x, point.y};
    }

    @JsonSetter("point")
    private void setPointFromArr(int[] arr) {
        this.point.setLocation(arr[0], arr[1]);
    }
}
