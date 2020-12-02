package quadcore.paintproject.paint.api;

import quadcore.paintproject.paint.model.app.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.awt.Point;

public class Service {

    Canvas canvas = App.getInstance().getCanvas();


    protected int addShape(Map<String, Object> shape) {
        String name = (String) shape.get("type");
        //System.out.println("Add req");
//            for (Shape s: App.getInstance().getCanvas().getShapes()) {
//                System.out.println(s.getId());
//        }
        Shape myShape = canvas.addShape(name);
        Class<? extends Shape> type = myShape.getClass();

        int id;

        if (type == Circle.class)
            id = setCircle(myShape, shape);
        else if (type == Line.class)
            id= setLine(myShape, shape);
        else if (type == Rectangle.class)
            id= setRectangle(myShape, shape);
        else if (type == Square.class)
            id= setSquare(myShape, shape);
        else if (type == Ellipse.class)
            id= setEllipse(myShape, shape);
        else if (type == Triangle.class)
            id= setTriangle(myShape, shape);
        else throw new RuntimeException("Invalid Shape Type");

        canvas.setShapeAfterAddAndEdit(myShape);
        return id;
    }

    protected void deleteShape(int id) {
        canvas.removeShape(id);
    }

    protected Action undo() {
        try {
            return canvas.undo();
        } catch (Exception e) {
            return null;
        }
    }

    protected Action redo() {
        try {
            return canvas.redo();
        } catch (Exception e) {
            return null;
        }
    }

    protected void editShape(Map<String, Object> shapeMap) {
        int id = (Integer) shapeMap.get("id");
        //System.out.println("Edit req");
//            for (Shape s: App.getInstance().getCanvas().getShapes()) {
//                System.out.println(s.getId());
//        }
        Shape shape = App.getInstance().getCanvas().getShapeForEditing(id);
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

    protected List<Shape> load(String file, String type) {
        try {
            List<Shape> list = App.getInstance().load(file, type).getShapes();
            canvas = App.getInstance().getCanvas();
            //System.out.println("Before sending req");
//            for (Shape s: canvas.getShapes()) {
//                System.out.println(s.getId());
//            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException("File Error");
        }
    }

    protected void setCanvasName(String name) {
        canvas.setName(name);
    }

    protected String getCanvasName() {
        return canvas.getName();
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
        int offSetX = (Integer) shape.get("x");
        int offSetY = (Integer) shape.get("y");
        @SuppressWarnings("unchecked")
        Map<String, Integer> p1 = (Map<String, Integer>) shape.get("p1");
        @SuppressWarnings("unchecked")
        Map<String, Integer> p2 = (Map<String, Integer>) shape.get("p2");
        line.setStart(p1.get("x") + offSetX, p1.get("y") + offSetY);
        line.setEnd(p2.get("x") + offSetX, p2.get("y") + offSetY);
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
        square.setLength((Integer) shape.get("length"));
        square.setColor((String) shape.get("fill"));
        return square.getId();
    }

    private int setEllipse(Shape myShape, Map<String, Object> shape) {
        Ellipse ellipse = (Ellipse) myShape;
        ellipse.setRadiusX((Integer) shape.get("radius_x"));
        ellipse.setRadiusY((Integer) shape.get("radius_y"));
        ellipse.setLocation((Integer) shape.get("x"), (Integer) shape.get("y"));
        ellipse.setColor((String) shape.get("fill"));
        return ellipse.getId();
    }

    private int setTriangle(Shape myShape, Map<String, Object> shape) {
        Triangle triangle = (Triangle) myShape;
        int offSetX = (Integer) shape.get("x");
        int offSetY = (Integer) shape.get("y");
        @SuppressWarnings("unchecked")
        Map<String, Integer> p1 = (Map<String, Integer>) shape.get("p1");
        @SuppressWarnings("unchecked")
        Map<String, Integer> p2 = (Map<String, Integer>) shape.get("p2");
        @SuppressWarnings("unchecked")
        Map<String, Integer> p3 = (Map<String, Integer>) shape.get("p3");
        Point[] points = new Point[]{new Point(p1.get("x") + offSetX, p1.get("y") + offSetY),
                new Point(p2.get("x") + offSetX, p2.get("y") + offSetY),
                new Point(p3.get("x") + offSetX, p3.get("y") + offSetY)};
        triangle.setLocation(points);
        triangle.setColor((String) shape.get("fill"));

        return triangle.getId();
    }
}
