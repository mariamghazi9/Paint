package quadcore.paintproject.paint.model.app;

public class ClosedShapeFactory extends ShapeFactory {
    @Override
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("ellipse")) {
            return new Ellipse(type, createID());
        } else if (type.equalsIgnoreCase("circle")) {
            return new Circle(type, createID());
        } else if (type.equalsIgnoreCase("triangle")) {
            return new Triangle(type, createID());
        } else if (type.equalsIgnoreCase("rectangle")) {
            return new Rectangle(type, createID());
        } else if (type.equalsIgnoreCase("square")) {
            return new Square(type, createID());
        } else {
            throw new RuntimeException("Invalid Shape Type");
        }
    }
}
