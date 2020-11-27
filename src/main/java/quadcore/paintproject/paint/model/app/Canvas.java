package quadcore.paintproject.paint.model.app;

import java.awt.*;
import java.util.HashMap;
import java.util.Stack;

public class Canvas {

    private String name;
    private int idCount = 0;

    private final ShapeFactory closedShapeFactory;
    private final ShapeFactory lineFactory;
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

    protected int createID() {
        return ++idCount;
    }

    public Shape addShape(String type) {
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

    public Action undo() {
        Action tempAction = undo.pop();
        Shape tempShape = tempAction.getShape();

        if (tempAction.getType() == Action.Type.ADD) {
            removeShape(tempShape.getId());
            redo.push(new Action(tempAction.reverseType(), tempShape));
            return redo.peek();

        } else if (tempAction.getType() == Action.Type.DELETE) {
            addShape(tempShape);
            redo.push(new Action(tempAction.reverseType(), tempShape));
            return redo.peek();

        } else if (tempAction.getType() == Action.Type.EDIT) {
            Shape soonToBeOld = shapes.remove(tempShape.getId());
            shapes.put(tempShape.getId(), tempShape);
            redo.push(new Action(tempAction.getType(), soonToBeOld));
            return new Action(tempAction.getType(), tempShape);

        } else throw new RuntimeException("Invalid Action Type");
    }

    //TODO: redo
    public Shape copyShape(int id) {
        Shape shape = shapes.get(id);
        shape = shape.copy();
        Point point = shape.getPoint();
        shape.setPoint(point.x + 1, point.y + 1);
        return shape;
    }
    //TODO: handle shapes returning objects that can be changed (ex. Points)
}
