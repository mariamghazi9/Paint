package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.awt.*;
import java.util.*;

public class Canvas {

    private String name;
    private int idCount = 0;

    private ShapeFactory factory;

    private final HashMap<Integer, Shape> shapes;
    private final Stack<Action> undo;
    private final Stack<Action> redo;


    public Canvas() {
        this.name = "Untitled";
        this.shapes = new HashMap<>();
        this.factory = new ShapeFactory();
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Shape> getShapes() {
        LinkedList<Shape> list = new LinkedList<>();
        for (Map.Entry<Integer, Shape> entry: shapes.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public Shape addShape(String type) {
        if (factory == null) factory = new ShapeFactory();

        Shape tempShape = factory.getShape(type);

        shapes.put(tempShape.getId(), tempShape);

        return tempShape;
    }

    /**
     * used after using addShape() and editing the shape in order to copy edited shape to undo stack
     * @param shape after addition and editing
     */
    public void setShapeAfterAddAndEdit(Shape shape) {
        undo.push(new Action(Action.Type.ADD, shape));
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

    protected int createID() {
        return ++idCount;
    }

    private Action myUndo(Stack<Action> from, Stack<Action> to) {
        if (from.size() == 0) return null;
        Action tempAction = from.pop();
        Shape tempShape = tempAction.getShape();

        if (tempAction.getType() == Action.Type.ADD) {
            shapes.remove(tempShape.getId());
            tempAction.setType(tempAction.reverseType());
            to.push(tempAction);
            return to.peek();

        } else if (tempAction.getType() == Action.Type.DELETE) {
            addShape(tempShape);
            tempAction.setType(tempAction.reverseType());
            to.push(tempAction);
            return to.peek();

        } else if (tempAction.getType() == Action.Type.EDIT) {
            Shape soonToBeOld = shapes.remove(tempShape.getId());
            shapes.put(tempShape.getId(), tempShape);
            to.push(new Action(tempAction.getType(), soonToBeOld));
            return tempAction;

        } else throw new RuntimeException("Invalid Action Type");
    }

    private void addShape(Shape shape) {
        shapes.put(shape.getId(), shape);
    }

    @JsonGetter("idCount")
    private int getIdCount() {
        return idCount;
    }

    @JsonSetter("shapes")
    private void setShapes(LinkedList<Shape> list) {
        for (Shape shape : list) {
            shapes.put(shape.getId(), shape);
        }
    }
}
