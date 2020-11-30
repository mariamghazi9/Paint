package quadcore.paintproject.paint.api;

import quadcore.paintproject.paint.model.app.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.awt.Point;

public class Service {

     Canvas canvas = App.getInstance().getCanvas();

    protected int add(Map<String, Object> shape) {
        String name = (String) shape.get("type");
        Shape myShape = canvas.addShape(name);
        Class<? extends Shape> type = myShape.getClass();
 
        if (type == Circle.class) 
            return setCircle(myShape, shape);
        if (type==Line.class)
            return setLine(myShape, shape);
        if(type==Rectangle.class)
            return setRectangle(myShape, shape);
        if(type==Square.class)
            return setSquare(myShape, shape);
        if(type==Ellipse.class)
           return setEllipse(myShape, shape);
        if(type==Triangle.class)
           return setTriangle(myShape, shape);
        return 0;
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

    protected int  editShape(Map<String, Object> shape) {
 
 
        int id=(Integer) shape.get("id");
  
        Shape tempShape = canvas.getShapeForEditing(id);
        Class<? extends Shape> type =tempShape.getClass();
        Shape editedShape = tempShape;
        if (type == Circle.class)
            return setCircle(editedShape, shape);
        if (type == Line.class)
            return setLine(editedShape, shape);
        if (type == Rectangle.class)
            return setRectangle(editedShape, shape);
        if (type == Square.class)
            return setSquare(editedShape, shape);
        if (type == Ellipse.class)
            return setEllipse(editedShape, shape);
        if (type == Triangle.class)
            return setTriangle(editedShape, shape);
         return 0;
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


    private int setCircle(Shape myShape,Map<String,Object>shape)
    { 
        Circle circle = (Circle) myShape;
        circle.setLocation((Integer) shape.get("x"), (Integer) shape.get("y"));
        circle.setRadius((Integer) shape.get("r"));
        circle.setColor((String) shape.get("fill"));
        return circle.getId();
    }
 
    private int setLine(Shape myShape,Map<String,Object>shape)
    {   
        Line line=(Line) myShape;
        Map<String,Integer> p1= (Map<String, Integer>) shape.get("p1");
        Map<String,Integer> p2= (Map<String, Integer>) shape.get("p2");
        line.setStart(p1.get("x"),p1.get("y"));
        line.setEnd(p2.get("x"),p2.get("y"));
        line.setColor((String)shape.get("fill"));
        return line.getId();
    }
 
    private int setRectangle(Shape myShape,Map<String,Object>shape)
    {
        Rectangle rectangle=(Rectangle) myShape;
        rectangle.setLocation((Integer)shape.get("x"), (Integer)shape.get("y"), (Integer)shape.get("w"), (Integer)shape.get("h"));
        rectangle.setColor((String)shape.get("fill"));
        return rectangle.getId();
    }
 
    private int setSquare(Shape myShape,Map<String,Object>shape)
    {
        Square square=(Square) myShape;
        square.setLocation((Integer)shape.get("x"), (Integer)shape.get("y"), (Float)shape.get("length"));
        square.setLength((Float)shape.get("length"));
        square.setColor((String)shape.get("fill"));
        square.setTopRight((Integer)shape.get("x"),(Integer)shape.get("y"));
        return square.getId();
    }
 
    private int setEllipse(Shape myShape,Map<String,Object>shape)
    { 
        Ellipse ellipse= (Ellipse) myShape;
        ellipse.setRadiusX((Float)shape.get("radius_x"));
        ellipse.setRadiusY((Float)shape.get("radius_y"));
        ellipse.setLocation((Integer)shape.get("x"),(Integer)shape.get("y"),ellipse.getRadiusX(),ellipse.getRadiusY());
        ellipse.setColor((String)shape.get("fill"));
        return ellipse.getId();
    }
 
 
    private int setTriangle(Shape myShape,Map<String,Object>shape)
    {   
        Triangle triangle=(Triangle) myShape;
        Map<String,Integer> p1= (Map<String, Integer>) shape.get("p1");
        Map<String,Integer> p2= (Map<String, Integer>) shape.get("p2");
        Map<String,Integer> p3= (Map<String, Integer>) shape.get("p3");
        Point[] points=new Point[]{new Point((Integer)p1.get("x"),(Integer)p1.get("y")),
                                   new Point((Integer)p2.get("x"),(Integer)p2.get("y")),
                                   new Point((Integer)p3.get("x"),(Integer)p3.get("y"))};
        triangle.setLocation(points);
        triangle.setColor((String)shape.get("fill"));
 
        return triangle.getId();
    }
}
