package quadcore.paintproject.paint.model.app;

public class ClosedShapeFactory extends ShapeFactory {
    @Override
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("ellipse")) {
            return new Ellipse();
        } else if (type.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (type.equalsIgnoreCase("triangle")) {
            return new Triangle();
        } else if (type.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else if (type.equalsIgnoreCase("square")) {
            return new Square();
        } else {
            throw new RuntimeException("Invalid Shape Type");
        }
    }
}
