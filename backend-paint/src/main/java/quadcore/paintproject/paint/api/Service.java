package quadcore.paintproject.paint.api;

import quadcore.paintproject.paint.model.app.*;

import java.util.Map;

public class Service {

    Canvas canvas = App.getInstance().getCanvas();

    protected Shape add(Map<String, Object> shape) {
        int id = (Integer)shape.get("id");
        Class<? extends Shape> type = canvas.getShape(id).getClass();

        if (type == Circle.class) {
            Circle circle = (Circle) canvas.addShape("circle");
            circle.setLocation((Integer) shape.get("x"), (Integer) shape.get("y"));
            circle.setRadius((Integer) shape.get("r"));
            circle.setColor((String) shape.get("fill"));
            return circle;
        }
        return null;
    }

    protected void delete(Map<String, Object> shape) {
        int id = (Integer)shape.get("id");
        canvas.removeShape(id);
    }

    protected Action undo() {
        return canvas.undo();
    }

    protected Action redo() {
        return canvas.redo();
    }
}
