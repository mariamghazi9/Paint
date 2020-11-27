package quadcore.paintproject.paint.model.app;

import java.util.HashMap;
import java.util.Stack;

public class Canvas {
    private String name;
    private final HashMap<Integer, Shape> shapes;
    private final ShapeFactory closedShapeFactory;
    private final ShapeFactory lineFactory;
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

    public void addShape(String type) {
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
    }

    private void addShape(Shape shape) {
        shapes.put(shape.getId(), shape);
    }

    public void removeShape(int id) {
        Shape tempShape = shapes.remove(id);
        undo.push(new Action(Action.Type.DELETE, tempShape));
    }

    //TODO: edit shape

    public Shape undo() { //TODO return Action
        Action tempAction = undo.pop();
        Shape tempShape = tempAction.getShape();

        if (tempAction.getType() == Action.Type.ADD) {
            removeShape(tempShape.getId());
            redo.push(new Action(tempAction.reverseType(), tempShape));
            return null;

        } else if (tempAction.getType() == Action.Type.DELETE) {
            addShape(tempShape);
            redo.push(new Action(tempAction.reverseType(), tempShape));
            return tempShape.copyWithSameID();

        } else if (tempAction.getType() == Action.Type.EDIT) {
            //TODO: edit shape
            return null;

        } else throw new RuntimeException("Invalid Action Type");
    }

    //TODO: redo
}
