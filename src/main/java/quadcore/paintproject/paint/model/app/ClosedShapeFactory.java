package quadcore.paintproject.paint.model.app;

public class ClosedShapeFactory extends Factory{
    @Override
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("ellipse")) {
            return new Ellipse(type, super.createID());
        } else if (type.equalsIgnoreCase("circle")) {
            return new Circle(type, super.createID());
        } else if (type.equalsIgnoreCase("triangle")) {
            return new Triangle(type, super.createID());
        } else if (type.equalsIgnoreCase("rectangle")) {
            return new Rectangle(type, super.createID());
        } else if (type.equalsIgnoreCase("square")) {
            return new Square(type, super.createID());
        } else {
            throw new RuntimeException("Invalid Shape Type");
        }
    }
}
