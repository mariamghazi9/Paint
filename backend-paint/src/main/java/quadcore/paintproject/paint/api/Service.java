package quadcore.paintproject.paint.api;

import quadcore.paintproject.paint.model.app.*;

import java.util.Map;

public class Service {

    Canvas canvas = App.getInstance().getCanvas();

    protected void add(Map<String, Object> shape) {
        String name = (String) shape.get("type");
        Shape myShape = canvas.addShape(name);
        Class<? extends Shape> type = myShape.getClass();

        if (type == Circle.class) {
            Circle circle = (Circle) myShape;
            circle.setLocation((Integer) shape.get("x"), (Integer) shape.get("y"));
            circle.setRadius((Integer) shape.get("r"));
            circle.setColor((String) shape.get("fill"));
        }
    }

    protected void delete(int id) {
        canvas.removeShape(id);
    }

    protected Action undo() {
        return canvas.undo();
    }

    protected Action redo() {
        return canvas.redo();
    }
}
