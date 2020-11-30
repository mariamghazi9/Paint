package quadcore.paintproject.paint.api;

import quadcore.paintproject.paint.model.app.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.awt.Point;

public class Service {

    Canvas canvas = App.getInstance().getCanvas();

    protected int addShape(Map<String, Object> shape) {
        String name = (String) shape.get("type");
        Shape myShape = canvas.addShape(name);
        Class<? extends Shape> type = myShape.getClass();

        if (type == Circle.class)
            return setCircle(myShape, shape);
        if (type == Line.class)
            return setLine(myShape, shape);
        if (type == Rectangle.class)
            return setRectangle(myShape, shape);
        if (type == Square.class)
            return setSquare(myShape, shape);
        if (type == Ellipse.class)
            return setEllipse(myShape, shape);
        if (type == Triangle.class)
            return setTriangle(myShape, shape);
        throw new RuntimeException("Invalid Shape Type");
    }

    protected void deleteShape(int id) {
        canvas.removeShape(id);
    }

    protected Action undo() {
        return canvas.undo();
    }

    protected Action redo() {
        return canvas.redo();
    }

    protected void editShape(Map<String, Object> shapeMap) {
        int id = (Integer) shapeMap.get("id");
        Shape shape = canvas.getShapeForEditing(id);
        Class<? extends Shape> type = shape.getClass();

        if (type == Circle.class)
            setCircle(shape, shapeMap);
        else if (type == Line.class)
            setLine(shape, shapeMap);
        else if (type == Rectangle.class)
            setRectangle(shape, shapeMap);
        else if (type == Square.class)
            setSquare(shape, shapeMap);
        else if (type == Ellipse.class)
            setEllipse(shape, shapeMap);
        else if (type == Triangle.class)
            setTriangle(shape, shapeMap);
        else throw new RuntimeException("Invalid Shape Type");
    }

    protected Shape copyShape(int id) {
        return canvas.copyShape(id);
    }

    protected void createCanvas() {
        canvas = App.getInstance().createCanvas();
    }

    protected File save(String type) {
        try {
            return App.getInstance().save(type);
        } catch (IOException e) {
            throw new RuntimeException("File Error");
        }
    }

    protected List<Shape> load(File file) {
        try {
            List<Shape> list = App.getInstance().load(file).getShapes();
            canvas = App.getInstance().getCanvas();
            return list;
        } catch (IOException e) {
            throw new RuntimeException("File Error");
        }
    }

    protected void setCanvasName(String name) {
        canvas.setName(name);
    }


    private int setCircle(Shape myShape, Map<String, Object> shape) {
        Circle circle = (Circle) myShape;
        circle.setLocation((Integer) shape.get("x"), (Integer) shape.get("y"));
        circle.setRadius((Integer) shape.get("r"));
        circle.setColor((String) shape.get("fill"));
        return circle.getId();
    }

    private int setLine(Shape myShape, Map<String, Object> shape) {
        Line line = (Line) myShape;
        int offSetx=(Integer)shape.get("x");
        int offSety=(Integer)shape.get("y");
        Map<String, Integer> p1 = (Map<String, Integer>) shape.get("p1");
        Map<String, Integer> p2 = (Map<String, Integer>) shape.get("p2");
        line.setStart(p1.get("x")+offSetx, p1.get("y")+offSety);
        line.setEnd(p2.get("x")+offSetx, p2.get("y")+offSety);
        line.setColor((String) shape.get("fill"));
        return line.getId();
    }

    private int setRectangle(Shape myShape, Map<String, Object> shape) {
        Rectangle rectangle = (Rectangle) myShape;
        rectangle.setLocation((Integer) shape.get("x"), (Integer) shape.get("y"));
        rectangle.setHeight((Integer) shape.get("h"));
        rectangle.setWidth((Integer) shape.get("w"));
        rectangle.setColor((String) shape.get("fill"));
        return rectangle.getId();
    }

    private int setSquare(Shape myShape, Map<String, Object> shape) {
        Square square = (Square) myShape;
        square.setLocation((Integer) shape.get("x"), (Integer) shape.get("y"));
        square.setLength((Float) shape.get("length"));
        square.setColor((String) shape.get("fill"));
        square.setTopRight((Integer) shape.get("x"), (Integer) shape.get("y"));
        return square.getId();
    }

    private int setEllipse(Shape myShape, Map<String, Object> shape) {
        Ellipse ellipse = (Ellipse) myShape;
        ellipse.setRadiusX((Float) shape.get("radius_x"));
        ellipse.setRadiusY((Float) shape.get("radius_y"));
        ellipse.setLocation((Integer) shape.get("x"), (Integer) shape.get("y"));
        ellipse.setColor((String) shape.get("fill"));
        return ellipse.getId();
    }


    private int setTriangle(Shape myShape, Map<String, Object> shape) {
        Triangle triangle = (Triangle) myShape;
        int offSetx= (Integer) shape.get("x");
        int offSety= (Integer) shape.get("y");
        
        Map<String, Integer> p1 = (Map<String, Integer>) shape.get("p1");
        Map<String, Integer> p2 = (Map<String, Integer>) shape.get("p2");
        Map<String, Integer> p3 = (Map<String, Integer>) shape.get("p3");
        Point[] points = new Point[]{new Point(p1.get("x")+offSetx, p1.get("y")+offSety),
                new Point(p2.get("x")+offSetx, p2.get("y")+offSety),
                new Point(p3.get("x")+offSetx, p3.get("y")+offSety)};
        triangle.setLocation(points);
        triangle.setColor((String) shape.get("fill"));

        return triangle.getId();
    }
}
