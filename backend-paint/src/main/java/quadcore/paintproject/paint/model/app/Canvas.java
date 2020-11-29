package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;
import java.util.*;

public class Canvas {

    private String name;
    private int idCount = 0;

    private ShapeFactory closedShapeFactory;
    private ShapeFactory lineFactory;

    private final HashMap<Integer, Shape> shapes;
    private final Stack<Action> undo;
    private final Stack<Action> redo;


    public Canvas() {
        this.name = "untitled";
        this.shapes = new HashMap<>();
        FactoryProducer factoryProducer = new FactoryProducer();
        this.closedShapeFactory = factoryProducer.getFactory("closedShape");
        this.lineFactory = factoryProducer.getFactory("line");
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCount() {
        return idCount;
    }

    public LinkedList<Shape> getShapes() {
        LinkedList<Shape> list = new LinkedList<>();
        for (Map.Entry<Integer, Shape> entry: shapes.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public void setShapes(LinkedList<Shape> list) {
        for (Shape shape : list) {
            shapes.put(shape.getId(), shape);
        }
    }
    @JsonIgnore
    public Stack<Action> getUndo() {
        return undo;
    }

    @JsonIgnore
    public Stack<Action> getRedo() {
        return redo;
    }

    protected int createID() {
        return ++idCount;
    }

    public Shape addShape(String type) {
        if (lineFactory == null) lineFactory = new LineFactory();
        if (closedShapeFactory == null) closedShapeFactory = new LineFactory();

        String[] detailedType = type.split("-");
        Shape tempShape;

        if (detailedType[0].equalsIgnoreCase("line")) {
            tempShape = lineFactory.getShape(detailedType[1]);

        } else if (detailedType[0].equalsIgnoreCase("closedShape") ||
                detailedType[0].equalsIgnoreCase("shape")) {
            tempShape = closedShapeFactory.getShape(detailedType[1]);

        } else {
            throw new RuntimeException("Invalid Shape Type");
        }

        shapes.put(tempShape.getId(), tempShape);
        undo.push(new Action(Action.Type.ADD, tempShape));

        return tempShape;
    }

    private void addShape(Shape shape) {
        shapes.put(shape.getId(), shape);
    }

    public void removeShape(int id) {
        Shape tempShape = shapes.remove(id);
        undo.push(new Action(Action.Type.DELETE, tempShape));
    }

    public Shape getShapeForEditing(int id) {
        Shape tempShape = shapes.get(id);
        undo.push(new Action(Action.Type.EDIT, tempShape));
        return tempShape;
    }

    private Action myUndo(Stack<Action> from, Stack<Action> to) {
        Action tempAction = from.pop();
        Shape tempShape = tempAction.getShape();

        if (tempAction.getType() == Action.Type.ADD) {
            removeShape(tempShape.getId());
            to.push(new Action(tempAction.reverseType(), tempShape));
            return to.peek();

        } else if (tempAction.getType() == Action.Type.DELETE) {
            addShape(tempShape);
            to.push(new Action(tempAction.reverseType(), tempShape));
            return to.peek();

        } else if (tempAction.getType() == Action.Type.EDIT) {
            Shape soonToBeOld = shapes.remove(tempShape.getId());
            shapes.put(tempShape.getId(), tempShape);
            to.push(new Action(tempAction.getType(), soonToBeOld));
            return new Action(tempAction.getType(), tempShape);

        } else throw new RuntimeException("Invalid Action Type");
    }

    public Action undo() {
        return myUndo(undo, redo);
    }

    public Action redo() {
        return myUndo(redo, undo);
    }

    public Shape copyShape(int id) {
        Shape shape = shapes.get(id);
        shape = shape.copy();
        Point point = shape.getPoint();
        shape.setPoint(point.x + 5, point.y + 5);
        return shape;
    }
}
